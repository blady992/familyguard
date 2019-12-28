package pl.aagenda.familyguard.uaa.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProvider extends OncePerRequestFilter {

    private final JWTVerifier verifier;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).filter(s -> s.startsWith("Bearer ")).map(s -> s.substring(7)).orElse(null);
        if (isNull(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            verifier.verify(jwt);
        } catch (JWTVerificationException ex) {
            log.warn("Failed to verify JWT token", ex);
            filterChain.doFilter(request, response);
            return;
        }

        DecodedJWT decodedJWT = JWT.decode(jwt);

        String username = decodedJWT.getClaim("username").asString();
        Collection<? extends GrantedAuthority> authorities = createAuthorityList(decodedJWT.getClaim("authorities").asArray(String.class));

        Authentication authentication = new UsernamePasswordAuthenticationToken(new User(username, "", authorities), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
