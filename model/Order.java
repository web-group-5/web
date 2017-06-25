package model;

import java.util.ArrayList;

public class Order {
	private int id;
	private int customer_id;
	private int total_price;
	private boolean isPay;
	private ArrayList<OrderItem> orders;
	
	public Order(){
		id=0;
		customer_id=0;
		total_price=0;
		isPay=false;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getCustomer_id(){
		return customer_id;
	}
	
	public void SetCustomer_id(int customer_id){
		this.customer_id=customer_id;
	}
	
	public int getTotal_price(){
		return total_price;
	}
	
	public void addPrice(int price){
		this.total_price+=price;
	}
	
	public boolean getIsPay(){
		return isPay;
	}
	
	public void setIsPay(boolean isPay){
		this.isPay=isPay;
	}
	
	public ArrayList<OrderItem> getOrders(){
		return orders;
	}
	
	public void addOrders(OrderItem orderItem){
		orders.add(orderItem);
	}
}
