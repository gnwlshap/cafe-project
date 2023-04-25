package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserDao;

public class DuplPhoneCheckAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		System.out.println("phone : "+ phone);
		
		UserDao dao = UserDao.getInstance();
		dao.checkPhoneExist(phone);
		
		boolean isPhoneExist = dao.checkPhoneExist(phone);
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(isPhoneExist);
		
		
	}

}
