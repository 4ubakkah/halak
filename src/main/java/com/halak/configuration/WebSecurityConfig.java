package com.halak.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Based on assumption that API is public and doesn't require auth at the moment

                .antMatchers("/swagger-resources/**").permitAll().anyRequest().fullyAuthenticated()
                .antMatchers("/", "/api/**").permitAll()
                .anyRequest().permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
               // .permitAll();

        //TODO [REMOVE] Temporary solution to be able to access h2-console for debug reasons
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new UserDetailsServiceImpl(userRepository));
//    }

}
