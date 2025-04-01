package com.coppel.sihe.config.filter;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.coppel.sihe.config.JWTUtil;
import com.coppel.sihe.service.EmpleadoDetailsServiceImpl;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private EmpleadoDetailsServiceImpl empServDetaImp;

	/*
	 * @Override protected void doFilterInternal(HttpServletRequest request,
	 * HttpServletResponse res, FilterChain filterChain) throws ServletException,
	 * IOException { String authorizationHeader =
	 * request.getHeader("Authorization"); if (authorizationHeader != null) { String
	 * username = jwtUtil.extractUsername(authorizationHeader);
	 * 
	 * if (username != null &&
	 * SecurityContextHolder.getContext().getAuthentication() == null) { UserDetails
	 * userDetails = empServDetaImp.loadUserByUsername(username);
	 * 
	 * if (jwtUtil.validateToken(authorizationHeader, userDetails)) {
	 * UsernamePasswordAuthenticationToken authToken = new
	 * UsernamePasswordAuthenticationToken(userDetails, null,
	 * userDetails.getAuthorities()); authToken.setDetails(new
	 * WebAuthenticationDetailsSource().buildDetails(request));
	 * SecurityContextHolder.getContext().setAuthentication(authToken); } } }
	 * 
	 * filterChain.doFilter(request, res); }
	 */

	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            String username = jwtUtil.extractUsername(authorizationHeader);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = empServDetaImp.loadUserByUsername(username);

                if (jwtUtil.validateToken(authorizationHeader, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
		
	}
    
    

