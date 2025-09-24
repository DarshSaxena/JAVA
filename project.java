import java.io.*;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public String toCSV() {
        return username + "," + password + "," + role;
    }
}

class UserData {
    private static final String USERS_FILE = "users.csv";
    private User[] users;
    private int userCount;

    public UserData() {
        users = new User[100]; // Assuming a maximum of 100 users
        userCount = 0;
        loadUsersFromCSV();
    }

    public void loadUsersFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users[userCount++] = new User(parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void saveUsersToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (int i = 0; i < userCount; i++) {
                writer.write(users[i].toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public User authenticateUser (String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }

    public boolean usernameExists(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void addUser (User user) {
        if (userCount < users.length) {
            users[userCount++] = user;
            saveUsersToCSV();
        } else {
            System.out.println("User  limit reached.");
        }
    }

    public User[] getAllUsers() {
        User[] result = new User[userCount];
        for (int i = 0; i < userCount; i++) {
            result[i] = users[i];
        }
        return result;
    }

    public void deleteUser (String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                // Shift users to remove the deleted user
                for (int j = i; j < userCount - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[--userCount] = null; // Clear the last user
                saveUsersToCSV();
                return;
            }
        }
        System.out.println("User  not found.");
    }
}

class Item {
    private String name;
    private String category;
    private double startingPrice;
    private double currentBid;
    private boolean isCertified;
    private boolean isSold;

    public Item(String name, String category, double startingPrice, boolean isCertified) {
        this.name = name;
        this.category = category;
        this.startingPrice = startingPrice;
        this.currentBid = startingPrice;
        this.isCertified = isCertified;
        this.isSold = false;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getCurrentBid() { return currentBid; }
    public boolean isCertified() { return isCertified; }
    public boolean isSold() { return isSold; }

    public void setCurrentBid(double bid) { this.currentBid = bid; }
    public void setSold(boolean sold) { this.isSold = sold; }

    public String toCSV() {
        return name + "," + category + "," + startingPrice + "," + (isCertified ? "Certified" : "Non-Certified");
    }
}

class Auction {
    private static final String ITEMS_FILE = "items.csv";
    private Item[] items;
    private int itemCount;

    public Auction() {
        items = new Item[100]; // Assuming a maximum of 100 items
        itemCount = 0;
        loadItemsFromCSV();
    }

    public void loadItemsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String category = parts[1];
                    double startingPrice = Double.parseDouble(parts[2]);
                    boolean isCertified = parts[3].equals("Certified");
                    items[itemCount++] = new Item(name, category, startingPrice, isCertified);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
    }

    public void saveItemsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEMS_FILE))) {
            for (int i = 0; i < itemCount; i++) {
                writer.write(items[i].toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    public Item[] getItemsByCategory(String category, boolean blackMarketOnly) {
        Item[] result = new Item[itemCount];
        int count = 0;
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getCategory().equals(category) && !items[i].isSold() && (!blackMarketOnly || (blackMarketOnly && items[i].isCertified()))) {
                result[count++] = items[i];
            }
        }
        // Resize the result array to the actual number of items found
        Item[] finalResult = new Item[count];
        System.arraycopy(result, 0, finalResult, 0, count);
        return finalResult;
    }

    public void placeBid(String itemName, double amount) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getName().equals(itemName) && !items[i].isSold()) {
                if (amount > items[i].getCurrentBid()) {
                    items[i].setCurrentBid(amount);
                    System.out.println("Bid placed successfully on " + itemName + " for $" + amount);
                } else {
                    System.out.println("Bid must be higher than the current bid.");
                }
                return;
            }
        }
        System.out.println("Item not found or already sold.");
    }

    public void buyDirectly(String itemName, double amount) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getName().equals(itemName) && !items[i].isSold()) {
                items[i].setSold(true);
                System.out.println("Item " + itemName + " purchased directly for $" + amount);
                return;
            }
        }
        System.out.println("Item not found or already sold.");
    }

    public Item[] getAllItems() {
        Item[] result = new Item[itemCount];
        System.arraycopy(items, 0, result, 0, itemCount);
        return result;
    }
}

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static UserData userData = new UserData();
    private static Auction auction = new Auction();
    private static User currentUser  = null;

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== AUCTION SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thank you for using the Auction System!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser  = userData.authenticateUser (username, password);
        if (currentUser  != null) {
            System.out.println("Login successful! Welcome, " + currentUser .getUsername() + "!");
            if (currentUser .getRole().equals("Admin")) {
                showAdminMenu();
            } else {
                showUser Menu();
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        if (userData.usernameExists(username)) {
            System.out.println("Username already exists. Please choose another.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String role = "Normal"; // Default role
        User newUser  = new User(username, password, role);
        userData.addUser (newUser );
        System.out.println("Registration successful! You can now login.");
    }

    private static void showUser Menu() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. View Marketplace");
            if (currentUser .getRole().equals("VIP")) {
                System.out.println("2. View Black Market");
                System.out.println("3. Buy Directly");
            }
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    viewMarketplace();
                    break;
                case 2:
                    if (currentUser .getRole().equals("VIP")) {
                        viewBlackMarket();
                    } else {
                        System.out.println("Access denied.");
                    }
                    break;
                case 3:
                    if (currentUser .getRole().equals("VIP")) {
                        buyDirectly();
                    } else {
                        System.out.println("Access denied.");
                    }
                    break;
                case 4:
                    logout = true;
                    currentUser  = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void viewMarketplace() {
        System.out.println("\n===== MARKETPLACE =====");
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        Item[] items = auction.getItemsByCategory(category, false);
        displayItems(items);
        placeBid(items);
    }

    private static void viewBlackMarket() {
        System.out.println("\n===== BLACK MARKET =====");
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        Item[] items = auction.getItemsByCategory(category, true);
        displayItems(items);
        placeBid(items);
    }

    private static void displayItems(Item[] items) {
        if (items.length == 0) {
            System.out.println("No items available in this category.");
            return;
        }
        System.out.printf("%-20s %-10s %-15s%n", "Item Name", "Current Bid", "Certified");
        for (Item item : items) {
            System.out.printf("%-20s $%-10.2f %-15s%n", item.getName(), item.getCurrentBid(), item.isCertified() ? "Yes" : "No");
        }
    }

    private static void placeBid(Item[] items) {
        System.out.print("Enter item name to bid on: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter your bid amount: $");
        double amount = Double.parseDouble(scanner.nextLine());
        auction.placeBid(itemName, amount);
    }

    private static void buyDirectly() {
        System.out.print("Enter item name to buy directly: ");
        String itemName = scanner.nextLine();
        double buyPrice = auction.getItemsByCategory(itemName, false)[0].getCurrentBid() * 1.1; // 10% premium
        auction.buyDirectly(itemName, buyPrice);
    }

    private static void showAdminMenu() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Manage Users");
            System.out.println("2. View All Auctions");
            System.out.println("3. Monitor Bids");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageUsers();
                    break;
                case 2:
                    viewAllAuctions();
                    break;
                case 3:
                    monitorBids();
                    break;
                case 4:
                    logout = true;
                    currentUser  = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageUsers() {
        System.out.println("\n===== MANAGE USERS =====");
        User[] allUsers = userData.getAllUsers();
        for (User  user : allUsers) {
            System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole());
        }
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();
        userData.deleteUser (username);
        System.out.println("User  deleted successfully.");
    }

    private static void viewAllAuctions() {
        System.out.println("\n===== VIEW ALL AUCTIONS =====");
        Item[] allItems = auction.getAllItems();
        for (Item item : allItems) {
            System.out.println("Item: " + item.getName() + ", Current Bid: $" + item.getCurrentBid());
        }
    }

    private static void monitorBids() {
        // Implement bid monitoring logic here
        System.out.println("Bid monitoring feature is not implemented yet.");
    }
}