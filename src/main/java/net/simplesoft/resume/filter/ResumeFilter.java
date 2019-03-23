package net.simplesoft.resume.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResumeFilter extends AbstractFilter {

	@Value("${application.production}")
	private boolean production;
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String requestUrl = request.getRequestURI();
		request.setAttribute("REQUEST_URL", requestUrl);
		LOGGER.info(requestUrl + " " + System.currentTimeMillis());
		try {
			if(requestUrl.contains("favicon")) {
				return;
			}
			chain.doFilter(request, response);
		} catch (Throwable t) {
			LOGGER.error("Process request failed: " + requestUrl, t);
			handleException(t, requestUrl, response);
		}
		
	}
	
	private void handleException(Throwable th, String requestUrl, HttpServletResponse response) throws ServletException, IOException {
		if(production) {
			if (requestUrl.startsWith("/fragment") || "/error".equals(requestUrl)) {
				sendErrorStatus(response);
			} else {
				response.sendRedirect("/error?url="+requestUrl);
			}
		} else {
			throw new ServletException(th);
		}
	}
	
	private void sendErrorStatus(HttpServletResponse response) throws IOException{
		response.reset();
		response.getWriter().write("");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
}
