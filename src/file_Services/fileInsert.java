package file_Services;

import java.io.BufferedWriter;
import java.io.*;
import java.io.FileWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class fileInsert implements fileImpl{

	@Override
	public void fileWork(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String chat = request.getParameter("chat");
		
		String log = "[" + name + "]" + chat + "\n";
		String path = request.getSession().getServletContext().getRealPath("/") + "/Chat_Log/chat_log.txt";
		//String path = "c:/Chat_Log/chat_log.txt";
		
		//C:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Live_Chat6\ 
		
		//FileWriter file = new FileWriter("c:/workspace/Live_Chat6/chat_log.txt", true);
		FileWriter file = new FileWriter(path, true);
		
		BufferedWriter bw = new BufferedWriter(file);
		
		PrintWriter pw = new PrintWriter(bw, true);
		
		pw.write(log);
		pw.flush();
		pw.close();
		
		System.out.println("성공");
		
		
	}

}
