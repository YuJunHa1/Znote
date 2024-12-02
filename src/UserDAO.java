import java.sql.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Junha
 */
public class UserDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
    public UserDAO() {
        try {
                //String dbURL = "jdbc:mysql://localhost:3306/znote";
                String dbURL = "jdbc:mysql://db-instance-jnote.clwkksk2ob9a.ap-northeast-2.rds.amazonaws.com:3306/znote";
		String dbID = "admin";
		String dbPassword = "inhatc2021";
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
                pstmt.setString(4, null);
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // DB 오류
	}
        
        public int update(User user) {
		String SQL = "UPDATE user SET userPassword = ?, userName = ?, updatedAt = ? WHERE userID = ?";
                
	    try {
	        pstmt = conn.prepareStatement(SQL);
	        pstmt.setString(1, user.getUserPassword());
	        pstmt.setString(2, user.getUserName());
	        pstmt.setString(3, user.getUpdatedAt().format(formatter));
                pstmt.setString(4, user.getUserID()); // 수정할 사용자의 ID가 필요합니다
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // DB 오류
	}
        
        public User getUser(String userID) {
    String SQL = "SELECT * FROM user WHERE userID = ?";
    User user = null; // 반환할 User 객체
    try {
        pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, userID);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            user = new User(); // User 객체 생성
            user.setUserID(rs.getString("userID")); // userID 가져오기
            user.setUserPassword(rs.getString("userPassword")); // userPassword 가져오기
            user.setUserName(rs.getString("userName")); // userName 가져오기
            user.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime()); // CreatedAt 가져오기
          //user.setUserImage(rs.getString("userImage")); // userImage (DB 컬럼이 있다면)
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return user; // User 객체 반환
}

        
        
}
