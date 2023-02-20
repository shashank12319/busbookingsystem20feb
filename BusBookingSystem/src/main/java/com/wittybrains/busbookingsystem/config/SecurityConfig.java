package com.wittybrains.busbookingsystem.config;



//
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//
//import com.wittybrains.busbookingsystem.service.UserServiceImpl;
//
//
//
//
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig  {
//	
//
//
//	
//	
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http,
//                                            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
//        http.cors().configurationSource(this::corsConfiguration).and().csrf().disable().exceptionHandling()
//            .authenticationEntryPoint((request, response, authException) -> {
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                response.setHeader("WWW-Authenticate", authException.getMessage());
//            }).accessDeniedHandler((request, response, accessDeniedException) -> {
//                response.setStatus(HttpStatus.FORBIDDEN.value());
//                response.setHeader("WWW-Authenticate", accessDeniedException.getMessage());
//            }).and()
//            .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
//                .antMatchers("/login", "/docs/**", "/swagger-ui/**").permitAll().anyRequest().authenticated())
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .addFilter(new CustomAuthenticationFilter(authenticationManager))
//            .addFilter(new CustomAuthorizationFilter(authenticationManager, userService));
//        return http.build();
//    }
//
//    private CorsConfiguration corsConfiguration(HttpServletRequest request) {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.applyPermitDefaultValues();
//        corsConfig.addAllowedOrigin(request.getHeader("Origin"));
//        return corsConfig;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//    	PasswordEncoder passwordencoder=new BCryptPasswordEncoder();
//    	System.out.println(passwordencoder.encode("1234"));
//        return new BCryptPasswordEncoder();
//    }
//	
//}
//
