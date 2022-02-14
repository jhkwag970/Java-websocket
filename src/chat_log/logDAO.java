package chat_log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import DBConn.DBConn;

public class logDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public logDAO() throws ClassNotFoundException, SQLException{
		con= new DBConn().getConnection();
	}
	public void pstmtClose() throws SQLException{
		if(pstmt != null){ pstmt.close(); }
	}
	public void getAllInfoClose() throws SQLException{;
		if(rs != null){ rs.close(); }
		if(pstmt != null){ pstmt.close(); }
		if(con != null){ con.close(); }
	}
	
	public boolean insertLog(String from_name, String to_name,String chat) {
		String sql = "insert into chat_log values"
				+ "((SELECT NVL(MAX(chat_id)+1,1) FROM chat_log),"
				+ "?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, from_name);
			pstmt.setString(2, to_name);
			pstmt.setString(3, chat);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public ArrayList<logVO> getAllLog() throws SQLException{
		ArrayList<logVO> arr = new ArrayList<logVO>();
		String sql = "select * from chat_log order by chat_id asc";
		
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int chat_id = rs.getInt(1);
			String from_name = rs.getString(2);
			String to_name = rs.getString(3);
			String chat = rs.getString(4);
			logVO tmp = new logVO(chat_id, from_name, to_name, chat);
			arr.add(tmp);
		}
		return arr;
	}

}
