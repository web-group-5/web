package model;

public class OrderItem {
	private int product_id;
	private int quantity;
	
	public OrderItem(){
		product_id=0;
		quantity=0;
	}
	
	public int getProduct_id(){
		return product_id;
	}
	
	public void getProduct_id(int product_id){
		this.product_id=product_id;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void getQuantity(int quantity){
		this.quantity=quantity;
	}
}
