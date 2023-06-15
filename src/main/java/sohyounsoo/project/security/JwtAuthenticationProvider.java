package sohyounsoo.project.security;

import sohyounsoo.project.errors.NotFoundException;
import sohyounsoo.project.users.Role;
import sohyounsoo.project.users.User;
import sohyounsoo.project.users.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.apache.commons.lang3.ClassUtils.isAssignable;

public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final UserService userService;

  public JwtAuthenticationProvider(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
    return processUserAuthentication(
      String.valueOf(authenticationToken.getPrincipal()),
      authenticationToken.getCredentials()
    );
  }

  private Authentication processUserAuthentication(String account_id, String password) {
    try {
      User user = userService.login(account_id, password);
      JwtAuthenticationToken authenticated =
        new JwtAuthenticationToken(
          new JwtAuthentication(user.getId(), user.getNickname()),
          null,
          AuthorityUtils.createAuthorityList(Role.USER.value())
        );
      authenticated.setDetails(user);
      return authenticated;
    } catch (NotFoundException e) {
      throw new UsernameNotFoundException(e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new BadCredentialsException(e.getMessage());
    } catch (DataAccessException e) {
      throw new AuthenticationServiceException(e.getMessage(), e);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return isAssignable(JwtAuthenticationToken.class, authentication);
  }

}