package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {

    private String ticketId;

    private String source;

    private String destination;

    private int seatRow;


    private int seatCol;

    private String departureTimeSourceStation;

    private String arrivalTimeDestinationStation;

    private double trainFare;

    private Train train;

    //Constructor called with details
    public Ticket(String ticketId1,  String source1, String destination1, String departureTimeSourceStation1, String arrivalTimeDestinationStation1, double trainFare1, Train train1){
        this.ticketId = ticketId1;
        this.source = source1;
        this.destination = destination1;
        this.departureTimeSourceStation = departureTimeSourceStation1;
        this.arrivalTimeDestinationStation = arrivalTimeDestinationStation1;
        this.trainFare = trainFare1;
        this.train = train1;
    }

    //Empty constructurs
    public Ticket(){

    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId1) {
        this.ticketId = ticketId1;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source1) {
        this.source = source1;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination1) {
        this.destination = destination1;
    }

    public String getDepartureTimeSourceStation() {
        return departureTimeSourceStation;
    }

    public void setDepartureTimeSourceStation(String departureTimeSourceStation1) {
        this.departureTimeSourceStation = departureTimeSourceStation1;
    }

    public String getArrivalTimeDestinationStation() {
        return arrivalTimeDestinationStation;
    }

    public void setArrivalTimeDestinationStation(String arrivalTimeDestinationStation1) {
        this.arrivalTimeDestinationStation = arrivalTimeDestinationStation1;
    }

    public double getTrainFare() {
        return trainFare;
    }

    public void setTrainFare(double trainFare1) {
        this.trainFare = trainFare1;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train1) {
        this.train = train1;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    public int getSeatCol() {
        return seatCol;
    }

    public int getSeatRow() {
        return seatRow;
    }
}

