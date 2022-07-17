package ua.hillel.bookstore.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationContext context;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(context.getBean(DataSource.class))
                .usersByUsernameQuery("select email,password,enabled "
                        + "from users "
                        + "where email = ?")
                .authoritiesByUsernameQuery("select email,authority "
                        + "from authorities "
                        + "where email = ?")
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/", "/book/**", "/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/admin/**", "/orders", "/form/**").hasAnyRole("ADMIN")
                .antMatchers("/cart", "/wishlist", "/my-orders", "/ordering," +
                        "/order/create").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .successHandler(new RefererAuthenticationSuccessHandler())
                .loginPage("/login").permitAll()
                .and().logout().permitAll();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandlerImpl();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    }
}
