package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.controller.CartDao;
import user.User;

public class ClearCartAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User log = (User) request.getSession().getAttribute("log");
		
		String userId = log.getUserId();
		
		CartDao cartDao = CartDao.getInstance();
		
		cartDao.deleteCart(userId);	
	}

}
