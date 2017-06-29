package service;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import utils.AppException;

public class ProductService {
	private ProductDao productDao=null;
	
	public ProductService(){
		productDao=new ProductDao();
	}
	
	/**
	 * �����Ʒ
	 * @param product
	 * @return
	 * @throws AppException
	 */
	public boolean add(Product product) throws AppException{
		try {
			productDao.add(product);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.getProductsByShopId");
		}
	}
	
	/**
	 * ͨ������id��ȡ������Ʒ��
	 * @param shop_id
	 * @return
	 * @throws AppException
	 */
	public List<Product> getProductsByShopId(int shop_id) throws AppException{
		List<Integer> ids=null;
		List<Product> products=new ArrayList<Product>();
		
		try {
			ids=productDao.getIdsByShopId(shop_id);
			
			for (int id : ids) {
				Product product=productDao.getById(id);
				products.add(product);
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.getProductsByShopId");
		}
		return products;
	}
	
	/**
	 * ͨ����Ʒid��ȡ������Ʒ
	 * @param shop_id
	 * @return
	 * @throws AppException
	 */
	public Product getProductById(int id) throws AppException{
		try {
			Product product=productDao.getById(id);
			return product;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.getProductsByShopId");
		}
		
	}
	
	/**
	 * ͨ����Ʒ�����ȡ��Ʒ��
	 * @param variety
	 * @return
	 * @throws AppException
	 */
	public List<Product> getProductsByVariety(int variety) throws AppException{
		List<Integer> ids=null;
		List<Product> products=new ArrayList<Product>();
		
		try {
			ids=productDao.getIdsByVariety(variety);
			
			for (int id : ids) {
				Product product=productDao.getById(id);
				products.add(product);
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.getProductsByVariety");
		}
		return products;
	}
	
	/**
	 * ͨ������ģ����ѯ��Ʒ��
	 * @param name
	 * @return
	 * @throws AppException
	 */
	public List<Product> getProductsByName(String name) throws AppException{
		List<Integer> ids=null;
		List<Product> products=new ArrayList<Product>();
		
		try {
			ids=productDao.getIdsByName(name);
			
			for (int id : ids) {
				Product product=productDao.getById(id);
				products.add(product);
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.getProductsByVariety");
		}
		return products;
	}
	
	/**
	 * ������Ʒ
	 * @param product
	 * @return
	 * @throws AppException
	 */
	public boolean update(Product product) throws AppException{
		boolean flag=false;
		try {
			flag=productDao.update(product);
			return flag;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.update");
		}
	}
	
	/**
	 * �ղ���Ʒ�����ղ���Ϣ���������ݿ�
	 * @param user_id
	 * @param product_id
	 * @return
	 * @throws AppException
	 */
	public boolean collect(int user_id,int product_id) throws AppException{
		boolean flag=false;
		try {
			flag=productDao.collect(user_id, product_id);
			return flag;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.update");
		}
	}
	
	/**
	 * ��ȡ�ղ���Ϣ
	 * @param user_id
	 * @return
	 * @throws AppException
	 */
	public List<Product> getCollection(int user_id) throws AppException{
		List<Integer> ids=null;
		List<Product> products=new ArrayList<Product>();
		try {
			ids=productDao.getCollection(user_id);
			for (int id : ids) {
				Product product=productDao.getById(id);
				products.add(product);
			}
			return products;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.getCollection");
		}
	}
	
	/**
	 * ɾ�����ղؼ�
	 * @param user_id
	 * @param product_id
	 * @throws AppException
	 */
	public void delCollection(int user_id,int product_id) throws AppException{
		try {
			productDao.delCollection(user_id, product_id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("dao.ProductService.delCollection");
		}
	}
}
