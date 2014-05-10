import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Stack;

public class JavaGame {
//Global variables
	
	public static int dir = -1;
	public static float totalScore = 5;  
	public static float moveCounter = 0;
	public static Item inventory[];
	public static ArrayList<String> itemsFromSearch = new ArrayList<String>();
	public static String command; 
	public static boolean stillPlaying = true;
	public static boolean hasWon = false;
	public static Locale loc0;
	public static Locale loc1;
	public static Locale loc2;
	public static Locale loc3;
	public static Locale loc4;
	public static Locale loc5;
	public static Locale loc6;
	public static Locale loc7;
	public static Locale currentLocale;
	public static Item item0;
	public static Item item1;
	public static Item item2;
	public static Item item3;
	public static Item item4;
	public static Item item5;
	public static Item item6;
	//create a queue
	public static PriorityQueue<String> localeQueue = new PriorityQueue<String>();
	//create a stack
	public static Stack<String> localeStack = new Stack<String>();


	
	public static void main(String args[]){
		
		//Items
		Item item0 = new Item(0);
        item0.setName("Map");
        item0.setDesc("This is a map.");
        
        Item item1 = new Item(1);
        item1.setName("Message in a Bottle");
        item1.setDesc("It reads: 'Save me! I'm trapped in the castle!");
        
        Item item2 = new Item(2);
        item2.setName("Water Bottle");
        item2.setDesc("It's a bottle full of water");
        
        Item item3 = new Item(3);
        item3.setName("Daniel Craig's Head");
        item3.setDesc("It's so sweet.");
        
        Item item4 = new Item(4);
        item4.setName("Magic Sword");
        item4.setDesc("It can slay Daniel Craig.");
        
        Item item5 = new Item(5);
        item5.setName("Magic Shield");
        item5.setDesc("It can block lasers.");
        
        Item item6 = new Item(6);
        item6.setName("Gold");
        item6.setDesc("It's shiny");
        item6.setAmount(1000);
        
        
        inventory = new Item[7];
		inventory[0] = item0; 
	    inventory[1] = item1;
		inventory[2] = item2;
		inventory[3] = item3;
		inventory[4] = item4;
		inventory[5] = item5;
		inventory[6] = item6;
		
		//Locations
    		loc0 = new Locale(0);
   	        loc0.setName("Town");
   	        loc0.setDesc("This is your home town.");
    	    loc0.setHasVisited(true);    
    	       
   	        loc1 = new Locale(1);
   	        loc1.setName("Magick Shoppe");
   	        loc1.setDesc("It's full of wonders.");
    	    loc1.setHasVisited(false); 
    	    
    	    loc2 = new Locale(2);
    	    loc2.setName("Ocean");
    	    loc2.setDesc("There is a message in a bottle.");
    	    loc2.setHasVisited(false); 
    	    
    	    loc3 = new Locale(3);
            loc3.setName("Gold Mine");
   	        loc3.setDesc("There are tons of gold coins.");
    	    loc3.setHasVisited(false); 
    	    
   	        EarthsCore loc4 = new EarthsCore(4);
   	        loc4.setName("Desert");
   	        loc4.setDesc("It is hot. What did you expect?");
   	        loc4.setDistance(3);
    	    loc4.setHasVisited(false); 
    	    
            loc5 = new Locale(5);
            loc5.setName("Plains");
   	        loc5.setDesc("This is a lot of grass.");
    	    loc5.setHasVisited(false); 
    	    
   	        EarthsCore loc6 = new EarthsCore(6);
   	        loc6.setName("Glacier");
   	        loc6.setDesc("It is cold. What did you expect?");    	      
   	        loc6.setDistance(-3);
   	        loc6.setHasVisited(false); 
    	        
    	    loc7 = new Locale(7);
    	    loc7.setName("Castle");
            loc7.setDesc("There is nothing to do here yet.");
    	    loc7.setHasVisited(false); 
            
  	     
  	      //Linked List Navigation
    	    currentLocale = loc0;
   	        loc0.setGoNorth(loc1);
   	        loc0.setGoSouth(null);
   	        loc0.setGoEast(null);
    	    loc0.setGoWest(null);
    	        
            loc1.setGoNorth(null);
    	    loc1.setGoSouth(loc0);
    	    loc1.setGoEast(loc3);
            loc1.setGoWest(loc2);
    	        
   	        loc2.setGoNorth(null);
   	        loc2.setGoSouth(null);
   	        loc2.setGoEast(loc1);
   	        loc2.setGoWest(null);
    	        
    	    loc3.setGoNorth(loc4);
    	    loc3.setGoSouth(null);
            loc3.setGoEast(null);
   	        loc3.setGoWest(loc1);
    	        
    	    loc4.setGoNorth(loc6);
    	    loc4.setGoSouth(loc3);
            loc4.setGoEast(null);
   	        loc4.setGoWest(loc5);
    	        
   	        loc5.setGoNorth(loc7);
   	        loc5.setGoSouth(null);
   	        loc5.setGoEast(loc4);
   	        loc5.setGoWest(null);
    	        
   	        loc6.setGoNorth(null);
    	    loc6.setGoSouth(loc4);
            loc6.setGoEast(null);
   	        loc6.setGoWest(loc7);
    	        
   	        loc7.setGoNorth(null);
   	        loc7.setGoSouth(loc5);
   	        loc7.setGoEast(loc6);
   	        loc7.setGoWest(null);
   	        
   	        


	updateDisplay();
		
		while(stillPlaying){
		   getCommand();
		   System.out.println("\n");
		   if(!stillPlaying){
			   break;
		   }
		   updateDisplay();
		}
		
		if(hasWon){
			System.out.println("Would you like to view your moves forwards or backwards?");
			getCommand();
		}
	}
	
	public static void updateDisplay() {
		   System.out.println(currentLocale.getName() + ": " + currentLocale.getDesc()); //location name and description
		   System.out.println("Number of moves: " + moveCounter + ", " + "Score: " + totalScore);  //number of moves and score
		   if (totalScore != 0 && moveCounter != 0){
		   System.out.println("Achievement Ratio: " + moveCounter/totalScore); // Achievement ratio displayed as float
		   }
		   if (currentLocale.getGoNorth() != null){
			   System.out.print("You can go north. ");
		   }
		   if (currentLocale.getGoSouth() != null){
			   System.out.print("You can go south. ");
		   }
		   if (currentLocale.getGoEast() != null){
			   System.out.print("You can go east. ");
		   }
		   if (currentLocale.getGoWest() != null){
			   System.out.println("You can go West. ");
		   }
		   if (currentLocale == loc4 ){
			   System.out.println(((EarthsCore) currentLocale).getDistance() + " feet from the Earth's Core. No wonder it's so hot.");
		   } else if (currentLocale == loc6){
			   System.out.println(((EarthsCore) currentLocale).getDistance() + " feet from the Earth's Core. No wonder it's so cold.");
		   } 
		   
		   
	 }
	 
	
	public static void getCommand() {
	     @SuppressWarnings("resource")
		Scanner inputReader = new Scanner(System.in);
	      command = inputReader.nextLine();  // command is global.
	      setDir();
	   }
	
	public static void setDir() {
		
		 if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {
         moveCounter = moveCounter + 1;
         localeQueue.offer(currentLocale.getName());
         localeStack.push(currentLocale.getName());
         move(0);
     
	
	 } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {
         moveCounter = moveCounter + 1;
         localeQueue.offer(currentLocale.getName());
         localeStack.push(currentLocale.getName());
         move(1);
     
	 
	 } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {
         moveCounter = moveCounter + 1;
         localeQueue.offer(currentLocale.getName());
         localeStack.push(currentLocale.getName());
         move(2);
     
	 
	 } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {
         moveCounter = moveCounter + 1;
         localeQueue.offer(currentLocale.getName());
         localeStack.push(currentLocale.getName());
         move(3); 
	
	 
	 } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q") ) {
         System.out.println("You quit loser.");
         stillPlaying = false;
     
	
	 } else if ( command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")){
    	  System.out.println("You can type north, south, east, west, quit, inventory, map, take, laser, kick, fight.");
     
	
	 } else if ( command.equalsIgnoreCase("map")  || command.equalsIgnoreCase("m")) {
    	  if (inventory[0].gethasItem() == true){
		  System.out.println("           ------      ------");
    	  System.out.println("           |    |      |    |");
    	  System.out.println("           |7   |------|6   |");
    	  System.out.println("           |    |      |    |");
    	  System.out.println("           ------      ------");
    	  System.out.println("             |           |   ");
    	  System.out.println("             |           |   ");
    	  System.out.println("           ------      ------");
    	  System.out.println("           |    |      |    |");
    	  System.out.println("           |5   |------|4   |");
    	  System.out.println("           |    |      |    |");
    	  System.out.println("           ------      ------");
    	  System.out.println("                         |   ");
    	  System.out.println("                         |   ");
    	  System.out.println("------     ------      ------");
    	  System.out.println("|    |     |    |      |    |");
    	  System.out.println("|2   |-----|1   |------|3   |");
    	  System.out.println("|    |     |    |      |    |");
    	  System.out.println("------     ------      ------");
    	  System.out.println("             |               ");
    	  System.out.println("             |               ");
    	  System.out.println("           ------            ");
    	  System.out.println("           |    |            ");
    	  System.out.println("           |0   |            ");
    	  System.out.println("           |    |            ");
    	  System.out.println("           ------");
    	  System.out.println("You are at " + currentLocale.getName());
    	  }
	 
	 
	 } else if ( command.equalsIgnoreCase("buy") || command.equalsIgnoreCase("b")){
		 if (currentLocale == loc1) {
				listSearch();
		 	}
	
	 } else if (command.equalsIgnoreCase("take") || command.equalsIgnoreCase("t")){
		
		if (currentLocale == loc0 && inventory[0].gethasItem() == false){
			inventory[0].sethasItem(true);
		 }
		 if (currentLocale == loc1 && inventory[4].gethasItem() == false && inventory[5].gethasItem() == false){
				inventory[4].sethasItem(true);
				inventory[5].sethasItem(true);
				loc7.setDesc("Quick, take out Daniel Craig!!!");
		 }
		 if (currentLocale == loc2 && inventory[1].gethasItem() == false){
				inventory[1].sethasItem(true);
				loc2.setDesc("The sound of the waves are calming.");
		 }
	 
	
	 } else if (command.equalsIgnoreCase("gold") || command.equalsIgnoreCase("g")){
		 	inventory[6].sethasItem(true);
		 		if (currentLocale == loc3){
		 			inventory[6].setAmount(inventory[6].getAmount() + 500);
		 		}
		 		
	 } else if ( command.equalsIgnoreCase("inventory") || command.equalsIgnoreCase("i")){
		 System.out.println("Bought Items:" + itemsFromSearch);

		 
		 if (inventory[0].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[0].getName() + ": " + inventory[0].getDesc());
		 }
		 if (inventory[1].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[1].getName() + ": " + inventory[1].getDesc());
		 }
		 if (inventory[2].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[2].getName() + ": " + inventory[2].getDesc());
		 } 
		 if (inventory[3].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[3].getName() + ": " + inventory[3].getDesc());
		 }
		 if (inventory[4].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[4].getName() + ": " + inventory[4].getDesc());
		 }
		 if (inventory[5].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[5].getName() + ": " + inventory[5].getDesc());
		 }
		 if (inventory[6].gethasItem() == true){
			 System.out.println("Found Items: " + inventory[6].getName() + ": " + inventory[6].getDesc() + ": " + inventory[6].getAmount());
		 }
	 
		 
		 	 
	 } else if ( command.equalsIgnoreCase("fight") || command.equalsIgnoreCase("f")){
		 	if ( currentLocale == loc7 && inventory[4].gethasItem() == false){
		 		System.out.println("You lose!! Why would you do that?");
		 		stillPlaying = false;
		 	} else if (currentLocale == loc7 && inventory[4].gethasItem() == true){
		 		System.out.println("You win!! You saved the James Bond Series!");
		 		stillPlaying = false;
		 		inventory[3].sethasItem(true);
		 		hasWon = true;
		 	} else if (inventory[4].gethasItem() == true && currentLocale != loc7){
		 		System.out.println("There's nothing to fight.");
		 	} else if (inventory[4].gethasItem() == false){
		 		System.out.println("You have nothing to fight with.");
		 	}
		 
	 
	 } else if (command.equalsIgnoreCase("laser") || command.equalsIgnoreCase("l")){
		 	System.out.println("You fired lasers out of your eyes.");	
	 
	 } else if(command.equalsIgnoreCase("forwards") || command.equalsIgnoreCase("forward")){
		 	System.out.printf("%s ", localeQueue);
	 
	 } else if(command.equalsIgnoreCase("backwards") || command.equalsIgnoreCase("backward")){
		 	printStack(localeStack);
	 }
  }
	 
		  
	 
	
    
	public static void move(int dir){  
     
    if (dir == 0){
    	if (currentLocale.getGoNorth() != null){
    		currentLocale = currentLocale.getGoNorth();
    		
    		if (currentLocale.getHasVisited() == false){
    			totalScore = totalScore + 5;
    			currentLocale.setHasVisited(true);
    		}
    	} else {
    			System.out.println("You cannot go that way.");
    	}

      } else if (dir == 1) {
    	 if (currentLocale.getGoSouth() != null) {
    	        currentLocale = currentLocale.getGoSouth();
    	    
    	        
    	 } else {
    		 	System.out.println("You cannot go that way.");
    	 } 
    	 
      } else if (dir == 2) {
    	 if (currentLocale.getGoEast() != null){
    		  	currentLocale = currentLocale.getGoEast();
    
    		  	
    	 } else {
    		 	System.out.println("You cannot go that way.");
    	 }
      
      } else if (dir == 3) {
    	 if (currentLocale.getGoWest() != null) {
    		  currentLocale = currentLocale.getGoWest();
  
    		  
    	 } else {
    		 	System.out.println("You cannot go that way.");
      }
    	 
      }
	}

	 public static void listSearch() {

	        // Make the list manager.
	        ListManager lm1 = new ListManager();
	        lm1.setName("Magic Items");
	        lm1.setDesc("These are some of my favorite things.");

	        final String fileName = "magicitems.txt";

	        readMagicItemsFromFileToList(fileName, lm1);
	        // Display the list of items.
	        // System.out.println(lm1.toString());

	        // Declare an array for the items.
	        ListItem[] items = new ListItem[lm1.getLength()];
	        readMagicItemsFromFileToArray(fileName, items);
	        // Display the array of items.
	      

	        selectionSort(items);

	        // Ask player for an item.
	        Scanner inputReader = new Scanner(System.in);
	        System.out.print("What item would you like? ");
	        String targetItem = new String();
	        targetItem = inputReader.nextLine();
	        System.out.println();

	        ListItem li = new ListItem();
	        li = binarySearchArray(items, targetItem);
	    }

	    //
	    // Private
	    //

	    private static ListItem binarySearchArray(ListItem[] items,
	                                              String target) {
	        ListItem retVal = null;
	        System.out.println("Binary Searching for " + target + ".");
	        ListItem currentItem = new ListItem();
	        boolean isFound = false;
	        int counter = 0;
	        int low  = 0;
	        int high = items.length-1; // because 0-based arrays
	        while ( (!isFound) && (low <= high)) {
	            int mid = Math.round((high + low) / 2);
	            currentItem = items[mid];
	            if (currentItem.getName().equalsIgnoreCase(target)) {
	                // We found it!
	                isFound = true;
	                retVal = currentItem;
	            } else {
	                // Keep looking.
	                counter++;
	                if (currentItem.getName().compareToIgnoreCase(target) > 0) {
	                    // target is higher in the list than the currentItem (at mid)
	                    high = mid - 1;
	                } else {
	                    // target is lower in the list than the currentItem (at mid)
	                    low = mid + 1;
	                }
	            }
	        }
	        if (isFound) {
	            System.out.println("Found " + target + " after " + counter + " comparisons.");
	            itemsFromSearch.add(currentItem.getName());
	            currentItem.setHasFound(true);
           		inventory[6].setAmount(inventory[6].getAmount() - (int)currentItem.getCost());
	        } else {
	            System.out.println("Could not find " + target + " in " + counter + " comparisons.");
	        }
	        return retVal;
	    }

	    private static ListItem sequentialSearchList(ListManager lm,
	                                                 String target) {
	        ListItem retVal = null;
	        System.out.println("Searching for " + target + ".");
	        int counter = 0;
	        ListItem currentItem = new ListItem();
	        currentItem = lm.getHead();
	        boolean isFound = false;
	        while ( (!isFound) && (currentItem != null) ) {
	            counter = counter +1;
	            if (currentItem.getName().equalsIgnoreCase(target)) {
	                // We found it!
	                isFound = true;
	                retVal = currentItem;
	            } else {
	                // Keep looking.
	                currentItem = currentItem.getNext();
	            }
	        }
	        if (isFound) {
	            System.out.println("Found " + target + " after " + counter + " comparisons.");
	        } else {
	            System.out.println("Could not find " + target + " in " + counter + " comparisons.");
	        }
	        return retVal;
	    }


	    private static void readMagicItemsFromFileToList(String fileName,
	                                                     ListManager lm) {
	        File myFile = new File(fileName);
	        try {
	            Scanner input = new Scanner(myFile);
	            while (input.hasNext()) {
	                // Read a line from the file.
	                String itemName = input.nextLine();

	                // Construct a new list item and set its attributes.
	                ListItem fileItem = new ListItem();
	                fileItem.setName(itemName);
	                fileItem.setCost(Math.random() * 100);
	                fileItem.setNext(null); // Still redundant. Still safe.

	                // Add the newly constructed item to the list.
	                lm.add(fileItem);
	            }
	            // Close the file.
	            input.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println("File not found. " + ex.toString());
	        }

	    }

	    private static void readMagicItemsFromFileToArray(String fileName,
	                                                      ListItem[] items) {
	        File myFile = new File(fileName);
	        try {
	            int itemCount = 0;
	            Scanner input = new Scanner(myFile);

	            while (input.hasNext() && itemCount < items.length) {
	                // Read a line from the file.
	                String itemName = input.nextLine();

	                // Construct a new list item and set its attributes.
	                ListItem fileItem = new ListItem();
	                fileItem.setName(itemName);
	                fileItem.setCost(Math.random() * 100);
	                fileItem.setNext(null); // Still redundant. Still safe.

	                // Add the newly constructed item to the array.
	                items[itemCount] = fileItem;
	                itemCount = itemCount + 1;
	            }
	            // Close the file.
	            input.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println("File not found. " + ex.toString());
	        }
	    }

	    private static void selectionSort(ListItem[] items) {
	        for (int pass = 0; pass < items.length-1; pass++) {
	            // System.out.println(pass + "-" + items[pass]);
	            int indexOfTarget = pass;
	            int indexOfSmallest = indexOfTarget;
	            for (int j = indexOfTarget+1; j < items.length; j++) {
	                if (items[j].getName().compareToIgnoreCase(items[indexOfSmallest].getName()) < 0) {
	                    indexOfSmallest = j;
	                }
	            }
	            ListItem temp = items[indexOfTarget];
	            items[indexOfTarget] = items[indexOfSmallest];
	            items[indexOfSmallest] = temp;
	        }
	    }
   private static void printStack(Stack<String> stack){
	   if(localeStack.isEmpty()){
		   System.out.println("It's empty.");
	   } else {
		   System.out.printf("%s TOP\n", stack);
	   }
   }
}





	    