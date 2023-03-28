package services.auth;

import java.sql.Connection;
import java.sql.SQLException;

import beans.UserBean;

public class AuthDataAccessObject  extends services.DataAccessObject{

	public AuthDataAccessObject() {
		
	}
	final Connection openConnection() {
		return this.openConnect();
	}
	final Connection openConnection(String userName, String password ) {
		return this.openConnect();
	}
	final void closeConnection(Connection connect) {
		this.closeConnect(connect);
	}
	final void modifyTranStatus(Connection connect, boolean tran) {
		this.setTranStatus(connect, tran);
	}
	final void setTransaction(boolean tran, Connection connect) {
		this.setTransactionEnd(tran, connect);
	}
	
	
	
	
	
	
	
	
	
	/*중복된 ID가 있는지 확인*/
	final int selUser(Connection connection, UserBean user) {
		int result = 0;
		
		String query = "SELECT COUNT(*) AS USERID FROM TRDBA.CS WHERE CS_ID = ?";
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, user.getUserId());
			//System.out.println(user.getUserId());
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				result = this.rs.getInt("USERID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(result);
		return result;
	}
	
	/*회원가입 했을 때 고객 테이블에 insert*/
	final int insUser(Connection connection, UserBean user) {
		int result = 0;
		
		String dml = "INSERT INTO TRDBA.CS(CS_ID, CS_NAME, CS_PW, CS_PHONE, CS_ADDR) VALUES(?,?,?,?,?)";
		
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		System.out.println(user.getUserPw());
		System.out.println(user.getUserPhone());
		System.out.println(user.getUserAddr());
		
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getUserName());
			this.ps.setNString(3, user.getUserPw());
			this.ps.setNString(4, user.getUserPhone());
			this.ps.setNString(5, user.getUserAddr());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/*로그인 하기 전 id 체크*/
	final int isId(Connection connection, UserBean user) {
		int result = 0;
		String dml = "SELECT COUNT(*) AS ISID FROM TRDBA.CS WHERE CS_ID = ?";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) result = this.rs.getInt("ISID");
			
		} catch (SQLException e) {e.printStackTrace();}
		return result;
	}
	
	/*로그인에서 사용자가 입력한 정보가 CS테이블에 있는 정보와 일치하는지 확인*/
	final int selLogin(Connection connection, UserBean user) {
		int result = 0;
		
		String query = "SELECT COUNT(*) AS LOGIN FROM TRDBA.CS WHERE CS_ID = ? AND CS_PW = ?";
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getUserPw());
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				result = this.rs.getInt("LOGIN");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	/*로그인괴 로그아웃을 했을 때 접속시간과 접속타입을 insert*/
	final int insAccess(Connection connection, UserBean user) {
		int result = 0;
		
		String dml = "INSERT INTO TRDBA.AL(AL_CSID, AL_DATE, AL_TYPE) "
				+ "VALUES(?,DEFAULT,?)";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getLogInfo().get(0).getLogType());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
