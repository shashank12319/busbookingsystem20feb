package com.wittybrains.busbookingsystem.config;




//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wittybrains.busbookingsystem.service.TokenAuthenticationService;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
//
//
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    public static final ObjectMapper MAPPER = new ObjectMapper();
//    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
//
//    
//    private final AuthenticationManager authenticationManager;
//    
//
//    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
//		super();
//		this.authenticationManager = authenticationManager;
//	}
//
//	@SuppressWarnings("unchecked")
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//        throws AuthenticationException {
//        try {
//
//            Map<String, String> creds = MAPPER.readValue(request.getInputStream(), Map.class);
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(creds.get("username"),
//                creds.get("password"), new ArrayList<>());
//            return authenticationManager.authenticate(authentication);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication auth) {
//        UserDetails principal = (UserDetails) auth.getPrincipal();
//        
//        TokenAuthenticationService.addAuthentication(response, principal); 
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                              AuthenticationException failed) throws IOException {
//        log.error("Authentication failed : {}", failed.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
//    }
//}