package com.miteyan.tubemap;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by miteyan on 08/09/2016.
 */

public class Tube implements Parcelable, Comparable<Tube> {
    private String line;
    private String platform;
    private String destination;
    private String currLocation;
    private String arrivalTime;
    private String stationName;
    private Date date;

    private String timeTo;

    public Tube (String stationName) {
        this.stationName = stationName;
        System.out.println();
        System.out.println("New Tube added: "+stationName);
    }
    public Date getDate() {
        return date;
    }
    public void setLine(String line) {
        this.line = line;
        System.out.println("Line: "+line);
    }

    public void setPlatform(String platform) {
        this.platform = platform;
        System.out.println("Platform set: " +platform);

    }
    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
        System.out.println("Current location set: " +currLocation);

    }
    public void setDestination(String destination) {
        this.destination = destination;
        System.out.println("Destination set: " +destination);

    }
    public void setArrivalTime(String arrivalTime) throws ParseException {
//        System.out.println(arrivalTime);
        String eTime = arrivalTime.substring(arrivalTime.indexOf("T")+1,arrivalTime.indexOf("."));
//        System.out.println(eTime);
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        date =  sdf.parse(eTime);
        String time= "";
        if (date.getMinutes()<10){
            time =(date.getHours())+":0" + date.getMinutes()+":"+date.getSeconds();
        }else{
            time =(date.getHours())+":" + date.getMinutes()+":"+date.getSeconds();
        }
        this.arrivalTime = time;
        System.out.println("Arrival set: " +this.arrivalTime);
        getTimeTo();
    }
//    public String getTimeTo(){
//        Date now = new Date();
//        //date is the time at which the train arrives
//        if (date.getHours()>0 )
//        date.setMonth(now.getMonth());
//        date.setYear(now.getYear());
//        date.setDate(now.getDate());
//        long diffMs = date.getTime()-now.getTime();
//        long diffSec = diffMs / 1000;
//        long min = diffSec / 60;
//        long sec = diffSec % 60;
//        timeTo = min+ ":"+sec;
//        String timeLeft="Arriving in "+min+":"+sec;
//        System.out.println(timeLeft);
//        return timeLeft;
//    }
    public String getTimeTo() {

        Calendar cal = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        cal.setTime(date);
        if ( (cal.getTime().getHours()==22||cal.getTime().getHours()==23) && now.getTime().getHours()==0){
            now.set(1970,00,02);
        }
        else{
            now.set(1970,00,01);
        }
        long diff = now.getTime().getTime()-cal.getTime().getTime();
        long diffSecs = diff/1000;
        long mins = diffSecs/60;
        long secs = diffSecs%60;
        if (Math.abs(secs)<10){
            return Math.abs(mins)+":0"+Math.abs(secs);
        }else {
            return Math.abs(mins)+":"+Math.abs(secs);

        }
    }

    @Override
    public String toString() {
        return "Tube{" +
                "line='" + line + '\'' +
                ", platform='" + platform + '\'' +
                ", destination='" + destination + '\'' +
                ", currLocation='" + currLocation + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", stationName='" + stationName + '\'' +
                ", date=" + date +
                ", TIME TO='" + timeTo + '\'' +
                '}';
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public String getCurrLocation() {
        return currLocation;
    }
    public String getLine() {
        return line;
    }
    public String getStationName() {
        return stationName;
    }
    public void setStationName(String stationName) {
        this.stationName = stationName;
        System.out.println("Station name set: " +stationName);
    }
    public String getDestination() {
        return destination;
    }
    public String getPlatform() {
        return platform;
    }

    protected Tube(Parcel in) {
        line = in.readString();
        platform = in.readString();
        destination = in.readString();
        currLocation = in.readString();
        arrivalTime = in.readString();
        stationName = in.readString();
        long tmpDate = in.readLong();
        date = tmpDate != -1 ? new Date(tmpDate) : null;
        timeTo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(line);
        dest.writeString(platform);
        dest.writeString(destination);
        dest.writeString(currLocation);
        dest.writeString(arrivalTime);
        dest.writeString(stationName);
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeString(timeTo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Tube> CREATOR = new Parcelable.Creator<Tube>() {
        @Override
        public Tube createFromParcel(Parcel in) {
            return new Tube(in);
        }

        @Override
        public Tube[] newArray(int size) {
            return new Tube[size];
        }
    };

    @Override
    public int compareTo(Tube comparedTube) {


        return (int) ((int) this.date.getTime()-comparedTube.date.getTime());
    }
}