package felipelosano.minecraftseedsdb.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  SecurityFilter securityFilter;

  public SecurityConfig(SecurityFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable) // Desabilitar CSRF apenas para facilitar testes (habilite em produção)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()

                    .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN")

                    .requestMatchers(HttpMethod.GET, "/seeds").permitAll()
                    .requestMatchers(HttpMethod.POST, "/seeds").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/seeds").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/seeds").hasRole("ADMIN")

                    .requestMatchers(HttpMethod.GET, "/images").permitAll()
                    .requestMatchers(HttpMethod.POST, "/images").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/images").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/images").hasRole("ADMIN")
                    .anyRequest().authenticated()
            ).headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
