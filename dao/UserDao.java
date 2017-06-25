package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.AppException;
import utils.DBUtil;

public class UserDao {
	/**
	 * �ж��û��Ƿ����
	 * 
	 * @param name User name
	 * @return  Return true if there are users with same name,otherwise return false 
	 * @throws AppException
	 */
	public boolean isExist(String nickname) throws AppException {
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id from user where nickname = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);

			rs = psmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.UserDao.isExist");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * �����û�
	 * 
	 * @param User object
	 * @return Return true if saved successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean add(User user) throws AppException {
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into user (nickname,account,login_password,pay_password,email)"
					+ " values (?,?,?,?,?)";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setString(1, user.getNickName());
			psmt.setString(2, user.getAccount());
			psmt.setString(3, user.getLogin_Password());
			psmt.setString(4, user.getPay_Password());
			psmt.setString(5, user.getEmail());
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.add");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * ��¼��ȡ�û�
	 * 
	 * @param name 
	 * @param password 
	 * @return User number
	 * @throws AppException
	 */
	public User login(String account, String login_password) throws AppException {
		User user = null;  
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			
			conn = DBUtil.getConnection();
			
			String sql = "select id,nickname,pay_password,balance,isShoper from user where account = ? and login_password = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, account);
			psmt.setString(2, login_password);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				user=new User();
				user.setId(rs.getInt("id"));
				user.setNickName(rs.getString("nickname"));
				user.setAccount(account);
				user.setPay_Password(rs.getString("pay_password"));
				user.setBalance(rs.getInt("balance"));
				user.setIsShoper(rs.getBoolean("isShoper"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.login");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return user;
	}
	
	/**
	 * �����û�id��ѯ�û�
	 * 
	 * @param id User id
	 * @return User User object
	 * @throws AppException
	 */
	public User getById(int id) throws AppException {
		 
		User user = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select *"
					+"from user"
					+"where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				user = new User(); 
				user.setId(rs.getInt("id"));
				user.setNickName(rs.getString("nickname"));
				user.setName(rs.getString("name"));
				user.setAccount(rs.getString("account"));
				user.setSex(rs.getString("sex"));
				user.setPhone(rs.getString("phone"));
				user.setBirthday(rs.getDate("birthday"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.getById");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return user;
	}

	/**
	 * ��ѯid��
	 * 
	 * @return User id set
	 * @throws AppException
	 */
	public List<Integer> getIds() throws AppException {
		 
		List<Integer> ids = new ArrayList<Integer>();
		
		 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try { 
			conn = DBUtil.getConnection();
			
			String sql = "select id from user";
			
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.UserDao.getIds");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}

	/**
	 * �����û�
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean UpdateUserInfo(User user) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set name = ?,sex = ?,birthday = ?,phone = ?,"
					+ "email = ?,address = ?"
					+ "where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getSex());
			psmt.setDate(3, user.getBirthday());
			psmt.setString(4, user.getPhone());
			psmt.setString(5, user.getEmail());
			psmt.setString(6, user.getAddress());
			psmt.setInt(7, user.getId());
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.UpdateUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	public boolean UpdateLogin_Password(int id,String login_password) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set login_password = ?"
					+ "where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, login_password);
			psmt.setInt(2, id);
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.UpdateUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	public boolean UpdatePay_Password(int id,String pay_password) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set pay_password = ?"
					+ "where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, pay_password);
			psmt.setInt(2, id);
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.UpdateUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * �շ�
	 * @param id
	 * @param money
	 * @return
	 * @throws AppException
	 */
	public boolean Charge(int id,int money) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set balance += ?"
					+ "where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, money);
			psmt.setInt(2, id);
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.UpdateUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}

	/**
	 * ����
	 * @param id
	 * @param money
	 * @return
	 * @throws AppException
	 */
	public boolean Expense(int id,int money) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set balance -= ?"
					+ "where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, money);
			psmt.setInt(2, id);
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.UpdateUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws AppException
	 */
	public boolean JudgeUser(String nickname) throws AppException {
		int status = 0;
		 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try { 
			conn = DBUtil.getConnection();
            String sql = "select * from user where nickname = ?;";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.JudgeUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return false;
	}
	
	public boolean IsPay_Password(int id,String pay_password) throws AppException{
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "select pay_password from user where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				if(rs.getString("pay_password").equals(pay_password))
				    flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.UserDao.isExist");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	public boolean OpenShop(int id) throws AppException{
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set isShoper = true"
					+ "where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.UserDao.UpdateUser");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
}