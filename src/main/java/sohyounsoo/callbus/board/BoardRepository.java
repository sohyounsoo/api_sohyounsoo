package sohyounsoo.callbus.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    List<Board> findAll(long id);

    Long createBoard(Board board);

    Optional<Board> findById(Long board_id, long user_id);

    void updateById(Board board);

    void deleteById(Board board);

    int isLikeChk(Long boardId, long usersId);

    void updateLike(Long boardId, Long userId, LocalDateTime now);

    int isBoardChk(Long boardId);

    List<Like> findByLike(long boardId);

    List<Board> findAllNotUser();
}

