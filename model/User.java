package model;

import java.sql.Date;

public class User {
	private int id;
	private String nickname;
	private String name;
	private String account;
	private String login_password;
	private String pay_password;
	private String sex;
	private Date birthday;
	private String phone;
	private String email;
	private String address;
	private int balance;
	private boolean isShoper;
	
	public User(){
		id=0;
		nickname="";
		name="";
		account="";
		login_password="";
		pay_password="";
		sex="";
		birthday=null;
		phone="";
		email="";
		address="";
		balance=0;
		isShoper=false;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public String getNickName(){
		return nickname;
	}
	
	public void setNickName(String nickname){
		this.nickname=nickname;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getAccount(){
		return account;
	}
	
	public void setAccount(String account){
		this.account=account;
	}
	
	public String getLogin_Password(){
		return login_password;
	}
	
	public void setLogin_Password(String login_password){
		this.login_password=login_password;
	}
	
	public String getPay_Password(){
		return pay_password;
	}
	
	public void setPay_Password(String pay_password){
		this.pay_password=pay_password;
	}
	
	public String getSex(){
		return sex;
	}
	
	public void setSex(String sex){
		this.sex=sex;
	}
	
	public Date getBirthday(){
		return birthday;
	}
	
	public void setBirthday(Date birthday){
		this.birthday=birthday;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address=address;
	}

	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance=balance;
	}
	
	public boolean getIsShoper(){
		return isShoper;
	}
	
	public void setIsShoper(boolean isShoper){
		this.isShoper=isShoper;
	}
}
