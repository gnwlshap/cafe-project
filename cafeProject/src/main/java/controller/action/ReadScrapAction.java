package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cafe.Cafe;
import cafe.controller.CafeDao;
import scrap.Scrap;
import scrap.controller.ScrapDao;
import user.User;

public class ReadScrapAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User log = (User) request.getSession().getAttribute("log");
	    
		String userId = log.getUserId();
		
		ScrapDao scrapDao = ScrapDao.getInstance();
		
		ArrayList<Scrap> scrapList = scrapDao.getScrapByUserId(userId);
		
		CafeDao cafeDao = CafeDao.getInstance();
		
		ArrayList<Cafe> list = new ArrayList<>();
		
		for(Scrap scrap : scrapList) {
			list.add(cafeDao.getCafeByCode(scrap.getCafeCode()));
		}
		
		JSONArray data = new JSONArray();
		for(Cafe cafe : list) {
			JSONObject obj = new JSONObject();
			obj.put("cafeCode", cafe.getCafeCode());
			obj.put("cafeName", cafe.getCafeName());
			obj.put("cafeAddress", cafe.getCafeAddress());
			obj.put("cafePhone", cafe.getCafePhone());
			data.put(obj);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json charset=utf8");
		response.setHeader("Content-Type", "application/json charset=utf8");
		PrintWriter out = response.getWriter();
	  
		out.print(data);
		out.flush();
	}
}
