package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orderdetail.OrderDetail;
import orderdetail.controller.OrderDetailDao;

public class OrderDetailListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		OrderDetailDao dao = OrderDetailDao.getInstance();
		ArrayList<OrderDetail> list = dao.getOrderDetailByOrderCode(orderCode);
		int totalPrice = dao.getTotalPriceByOrderCode(orderCode);

		if(list.size() == 0) {
			response.sendRedirect("myorder");
		}else {
			request.setAttribute("orderCode", orderCode);
			request.setAttribute("list", list);
			request.setAttribute("totalPrice", totalPrice);
			
			request.getRequestDispatcher("orderDetail").forward(request, response);			
		}
	}

}
