package voot.oauth;

import org.springframework.security.core.Authentication;

public interface SchacHomeAuthentication extends Authentication {

  String getSchacHomeAuthentication();

}
