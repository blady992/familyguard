package pl.aagenda.familyguard.uaa.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC256("secret");
    }

    @Bean
    public JWTVerifier verifier() {
        return JWT.require(algorithm())
                .build();
    }
}
