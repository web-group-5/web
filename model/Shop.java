package model;

public class Shop {
	private int id;
	private int user_id;
	private String name;
	private String description;
	private boolean isOpen;
	
	public Shop(){
		id=0;
		user_id=0;
		name="";
		description="";
		isOpen=false;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getUserId(){
		return user_id;
	}
	
	public void setUserId(int user_id){
		this.user_id=user_id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public boolean getIsOpen(){
		return isOpen;
	}
	
	public void SetIsOpen(boolean isOpen){
		this.isOpen=isOpen;
	}
}
