package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private int user_id;
	private float total_price;
	private String status;
	private Timestamp TradeTime;
	private List<OrderItem> orderItems;
	
	public Order(){
		id=0;
		user_id=0;
		total_price=0;
		status="";
		TradeTime=null;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getUser_id(){
		return user_id;
	}
	
	public void SetUser_id(int user_id){
		this.user_id=user_id;
	}
	
	public float getTotal_price(){
		return total_price;
	}
	
	public void setPrice(float total_price){
		this.total_price=total_price;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status=status;
	}
	
	public Timestamp getTradeTime(){
		return TradeTime;
	}
	
	public void setTradeTime(Timestamp TradeTime){
		this.TradeTime=TradeTime;
	}
	
	public List<OrderItem> getOrderItems(){
		return orderItems;
	}
	
	public void setOrderItems(List<OrderItem> list){
		this.orderItems=list;
	}
}
