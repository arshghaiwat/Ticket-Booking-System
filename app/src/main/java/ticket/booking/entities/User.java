package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class User {

    private String userID;

    private String userName;

    private String age;

    private String sex;

    private String password;

    private String hashedPassword;

    private List<Ticket> ticketsBooked;

    //Constructor with User Details
    public User(String userID1, String userName1, String age1, String sex1, String password1, String hashedPassword1, List<Ticket> ticketsBooked1){

        this.userID = userID1;
        this.userName = userName1;
        this.age = age1;
        this.sex = sex1;
        this.password = password1;
        this.hashedPassword = hashedPassword1;
        this.ticketsBooked = ticketsBooked1;

    }

    Scanner scanner = new Scanner(System.in);

    //If no User given or no input, then this constructor would be called.
    public User(){

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID1) {
        this.userID = userID1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName1) {
        this.userName = userName1;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age1) {
        this.age = age1;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex1) {
        this.sex = sex1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password1) {
        this.password = password1;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword1) {
        this.hashedPassword = hashedPassword1;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked1) {
        this.ticketsBooked = ticketsBooked1;
    }

    public void printTicket(){

        int r = 1;
        for(Ticket t: ticketsBooked){

            System.out.println("Ticket No." + r + " -------->   "+ t.getTicketId()+" -> "+t.getSource()+" -> "+t.getDestination()+" ->Rs. "+t.getTrainFare());

            System.out.println("===========================================");
            System.out.println(" Ticket ID: " + t.getTicketId());
            System.out.println("From: " + t.getSource() + " -->  To: " + t.getDestination());
            System.out.println("Train: " + t.getTrain().getTrainName() + " (" + t.getTrain().getTrainNo() + ")");
            System.out.println("Departure: " + t.getDepartureTimeSourceStation());
            System.out.println("Arrival: " + t.getArrivalTimeDestinationStation());
            System.out.println("Fare:: " + t.getTrainFare());
            System.out.println("-------------------------------------------");

            List<List<Boolean>> seatMatrix = t.getTrain().getSeatMatrix();

            if(seatMatrix == null || seatMatrix.isEmpty()){
                System.out.println("No seat matrix available for this train");
                continue;
            }

            System.out.println("Seat Matrix (X = booked, O = available): ");
            for(int i = 0; i < seatMatrix.size(); i++){
                for(int j=0; j < seatMatrix.get(i).size(); j++){
                    if (i == t.getSeatRow() && j == t.getSeatCol()) {
                        System.out.print("U "); // user's seat
                    } else {
                        System.out.print(seatMatrix.get(i).get(j) ? "X " : "O ");
                    }
                }
                System.out.println();
            }
            System.out.println("===========================================");

            r++;
        }

    }

    public String printTicketCheck(){
        printTicket();
        System.out.println("Enter which ticket Serial No. to delete here:: ");
        int option = scanner.nextInt();

        Ticket ticketToCancel = ticketsBooked.get(option-1);

        return ticketToCancel.getTicketId();
    }

}

