package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserDao;

public class DuplIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		System.out.println("id : "+id );
		//String id = request.getParameter("id");
		
		UserDao dao = UserDao.getInstance();
		dao.checkIdExist(id);
		
		boolean isIdExist = dao.checkIdExist(id);
		
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(isIdExist);
		
		
	}
}
