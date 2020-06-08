package com.jsf2184.springsecurityjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource _dataSource;

    //    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set your configuration on the auth object
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcAuthentication = auth.jdbcAuthentication();
        jdbcAuthentication
                .dataSource(_dataSource)
//                .withDefaultSchema()
//                .withUser(User.withUsername("JohnUser").password("abc").roles("USER"))
//                .withUser(User.withUsername("JoeAdmin").password("def").roles("ADMIN"));
                .usersByUsernameQuery("select username, password, enabled from my_users where username =  ? ")
                .authoritiesByUsernameQuery("select username, authority from my_authorities where username =  ? ")
        ;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // no password encding -- clear text. Don't do this in a real application.
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests();
        urlRegistry
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/pla*").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .formLogin();


    }
}
