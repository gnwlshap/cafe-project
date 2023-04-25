package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.Cart;
import cart.controller.CartDao;
import item.ItemDto;
import item.controller.ItemDao;
import order.OrderDto;
import order.controller.OrderDao;
import user.User;

public class OrderAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User log = (User) request.getSession().getAttribute("log");
		
		String userId = log.getUserId();
		
		int cafeCode = Integer.parseInt(request.getParameter("cafeCode"));
		// 오더 생성
		OrderDao orderDao = OrderDao.getInstance();
		OrderDto orderDto = new OrderDto(cafeCode, userId);
		orderDao.createOrder(orderDto);
		
		int orderCode = orderDao.getThisOrderCodeByUserId(userId);
		
		CartDao cartDao = CartDao.getInstance();
		ArrayList<Cart> cartList = cartDao.getCartByUserId(userId);
		// item 생성
		ItemDao itemDao = ItemDao.getInstance();
		for(Cart cart : cartList) {
			int menuCode = cart.getMenuCode();
			int menuCount = cart.getMenuCount();
			
			ItemDto itemDto = new ItemDto(orderCode, menuCode, menuCount);
			itemDao.createItem(itemDto);
			
			System.out.println("확인");
		}
		cartDao.deleteCart(userId);
		response.sendRedirect("myorder");
	}
}
