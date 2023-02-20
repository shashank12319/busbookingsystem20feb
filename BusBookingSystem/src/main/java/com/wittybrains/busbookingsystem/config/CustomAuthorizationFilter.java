package com.wittybrains.busbookingsystem.config;




//
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import com.wittybrains.busbookingsystem.service.TokenAuthenticationService;
//import com.wittybrains.busbookingsystem.service.UserServiceImpl;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class CustomAuthorizationFilter extends BasicAuthenticationFilter {
//
//  
//    public CustomAuthorizationFilter(AuthenticationManager authenticationManager,
//                                      UserServiceImpl userService) {
//        super(authenticationManager);
//    }
//
//    
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer")) {
//            chain.doFilter(request, response);
//            return;
//        }
//        UsernamePasswordAuthenticationToken authentication =TokenAuthenticationService.getAuthentication(request);
//        if (authentication != null) {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        chain.doFilter(request, response);
//    }
//
//
//   
//      
//
//}
