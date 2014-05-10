
public class Item {
	
	public Item(int id){
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String value) {
        this.desc = value;
    }
    
    public boolean gethasItem(){
    	return this.hasItem;
    }
    public void sethasItem(boolean value){
    	this.hasItem = value;
    }
    
    public int getAmount(){
    	return this.amount;
    }
    public void setAmount(int value){
    	this.amount = value;
    }
   
    
    @Override
    public String toString(){
        return "[Item id="
                + this.id
                + " name="
                + this.name
                + " desc=" + this.desc;
    }

private int id;	
private String name;
private String desc;
private boolean hasItem = false;
private int amount;
}