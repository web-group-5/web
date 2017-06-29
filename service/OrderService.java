package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.OrderDao;
import dao.ProductDao;
import dao.ShopDao;
import dao.UserDao;
import model.Order;
import model.OrderItem;
import model.Product;
import utils.AppException;

public class OrderService {
	private OrderDao orderDao=null;
	private ProductDao productDao=null;
	private ShopDao shopDao=null;
	private UserDao userDao=null;
	
	public OrderService(){
		orderDao=new OrderDao();
		productDao=new ProductDao();
		shopDao=new ShopDao();
		userDao=new UserDao();
	}
	
	/**
	 * 计算购物车总价
	 * @param orderItems
	 * @return
	 * @throws AppException
	 */
	public float countOrderPrice(List<OrderItem> orderItems) throws AppException{
		float total_price=0;
		try {
			for (OrderItem orderItem : orderItems) {
				Product product;
				product = productDao.getById(orderItem.getProduct_id());
				total_price+=orderItem.getQuantity()*product.getPrice();
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.countOrderPrice");
		}
		return total_price;
	}
	
	/**
	 * 生成订单
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public boolean generateOrder(int user_id) throws AppException{
		try {
			List<OrderItem> orderItems=orderDao.getItemsFromShopcart(user_id);  //获取购物车信息，得到一组购物车商品项
			float total_price=countOrderPrice(orderItems); //计算订单总价
			int order_id=orderDao.generateOrder(user_id, total_price); //生成订单号
			
			//生成订单项
			for(OrderItem orderItem:orderItems){
				orderDao.addToOrderDetail(order_id, orderItem);
			}
			
			//清空购物车
			orderDao.delItemsFromShopcart(user_id);
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.generateOrder");
		}
		return true;
	}
	
	/**
	 * 完成订单
	 * 包括订单状态的改变，及交易
	 * @param order
	 * @return
	 * @throws AppException
	 */
	public boolean achieveOrder(Order order) throws AppException{
		try {
			/**
			 * 订单状态设置
			 */
			orderDao.achieveOrder(order);
			
			/**
			 * 结款
			 */
			//买家
			if(userDao.getById(order.getUser_id()).getBalance()>=order.getTotal_price())
				userDao.Expense(order.getUser_id(), order.getTotal_price());
			else
				return false;
			
			//卖家
			Map<Integer, Integer> map=new HashMap<Integer, Integer>(); //map<user_id,total_price>
			List<OrderItem> orderItems=order.getOrderItems();
			//计算该次交易涉及的每位卖家的收入
			for(OrderItem orderItem:orderItems){
				int shop_id=productDao.getShopIdById(orderItem.getProduct_id());
				int user_id=shopDao.getUserId(shop_id);
				if(map.containsKey(user_id))
				{
					int newValue=map.get(user_id)+orderItem.getQuantity()*productDao.getPriceById(orderItem.getProduct_id());
					map.put(user_id, newValue);
				}else{
					int Value=orderItem.getQuantity()*productDao.getPriceById(orderItem.getProduct_id());
					map.put(user_id, Value);
				}
			}
			//卖家遍历收钱
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {  
				userDao.Charge(entry.getKey(), entry.getValue());  
			}  
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.achieveOrder");
		}
		return true;
	}
	
	/**
	 * 获取购物车
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public List<OrderItem> getShopcart(int user_id) throws AppException{
		List<OrderItem> orderItems=new ArrayList<OrderItem>();
		try {
			orderItems=orderDao.getItemsFromShopcart(user_id);
			return orderItems;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.getShopcart");
		}
	}
	
	/**
	 * 获取用户所有订单
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public List<Order> getOrderS(int user_id) throws AppException{
		List<Integer> orderids=new ArrayList<Integer>();
		List<Order> orders=new ArrayList<Order>();
		orderids=orderDao.getOrderIds(user_id);
		try {
			for(int order_id:orderids){
				Order order=orderDao.getOrder(order_id);
				orders.add(order);
			}
			return orders;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.getOrderS");
		}
	}
	
	/**
	 * 添加购物车
	 * @param orderItem
	 * @return
	 * @throws AppException
	 */
	public boolean addShopcart(OrderItem orderItem) throws AppException{
		try {
			orderDao.addToShopcart(orderItem);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.addShopcart");
		}
	}
	
	/**
	 * 单项删除购物车商品
	 * @param orderItem
	 * @return
	 * @throws AppException
	 */
	public boolean delShopcart(OrderItem orderItem) throws AppException{
		try {
			orderDao.delItemFromShopcart(orderItem);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.delShopcart");
		}
	}
	
	/**
	 * 清空购物车
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public boolean emptyShopcart(int user_id) throws AppException{
		try {
			orderDao.delItemsFromShopcart(user_id);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.emptyShopcart");
		}
	}
	
	/**
	 * 更新购物车（传列表）
	 * @param orderItems
	 * @return
	 * @throws AppException
	 */
	public boolean updateShopcart(List<OrderItem> orderItems) throws AppException{
		try {
			for(OrderItem orderItem:orderItems)
			    orderDao.updateShopcart(orderItem);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.updateShopcart");
		}
	} 

}
