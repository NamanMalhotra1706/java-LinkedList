import java.util.HashSet;

class SocialMediaList {

    class FriendNode {
        String friendID;
        FriendNode next;

        FriendNode(String friendID, FriendNode next) {
            this.friendID = friendID;
            this.next = next;
        }
    }

    class SocialMediaNode {
        String userID;
        String name;
        int age;
        FriendNode friendList;
        SocialMediaNode next;

        SocialMediaNode(String userID, String name, int age) {
            this.userID = userID;
            this.name = name;
            this.age = age;
            this.friendList = null;
            this.next = null;
        }
    }

    private SocialMediaNode head = null;

    public void addNewUser(String userID, String name, int age) {
        SocialMediaNode newUser = new SocialMediaNode(userID, name, age);
        newUser.next = head;
        head = newUser;
    }

    private SocialMediaNode findUser(String userID) {
        SocialMediaNode temp = head;
        while (temp != null) {
            if (temp.userID.equals(userID)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void addFriend(String userID1, String userID2) {
        SocialMediaNode user1 = findUser(userID1);
        SocialMediaNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!isFriend(user1.friendList, userID2)) {
            user1.friendList = new FriendNode(userID2, user1.friendList);
        }
        if (!isFriend(user2.friendList, userID1)) {
            user2.friendList = new FriendNode(userID1, user2.friendList);
        }
    }

    private boolean isFriend(FriendNode friendList, String friendID) {
        while (friendList != null) {
            if (friendList.friendID.equals(friendID)) return true;
            friendList = friendList.next;
        }
        return false;
    }

    public void removeFriend(String userID1, String userID2) {
        SocialMediaNode user1 = findUser(userID1);
        SocialMediaNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.friendList = removeFriendFromList(user1.friendList, userID2);
        user2.friendList = removeFriendFromList(user2.friendList, userID1);
    }

    private FriendNode removeFriendFromList(FriendNode head, String friendID) {
        if (head == null) return null;
        if (head.friendID.equals(friendID)) return head.next;

        FriendNode temp = head;
        while (temp.next != null) {
            if (temp.next.friendID.equals(friendID)) {
                temp.next = temp.next.next;
                return head;
            }
            temp = temp.next;
        }
        return head;
    }

    public void findMutualFriends(String userID1, String userID2) {
        SocialMediaNode user1 = findUser(userID1);
        SocialMediaNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        HashSet<String> user1Friends = new HashSet<>();
        FriendNode temp = user1.friendList;
        while (temp != null) {
            user1Friends.add(temp.friendID);
            temp = temp.next;
        }

        System.out.print("Mutual Friends: ");
        boolean found = false;
        temp = user2.friendList;
        while (temp != null) {
            if (user1Friends.contains(temp.friendID)) {
                System.out.print(temp.friendID + " ");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    public void displayFriends(String userID) {
        SocialMediaNode user = findUser(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print(user.name + "'s Friends: ");
        FriendNode temp = user.friendList;
        if (temp == null) {
            System.out.println("No friends.");
            return;
        }
        while (temp != null) {
            System.out.print(temp.friendID + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void searchUser(String keyword) {
        SocialMediaNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.userID.equals(keyword) || temp.name.equalsIgnoreCase(keyword)) {
                System.out.println("User Found: " + temp.name + " (ID: " + temp.userID + "), Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("User not found.");
        }
    }

    public void countFriends() {
        SocialMediaNode temp = head;
        while (temp != null) {
            int count = 0;
            FriendNode friendTemp = temp.friendList;
            while (friendTemp != null) {
                count++;
                friendTemp = friendTemp.next;
            }
            System.out.println(temp.name + " has " + count + " friend(s).");
            temp = temp.next;
        }
    }

    public void displayAllUsers() {
        SocialMediaNode temp = head;
        if (temp == null) {
            System.out.println("No users found.");
            return;
        }
        System.out.println("All Users:");
        while (temp != null) {
            System.out.println("Name: " + temp.name + " | ID: " + temp.userID + " | Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaFriendConnections {
    public static void main(String[] args) {
        SocialMediaList network = new SocialMediaList();

        // Adding users
        network.addNewUser("101", "Naman", 25);
        network.addNewUser("102", "Manvi", 23);
        network.addNewUser("103", "Vaishu", 30);


        // Adding friends
        network.addFriend("101", "102");
        network.addFriend("101", "103");
        network.addFriend("102", "103");
        network.addFriend("103", "104");

        // Display all users
        network.displayAllUsers();

        // Display friends of a user
        network.displayFriends("101");
        network.displayFriends("103");

        // Finding mutual friends
        network.findMutualFriends("101", "103");

        // Removing a friend
        network.removeFriend("101", "103");
        System.out.println("\nAfter removing friend:");
        network.displayFriends("101");

        // Searching for a user
        network.searchUser("Alice");
        network.searchUser("105");

        // Counting friends for each user
        network.countFriends();
    }
}
