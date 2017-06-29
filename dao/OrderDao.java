package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderItem;
import model.Product;
import utils.AppException;
import utils.Constant;
import utils.DBUtil;

public class OrderDao {
	
	/**
	 * 向购物车中添加订单项
	 * @param orderItem
	 * @return
	 * @throws AppException
	 */
	public boolean addToShopcart(OrderItem orderItem) throws AppException
	{
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into shopcart (user_id,product_id,quantity)"
					+ " values (?,?,?)";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setInt(1, orderItem.getUser_id());
			psmt.setInt(2, orderItem.getProduct_id());
			psmt.setInt(3, orderItem.getQuantity());
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.addToShopcart");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * 更新购物车
	 * @param orderItem
	 * @return
	 * @throws AppException
	 */
	public boolean updateShopcart(OrderItem orderItem) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update shopcart set quantity = ?"
					+ "where user_id = ? and product_id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, orderItem.getQuantity());
			psmt.setInt(2, orderItem.getUser_id());
			psmt.setInt(3, orderItem.getProduct_id());
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.updateShopcart");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * 单项删除购物车
	 * @param orderItem
	 * @return
	 * @throws AppException
	 */
	public boolean delItemFromShopcart(OrderItem orderItem) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from shopcart"
					+ "where user_id = ? and product_id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, orderItem.getUser_id());
			psmt.setInt(2, orderItem.getProduct_id());
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.delItemFromShopcart");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * 清空购物车
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public boolean delItemsFromShopcart(int user_id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from shopcart"
					+ "where user_id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, user_id);
			
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.delItemsFromShopcart");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * 从购物车中获取所有订单项
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public List<OrderItem> getItemsFromShopcart(int user_id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		ProductDao productDao=new ProductDao();
		
		List<OrderItem> orderItems=new ArrayList<OrderItem>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from shopcart"
					+ "where user_id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, user_id);
			
			rs=psmt.executeQuery();
			
			while(rs.next()){
				OrderItem orderItem=new OrderItem();
				orderItem.setUser_id(user_id);
				orderItem.setProduct_id(rs.getInt("product_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				Product product=productDao.getById(rs.getInt("product_id"));
				orderItem.setDescription(product.getDescription());
				orderItem.setPrice(product.getPrice());
				orderItem.setImage(product.getImage());
				
				orderItems.add(orderItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.getItemsFromShopcart");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return orderItems;
	}
	
	/**
	 * 根据订单id获取订单细节（一组订单项）
	 * @param orders_id
	 * @return
	 * @throws AppException
	 */
	public List<OrderItem> getItemsFromOrderDetail(int order_id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ProductDao productDao=new ProductDao();
		
		List<OrderItem> orderItems=new ArrayList<OrderItem>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from orderDetail"
					+ "where orders_id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, order_id);
			
			rs=psmt.executeQuery();
			
			while(rs.next()){
				OrderItem orderItem=new OrderItem();
				orderItem.setOrder_id(order_id);
				orderItem.setProduct_id(rs.getInt("product_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				Product product=productDao.getById(rs.getInt("product_id"));
				orderItem.setDescription(product.getDescription());
				orderItem.setPrice(product.getPrice());
				orderItem.setImage(product.getImage());
				
				orderItems.add(orderItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.getItemsFromOrderDetail");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return orderItems;
	}
	
	/**
	 * 生成订单，返回订单id
	 * @param user_id
	 * @param total_price
	 * @return
	 * @throws AppException
	 */
	public int generateOrder(int user_id,float total_price) throws AppException{
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs=null;
		int order_id=0;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into orders (user_id,total_price,status)"
					+ " values (?,?,?)";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setInt(1, user_id);
			psmt.setFloat(2, total_price);
			psmt.setInt(3, Constant.ORDERSTATUS_UNDO);
			
			psmt.executeUpdate();

			rs = psmt.getGeneratedKeys();
			if(rs.next()){
				order_id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.generateOrder");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return order_id;
	}
	
	/**
	 * 根据订单编号以及购物车项 生成订单细节
	 * @param order_id
	 * @param orderItem
	 * @return
	 * @throws AppException
	 */
	public boolean addToOrderDetail(int order_id,OrderItem orderItem) throws AppException
	{
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into orderDetail (orders_id,product_id,quantity)"
					+ " values (?,?,?)";
			
			psmt = conn.prepareStatement(sql); 
			
			psmt.setInt(1, order_id);
			psmt.setInt(2, orderItem.getProduct_id());
			psmt.setInt(3, orderItem.getQuantity());
			
			flag = psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.addToOrderDetail");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
	/**
	 * 根据用户id获取该用户所有未完成订单号
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public List<Integer> getOrderIds(int user_id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Integer> ids=new ArrayList<Integer>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select id from orders"
					+ "where user_id = ? and status = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, user_id);
			psmt.setInt(2, Constant.ORDERSTATUS_UNDO);
			
			rs=psmt.executeQuery();
			
			while(rs.next()){
				ids.add(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.getOrder");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return ids;
	}
	
	/**
	 * 根据订单id获取订单信息
	 * @param order_id
	 * @return
	 * @throws AppException
	 */
	public Order getOrder(int order_id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Order order=new Order();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from orders"
					+ "where id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, order_id);
			
			rs=psmt.executeQuery();
			
			if(rs.next()){
				order.setId(order_id);
				order.SetUser_id(rs.getInt("user_id"));
				order.setPrice(rs.getFloat("total_price"));
			}
			
			order.setOrderItems(getItemsFromOrderDetail(order_id));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.getOrder");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return order;
	}
	
	/**
	 * 设置订单中status为完成
	 * @param user_id
	 * @param order_id
	 * @return
	 * @throws AppException
	 */
	public boolean achieveOrder(Order order) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update orders set status = ?"
					+ "where user_id = ? and id = ?"; 
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, Constant.ORDERSTATUS_DONE);
			psmt.setInt(2, order.getUser_id());
			psmt.setInt(3, order.getId());
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.OrderDao.achieveOrder");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return true;
	}
	
}
