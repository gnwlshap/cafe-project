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

public class ReadAllCafeAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CafeDao dao = CafeDao.getInstance();
		
		ArrayList<Cafe> list = dao.getCafeAll();
		
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
