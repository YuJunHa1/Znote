import java.sql.*;

/**
 *
 * @author Junha
 */
public class UserDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
	
    public UserDAO() {
        try {
                String dbURL = "jdbc:mysql://localhost:3306/znote";
		String dbID = "root";
		String dbPassword = "wnsgk7575";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
        
    public int login(String userID, String userPassword) {
		String SQL = "select userPassword from user where userID=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else
					return 0; //비밀번호 불일치
			}
			return -1; // 아이디가 없음
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //DB 오류
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO user (userID, userPassword, userName) VALUES (?, ?, ?)";
	    try {
	        pstmt = conn.prepareStatement(SQL);
	        pstmt.setString(1, user.getUserID());
	        pstmt.setString(2, user.getUserPassword());
	        pstmt.setString(3, user.getUserName());
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // DB 오류
	}
        
        
}
