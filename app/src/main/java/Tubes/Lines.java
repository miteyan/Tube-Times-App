package Tubes;
/**
 * Created by miteyan on 10/09/2016.
 */
public enum Lines {
    METROPOLITAN ("metropolitan"),
    PICCADILLY ("piccadilly"),
    NORTHERN ("northern"),
    BAKERLOO ("bakerloo"),
    CENTRAL ("central"),
    CIRCLE ("circle"),
    DISTRICT ("district"),
    HAMMERSMITH ("hammersmith-city"),
    JUBILEE ("jubilee"),
    VICTORIA ("victoria"),
    WATERLOO ("waterloo-city");

    private String stationID;
    Lines(String stationID) {
        this.stationID=stationID;
    }
    Lines() {

    }

    public String getStationID() {
        return stationID;
    }
    public static Lines[] getLines(){
        return new Lines[] {METROPOLITAN,PICCADILLY,NORTHERN,BAKERLOO,CENTRAL,CIRCLE,DISTRICT,HAMMERSMITH,JUBILEE,VICTORIA,WATERLOO};
    }
}
