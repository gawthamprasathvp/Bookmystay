import java.util.*;

/* ============================
   CLASS: Service
   ============================ */
class Service {

    private String serviceName;
    private double cost;

    // Constructor
    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/* ============================
   CLASS: AddOnServiceManager
   ============================ */
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to a reservation
    public void addService(String reservationId, Service service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {

        List<Service> services = servicesByReservation.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (Service s : services) {
            total += s.getCost();
        }

        return total;
    }
}

/* ============================
   MAIN CLASS
   ============================ */
public class Bookmystay {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        // Assume reservation already confirmed
        String reservationId = "Single-1";

        // Create services
        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1000);

        // Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services to reservation
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        // Calculate total cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Output
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}