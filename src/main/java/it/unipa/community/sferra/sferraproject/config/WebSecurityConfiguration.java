package it.unipa.community.sferra.sferraproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/images/cardify.png").permitAll()
                        .requestMatchers("/images/generic.png").permitAll()
                        .requestMatchers("/images/internal-server-error.png").permitAll()
                        .requestMatchers("/scripts/check_credit.js").permitAll()
                        .requestMatchers("/errors/generic").permitAll()
                        .requestMatchers("/errors/internal-server-error").permitAll()
                        .requestMatchers("/cards/check-credit").permitAll()
                        .requestMatchers("/users/blocked").permitAll()
                        .requestMatchers("/users/login").permitAll()
                        .requestMatchers("/cards/merchant/**").hasAuthority("merchant")
                        .requestMatchers("/scripts/merchant/**").hasAuthority("merchant")
                        .requestMatchers("/users/admin/**").hasAuthority("admin")
                        .requestMatchers("/cards/admin/**").hasAuthority("admin")
                        .requestMatchers("/scripts/admin/**").hasAuthority("admin")
                        .requestMatchers("/transactions/**").hasAnyAuthority("merchant", "admin")
                        .requestMatchers("/add-ons/**").denyAll()
                        .anyRequest().authenticated())
                .exceptionHandling((exc) -> exc
                        .accessDeniedPage("/not-allowed"))
                .formLogin((form) -> form
                        .loginPage("/users/login")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                        .permitAll())
                .sessionManagement((sessionManagement) -> sessionManagement
                        .maximumSessions(-1)
                        .expiredUrl("/users/blocked")
                        .sessionRegistry(sessionRegistry()));

        return http.build();
    }

}