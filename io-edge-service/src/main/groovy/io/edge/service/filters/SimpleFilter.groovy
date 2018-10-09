package io.edge.service.filters

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

class SimpleFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(SimpleFilter.class)

	@Override
	String filterType() {
		return "pre"
	}

	@Override
	int filterOrder() {
		return 1
	}

	@Override
	boolean shouldFilter() {
		return true
	}

	@Override
	Object run() {
		RequestContext ctx = RequestContext.getCurrentContext()
		HttpServletRequest request = ctx.getRequest()
		log.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()))
		return null
	}
}
