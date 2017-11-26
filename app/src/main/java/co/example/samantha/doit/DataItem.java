package co.example.samantha.doit;

import java.util.Date;

/**
 * Created by Asus Rtrix on 11/26/2017.
 */

public class DataItem {
    private int betID;
    private int ownerID;
    private int betMateID;
    private String description;
    private Date createDate;
    private Date endDate;
    private String location;
    private int wager;

    public DataItem(int betID, int ownerID, int betMateID, String description, Date endDate, String location, int wager) {
        this.betID = betID;
        this.ownerID = ownerID;
        this.betMateID = betMateID;
        this.description = description;
        this.createDate = new Date();
        this.endDate = endDate;
        this.location = location;
        this.wager = wager;
    }

    public int getBetID() {
        return betID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getBetMateID() {
        return betMateID;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public int getWager() {
        return wager;
    }



}
