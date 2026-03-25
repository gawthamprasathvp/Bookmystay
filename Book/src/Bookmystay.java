import java.util.HashMap;
import java.util.Map;

/* ============================
   CLASS: Room
   ============================ */
class Room {
    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}

/* ============================
   CLASS: RoomInventory
   ============================ */
class RoomInventory {
    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
    }

    public void setAvailability(String type, int count) {
        availability.put(type, count);
    }

    // Read-only access
    public Map<String, Integer> getRoomAvailability() {
        return availability;
    }
}

/* ============================
   CLASS: RoomSearchService
   ============================ */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        // Single Room
        if (availability.get("Single") != null && availability.get("Single") > 0) {
            printRoom(singleRoom, availability.get("Single"));
        }

        // Double Room
        if (availability.get("Double") != null && availability.get("Double") > 0) {
            printRoom(doubleRoom, availability.get("Double"));
        }

        // Suite Room
        if (availability.get("Suite") != null && availability.get("Suite") > 0) {
            printRoom(suiteRoom, availability.get("Suite"));
        }
    }

    // Helper method (clean separation)
    private void printRoom(Room room, int available) {
        System.out.println(room.getType() + " Room:");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSize() + " sqft");
        System.out.println("Price per night: " + room.getPrice());
        System.out.println("Available: " + available);
        System.out.println();
    }
}

/* ============================
   MAIN CLASS
   ============================ */
public class Bookmystay {

    public static void main(String[] args) {

        // Create Room objects
        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        // Setup Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.setAvailability("Single", 5);
        inventory.setAvailability("Double", 3);
        inventory.setAvailability("Suite", 2);

        // Search Service
        RoomSearchService searchService = new RoomSearchService();

        // Perform Search (READ-ONLY)
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}