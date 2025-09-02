public class auction {
    private static final String ITEMS_FILE = "C:\\Users\\darsh\\OneDrive\\Documents\\auction[1]\\auction\\item";
    private static final String BIDS_FILE = "C:\\Users\\darsh\\OneDrive\\Documents\\auction[1]\\auction\\bids";
    public static class Item {
        private String name;
        private int category;
        private double startingPrice;
        private String certification;
        private double currentBid;
        private String currentBidder;

        public Item(String name, int category, double startingPrice, String certification) {
            this.name = name;
            this.category = category;
            this.startingPrice = startingPrice;
            this.certification = certification;
            this.currentBid = startingPrice;
            this.currentBidder = "";
        }

        public String getName() {
            return name;
        }
    
        public int getCategory() {
            return category;
        }
        public double getStartingPrice() {
            return startingPrice;
        }
        public String getCertification() {
            return certification;
        }
        public double getCurrentBid() {
            return currentBid;
        }
        public void setCurrentBid(double currentBid) {
            this.currentBid = currentBid;
        }
        public String getCurrentBidder() {
            return currentBidder;
        }
        public void setCurrentBidder(String currentBidder) {
            this.currentBidder = currentBidder;
        }

        // Calculate direct buy price for VIP 
        public double getDirectBuyPrice() {
            return startingPrice * 1.1;
        }
        // Check if item is certified
        public boolean isCertified() {
            return certification.equals("Certified");
        }
    }
    public static Item[] getAllItems() {
        int itemCount = countItems();
        Item[] items = new Item[itemCount > 0 ? itemCount : 1];

        try {
            java.io.File file = new java.io.File(ITEMS_FILE);
            
            // Create file if it doesn't exist
            if (!file.exists()) {
                createItemsFile();
                return getAllItems(); 
            }
            
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;

            // Skip the header
            reader.readLine();

            int index = 0;
            while ((line = reader.readLine()) != null && index < itemCount) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    items[index] = new Item(
                        data[0],
                        Integer.parseInt(data[1]),
                        Double.parseDouble(data[2]),
                        data[3]
                    );
                    index++;
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading items file: " + e.getMessage());
            e.printStackTrace();
        }

        return items;
    }
    // Create items file with header and sample items if it doesn't exist
    private static void createItemsFile() {
        try {
            java.io.File file = new java.io.File(ITEMS_FILE);
            
            file.getParentFile().mkdirs();
            
            java.io.FileWriter fw = new java.io.FileWriter(file);
            fw.write("Name,Category,StartingPrice,Certification");
            fw.write("\nVintage Watch,3,500.00,Certified");
            fw.write("\nSamsung TV,1,800.00,Not Certified");
            fw.write("\nAntique Chair,10,350.00,Certified");
            fw.write("\nMountain Bike,4,250.00,Not Certified");
            fw.write("\nGuitar,6,400.00,Certified");
            fw.close();
            
            System.out.println("Created new items file with sample items.");
        } catch (Exception e) {
            System.out.println("Error creating items file: " + e.getMessage());
        }
    }

    private static void createBidsFile() {
        try {
            java.io.File file = new java.io.File(BIDS_FILE);
            
            file.getParentFile().mkdirs();
            java.io.FileWriter fw = new java.io.FileWriter(file);
            fw.write("Item Name,Starting Price,Bid Amount,Price Sold At");
            fw.close();
            System.out.println("Created new bids file.");
        } catch (Exception e) {
            System.out.println("Error creating bids file: " + e.getMessage());
        }
    }
    // Count total items
    private static int countItems() {
        int count = 0;
        try {
            java.io.File file = new java.io.File(ITEMS_FILE);
            
            // Create file if it doesn't exist
            if (!file.exists()) {
                createItemsFile();
                return countItems(); // Recursive call after creating file
            }         
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                count++;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error counting items: " + e.getMessage());
        }
        return count;
    }
    // Get items by category
    public static Item[] getItemsByCategory(int category) {
        Item[] allItems = getAllItems();
        int categoryItemCount = 0;

        // Count items in this category
        for (Item item : allItems) {
            if (item.getCategory() == category) {
                categoryItemCount++;
            }
        }

        if (categoryItemCount == 0) {
            return new Item[0];
        }

        Item[] categoryItems = new Item[categoryItemCount];
        int index = 0;

        for (Item item : allItems) {
            if (item.getCategory() == category) {
                categoryItems[index++] = item;
            }
        }
        return categoryItems;
    }
    // Get items for black market (only certified items)
    public static Item[] getBlackMarketItems() {
        Item[] allItems = getAllItems();
        int certifiedItemCount = 0;
        for (Item item : allItems) {
            if (item.isCertified()) {
                certifiedItemCount++;
            }
        }
        // If no certified items, return empty array
        if (certifiedItemCount == 0) {
            return new Item[0];
        }
        // Create array for certified items
        Item[] certifiedItems = new Item[certifiedItemCount];
        int index = 0;
        for (Item item : allItems) {
            if (item.isCertified()) {
                certifiedItems[index++] = item;
            }
        }
        return certifiedItems;
    }
    // Place a bid
    public static boolean placeBid(String itemName, double bidAmount, String bidder) {
        if (bidAmount <= 0) {
            return false;
        }
        Item[] allItems = getAllItems();
        Item itemToBid = null;
        // Find the item
        for (Item item : allItems) {
            if (item.getName().equals(itemName)) {
                itemToBid = item;
                break;
            }
        }
        if (itemToBid == null) {
            return false; // Item not found
        }
        // Check if bid is higher than current bid
        double currentBid = getCurrentBid(itemName);
        if (bidAmount <= currentBid) {
            return false; // Bid is too low
        }

        // Update the bids file with new bid
        try {
            java.io.File file = new java.io.File(BIDS_FILE);
            if (!file.exists()) {
                createBidsFile();
            }          
            boolean exists = false;
            String[] bids = readBidsFile();
            // Check if item already exists in bids file
            for (int i = 0; i < bids.length; i++) {
                if (bids[i] != null && bids[i].startsWith(itemName + ",")) {
                    // Update existing bid
                    bids[i] = itemName + "," + itemToBid.getStartingPrice() + "," + bidAmount + "," + bidAmount;
                    exists = true;
                    break;
                }
            }
            // If not exists, add new bid
            if (!exists) {
                // Append to the bids array
                for (int i = 0; i < bids.length; i++) {
                    if (bids[i] == null) {
                        bids[i] = itemName + "," + itemToBid.getStartingPrice() + "," + bidAmount + "," + bidAmount;
                        break;
                    }
                }
            }
            writeBidsFile(bids);
            return true;
        } catch (Exception e) {
            System.out.println("Error placing bid: " + e.getMessage());
            return false;
        }
    }

    // Direct purchase for VIP users (and remove from items list)
    public static boolean directPurchase(String itemName, String buyer) {
        Item[] allItems = getAllItems();
        Item itemToBuy = null;
 
        for (Item item : allItems) {
            if (item.getName().equals(itemName)) {
                itemToBuy = item;
                break;
            }
        }
        if (itemToBuy == null) {
            return false; 
        }
        double directBuyPrice = itemToBuy.getDirectBuyPrice();

        // Update the bids file with direct purchase
        try {
            java.io.File file = new java.io.File(BIDS_FILE);
            // Create file if it doesn't exist
            if (!file.exists()) {
                createBidsFile();
            }
            
            boolean exists = false;
            String[] bids = readBidsFile();

            // Check if item already exists in bids file
            for (int i = 0; i < bids.length; i++) {
                if (bids[i] != null && bids[i].startsWith(itemName + ",")) {
                    // Update existing record with direct purchase
                    bids[i] = itemName + "," + itemToBuy.getStartingPrice() + "," + directBuyPrice + "," + directBuyPrice;
                    exists = true;
                    break;
                }
            }

            // If not exists, add new record
            if (!exists) {
                // Append to the bids array
                for (int i = 0; i < bids.length; i++) {
                    if (bids[i] == null) {
                        bids[i] = itemName + "," + itemToBuy.getStartingPrice() + "," + directBuyPrice + "," + directBuyPrice;
                        break;
                    }
                }
            }
            // Write back to file
            writeBidsFile(bids);
            // Remove item from items list
            removeItemFromList(itemName);
            return true;
        } catch (Exception e) {
            System.out.println("Error with direct purchase: " + e.getMessage());
            return false;
        }
    }
    // Handle removal after purchase
    public static boolean removeItemAfterPurchase(String itemName) {
        return removeItemFromList(itemName);
    }
    
    private static boolean removeItemFromList(String itemName) {
        try {
            Item[] allItems = getAllItems();
            java.io.FileWriter fw = new java.io.FileWriter(ITEMS_FILE, false);
            fw.write("Name,Category,StartingPrice,Certification");         
            boolean itemFound = false;
            for (Item item : allItems) {
                if (item.getName().equals(itemName)) {
                    itemFound = true;
                } else {
                    fw.write("\n" + item.getName() + "," + item.getCategory() + "," + 
                           item.getStartingPrice() + "," + item.getCertification());
                }
            }
            fw.close();
            if (itemFound) {
                System.out.println("Item removed from items list after direct purchase.");
            }
            return itemFound;
        } catch (Exception e) {
            System.out.println("Error removing item from list: " + e.getMessage());
            return false;
        }
    }

    // Get current bid information for an item
    public static double getCurrentBid(String itemName) {
        try {
            java.io.File file = new java.io.File(BIDS_FILE);
            if (!file.exists()) {
                createBidsFile();
                return 0.0;
            }
            String[] bids = readBidsFile();
            for (String bid : bids) {
                if (bid != null && bid.startsWith(itemName + ",")) {
                    String[] parts = bid.split(",");
                    if (parts.length >= 3) {
                        return Double.parseDouble(parts[2]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting current bid: " + e.getMessage());
        }

        Item[] allItems = getAllItems();
        for (Item item : allItems) {
            if (item.getName().equals(itemName)) {
                return item.getStartingPrice();
            }
        }
        return 0.0;
    }
    private static String[] readBidsFile() {
        int maxBids = 1000;
        String[] bids = new String[maxBids];
        try {
            java.io.File file = new java.io.File(BIDS_FILE);
            if (!file.exists()) {
                createBidsFile();
                return new String[maxBids];
            }
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;
            reader.readLine();
            int index = 0;
            while ((line = reader.readLine()) != null && index < maxBids) {
                bids[index++] = line;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading bids file: " + e.getMessage());
        }
        return bids;
    }
    private static void writeBidsFile(String[] bids) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(BIDS_FILE, false);
            fw.write("Item Name,Starting Price,Bid Amount,Price Sold At");
            for (String bid : bids) {
                if (bid != null) {
                    fw.write("\n" + bid);
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing bids file: " + e.getMessage());
        }
    }
    // Get all bids for admin view
    public static String[] getAllBids() {
        return readBidsFile();
    }
    
    public static boolean addItem(String name, int category, double price, String certification) {
        try {
            java.io.File file = new java.io.File(ITEMS_FILE);           
            if (!file.exists()) {
                createItemsFile();
            }       
            Item[] existingItems = getAllItems();
            for (Item item : existingItems) {
                if (item.getName().equals(name)) {
                    return false;
                }
            }          
            // Add new item
            java.io.FileWriter fw = new java.io.FileWriter(file, true);
            fw.write("\n" + name + "," + category + "," + price + "," + certification);
            fw.close();           
            return true;
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
            return false;
        }
    } 
    // update item for admin
    public static boolean updateItem(String name, int newCategory, double newPrice, String newCertification) {
        try {
            Item[] allItems = getAllItems();
            boolean itemFound = false;
            for (Item item : allItems) {
                if (item.getName().equals(name)) {
                    itemFound = true;
                    break;
                }
            }           
            if (!itemFound) {
                return false;
            }           
            java.io.FileWriter fw = new java.io.FileWriter(ITEMS_FILE, false);
            fw.write("Name,Category,StartingPrice,Certification");
            
            for (Item item : allItems) {
                if (item.getName().equals(name)) {
                    fw.write("\n" + name + "," + newCategory + "," + newPrice + "," + newCertification);
                } else {
                    fw.write("\n" + item.getName() + "," + item.getCategory() + "," + 
                           item.getStartingPrice() + "," + item.getCertification());
                }
            }         
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating item: " + e.getMessage());
            return false;
        }
    }  
    // Delete item (for admin)
    public static boolean deleteItem(String name) {
        return removeItemFromList(name);
    }
}