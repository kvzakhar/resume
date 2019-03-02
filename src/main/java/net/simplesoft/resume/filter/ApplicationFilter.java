package net.simplesoft.resume.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@WebFilter("/*")
@Component
public class ApplicationFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.debug("Before URL processing: {}", req.getRequestURI());
		chain.doFilter(req, response);
		LOGGER.debug("After URL processing: {}", req.getRequestURI());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
