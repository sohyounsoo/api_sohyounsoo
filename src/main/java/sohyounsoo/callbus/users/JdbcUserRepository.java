package sohyounsoo.callbus.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sohyounsoo.callbus.utils.DateTimeUtils;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class JdbcUserRepository implements UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void update(User user) {
    jdbcTemplate.update(
      "UPDATE TB_USERS SET passwd=?,login_count=?,last_login_at=? WHERE id=?",
      user.getPassword(),
      user.getLoginCount(),
      user.getLastLoginAt().orElse(null),
      user.getId()
    );
  }

  @Override
  public Optional<User> findById(long id) {
    List<User> results = jdbcTemplate.query(
      "SELECT * FROM TB_USERS WHERE id=?",
      mapper,
      id
    );
    return ofNullable(results.isEmpty() ? null : results.get(0));
  }

  @Override
  public Optional<User> findByEmail(String account_id) {
    List<User> results = jdbcTemplate.query(
      "SELECT * FROM TB_USERS WHERE account_id=?",
      mapper,
      account_id
    );
    return ofNullable(results.isEmpty() ? null : results.get(0));
  }

  static RowMapper<User> mapper = (rs, rowNum) ->
    new User.Builder()
      .id(rs.getLong("id"))
      .nickname(rs.getString("nickname"))
      .account_id(rs.getString("account_id"))
      .password(rs.getString("passwd"))
      .loginCount(rs.getInt("login_count"))
      .lastLoginAt(DateTimeUtils.dateTimeOf(rs.getTimestamp("last_login_at")))
      .createAt(DateTimeUtils.dateTimeOf(rs.getTimestamp("create_at")))
      .AccountType(Account_type.valueOf(rs.getString("account_type")))
      .build();

}