package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;
import user.controller.UserDao;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");

		UserDao userDao = UserDao.getInstance();
		User user = userDao.getUserById(userId);
		
		HttpSession session = request.getSession();

		boolean success = false;
		
		if (user != null && userPassword.equals(user.getUserPassword())) {
			session.setAttribute("log", user);
			success = true;
		} 
		
        // 응답 반환
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json charset=utf8");
		response.setHeader("Content-Type", "application/json charset=utf8");
        response.getWriter().write("{\"success\": " + success + "}");
	}
}