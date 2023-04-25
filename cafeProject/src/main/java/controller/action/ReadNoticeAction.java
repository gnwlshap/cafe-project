package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.Notice;
import notice.controller.NoticeDao;

public class ReadNoticeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noticeNumber = Integer.parseInt(request.getParameter("notice_number"));

		NoticeDao dao = NoticeDao.getInstance();
		Notice notice = dao.getNoticeByNoticeNumOnlyData(noticeNumber);

		request.setAttribute("Notice", notice);
		//String url = "noticedetail?&notice_number=" + noticeNumber + "";
		request.getRequestDispatcher("noticedetail").forward(request, response);
	}

}
