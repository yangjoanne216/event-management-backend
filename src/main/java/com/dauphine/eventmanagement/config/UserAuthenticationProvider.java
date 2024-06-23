package com.dauphine.eventmanagement.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dauphine.eventmanagement.dto.UserDTO;
import com.dauphine.eventmanagement.mapper.UserDTOMapper;
import com.dauphine.eventmanagement.models.User;
import com.dauphine.eventmanagement.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
/**
 * 身份验证起
 * */
public class UserAuthenticationProvider {

  private final UserDTOMapper userDTOMapper;
  private final UserRepository userRepository;
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); //防止原始密码在JVM中可用
  }

  //create token
  public String createToken(UserDTO dto) {
    Date now = new Date();
    Date validity = new Date((now.getTime() + 7_200_00)); //2 hours
    return JWT.create()
        .withIssuer(dto.getEmail())
        .withIssuedAt(now)
        .withExpiresAt(validity)
        .withClaim("firstName", dto.getFirstname())
        .withClaim("lastname", dto.getLastname())
        .sign(Algorithm.HMAC256(secretKey));
  }

  //to validate it
  public Authentication validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    JWTVerifier verifier = JWT.require(algorithm).build();

    DecodedJWT decoded = verifier.verify(token);

    UserDTO user = UserDTO.builder()
        .email(decoded.getIssuer())
        .firstname(decoded.getClaim("firstName").asString())
        .lastname(decoded.getClaim("lastName").asString())
        .build();

    return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
  }

  public Authentication validateStrongerToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    JWTVerifier verifier = JWT.require(algorithm).build();

    DecodedJWT decoded = verifier.verify(token);
    //if the user still in database
    User user = userRepository.findByEmail(decoded.getIssuer())
        .orElseThrow(() -> new RuntimeException("User not found in DB"
            + decoded.getIssuer()));

    return new UsernamePasswordAuthenticationToken(userDTOMapper.apply(user), null,
        Collections.emptyList());
  }
}
