package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.AppException;
import utils.DBUtil;

public class ShopDao {
	/**
	 * 根据店铺id获取店主id
	 * @param id
	 * @return
	 * @throws AppException 
	 */
	public int getUserId(int id) throws AppException{

		int user_id=-1;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select user_id "
					+"from shop "
					+"where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				user_id=rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ShopDao.getUserId");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return user_id;
	}
}
