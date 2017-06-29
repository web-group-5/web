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
	 * ���㹺�ﳵ�ܼ�
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
	 * ���ɶ���
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public boolean generateOrder(int user_id) throws AppException{
		try {
			List<OrderItem> orderItems=orderDao.getItemsFromShopcart(user_id);  //��ȡ���ﳵ��Ϣ���õ�һ�鹺�ﳵ��Ʒ��
			float total_price=countOrderPrice(orderItems); //���㶩���ܼ�
			int order_id=orderDao.generateOrder(user_id, total_price); //���ɶ�����
			
			//���ɶ�����
			for(OrderItem orderItem:orderItems){
				orderDao.addToOrderDetail(order_id, orderItem);
			}
			
			//��չ��ﳵ
			orderDao.delItemsFromShopcart(user_id);
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.OrderService.generateOrder");
		}
		return true;
	}
	
	/**
	 * ��ɶ���
	 * ��������״̬�ĸı䣬������
	 * @param order
	 * @return
	 * @throws AppException
	 */
	public boolean achieveOrder(Order order) throws AppException{
		try {
			/**
			 * ����״̬����
			 */
			orderDao.achieveOrder(order);
			
			/**
			 * ���
			 */
			//���
			if(userDao.getById(order.getUser_id()).getBalance()>=order.getTotal_price())
				userDao.Expense(order.getUser_id(), order.getTotal_price());
			else
				return false;
			
			//����
			Map<Integer, Integer> map=new HashMap<Integer, Integer>(); //map<user_id,total_price>
			List<OrderItem> orderItems=order.getOrderItems();
			//����ôν����漰��ÿλ���ҵ�����
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
			//���ұ�����Ǯ
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
	 * ��ȡ���ﳵ
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
	 * ��ȡ�û����ж���
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
	 * ��ӹ��ﳵ
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
	 * ����ɾ�����ﳵ��Ʒ
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
	 * ��չ��ﳵ
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
	 * ���¹��ﳵ�����б�
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
