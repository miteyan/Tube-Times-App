/**
 * Created by miteyan on 08/09/2016.
 */
public class Tube {

    public Tube (String line) {
        this.line = line;
        System.out.println();
        System.out.println("New Tube added: "+line);
    }

    private String line;
    private String platform;
    private String destination;
    private String currLocation;
    private String arrivalTime;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
        System.out.println("Platform set: " +platform);

    }

    public String getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
        System.out.println("Current location set: " +currLocation);

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
        System.out.println("Destination set: " +destination);

    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
        String eTime = arrivalTime.substring(arrivalTime.indexOf("T")+1,arrivalTime.indexOf("."));

        System.out.println("Arrival set: " +eTime);

    }
}
