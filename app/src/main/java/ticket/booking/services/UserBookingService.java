package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import javax.crypto.spec.PSource;
import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static ticket.booking.utils.UserServiceUtil.checkPassword;

public class UserBookingService {

    private User loggedInUser;

    private User user;

    private List<User> userList;


    //to deserialize
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH = "src\\main\\java\\ticket\\booking\\localDB\\users.json";

    public UserBookingService() throws IOException {

        loadUserFromList();
    }

    public UserBookingService(User user1) throws IOException {

        this.user = user1;
        loadUserFromList();
    }

    public void loadUserFromList() throws IOException {
        userList = objectMapper.readValue(new File(USER_PATH), new TypeReference<List<User>>() {
        });
    }

    public Optional<User> loginUser(String userName1, String plainPassword1) {
        //for int long, we use ==. for string, we use equals
        Optional<User> userExists = userList.stream().filter(e1 -> Objects.equals(userName1, e1.getUserName()) && checkPassword(plainPassword1, e1.getHashedPassword())).findFirst();

        userExists.ifPresent(value -> loggedInUser = value);

        return userExists;
    }

    public boolean signUp(User user1) {
        Optional<User> userExists = userList.stream().filter(e1 -> Objects.equals(user1.getUserID(), e1.getUserID())).findFirst();

        if (userExists.isEmpty()) {
            String hashedPassword = UserServiceUtil.hashPassword(user1.getPassword());
            user1.setHashedPassword(hashedPassword);

            try {
                userList.add(user1);
                saveUserListToFile(userList);
                System.out.println("Signup successful!!");
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            System.out.println("User already exists!!");
            return false;
        }

    }

    private void saveUserListToFile(List<User> userList1) throws IOException {
        File usersFile = new File(USER_PATH);
        objectMapper.writeValue(usersFile, userList1);
    }

    public void fetchBooking() {
        if (loggedInUser == null) {
            System.out.println("Please login first");
        } else {
            System.out.println("Booking Details for : UserID:: " + loggedInUser.getUserID() + "--> userName:: " + loggedInUser.getUserName());
            loggedInUser.printTicket();
        }

    }

    public boolean checkLoggedInUser() {
        if (loggedInUser == null) {
            return false;
        } else {
            return true;
        }
    }

    public String fetchBookingCheck() throws IOException {

        System.out.println("Booking Details for : UserID:: " + loggedInUser.getUserID() + "--> userName:: " + loggedInUser.getUserName());
        return loggedInUser.printTicketCheck();


    }

    public boolean bookTickets(String Source, String Destination) throws IOException {

        Scanner scanner = new Scanner(System.in);
        TrainService trainService = new TrainService();

        Optional<List<Train>> trainExists = trainService.getTrainsCheck(Source, Destination);

        if (trainExists.isEmpty()) {
            System.out.println("No trains available for this route: ");
            return false;
        }

        //getting the value from optional
        List<Train> availableTrains = trainExists.get();

        System.out.println(" Available Trains: ");
        for (int i = 0; i < availableTrains.size(); i++) {
            Train t = availableTrains.get(i);
            System.out.println((i + 1) + ". " + t.getTrainName() + " (" + t.getTrainNo() + ")");
        }

        System.out.println("Choose a train by number:");

        int trainChoice = scanner.nextInt();
        scanner.nextLine();

        if (trainChoice < 1 || trainChoice > availableTrains.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        Train selectedTrain = availableTrains.get(trainChoice - 1);

        System.out.println("Enter date of travel (yyyy-MM-dd):");
        String travelDate = scanner.nextLine();

        List<List<Boolean>> seatMatrix = selectedTrain.getSeatMatrix();
        System.out.println("Available Seats (O = available, X = booked): ");
        for (int i = 0; i < seatMatrix.size(); i++) {
            for (int j = 0; j < seatMatrix.get(i).size(); j++) {
                System.out.print(seatMatrix.get(i).get(j) ? "X" : "O");
            }
            System.out.println();
        }


        System.out.println("Enter row number:");
        int row = scanner.nextInt();
        System.out.println("Enter column number:");
        int col = scanner.nextInt();

        //check if seat is booked or not?
        if (seatMatrix.get(row - 1).get(col - 1)) {
            System.out.println("Seat already booked!");
            return false;
        }

        //book seat
        seatMatrix.get(row - 1).set(col - 1, true);
        selectedTrain.setSeatMatrix(seatMatrix);

        trainService.saveTrainListToFile();

        //calculate fare.
        double baseFare = 300;
        double dynamicFare = Math.random() * 500;
        double finalFare = baseFare + dynamicFare;


        String ticketID = UUID.randomUUID().toString();
        //setting up ticket details up-till now.

        //creating a ticket
        Ticket ticket = new Ticket(
                ticketID,
                Source,
                Destination,
                selectedTrain.getStationTime().get(Source),
                selectedTrain.getStationTime().get(Destination),
                finalFare,
                selectedTrain
        );

        //set data of ticket in users.
        if (loggedInUser.getTicketsBooked() == null) {
            loggedInUser.setTicketsBooked(new ArrayList<>());
        }

        //setData
        loggedInUser.getTicketsBooked().add(ticket);
        saveUserListToFile(userList);

        System.out.println(" Ticket booked successfully for " + loggedInUser.getUserName());
        System.out.println(" Ticket ID: " + ticketID);
        System.out.println(" Train: " + selectedTrain.getTrainName());
        System.out.println(" Fare: " + finalFare);
        System.out.println(" Date of travel: " + travelDate);
        System.out.println(" Seat: Row " + row + ", Col " + col);

        return true;

    }

    public boolean cancelTicket(String ticketID) throws IOException {

        ///check if user logged in.
        if (loggedInUser == null) {
            System.out.println("please login first");
            return false;
        }

        //check if user has any tickets booked
        if (loggedInUser.getTicketsBooked() == null || loggedInUser.getTicketsBooked().isEmpty()) {
            System.out.println("No tickets found for this user.");
            return false;
        }

        //get ticket by ID.
        Optional<Ticket> ticketCancelOpt = loggedInUser.getTicketsBooked().stream().filter(e -> Objects.equals(e.getTicketId(), ticketID)).findFirst();

        if (ticketCancelOpt.isEmpty()) {
            System.out.println("No tickets found with the ID: " + ticketID);
            return false;
        }

        Ticket ticketToCancel = ticketCancelOpt.get();

        Train train = ticketToCancel.getTrain();

        //get the seat in seatMatrix
        List<List<Boolean>> seatMatrix = train.getSeatMatrix();

        if (seatMatrix == null || seatMatrix.isEmpty()) {
            System.out.println("Error: Train seat matrix not found!");
            return false;
        }

        int seatRow = ticketToCancel.getSeatRow();
        int seatCol = ticketToCancel.getSeatCol();

        // Safety check
        if (seatRow < 0 || seatRow >= seatMatrix.size() || seatCol < 0 || seatCol >= seatMatrix.get(0).size()) {
            System.out.println("Invalid seat position in ticket data.");
            return false;
        }

        seatMatrix.get(seatRow).set(seatCol, false); // mark as available again
        train.setSeatMatrix(seatMatrix);

        // Step 6: Remove the ticket from user’s booked list
        loggedInUser.getTicketsBooked().remove(ticketToCancel);

        // Step 7: Persist both user and train data
        saveUserListToFile(userList);

        TrainService trainService = new TrainService();

        List<Train> allTrains = trainService.getTrainList();

        for (Train t : allTrains) {
            if (t.getTrainNo().equals(train.getTrainNo())) {
                t.setSeatMatrix(seatMatrix);
                break;
            }

        }
        trainService.saveTrainListToFile();

        // Step 8: Confirm to user
        System.out.println(" Ticket cancelled successfully!");
        System.out.println("Train: " + train.getTrainName() + " (" + train.getTrainNo() + ")");
        System.out.println("Seat freed: Row " + (seatRow + 1) + ", Column " + (seatCol + 1) + ")");
        System.out.println("Refund of :::" + ticketToCancel.getTrainFare() + " will be processed soon.");

        return true;
    }

    public void logoutUser() {
        if (loggedInUser != null) {
            System.out.println(loggedInUser.getUserID() + " -> " + loggedInUser.getUserName() + " has been logged out successfully");
            loggedInUser = null;
        } else {
            System.out.println("No user logged in currently.");
        }

    }
}
