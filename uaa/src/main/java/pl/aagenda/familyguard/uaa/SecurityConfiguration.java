package pl.aagenda.familyguard.uaa;

import com.auth0.jwt.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import pl.aagenda.familyguard.uaa.jwt.JwtAuthenticationProvider;
import pl.aagenda.familyguard.uaa.jwt.JwtAuthenticationSuccessHandler;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSuccessHandler successHandler;

    @Autowired
    private JWTVerifier verifier;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .authorities("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationProvider(verifier), AnonymousAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .failureHandler((request, response, exception) -> response.sendError(SC_UNAUTHORIZED))
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .csrf().disable();
    }
}
