package com.lokiy.learning.spring.security.config;

import cn.hutool.core.util.StrUtil;
import com.lokiy.learning.spring.security.config.ss.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;

/**
 * @author lokiy
 * @date 2022/9/1
 * @description TODO
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${security.exclude-urls: /auth/**}")
    private String excludeUrls;

    @Resource
    private UserDetailsService sysUserDetailsService;

    @Resource
    private AccessDeniedHandler jwtAccessDeniedHandler;

    @Resource
    private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private OncePerRequestFilter jwtCustomizeFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService).passwordEncoder(passwordEncoder());
    }

    public BasicAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
        return new JwtAuthenticationTokenFilter(authenticationManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()

//                .formLogin()
//                .successHandler(null)
//                .failureHandler(null)

//                .and()
//                .logout()
//                .logoutSuccessHandler(null)

//                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers( excludeUrls.split(StrUtil.COMMA))
                .anonymous()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)



                .and()
                .addFilter(jwtAuthenticationTokenFilter())
                .addFilterBefore( jwtCustomizeFilter, UsernamePasswordAuthenticationFilter.class)


        ;
    }


}
