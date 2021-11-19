package com.config;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "springsecurityconfig")
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {

    private final String loginUrl = "/ace/login.html";
    private String indexUrl = "/ace/index.html";
    private String permitAll = "/api";
    private String deniedPage = "/deny";
    //邦定的用户组才能登入url
    private String urlRoles;

    @Autowired
    private UsersService usersService;

    //RESTful and CRSF have conflict
    //CRSF default support GET,head,trace,options,biy not support post
    //in security config, disable .and().csrf().disable()
    //refer to hankuikui/p/14024637.html
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //doc html
                .antMatchers("/doc.html").permitAll()
                //swagger2
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/api/**").permitAll()
                //allow access static
                .antMatchers("classpath:/static/").permitAll()
                //allow access templates
                //.antMatchers("classpath:/templates/").permitAll()
                //login
                .antMatchers(loginUrl).permitAll()
               // open spring security, login success can access
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(loginUrl)
                .permitAll()
                .and()
                .logout()
                .permitAll()

                //close spring security
                //.anyRequest().permitAll()
                //.and().logout().permitAll()
                //.tokenValiditySeconds(84600).tokenRepository("保存用户token信息到数据库")
                .and().rememberMe();

    }



   // 静态资源配置
    @Override
    public void configure(WebSecurity web) {
        //swagger2所需要用到的静态资源，允许访问
        //static文件, 允许访问
        web.ignoring()
                .antMatchers("/v2/api-docs"
                        , "/swagger-resources/configuration/ui"
                        , "/swagger-resources"
                        , "/swagger-resources/configuration/security"
                        , "/swagger-resources/**"
                        , "/swagger-ui.html"

                        , "/**/*.html"
                        , "/**/*.htm"
                        , "/**/*.js"
                        , "/**/*.png"
                        , "/**/*.jpg"
                        , "/favicon.ico"
                        , "/**/*.css"
                        , "/images/**"
                      //, "/font-awesome/4.5.0/fonts/**.*"
                        , "/**/*.woff2"
                        , "/**/*.woff"
                        , "/**/*.ttf"
                        , "/**/*.svg"
                        , "/**/*.eot"
                        , "/error"
                );
    }

    /**
     * 设置admin 用户
     * 自定义配置认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN", "USER")
                .and()
                .withUser("garlam")
                .password("{noop}garlamau")
                .roles("USER");
        auth.userDetailsService(usersService).passwordEncoder(encoder());

    }


    @Bean
    public PasswordEncoder encoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
        return passwordEncoder;
    }

}
