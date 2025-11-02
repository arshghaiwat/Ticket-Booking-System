package ticket.booking.entities;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Train {

    private String trainName;

    private String trainNo;

    private String sourceStation;

    private String destinationStation;

    private String departureTimeSourceStation;

    private String arrivalTimeSourceStation;

    private boolean arrivesNextDay;

    private List<List<Boolean>> seatMatrix;

    private List<String> station;

    private Map<String, String> stationTime;

    public Train(String trainName1, String trainNo1, String sourceStation1, String destinationStation1, String departureTimeSourceStation1, String arrivalTimeSourceStation1, boolean arrivesNextDay1, List<List<Boolean>> seatMatrix1, List<String> station1, Map<String, String> stationTime1) {
        this.trainName = trainName1;
        this.trainNo = trainNo1;
        this.sourceStation = sourceStation1;
        this.destinationStation = destinationStation1;
        this.departureTimeSourceStation = departureTimeSourceStation1;
        this.arrivalTimeSourceStation = arrivalTimeSourceStation1;
        this.arrivesNextDay = arrivesNextDay1;
        this.seatMatrix = seatMatrix1;
        this.station = station1;
        this.stationTime = stationTime1;
    }

    public Train() {
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName1) {
        this.trainName = trainName1;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo1) {
        this.trainNo = trainNo1;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation1) {
        this.sourceStation = sourceStation1;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation1) {
        this.destinationStation = destinationStation1;
    }

    public String getDepartureTimeSourceStation() {
        return departureTimeSourceStation;
    }

    public void setDepartureTimeSourceStation(String departureTimeSourceStation1) {
        this.departureTimeSourceStation = departureTimeSourceStation1;
    }

    public String getArrivalTimeSourceStation() {
        return arrivalTimeSourceStation;
    }

    public void setArrivalTimeSourceStation(String arrivalTimeSourceStation1) {
        this.arrivalTimeSourceStation = arrivalTimeSourceStation1;
    }

    public boolean isArrivesNextDay() {
        return arrivesNextDay;
    }

    public void setArrivesNextDay(boolean arrivesNextDay1) {
        this.arrivesNextDay = arrivesNextDay1;
    }

    public List<List<Boolean>> getSeatMatrix() {
        return seatMatrix;
    }

    public void setSeatMatrix(List<List<Boolean>> seatMatrix1) {
        this.seatMatrix = seatMatrix1;
    }

    public List<String> getStation() {
        return station;
    }

    public void setStation(List<String> station1) {
        this.station = station1;
    }

    public Map<String, String> getStationTime() {
        return stationTime;
    }

    public void setStationTime(Map<String, String> stationTime1) {
        this.stationTime = stationTime1;
    }
}
