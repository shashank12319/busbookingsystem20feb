package com.wittybrains.busbookingsystem.service;


//
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Date;
//
//import static java.util.Collections.emptyList;
//
//public class TokenAuthenticationService {
//    static final long EXPIRATIONTIME = 864_000_000; // 10 days
//    static final String SECRET = "ThisIsASecret";
//    static final String TOKEN_PREFIX = "Bearer";
//    static final String HEADER_STRING = "Authorization";
//    
//  public  static void addAuthentication(HttpServletResponse res, UserDetails userDetails) {
//        String JWT = Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//                .claim("role", userDetails.getAuthorities())
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .compact();
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
//    }
//
//   public  static UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            String username = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
//                    .getBody()
//                    .getSubject();
//            return username != null ?
//                    new UsernamePasswordAuthenticationToken(username, null, emptyList()) :
//                    null;
//        }
//        return null;
//    }
//}
