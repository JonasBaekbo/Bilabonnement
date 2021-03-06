//Jonas
package com.example.bilabonnement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

//https://www.codejava.net/frameworks/spring-boot/form-authentication-with-jdbc-and-mysql
//https://www.codejava.net/frameworks/spring-boot/http-basic-authentication-with-in-memory-users
//https://www.codejava.net/frameworks/spring-boot/spring-boot-security-customize-login-and-logout
@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource).usersByUsernameQuery("select username, password, enabled from users where username=?").authoritiesByUsernameQuery("select username, role from users where username=?");
    }

    //login
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN", "DATA", "DAMAGE", "BUISNESS").antMatchers("/admin/opretlease").hasAnyRole("ADMIN", "DATA").antMatchers("/admin/lejedebiler").hasAnyRole("ADMIN", "BUISNESS").antMatchers("/admin/visekstraudstyr").hasAnyRole("ADMIN", "DATA").antMatchers("/admin/lukskade").hasAnyRole("ADMIN", "DAMAGE").antMatchers("/admin/opretskade").hasAnyRole("ADMIN", "DAMAGE").antMatchers("/admin/opretbil").hasAnyRole("ADMIN", "DATA").antMatchers("/admin/tilfoejnummerplade").hasAnyRole("ADMIN", "DATA").antMatchers("/admin/tilfoejekstraudstyr").hasAnyRole("ADMIN", "DATA").and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("pass").failureUrl("/login-error").successForwardUrl("/login_success").successHandler(new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                response.sendRedirect("/admin");
            }
        }).and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.sendRedirect("/");
            }
        });
    }
}
