
public class EarthsCore extends Locale {
	   //
	   // -- PRIVATE --
	   //

	   // Constructor
	   public EarthsCore(int id) {
	      super(id);
	   }

	   // Getters and Setters
	   public void setDistance(int value) {
	      this.distance = value;
	   }
	   public int getDistance() {
	      return this.distance;
	   }

	   // Other methods
	   @Override
	   public String toString() {
	      return super.toString() + ".  At this location, you are " + this.distance + " feet from the Earth's Core!";
	   }


	   //
	   // -- PRIVATE --
	   //
	   private int distance;
}