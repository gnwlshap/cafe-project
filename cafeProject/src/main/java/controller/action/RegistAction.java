package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDto;
import user.controller.UserDao;

public class RegistAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");

		//String address_postNum = request.getParameter("address_postNum");
		String address_roadNm = request.getParameter("address_roadNm");
		//String address_jibun = request.getParameter("address_jibun");
		String address_detail = request.getParameter("address_detail");
		//String address_extra = request.getParameter("address_extra");

		String userAddress = address_roadNm + " " + address_detail;

		UserDto userDto = new UserDto(userId, userPassword, userName, userAddress, userPhone);

		UserDao userDao = UserDao.getInstance();
		userDao.createUser(userDto);

		response.sendRedirect("/");
	}

}
