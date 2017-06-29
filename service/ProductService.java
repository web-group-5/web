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
	 * 添加商品
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
	 * 通过商铺id获取所属商品集
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
	 * 通过商品id获取所属商品
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
	 * 通过商品种类获取商品集
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
	 * 通过名称模糊查询商品集
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
	 * 更新商品
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
	 * 收藏商品，将收藏信息保存在数据库
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
	 * 获取收藏信息
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
	 * 删除受收藏夹
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
