package sohyounsoo.callbus.users;

import sohyounsoo.callbus.security.Jwt;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class User {

  private final Long id;

  private final String nickname;

  private final String account_id;

  private String password;

  private int loginCount;

  private LocalDateTime lastLoginAt;

  private final LocalDateTime createAt;

  private String quit;

  private final Account_type account_type;

  public User(String nickname, String account_id, String password, Account_type accountType) {
    this(null, nickname, account_id, password, 0, null, null, accountType);
  }

  public User(Long id, String nickname, String account_id, String password, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt, Account_type accountType) {
    checkArgument(isNotEmpty(nickname), "nickname must be provided");
    checkArgument(
      nickname.length() >= 1 && nickname.length() <= 10,
      "nickname length must be between 1 and 10 characters"
    );
    checkNotNull(account_id, "account_id must be provided");
    checkNotNull(password, "password must be provided");

    this.id = id;
    this.nickname = nickname;
    this.account_id = account_id;
    this.password = password;
    this.loginCount = loginCount;
    this.lastLoginAt = lastLoginAt;
    this.createAt = defaultIfNull(createAt, now());
    this.account_type = accountType;
  }

  public String newJwt(Jwt jwt, String[] roles) {
    Jwt.Claims claims = Jwt.Claims.of(id, nickname, roles);
    return jwt.create(claims);
  }

  public void login(PasswordEncoder passwordEncoder, String credentials) {
    if (!passwordEncoder.matches(credentials, password)) {
      throw new IllegalArgumentException("Bad credential");
    }
  }

  public void afterLoginSuccess() {
    loginCount++;
    lastLoginAt = now();
  }

  public Long getId() {
    return id;
  }

  public String getNickname() {
    return nickname;
  }

  public String getAccount_id() {
    return account_id;
  }

  public String getPassword() {
    return password;
  }

  public int getLoginCount() {
    return loginCount;
  }

  public Optional<LocalDateTime> getLastLoginAt() {
    return ofNullable(lastLoginAt);
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public String getQuit() {
    return quit;
  }

  public Account_type getAccount_type() {
    return account_type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("id", id)
      .append("nickname", nickname)
      .append("account_id", account_id)
      .append("password", "[PROTECTED]")
      .append("loginCount", loginCount)
      .append("lastLoginAt", lastLoginAt)
      .append("createAt", createAt)
      .append("quit", quit)
      .toString();
  }

  static public class Builder {
    private Long id;
    private String nickname;
    private String account_id;
    private String password;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createAt;
    private Account_type account_type;

    private String quit;

    public Builder() {/*empty*/}

    public Builder(User user) {
      this.id = user.id;
      this.nickname = user.nickname;
      this.account_id = user.account_id;
      this.password = user.password;
      this.loginCount = user.loginCount;
      this.lastLoginAt = user.lastLoginAt;
      this.createAt = user.createAt;
      this.account_type = user.getAccount_type();
      this.quit = user.getQuit();
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder nickname(String nickname) {
      this.nickname = nickname;
      return this;
    }

    public Builder account_id(String account_id) {
      this.account_id = account_id;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder loginCount(int loginCount) {
      this.loginCount = loginCount;
      return this;
    }

    public Builder lastLoginAt(LocalDateTime lastLoginAt) {
      this.lastLoginAt = lastLoginAt;
      return this;
    }

    public Builder createAt(LocalDateTime createAt) {
      this.createAt = createAt;
      return this;
    }

    public Builder AccountType(Account_type accountType) {
      this.account_type = accountType;
      return this;
    }

    public User build() {
      return new User(id, nickname, account_id, password, loginCount, lastLoginAt, createAt, account_type);
    }
  }

}