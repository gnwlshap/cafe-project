package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import notice.Notice;
import notice.controller.NoticeDao;

public class SearchNoticeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		int category = Integer.parseInt(request.getParameter("category"));
		String keyword = request.getParameter("keyword");
		String page = request.getParameter("page"); 

		NoticeDao dao = NoticeDao.getInstance();
		ArrayList<Notice> list = null;
		
		int count = dao.getNoticeCountByCategory(category, keyword); 
		int pageSize = 10; 
		int currentPage = 1; 

		if (page != null) { 
			currentPage = Integer.parseInt(page);
		}
		int startIndex = (currentPage - 1) * pageSize; 

		if (category == 1) {
			list = dao.getNoticeByNoticeTile(keyword, startIndex, pageSize);
		} else if (category == 2) {
			list = dao.getNoticeByNoticeContent(keyword, startIndex, pageSize);
		} else if (category == 3) {
			list = dao.getNoticeByKeyword(keyword, startIndex, pageSize);
		}

		int totalPage = (int) Math.ceil( (double) count / pageSize); 
		int prevPage = currentPage - 1; 
		int nextPage = currentPage + 1; 

		JSONObject result = new JSONObject();
		
		JSONObject status = new JSONObject(); 
		status.put("currentPage", currentPage); 
		status.put("prevPage", prevPage); 
		status.put("nextPage", nextPage); 
		status.put("totalPages", totalPage);
		status.put("count", count);
		
		JSONArray listObj = new JSONArray(list);

		result.put("status", status); 
		result.put("list", listObj);

		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}
}
