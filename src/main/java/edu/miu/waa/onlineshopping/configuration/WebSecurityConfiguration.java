package edu.miu.waa.onlineshopping.configuration;

import edu.miu.waa.onlineshopping.service.OnlineShoppingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OnlineShoppingUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginPage = "/login";
        String logoutPage = "/logout";

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(loginPage).permitAll()
                .antMatchers("/not-approved-yet").permitAll()
                .antMatchers("/unAuth").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/download/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/seller/**").hasAuthority("SELLER")
                .antMatchers("/buyer/**").hasAuthority("BUYER")
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage(loginPage)
                .failureHandler(customAuthenticationFailureHandler())
                .defaultSuccessUrl("/")
                .usernameParameter("user_name")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                .logoutSuccessUrl(loginPage)
                .and().exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
