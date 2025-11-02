package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrainService {

    private Train train;
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAINS_PATH = "src\\main\\java\\ticket\\booking\\localDB\\Trains.json";

    public TrainService() throws IOException {
        loadTrainsFromList();
    }

    public void loadTrainsFromList() throws IOException {
        File trains = new File(TRAINS_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public void getTrains(String source, String destination){
        List<Train> matchingTrains = trainList.stream().filter(train ->{
                    List<String> stations = train.getStation();

                    int sourceIndex = stations.indexOf(source);
                    int destIndex = stations.indexOf(destination);

                    return sourceIndex != -1 && destIndex != -1 && destIndex > sourceIndex;
                }
            ).collect(Collectors.toList());

        if(matchingTrains.isEmpty()){
            System.out.println("No trains found from " + source + " to " + destination);
        }else{
            System.out.println("Trains from " + source + " to " + destination + ":");
            matchingTrains.forEach(train -> {
                System.out.println(train.getTrainName() + "(" + train.getTrainNo() + ")");
            });
        }


    }

    public List<Train> getTrainList(){
        return trainList;
    }

    public Optional<List<Train>> getTrainsCheck(String source, String destination){
        List<Train> matchingTrains = trainList.stream().filter(train ->{
                    List<String> stations = train.getStation();

                    int sourceIndex = stations.indexOf(source);
                    int destIndex = stations.indexOf(destination);

                    return sourceIndex != -1 && destIndex != -1 && destIndex > sourceIndex;
                }
        ).collect(Collectors.toList());

        if(matchingTrains.isEmpty()){
            System.out.println("No trains found from " + source + " to " + destination);
            return Optional.empty();
        }else{
            System.out.println("Trains from " + source + " to " + destination + ":");
            matchingTrains.forEach(train -> {
                System.out.println(train.getTrainName() + "(" + train.getTrainNo() + ")");
            });
        }

        return Optional.of(matchingTrains);
    }

    public void saveTrainListToFile() throws IOException {
        File trainsFile = new File(TRAINS_PATH);
        objectMapper.writeValue(trainsFile, trainList);
    }

}

