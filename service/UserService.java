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
					"service.UserService.isExist_account");
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
					"service.UserService.isExist_nickname");
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
					"service.UserService.register");
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
					"service.UserService.login");
		}
	}
	
	/**
	 * ��֤֧������
	 * @param id
	 * @param pay_password
	 * @return
	 * @throws AppException
	 */
	public boolean judgePay_password(int id,String pay_password) throws AppException{
		boolean flag=false;
		try {
			flag=userDao.IsPay_Password(id, pay_password);
			return flag;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"service.UserService.judgePay_password");
		}
	}
	
	public boolean judgeLogin_password(int id,String login_password) throws AppException{
		boolean flag=false;
		try {
			flag=userDao.IsLogin_Password(id, login_password);
			return flag;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"service.UserService.judgeLogin_password");
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
					"service.UserService.getInfoById");
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
				float balance=userDao.getBalanceById(id);
				return balance;
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"service.UserService.charge");
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
					"service.UserService.expense");
		}
		return -1;
	}
	
	/**
	 * ���µ�¼����
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean updateLogin_Password(int id,String old_password,String new_password) throws AppException{
		try {
			if(judgeLogin_password(id, old_password))
			{
				userDao.UpdateLogin_Password(id,new_password);
				return true;
			}else
				return false;
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"service.UserService.updateLogin_Password");
		}
	}
	
	/**
	 * ����֧������
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean updatePay_Password(int id,String old_password,String new_password) throws AppException{
		try {
			if(judgePay_password(id, old_password))
			{
				userDao.UpdatePay_Password(id,new_password);
				return true;
			}else
				return false;
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"service.UserService.updatePay_Password");
		}
	}
	
	/**
	 * �����û�������Ϣ
	 * name,sex,birthday,phone,email,address,nickname
	 * @param user
	 * @return
	 * @throws AppException
	 */
	public boolean updateUserInfo(User user) throws AppException{
		try {
			userDao.UpdateUserInfo(user);
			return true;
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"service.UserService.updateLogin_Password");
		}
	}
}
