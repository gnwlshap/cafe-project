package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.controller.ReviewDao;

public class DeleteReviewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int num = Integer.parseInt(request.getParameter("review_number"));
		System.out.println(num);

		ReviewDao dao = ReviewDao.getInstance();
		dao.deleteBoardtByBoardNum(num);

		response.sendRedirect("review");
	}

}
