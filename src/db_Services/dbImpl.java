package db_Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface dbImpl {
	
	public void dbWork(HttpServletRequest request, 
	          HttpServletResponse response)	 
	        		          throws Exception;
	
}
