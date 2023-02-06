package sohyounsoo.callbus.users;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

  @NotBlank(message = "accountId must be provided")
  private String accountId;

  @NotBlank(message = "password must be provided")
  private String password;

  protected LoginRequest() {/*empty*/}

  public LoginRequest(String accountId, String password) {
    this.accountId = accountId;
    this.password = password;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("accountId", accountId)
      .append("password", password)
      .toString();
  }

}