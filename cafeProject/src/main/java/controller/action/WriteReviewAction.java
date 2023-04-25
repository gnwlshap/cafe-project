package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.controller.OrderDao;
import review.ReviewDto;
import review.controller.ReviewDao;

public class WriteReviewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("user_id");
		String title = request.getParameter("review_title");
		String content = request.getParameter("review_content");

		ReviewDto dto = new ReviewDto(id, title, content);

		ReviewDao dao = ReviewDao.getInstance();
		boolean isSuccess = dao.writeReview(dto);
		
		if (isSuccess) {
			int orderCode = Integer.parseInt(request.getParameter("order_code"));
			OrderDao orderDao = OrderDao.getInstance();
			orderDao.updateReviewWrittenByOrderCode(orderCode);
			
			response.sendRedirect("myorder");
		}
	}	

}
