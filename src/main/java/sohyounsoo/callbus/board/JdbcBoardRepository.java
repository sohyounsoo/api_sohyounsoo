package sohyounsoo.callbus.board;

import sohyounsoo.callbus.users.Account_type;
import sohyounsoo.callbus.utils.DateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class JdbcBoardRepository implements BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> findAll(long id) {
        String sql = "SELECT";
        sql += "  tb.*";
        sql += ", ts.account_type";
        sql += ", ts.account_id";
        sql += ", (SELECT COUNT(*) FROM tb_like WHERE board_id = tb.id ) like_cnt";
        sql += ", (SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END AS cnt FROM tb_like WHERE users_id = ? AND board_id = tb.id ) like_yn";
        sql += " FROM tb_board tb INNER JOIN tb_users ts ON tb.users_id = ts.id";
        sql += " WHERE delete_yn = 'N'";
        sql += " ORDER BY tb.id DESC";

        return jdbcTemplate.query(
                sql,
                mapper,
                id
        );
    }

    @Override
    public Long createBoard(Board board) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement pstmt = connection.prepareStatement(
                                "INSERT INTO tb_board(users_id, content, create_at) VALUES (?,?, ?)",
                                new String[]{"id"}
                        );
                        pstmt.setLong(1, board.getUsers_id());
                        pstmt.setString(2, board.getContent());
                        pstmt.setTimestamp(3, DateTimeUtils.timestampOf(board.getCreate_at()));
                        return pstmt;
                    }
                }, generatedKeyHolder
        );

        long board_id = generatedKeyHolder.getKey().longValue();


        return board_id;
    }

    @Override
    public Optional<Board> findById(Long board_id, long user_id) {

        List<Board> results = jdbcTemplate.query(
                " SELECT *, (SELECT COUNT(*)  FROM tb_like WHERE board_id = tb.id) like_cnt FROM tb_board tb WHERE id=? AND users_id=? AND delete_yn='N' ",
                countMapper,
                board_id,
                user_id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public void updateById(Board board) {
        jdbcTemplate.update(
                "UPDATE tb_board SET content=?, update_at=? WHERE id=?",
                board.getContent(),
                DateTimeUtils.timestampOf(board.getUpdate_at()),
                board.getId()
        );
    }

    @Override
    public void deleteById(Board board) {
        jdbcTemplate.update(
                "UPDATE tb_board SET delete_yn='Y', delete_at=? WHERE id=?",
                DateTimeUtils.timestampOf(board.getDelete_at()),
                board.getId()
        );
    }

    @Override
    public int isLikeChk(Long boardId, long usersId) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM TB_LIKE where board_id = ? AND users_id = ?",
                Integer.class,
                boardId,
                usersId);
        return count;
    }

    @Override
    public void updateLike(Long boardId, Long userId, LocalDateTime now) {
        jdbcTemplate.update(
                "INSERT INTO tb_like(board_id, users_id, create_at) VALUES (?, ?, ?)",
                boardId,
                userId,
                now
        );
    }

    @Override
    public int isBoardChk(Long boardId) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM tb_board where id = ?",
                Integer.class,
                boardId);
        return count;
    }

    @Override
    public List<Like> findByLike(long boardId) {

        return jdbcTemplate.query(
                "SELECT board_id, (select account_id from tb_users where id = tl.users_id) account_id, tl.create_at FROM TB_LIKE tl where board_id = ?",
                likeMapper,
                boardId
        );
    }


    static RowMapper<Board> mapper = (rs, rowNum) ->
            new Board.Builder()
                    .id(rs.getLong("id"))
                    .accountId(rs.getString("account_id"))
                    .content(rs.getString("content"))
                    .create_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("create_at")))
                    .update_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("update_at")))
                    .delete_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("delete_at")))
                    .delete_yn(rs.getString("delete_yn"))
                    .account_type(Account_type.valueOf(rs.getString("account_type")).getName())
                    .like_cnt(rs.getLong("like_cnt"))
                    .like_yn(rs.getString("like_yn"))
                    .build();

    static RowMapper<Board> countMapper = (rs, rowNum) ->
            new Board.Builder()
                    .id(rs.getLong("id"))
                    .content(rs.getString("content"))
                    .create_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("create_at")))
                    .update_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("update_at")))
                    .delete_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("delete_at")))
                    .delete_yn(rs.getString("delete_yn"))
                    .like_cnt(rs.getLong("like_cnt"))
                    .build();

    static RowMapper<Like> likeMapper = (rs, rowNum) ->
            new Like.Builder()
                    .board_id(rs.getLong("board_id"))
                    .account_id(rs.getString("account_id"))
                    .create_at(DateTimeUtils.dateTimeOf(rs.getTimestamp("create_at")))
                    .build();

}
