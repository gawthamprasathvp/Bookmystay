import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized inventory management using HashMap.
 * Room availability is stored and managed from a single source.
 *
 * @author Student
 * @version 3.1
 */

public class Bookmystay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Hotel Booking App");
        System.out.println("Version 3.1");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        System.out.println("\nCurrent Room Inventory:");
        inventory.displayInventory();

        // Update availability
        System.out.println("\nUpdating Inventory...");
        inventory.updateAvailability("Single Room", 8);
        inventory.updateAvailability("Double Room", 4);

        // Display updated inventory
        System.out.println("\nUpdated Room Inventory:");
        inventory.displayInventory();
    }
}


/**
 * RoomInventory manages centralized room availability
 */
class RoomInventory {

    private HashMap<String, Integer> roomAvailability;

    /**
     * Constructor initializes room availability
     */
    public RoomInventory() {

        roomAvailability = new HashMap<>();

        roomAvailability.put("Single Room", 10);
        roomAvailability.put("Double Room", 5);
        roomAvailability.put("Suite Room", 2);
    }

    /**
     * Get availability of a room type
     */
    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    /**
     * Update room availability
     */
    public void updateAvailability(String roomType, int newCount) {
        roomAvailability.put(roomType, newCount);
    }

    /**
     * Display current inventory
     */
    public void displayInventory() {

        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}