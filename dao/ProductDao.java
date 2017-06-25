package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;
import utils.AppException;
import utils.DBUtil;

public class ProductDao {
	
	public Product getById(int id) throws AppException
	{
		Product product = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select *"
					+"from product"
					+"where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				product = new Product(); 
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setNum(rs.getString("num"));
				product.setDescription(rs.getString("description"));
				product.setVariety(rs.getInt("variety"));
				product.setIsSoldout(rs.getBoolean("soldout"));
				product.setPrice(rs.getInt("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setShopId(rs.getInt("shop_id"));
				product.setImage(rs.getString("iamge"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.getById");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return product;
	}
	
	public int getPriceById(int id) throws AppException
	{
		int price=0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select price"
					+"from product"
					+"where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				price=rs.getInt("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.getPriceById");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return price;
	}
	
	public int getQuantityById(int id) throws AppException
	{
        int quantity=0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select quantity"
					+"from product"
					+"where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				quantity=rs.getInt("quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.getQuantityById");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return quantity;
	}
	
	public void update(Product product) throws AppException
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set shop_id = ?,num = ?,name = ?,price = ?,"
					+ "description = ?,quantity = ?,variety = ?"
					+ "where id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, product.getShopId());
			psmt.setString(2, product.getNum());
			psmt.setString(3, product.getName());
			psmt.setFloat(4, product.getPrice());
			psmt.setString(5, product.getDescription());
			psmt.setInt(6, product.getQuantity());
			psmt.setInt(7, product.getVariety());
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.update");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
	}
	
	public void soldout(int id) throws AppException
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set soldout = true"
					+ "where id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.soldout");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
	}
	
	public void RecoverySell(int id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set soldout = false"
					+ "where id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.RecoverySell");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
	}
	
	public List<Integer> getIdsByShopId(int shop_id) throws AppException
	{
		List<Integer> ids=new ArrayList<Integer>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id"
					+"from product"
					+"where shop_id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, shop_id);
			
			rs = psmt.executeQuery();
			
			
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.getById");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
}
