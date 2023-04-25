package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orderhistory.OrderHistory;
import orderhistory.controller.OrderHistoryDao;

public class OrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");

		OrderHistoryDao dao = OrderHistoryDao.getInstance();
		ArrayList<OrderHistory> list = dao.getOrderHistoryByUserId(userId);

		request.setAttribute("list", list);

		request.getRequestDispatcher("myorder").forward(request, response);
	}

}
