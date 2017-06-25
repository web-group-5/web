package model;

public class Product {
	private int id;
	private int shop_id;
	private String num;
	private String name;
	private String description;
	private int price;
	private int quantity;
	private int variety;
	private boolean soldout;
	private String image;
	
	public Product(){
		id=0;
		shop_id=0;
		name="";
		num="";
		description="";
		price=0;
		quantity=0;
		variety=0;
		image="";
		soldout=false;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getShopId(){
		return shop_id;
	}
	
	public void setShopId(int shop_id){
		this.shop_id=shop_id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getNum(){
		return num;
	}
	
	public void setNum(String num){
		this.num=num;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setPrice(int price){
		this.price=price;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	
	public int getVariety(){
		return variety;
	}
	
	public void setVariety(int variety){
		this.variety=variety;
	}
	
	public boolean getIsSoldout(){
		return soldout;
	}
	
	public void setIsSoldout(boolean soldout){
		this.soldout=soldout;
	}

    public String getImage(){
    	return image;
    }
    
    public void setImage(String image){
    	this.image=image;
    }
}
