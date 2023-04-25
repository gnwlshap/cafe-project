package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.ReviewDto;
import review.controller.ReviewDao;

public class UpdateReviewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("[check]");

		String id = request.getParameter("user_id");
		int reviewNumber = Integer.parseInt(request.getParameter("review_number"));
		String title = request.getParameter("review_title");
		String content = request.getParameter("review_content");

		System.out.println(id);
		System.out.println(reviewNumber);
		System.out.println(title);
		System.out.println(content);

		ReviewDto dto = new ReviewDto(reviewNumber, id, title, content);
		ReviewDao dao = ReviewDao.getInstance();
		dao.updateReview(dto);

		String url = "reviewdetail?&review_number=" + reviewNumber + "";
		response.sendRedirect(url);

	}

}
