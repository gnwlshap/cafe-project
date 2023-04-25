package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserDao;

public class LeaveAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		UserDao dao = UserDao.getInstance();
		dao.deleteUserById(userId);
		
		request.getSession().removeAttribute("log");
		response.sendRedirect("/");
		
	}

}
