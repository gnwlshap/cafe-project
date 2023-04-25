package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrap.ScrapDto;
import scrap.controller.ScrapDao;
import user.User;

public class DeleteScrapAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User log = (User) request.getSession().getAttribute("log");

		String userId = log.getUserId();
		
		int cafeCode = Integer.parseInt(request.getParameter("cafeCode"));
		
		ScrapDto scrapDto = new ScrapDto(cafeCode, userId);
		ScrapDao scrapDao = ScrapDao.getInstance();
		
		scrapDao.deleteScraop(scrapDto);
	}

}
