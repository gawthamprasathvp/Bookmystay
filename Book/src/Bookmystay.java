/**
 * UseCase2RoomInitialization
 *
 * Demonstrates basic room types using abstraction,
 * inheritance and static availability.
 *
 * @author Student
 * @version 2.1
 */

public class Bookmystay {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Hotel Booking App");
        System.out.println("Version 2.1");
        System.out.println("=================================");

        // Creating room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;

        System.out.println("\nRoom Details:\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailability);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailability);
        System.out.println();

        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
    }
}


/**
 * Abstract class representing a Room
 */
abstract class Room {

    protected String type;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Room Size: " + size + " sq.ft");
        System.out.println("Price per Night: $" + price);
    }
}


/**
 * Single Room class
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}


/**
 * Double Room class
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 300, 180);
    }
}


/**
 * Suite Room class
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 300);
    }
}