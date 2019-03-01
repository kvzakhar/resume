package net.simplesoft.resume.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = -2936017476094817554L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("doGet profile 1");
		req.getRequestDispatcher("/WEB-INF/JSP/profile.jsp").forward(req, resp);
	}

}
