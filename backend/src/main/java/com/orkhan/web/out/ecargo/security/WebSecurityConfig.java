package com.orkhan.web.out.ecargo.security;


import com.orkhan.web.out.ecargo.security.jwt.JwtAuthEntryPoint;
import com.orkhan.web.out.ecargo.security.jwt.JwtAuthTokenFilter;
import com.orkhan.web.out.ecargo.security.services.UserDetailsServiceImpl;
import com.orkhan.web.out.ecargo.util.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    final String clientUrl = Client.clientUrl;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/signup","/signin","/options");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers("/auth/country/getAll","/addAdmin","/auth/verifyEmail/**","/signin","/signup","/auth/resetPassword","/auth/trucks/getAllTrucks","/auth/trucks/getAllTrucksByTypes","/auth/trucks/FilterTruck").permitAll()
                .antMatchers("/auth/**").authenticated()
                .and()
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}