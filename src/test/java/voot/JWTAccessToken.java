package voot;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;

public class JWTAccessToken {

  public static String jwtAccessToken(String issuer) {
    return new PlainJWT(new JWTClaimsSet.Builder().issuer(issuer).build()).serialize();
  }


}
