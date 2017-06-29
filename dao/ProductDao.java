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
	
	public boolean add(Product product) throws AppException{
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into product (name,shop_id,price,description,quantity,variety,soldout,image)"
					+ " values (?,?,?,?,?,?,?,?)";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setString(1, product.getName());
			psmt.setInt(2, product.getShopId());
			psmt.setFloat(3, product.getPrice());
			psmt.setString(4, product.getDescription());
			psmt.setInt(5, product.getQuantity());
			psmt.setInt(6, product.getVariety());
			psmt.setBoolean(7, product.getIsSoldout());
			psmt.setString(8, product.getImage());
			
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.add");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
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
	
	public List<Integer> getIdsByName(String name) throws AppException{
		List<Integer> ids = new ArrayList<Integer>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String s_name="%"+name+"%";
			String sql = "select id"
					+"from product"
					+"where variety like ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, s_name);
			
			rs = psmt.executeQuery();
			
			
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.getIdsByName");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	public List<Integer> getIdsByVariety(int variety) throws AppException
	{
		List<Integer> ids = new ArrayList<Integer>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id"
					+"from product"
					+"where variety = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, variety);
			
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
	
	public boolean update(Product product) throws AppException
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update product set shop_id = ?,num = ?,name = ?,price = ?,"
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
		return true;
	}
	
	/**
	 * 商品下架
	 * @param id
	 * @throws AppException
	 */
	public void soldout(int id) throws AppException
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update product set soldout = true"
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
	
	/**
	 * 恢复售卖
	 * @param id
	 * @throws AppException
	 */
	public void RecoverySell(int id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update product set soldout = false"
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
			throw new AppException("dao.ProductDao.getIdsByShopId");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	public int getShopIdById(int id) throws AppException{
		int shop_id=-1;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select shop_id"
					+"from product"
					+"where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				shop_id=rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.getIdsByShopId");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return shop_id;
	}
	
	public boolean collect(int user_id,int product_id) throws AppException{
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into collection (user_id,product_id)"
					+ " values (?,?)";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setInt(1, user_id);
			psmt.setInt(2, product_id);
			
			int rowAffected = psmt.executeUpdate();
			if(rowAffected==1)
				return true;
			else 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.add");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
	}
	
	public List<Integer> getCollection(int user_id) throws AppException{
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Integer> ids=new ArrayList<Integer>();
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();

			String sql = "select product_id"
					+"from product"
					+"where user_id = ?";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setInt(1, user_id);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				int product_id=rs.getInt("product_id");
				ids.add(product_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.ProductDao.add");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	public void delCollection(int user_id,int product_id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from collection"
					+ "where user_id = ? and product_id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, user_id);
			psmt.setInt(2, product_id);
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.delItemFromShopcart");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
	}
}
