package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.controller.CartDao;
import menu.controller.MenuDao;
import user.User;

public class RemoveCartAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유저 아이디
		User log = (User) request.getSession().getAttribute("log");
		
		String userId = log.getUserId();
		// 메뉴코드
		MenuDao menuDao = MenuDao.getInstance();
		String menuName = request.getParameter("menuName");
		int menuCode = menuDao.getMenuCodeByMenuName(menuName);
		// 카페코드
		int cafeCode = Integer.parseInt(request.getParameter("cafeCode"));
		
		CartDao cartDao = CartDao.getInstance();
		
		cartDao.deleteCartByMenuCode(menuCode, userId, cafeCode);
	}
}
