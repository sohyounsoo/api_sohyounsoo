package sohyounsoo.callbus.users;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class UserDto {

  private String nickname;

  private String account_id;

  private int loginCount;

  private LocalDateTime lastLoginAt;

  private LocalDateTime createAt;

  public UserDto(User source) {
    copyProperties(source, this);

    this.lastLoginAt = source.getLastLoginAt().orElse(null);
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAccountId() {
    return account_id;
  }

  public void setAccountId(String account_id) {
    this.account_id = account_id;
  }

  public int getLoginCount() {
    return loginCount;
  }

  public void setLoginCount(int loginCount) {
    this.loginCount = loginCount;
  }

  public LocalDateTime getLastLoginAt() {
    return lastLoginAt;
  }

  public void setLastLoginAt(LocalDateTime lastLoginAt) {
    this.lastLoginAt = lastLoginAt;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("nickname", nickname)
      .append("account_id", account_id)
      .append("loginCount", loginCount)
      .append("lastLoginAt", lastLoginAt)
      .append("createAt", createAt)
      .toString();
  }

}