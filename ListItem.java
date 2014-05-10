public class ListItem {

    //
    // Public
    //
    public ListItem() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public boolean getHasFound(){
    	return hasFound;
    }
    public void setHasFound(boolean hasFound){
    	this.hasFound = hasFound;
    }

    public ListItem getNext() {
        return next;
    }
    public void setNext(ListItem next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[ListItem name=" + this.name
                + " desc=" + this.desc
                + " cost=" + this.cost
                + " hasFound=" + this.hasFound;
    }

    //
    // Private
    //
    private String name;
    private String desc;
    private double cost;
    private ListItem next = null;
    private boolean hasFound = false;
}