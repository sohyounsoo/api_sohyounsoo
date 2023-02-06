package sohyounsoo.callbus.security;

import lombok.Getter;
import lombok.Setter;

import static com.google.common.base.Preconditions.checkNotNull;

@Setter
@Getter
public class JwtAuthentication {

  public final Long id;

  public final String name;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  JwtAuthentication(Long id, String name) {
    checkNotNull(id, "id must be provided");
    checkNotNull(name, "name must be provided");

    this.id = id;
    this.name = name;
  }

}