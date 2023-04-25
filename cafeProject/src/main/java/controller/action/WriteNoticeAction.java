package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDto;
import notice.controller.NoticeDao;

public class WriteNoticeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("user_id");
		if( !id.equals("composecoffee")) {
			id = "composecoffee";
		}
		String title = request.getParameter("notice_title");
		String content = request.getParameter("notice_content");
		
		NoticeDao dao = NoticeDao.getInstance();
		NoticeDto dto = new NoticeDto(id, title, content);
		dao.writeNotice(dto);
		
		response.sendRedirect("notice");
	}

}
