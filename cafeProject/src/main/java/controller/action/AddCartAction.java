package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.Cart;
import cart.CartDto;
import cart.controller.CartDao;
import user.User;

public class AddCartAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User log = (User) request.getSession().getAttribute("log");
		
		String userId = log.getUserId();
		int cafeCode = Integer.parseInt(request.getParameter("cafeCode"));
		int menuCode = Integer.parseInt(request.getParameter("menuCode"));
		int menuCount = Integer.parseInt(request.getParameter("menuCount"));
		
		CartDao cartDao = CartDao.getInstance();
		ArrayList<Cart> cartList = cartDao.getCartByUserId(userId);
		
		boolean checkCart = true;
		for(int i=0; i<cartList.size(); i++) {
			if(cafeCode == cartList.get(i).getCafeCode() && menuCode == cartList.get(i).getMenuCode()) {
				checkCart = false;
				menuCount += cartList.get(i).getMenuCount();
			}
		}
		
		CartDto cartDto = new CartDto(userId, cafeCode, menuCode, menuCount);
		
		if(!checkCart) {
			cartDao.updateCartMenuCount(cartDto);
			System.out.println("update");
		}
		else {
			cartDao.createCart(cartDto);
			System.out.println("create");
		}
	}
}
