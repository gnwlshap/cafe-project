package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;
import user.controller.UserDao;

public class updateMypageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User log = (User) request.getSession().getAttribute("log");
		
		String id = log.getUserId();
		String password = request.getParameter("password");
		
		UserDao dao = UserDao.getInstance();
		
		dao.updatePasswordById(id, password);
		
		response.sendRedirect("mypage");
	}

}
