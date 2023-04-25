package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cafe.controller.CafeDao;
import cart.Cart;
import cart.controller.CartDao;
import menu.controller.MenuDao;
import user.User;

public class ReadCartAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		User log = (User) request.getSession().getAttribute("log");
	    
		String userId = log.getUserId();
		CartDao cartDao = CartDao.getInstance();
		CafeDao cafeDao = CafeDao.getInstance();
		MenuDao menuDao = MenuDao.getInstance();
		ArrayList<Cart> cartList = cartDao.getCartByUserId(userId);
		
		JSONArray data = new JSONArray();
		for(Cart cart : cartList) {
			JSONObject obj = new JSONObject();
			obj.put("cafeCode", cart.getCafeCode());
			obj.put("cafeName", cafeDao.getCafeByCode(cart.getCafeCode()).getCafeName());
			obj.put("menuName", menuDao.getMenuByMenuCode(cart.getMenuCode()));
			obj.put("menuPrice", menuDao.getMenuPriceByMenuCode(cart.getMenuCode()));
			obj.put("menuCount", cart.getMenuCount());
			data.put(obj);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json charset=utf8");
		response.setHeader("Content-Type", "application/json charset=utf8");
		PrintWriter out = response.getWriter();
	  
		out.print(data);
		out.flush();
	}
}
