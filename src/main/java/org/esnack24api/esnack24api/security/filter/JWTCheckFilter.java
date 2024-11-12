package org.esnack24api.esnack24api.security.filter;

import com.google.gson.Gson;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import org.esnack24api.esnack24api.security.auth.CustomUserPrincipal;
import org.esnack24api.esnack24api.security.util.JWTUtil;

import java.io.IOException;
import java.io.Writer;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;


@Log4j2
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class JWTCheckFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        log.info("--------------------------------------");
        log.info("shouldNotFilter!!!");

        String uri = request.getRequestURI();

        if(uri.equals("/api/test/member/makeToken")) return true;
        if(uri.equals("/api/test/member/refreshToken")) return true;
        if(uri.equals("/api/test/member/kakao")) return true;
        if(uri.equals("/api/test/member/google")) return true;
        if(uri.equals("/api/orders")) return true;
        if(uri.equals("/api/orders/{orderID}/capture")) return true;
        if(uri.equals("/api/v1/product/list")) return true;
        if(uri.equals("/api/v1/product")) return true;

        return false;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("++++++++++++++++++++++++++++++++++++++++++");
        log.info("doFilterInternal!!! 다음 단계로 넘어감");

        log.info(request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        String token = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);
        }else {

            makeError(response, Map.of("status", 401, "msg", "No Access Token"));

            return;
        }

        //JWT validate
        try{

            Map<String, Object> claims = jwtUtil.validateToken(token);
            log.info(claims);

            String email = (String) claims.get("email");
            String role = (String) claims.get("role");

            Principal userPrincipal = new CustomUserPrincipal(email);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userPrincipal, null,
                    List.of(new SimpleGrantedAuthority("ROLE_"+role)));

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        }catch(JwtException e){

            log.info(e.getClass().getName());
            log.info(e.getMessage());
            log.info("111111111111111111111111111");

            String classFullName = e.getClass().getName();
            String shortClassName = classFullName.substring(classFullName.lastIndexOf(".") + 1);

            makeError(response,
                    Map.of("status", 401, "msg", shortClassName));

            e.printStackTrace();
        }
    }


    private void makeError(HttpServletResponse response, Map<String, Object> map) {

        Gson gson = new Gson();

        String jsonStr = gson.toJson(map);

        response.setContentType("application/json");
        response.setStatus((int)map.get("status"));
        try{

            Writer writer = response.getWriter();

            out.println(jsonStr);
            out.close();
        }catch(IOException e) {

            throw new RuntimeException(e);
        }

    }
}