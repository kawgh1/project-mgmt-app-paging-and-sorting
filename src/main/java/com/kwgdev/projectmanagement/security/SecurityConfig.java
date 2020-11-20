package com.kwgdev.projectmanagement.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig
//        extends WebSecurityConfigurerAdapter
        {

    // Security off for demo purposes - security isn't the point of this app


//    This inMemory Security obvs not recommended, just for example
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("test")
//                    .password("test")
//                    .roles("USER")
//            .and()
//                .withUser("taz")
//                    .password("pass2")
//                    .roles("USER")
//            .and()
//                    .withUser("managerUser")
//                    .password("pass3")
//                    .roles("ADMIN");
//
//    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                // only admins can create new projects, employees, managers
//                .antMatchers("/projects/new").hasRole("ADMIN")
//                .antMatchers("/employees/new").hasRole("ADMIN")
//                .antMatchers("/managers/new").hasRole("ADMIN")
//                // authenticated users can access everywhere else
//                .antMatchers("/").authenticated().and().formLogin();
//    }

}
