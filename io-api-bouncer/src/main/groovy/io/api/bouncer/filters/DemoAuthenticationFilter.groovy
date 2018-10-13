package io.api.bouncer.filters


import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DemoAuthenticationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, java.io.IOException {
		String xAuth = request.getHeader("X-Authorization")
		filterChain.doFilter(request, response)
	}
}
