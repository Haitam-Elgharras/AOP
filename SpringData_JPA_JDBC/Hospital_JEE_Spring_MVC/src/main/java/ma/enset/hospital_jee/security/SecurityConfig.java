package ma.enset.hospital_jee.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ma.enset.hospital_jee.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // to enable method level security using @PreAuthorize.
@RequiredArgsConstructor
public class SecurityConfig {
    // The technique of separation of the pass enc is to allow spring to instantiate the security config without
    // the need of the password encoder, and then inject it later when it's needed.
//    @Lazy // to avoid circular dependency
    private PasswordEncoder passwordEncoder;

    private final UserDetailServiceImpl userDetailsService;


//    @Bean this for jdbc authentication
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


//    @Bean the bean will not be created now. this for in memory authentication
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user@gmail.com")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin@gmail.com")
                        .password(passwordEncoder.encode("1234"))
                        .roles("ADMIN", "USER")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.userDetailsService(userDetailsService);
        httpSecurity
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // This line permits all requests to webjars, so the login page can load the css and js files
                        .requestMatchers("/webjars/**","/public/**").permitAll()
                        .anyRequest().authenticated())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds(86400) // This sets the duration of the remember me cookie (1 day in this case)
                        .key("theSecretKey") // This sets the key used to identify tokens
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/notAuthorized"));

        return httpSecurity.build();
    }


}
