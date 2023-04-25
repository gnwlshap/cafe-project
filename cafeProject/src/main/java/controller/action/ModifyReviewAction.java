package controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.Review;
import review.controller.ReviewDao;

public class ModifyReviewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int reviewNumber = Integer.parseInt(request.getParameter("review_number"));
		System.out.println("reviewNumber : " + reviewNumber);

		ReviewDao dao = ReviewDao.getInstance();
		Review review = dao.getReviewByReviewNumber(reviewNumber);

		Timestamp now = new Timestamp(System.currentTimeMillis());
		request.setAttribute("review", review);
		request.setAttribute("now", now.toString().split(" ")[0]);
		String url = "updatereview?notice_number=" + reviewNumber + ""; 
		//request.getRequestDispatcher("updatereview").forward(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
