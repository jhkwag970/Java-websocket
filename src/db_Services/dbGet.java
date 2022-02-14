package db_Services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat_log.logDAO;
import chat_log.logVO;

public class dbGet implements dbImpl {

	@Override
	public void dbWork(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logDAO lDAO = new logDAO();
		
		ArrayList<logVO> arr = lDAO.getAllLog();
		String chat = "";
		String user = (String)request.getAttribute("user");
		
		for(logVO tmp : arr) {
			if(tmp.getTo_name().equals("all")) {
				chat += "[" + tmp.getFrom_name() +"] " + tmp.getChat() + "\n";
			}else {
				
				if(tmp.getTo_name().equals(user) || tmp.getFrom_name().equals(user)) {
					chat += "[" + tmp.getFrom_name() + "]" +"/w" + tmp.getTo_name() + " " + tmp.getChat() + "\n";
				}
			}
			//System.out.println(tmp.getChat());
			//chat += "[" +tmp.getFrom_name() +"] " + " " + tmp.getChat() +"\n";
		}
		
		request.setAttribute("chat", chat);
		
		
	}

}
