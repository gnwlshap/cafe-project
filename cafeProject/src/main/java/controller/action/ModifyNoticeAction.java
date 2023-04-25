package controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.Notice;
import notice.controller.NoticeDao;

public class ModifyNoticeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noticeNumber = Integer.parseInt(request.getParameter("notice_number"));
		System.out.println("noticeNumber : " + noticeNumber);

		NoticeDao dao = NoticeDao.getInstance();
		Notice notice = dao.getNoticeByNoticeNum(noticeNumber);

		Timestamp now = new Timestamp(System.currentTimeMillis());
		request.setAttribute("notice", notice);
		request.setAttribute("now", now.toString().split(" ")[0]);
		String url = "updatenotice?notice_number=" + noticeNumber + ""; 
		request.getRequestDispatcher(url).forward(request, response);
	}

}
