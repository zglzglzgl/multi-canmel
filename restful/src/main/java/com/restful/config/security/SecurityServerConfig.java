package com.restful.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * <p>
 * 资源服务器配置
 * </p>
 *
 * @author  *
 *   ┏ ┓   ┏ ┓
 *  ┏┛ ┻━━━┛ ┻┓
 *  ┃         ┃
 *  ┃    ━    ┃
 *  ┃  ┳┛  ┗┳ ┃
 *  ┃         ┃
 *  ┃    ┻    ┃
 *  ┃         ┃
 *  ┗━━┓    ┏━┛
 *     ┃    ┃神兽保佑
 *     ┃    ┃代码无BUG！
 *     ┃    ┗━━━━━━━┓
 *     ┃            ┣┓
 *     ┃            ┏┛
 *     ┗┓┓┏━━━━━━┳┓┏┛
 *      ┃┫┫      ┃┫┫
 *      ┗┻┛      ┗┻┛
 * @since 2018-08-12
 */

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login/**", "/configuration/**","/auth/**",
                "/index", "/", "/code/verify", "/css/**", "/javascript/**", "/images/**", "/plugins/**", "/error/**",
                "/fonts/**", "/webjars/**", "/resources/**", "/swagger-ui.html"
                , "/swagger-resources/**", "/v2/api-docs", "index.html", "/oauth/token"
                , "/oauth/authorize", "/swagger").permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests().anyRequest().authenticated();
        http.headers().frameOptions().disable();
        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""));
    }
}
