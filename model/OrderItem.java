package model;

public class OrderItem {
	private int order_id;   //����id
	private int user_id;    //���id
	private int product_id; //��Ʒid
	private String description;
	private float price;
	private String image;
	private int quantity;   //����
	
	public OrderItem(){
		order_id=0;
		user_id=0;
		product_id=0;
		quantity=0;
	}
	
	public int getOrder_id(){
		return order_id;
	}
	
	public void setOrder_id(int order_id){
		this.order_id=order_id;
	}
	
	public int getUser_id(){
		return user_id;
	}
	
	public void setUser_id(int user_id){
		this.user_id=user_id;
	}
	
	public int getProduct_id(){
		return product_id;
	}
	
	public void setProduct_id(int product_id){
		this.product_id=product_id;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public String getImage(){
		return image;
	}
	
	public void setImage(String image){
		this.image=image;
	}
	
	public float getPrice(){
		return price;
	}
	
	public void setPrice(float price){
		this.price=price;
	}
}
