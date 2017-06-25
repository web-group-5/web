package service;

import dao.UserDao;
import model.User;
import utils.AppException;

public class UserService {
	private UserDao userDao;
	
	public UserService(){
		userDao=new UserDao();
	}
	
	/**
	 * �ж��˺��Ƿ��ظ�
	 * @param account
	 * @return
	 * @throws AppException 
	 */
	public boolean isExist_account(String account) throws AppException{
		boolean flag=true;
		try {
			flag=userDao.isExist_account(account);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.isExist_account");
		}
		return flag;
	}
	
	/**
	 * �ж��ǳ��Ƿ����
	 * @param nickname
	 * @return
	 * @throws AppException 
	 */
	public boolean isExist_nickname(String nickname) throws AppException{
		boolean flag=true;
		try {
			flag=userDao.isExist_account(nickname);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.isExist_nickname");
		}
		return flag;
	}
	
	/**
	 * ע��
	 * @param account
	 * @param login_password
	 * @return
	 * @throws AppException
	 */
	public boolean register(String account, String login_password) throws AppException{
		User user=new User();
		user.setAccount(account);
		user.setLogin_Password(login_password);
		user.setPay_Password(login_password);
		
		try {
			userDao.add(user);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.register");
		}
	}
	
	/**
	 * ��¼
	 * @param account
	 * @param login_password
	 * @return
	 * @throws AppException
	 */
	public User login(String account, String login_password) throws AppException{	
		try {
			User user=userDao.login(account, login_password);
			return user;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.login");
		}
	}
	
	/**
	 * ��֤֧������
	 * @param id
	 * @param pay_password
	 * @return
	 * @throws AppException
	 */
	public boolean canPay(int id,String pay_password) throws AppException{
		boolean flag=false;
		try {
			flag=userDao.IsPay_Password(id, pay_password);
			return flag;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.canPay");
		}
		
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param id
	 * @return
	 * @throws AppException
	 */
	public User getInfoById(int id) throws AppException{
		User user=new User();
		try {
			user=userDao.getById(id);
			return user;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.getInfoById");
		}
	}
	
	/**
	 * �շ�
	 * @param id
	 * @param money
	 * @return
	 * @throws AppException 
	 */
	public float charge(int id,float money) throws AppException{
		try {
			if(userDao.Charge(id, money))
			{
				return userDao.getBalanceById(id);
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.charge");
		}
		return -1;
	}
	
	/**
	 * ����
	 * @param id
	 * @param money
	 * @return
	 * @throws AppException
	 */
	public float expense(int id,float money) throws AppException{
		try {
			if(userDao.Expense(id, money))
			{
				return userDao.getBalanceById(id);
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"dao.UserService.expense");
		}
		return -1;
	}
	
	
}
