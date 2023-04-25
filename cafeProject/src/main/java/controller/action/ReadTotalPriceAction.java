package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cartDetail.controller.CartDetailDao;
import user.User;

public class ReadTotalPriceAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User log = (User) request.getSession().getAttribute("log");
	    
		String userId = log.getUserId();
		
		CartDetailDao cartDetailDao = CartDetailDao.getInstance();
		JSONObject obj = new JSONObject();
		
		obj.put("totalPrice", cartDetailDao.getCartTotalByUserId(userId));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json charset=utf8");
		response.setHeader("Content-Type", "application/json charset=utf8");
		PrintWriter out = response.getWriter();
	  
		out.print(obj);
		out.flush();
	}
}
