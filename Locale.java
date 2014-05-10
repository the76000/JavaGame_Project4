
public class Locale {
	 //
    // Public
    //

    // Constructor
    public Locale(int id) {
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.name + "\n" + this.desc;
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
    public boolean getHasVisited() {
    	return this.hasVisited;
    }
    public void setHasVisited(boolean value){
    	this.hasVisited = value;
    }
    
    public Locale getGoNorth() {
    	return this.goNorth;
    }
    public void setGoNorth(Locale value) {
    	this.goNorth = value;
    }
    
    public Locale getGoSouth() {
    	return this.goSouth;
    }
    public void setGoSouth(Locale value) {
    	this.goSouth = value;
    }
    
    public Locale getGoEast() {
    	return this.goEast;
    }
    public void setGoEast(Locale value) {
    	this.goEast = value;
    }
    
    public Locale getGoWest() {
    	return this.goWest;
    }
    public void setGoWest(Locale value) {
    	this.goWest = value;
    }



    // Other methods
    @Override
    public String toString(){
        return "[Locale id="
                + this.id
                + " name="
                + this.name
                + " desc=" + this.desc
        		+ this.hasVisited
        		+ " hasVisited=" + this.hasVisited;
    }

    //
    //  Private
    //
    private int     id;
    private String  name;
    private String  desc;
    private boolean hasVisited = false;
    private Locale  goNorth = null;
    private Locale  goSouth = null;
    private Locale  goEast = null;
    private Locale  goWest = null;

}