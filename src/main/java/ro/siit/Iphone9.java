package ro.siit;

import java.util.ArrayList;
import java.util.List;

public class Iphone9 extends Iphone {
    private int imei;
    private int batteryLifeRemaining;
    private List<Contact> contacts;
    private List<String> messages;
    private List<String> calls;
    public Iphone9(String color, String material,int IMEI) {
        super(500, color, material);
        this.imei = IMEI;
        this.contacts = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.calls = new ArrayList<>();
        this.batteryLifeRemaining = getBatteryLife();
    }
    @Override
    public void addContact() {

    }
    @Override
    public void viewContactList() {

    }
    @Override
    public void sendTextMessage() {

    }
    @Override
    public void seeAllMessagesFromContact() {

    }
    @Override
    public void makeCall() {

    }
    @Override
    public void callHistory() {

    }
    @Override
    public void loadMessages() {

    }
    @Override
    public void loadContactList() {

    }
    @Override
    public void loadCalls() {

    }

}
