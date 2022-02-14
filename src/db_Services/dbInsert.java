package db_Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat_log.logDAO;

public class dbInsert implements dbImpl{

	@Override
	public void dbWork(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String from_name = (String) request.getParameter("name");
		String to_name = "all";
		String chat = (String) request.getParameter("chat");
		boolean insert = false;
		
		logDAO lDAO = new logDAO();
		if(chat.substring(0,2).equals("/w")) {
			int index = chat.indexOf(" ");
			to_name = chat.substring(2, index);	
			chat = chat.substring(index + 1);
		}
		
		insert = lDAO.insertLog(from_name, to_name, chat);
		
		if(insert) {
			System.out.println("insert success");
		}else {
			System.out.println("insert fail");
		}
		
		
	}

}
