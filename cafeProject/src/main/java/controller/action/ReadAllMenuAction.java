package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import menu.Menu;
import menu.controller.MenuDao;

public class ReadAllMenuAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDao dao = MenuDao.getInstance();;
		
		ArrayList<Menu> list = dao.getMenuAll();
		
		JSONArray data = new JSONArray();
		for(Menu menu : list) {
			JSONObject obj = new JSONObject();
			obj.put("menuCode", menu.getMenuCode());
			obj.put("menuName", menu.getMenuName());
			obj.put("category", menu.getMenuCategory());
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
