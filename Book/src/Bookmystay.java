import java.util.*;

/* ============================
   CLASS: Reservation
   ============================ */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/* ============================
   CLASS: BookingRequestQueue
   ============================ */
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
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

    public int getAvailableCount(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void decrement(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

/* ============================
   CLASS: RoomAllocationService
   ============================ */
class RoomAllocationService {

    // Prevent duplicate room IDs
    private Set<String> allocatedRoomIds;

    // Track rooms per type
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        // Check availability
        if (inventory.getAvailableCount(roomType) <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }

        // Generate unique ID
        String roomId = generateRoomId(roomType);

        // Store in global set
        allocatedRoomIds.add(roomId);

        // Store in type map
        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        // Update inventory
        inventory.decrement(roomType);

        // Confirmation message
        System.out.println(
                "Booking confirmed for Guest: "
                        + reservation.getGuestName()
                        + ", Room ID: "
                        + roomId
        );
    }

    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        String roomId = roomType + "-" + count;

        // Ensure uniqueness (extra safety)
        while (allocatedRoomIds.contains(roomId)) {
            count++;
            roomId = roomType + "-" + count;
        }

        return roomId;
    }
}

/* ============================
   MAIN CLASS
   ============================ */
public class Bookmystay {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.setAvailability("Single", 2);
        inventory.setAvailability("Double", 1);
        inventory.setAvailability("Suite", 1);

        // Initialize queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Add booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Allocation service
        RoomAllocationService service = new RoomAllocationService();

        // Process FIFO
        while (queue.hasPendingRequests()) {
            Reservation r = queue.getNextRequest();
            service.allocateRoom(r, inventory);
        }
    }
}