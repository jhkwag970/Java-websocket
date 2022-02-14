package file_Services;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class fileGet implements fileImpl{

	@Override
	public void fileWork(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String path = request.getSession().getServletContext().getRealPath("/") + "/Chat_Log/chat_log.txt";
		String user = (String)request.getAttribute("user");
		//String path = "c:/Chat_Log/chat_log.txt";
		//ArrayList<String> chat = new ArrayList<String>();
		String chat = "";
		try{
            
            File file = new File(path);
            
            FileReader filereader = new FileReader(file);
                        BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
            	String[] tmp = line.split("]");
            	if(line.contains("/w")) {
            		if(tmp[1].substring(0,2).equals("/w")) {
                		int index = tmp[1].indexOf(" ");
                		if(tmp[0].substring(1).equals(user) || tmp[1].substring(2, index).equals(user)) {
                			chat += line +"\n";
                		}
                	}else {
                		chat += line + "\n";
                	}
            	}else {
            		chat += line + "\n";
            	}
            	//chat += line + "\n";
            }            
            bufReader.close();
        }catch (Exception e) {
        	System.out.println(e.getMessage());
        }
		request.setAttribute("chat", chat);
		
	}
	

}
