class InventoryList{
    class InventoryNode{
        String itemName;
        String itemID;
        int quantity;
        double price;
        InventoryNode next;

        InventoryNode(String itemName, String itemID, int quantity, double price){
            this.itemName = itemName;
            this.itemID = itemID;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    private InventoryNode head = null;

    // Add item at the beginning
    public void addAtBeginning(String itemName, String itemID, int quantity, double price){
        InventoryNode inventory = new InventoryNode(itemName, itemID, quantity, price);
        inventory.next = head;
        head = inventory;
    }
    // Add item at the end
    public void add(String itemName, String itemID, int quantity, double price){

        InventoryNode inventory = new InventoryNode(itemName, itemID, quantity, price);

        if(head==null){
            head = inventory;
            return;
        }

        InventoryNode temp = head;

        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = inventory;
    }

    // Add item at a specific position
    public void addAtPosition(String itemName, String itemID, int quantity, double price, int position){

        InventoryNode inventory = new InventoryNode(itemName, itemID, quantity, price);

        if(position==1){
            addAtBeginning(itemName, itemID, quantity, price);
            return;
        }

        InventoryNode temp = head;

        for(int i=0; temp!=null && i<position-1; i++){
            temp = temp.next;
        }

        if(temp.next==null){
            System.out.println("Position out of range");
            return;
        }
        inventory.next = temp.next;
        temp.next = inventory;
    }

    // Remove item based on Item ID
    public void removeItem(String itemID) {
        if (head == null) return;

        if(head.itemID.equals(itemID)){
            head = head.next;
            return;
        }

        InventoryNode temp  = head;

        while(temp.next!=null && !temp.next.itemID.equals(itemID)){
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Item not found.");
            return;
        }
        temp.next = temp.next.next;
    }
    public void updateQuantity(String itemID, int newQuantity) {
        InventoryNode temp = head;
        while (temp != null) {
            if (temp.itemID.equals(itemID)) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    // Search for an item by ID or Name
    public void searchItem(String keyword) {
        InventoryNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.itemID.equals(keyword) || temp.itemName.equalsIgnoreCase(keyword)) {
                System.out.println("Item Found: " + temp.itemName + " (ID: " + temp.itemID + "), Quantity: " + temp.quantity + ", Price: Rs." + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Calculate total value of inventory
    public double totalInventoryValue() {
        double totalValue = 0;
        InventoryNode temp = head;
        while (temp != null) {
            totalValue += temp.price * temp.quantity;
            temp = temp.next;
        }
        return totalValue;
    }

    // Sort inventory by item name
    public void sortByName() {
        head = mergeSort(head, "name");
    }

    // Sort inventory by price
    public void sortByPrice() {
        head = mergeSort(head, "price");
    }

    private InventoryNode mergeSort(InventoryNode node, String criteria) {
        if (node == null || node.next == null) return node;

        InventoryNode middle = getMiddle(node);
        InventoryNode nextOfMiddle = middle.next;
        middle.next = null;

        InventoryNode left = mergeSort(node, criteria);
        InventoryNode right = mergeSort(nextOfMiddle, criteria);

        return merge(left, right, criteria);
    }

    private InventoryNode merge(InventoryNode left, InventoryNode right, String criteria) {
        if (left == null) return right;
        if (right == null) return left;

        InventoryNode result;
        if (criteria.equals("name")) {
            if (left.itemName.compareToIgnoreCase(right.itemName) < 0) {
                result = left;
                result.next = merge(left.next, right, criteria);
            } else {
                result = right;
                result.next = merge(left, right.next, criteria);
            }
        } else { // Sorting by price
            if (left.price < right.price) {
                result = left;
                result.next = merge(left.next, right, criteria);
            } else {
                result = right;
                result.next = merge(left, right.next, criteria);
            }
        }
        return result;
    }

    private InventoryNode getMiddle(InventoryNode node) {
        if (node == null) return node;
        InventoryNode slow = node, fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Display Inventory
    public void displayInventory() {
        InventoryNode temp = head;
        if (temp == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory List:");
        while (temp != null) {
            System.out.println("Item: " + temp.itemName + " | ID: " + temp.itemID + " | Quantity: " + temp.quantity + " | Price: Rs." + temp.price);
            temp = temp.next;
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryList inventory = new InventoryList();

        inventory.add("Laptop", "101", 5, 1200.0);
        inventory.add("Mouse", "102", 15, 25.5);
        inventory.add("Keyboard", "103", 10, 45.0);
        inventory.addAtBeginning("Monitor", "104", 8, 150.0);

        System.out.println("Initial Inventory:");
        inventory.displayInventory();

        inventory.updateQuantity("102", 20);
        inventory.removeItem("103");

        System.out.println("\nAfter Update and Removal:");
        inventory.displayInventory();

        System.out.println("\nTotal Inventory Value: $" + inventory.totalInventoryValue());

        inventory.sortByName();
        System.out.println("\nSorted by Name:");
        inventory.displayInventory();

        inventory.sortByPrice();
        System.out.println("\nSorted by Price:");
        inventory.displayInventory();
    }
}
