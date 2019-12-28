package pl.aagenda.familyguard.uaa.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.vavr.collection.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private Algorithm algorithm;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails user = (UserDetails) authentication.getPrincipal(); //TODO: this cast might be improper
        String jwt = JWT.create()
                .withClaim("username", user.getUsername())
                .withArrayClaim("authorities", extractAuthorities(authentication.getAuthorities()))
                .sign(algorithm);

        response.setContentType(TEXT_PLAIN_VALUE);
        response.setStatus(SC_OK);
        response.getWriter().print(jwt);
    }

    private String[] extractAuthorities(Collection<? extends GrantedAuthority>grantedAuthorities) {
        return Stream.ofAll(grantedAuthorities)
                .map(GrantedAuthority::getAuthority)
                .toJavaArray(String[]::new);
    }
}
