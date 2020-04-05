package ro.siit;
//sunt 230 linii de cod aprox aici dar am preferat sa scriu mai user friendly decat mai concis si ambiguu
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SamsungGalaxy7 extends Samsung  {
    private int imei;
    private int batteryLifeRemaining;
    private List<Contact> contacts;
    private List<String> messages;
    private List<String> calls;
    public SamsungGalaxy7(String color, String material, int IMEI) {
        super(300, color, material);
        this.imei = IMEI;
        this.contacts = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.calls = new ArrayList<>();
        this.batteryLifeRemaining = getBatteryLife();
    }

    Scanner scanner = new Scanner(System.in);
    @Override
    public void addContact() {
        System.out.println("Please enter contact information using example pattern (ID phoneNumber firstName lastName): ");
        String newContact = scanner.nextLine();
        String[] arr = newContact.split(" ");
        int id = Integer.parseInt(arr[0]); String phoneNr = arr[1]; String firstName = arr[2]; String lastName = arr[3];
        Contact contactToBeSaved = new Contact(id,phoneNr,firstName,lastName);
        String x =""; // will use it to check when we try to enter a new contact with the same number as an existing contact.
        if (checkContactToBeAdded(contactToBeSaved)){
            System.out.println("New contact added: " + contactToBeSaved.toString());
            contacts.add(contactToBeSaved);
            saveContactToFile();
        }
        else {
            for (Contact contact: contacts) if (contact.getPhoneNumber().equals(contactToBeSaved.getPhoneNumber()))  x = contact.toString();
            System.out.println("The phone number entered has already been saved under " + x);
        }
    }

    private boolean checkContactToBeAdded( Contact contactToBeChecked){
        for (Contact contact: contacts){
            if (contactToBeChecked.getPhoneNumber().equals(contact.getPhoneNumber())) return false;
        }
        return true;
    }

    private  void saveContactToFile(){
        Path contactsDB = Paths.get("resources" + File.separator + "contacts.txt");
        try {
            Files.write(contactsDB, prepareContactsForSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> prepareContactsForSave(){
        List<String> stringContacts = new ArrayList<>();
        for (Contact contact: contacts){
            stringContacts.add(contact.toString());
        }
        return stringContacts;
    }

    @Override
    public void viewContactList() {
        loadContactList();
        List<Contact> copyOfContacts = new ArrayList<>(contacts);
        for (Contact contact: copyOfContacts) System.out.println(contact);
    }

    public void loadContactList(){
        Path contactsDB = Paths.get("resources" + File.separator + "contacts.txt");
        try {
            List<String>stringLines = Files.readAllLines(contactsDB);
            for (String contactFromFile:stringLines){
                if (contactFromFile.contains(",")) {
                    Contact checkContact = parseContact(contactFromFile);
                    if (checkContactToBeAdded(checkContact)) contacts.add(parseContact(contactFromFile));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Contact parseContact(String contactAsString){
        String[] contactSplitted = contactAsString.split(",");
        int contactId = Integer.parseInt(contactSplitted[0].substring(contactSplitted[0].length()-1));
        String firstName = contactSplitted[1].substring(12);
        String lastName = contactSplitted[2].substring(11);
        String phoneNr = contactSplitted[3].substring(14);

        return new Contact(contactId,phoneNr,firstName,lastName);
    }

    @Override
    public void sendTextMessage() {
        if (batteryLifeRemaining < 1) System.out.println("Can't send message. Battery is dead");
        else {
            Date date = new Date();
            System.out.println("Enter contact ID to send message: ");
            int contactId = scanner.nextInt();
            scanner.nextLine();
            boolean checkFind = false;
            for (Contact contact : contacts) {
                if (contact.getContactID() == contactId) {
                    checkFind = true;
                    System.out.println("Contact found: " + contact.toString() +
                            " Enter text (max 500 characters): ");
                    String text = scanner.nextLine();
                    messages.add(date + ": " + "Contact id " + contactId + ": " +
                            contact.getFirstName() + " " + contact.getLastName() + ": " + text);
                    saveTextToPhone();
                    batteryLifeRemaining--;

                    System.out.println("Battery hours remaining: " + batteryLifeRemaining);
                    break;
                }
            }
            if (!checkFind) System.out.println("Contact not found. Try again.");
        }
    }
    private void saveTextToPhone(){

        Path messagesDB = Paths.get("resources" + File.separator + "messages.txt");
        try {
            Files.write(messagesDB,prepareMessagesForSave());
            System.out.println("Message sent and saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private  List<String> prepareMessagesForSave(){
        List<String> stringMessages = new ArrayList<>(messages);
        return stringMessages;
    }

    @Override
    public void seeAllMessagesFromContact() {
        System.out.println("Enter contact ID:");
        int id = scanner.nextInt();
        for (Contact contact: contacts){
            if (contact.getContactID() == id){
                for (String message:messages) if (message.contains("Contact id " + id)) System.out.println(message);
            }
        }
        /*
        // test to see all messages from phone
        List<String> copyOfMessages = new ArrayList<>(messages);
        for (String message: copyOfMessages) System.out.println(message);*/

    }
    public void loadMessages(){
        Path messagesDB = Paths.get("resources" + File.separator + "messages.txt");
        try {
            List<String>messageLines = Files.readAllLines(messagesDB);
            for (String messageRecord: messageLines){
                messages.add(messageRecord);
                batteryLifeRemaining--;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeCall() {
        if (batteryLifeRemaining < 2) System.out.println("Can't send call. Battery is dead");
        else {
            Date date = new Date();
            System.out.println("Enter caller ID: ");
            int callerId = scanner.nextInt();
            boolean checkFind = false;
            for (Contact contact : contacts) {
                if (contact.getContactID() == callerId) {
                    checkFind = true;
                    System.out.println("Contact found: " + contact.toString());
                    String text = date + ": " + contact.toString();
                    calls.add(text);
                    saveCallToPhone();
                    batteryLifeRemaining-=2;
                    System.out.println("Battery hours remaining: " + batteryLifeRemaining);
                    break;
                }
            }
            if (!checkFind) System.out.println("Contact not found. Try again.");
        }
    }
    private void saveCallToPhone(){
        Path callsDB = Paths.get("resources" + File.separator + "calls.txt");
        try {
            Files.write(callsDB,prepareCallsForSave());
            System.out.println("Call sent and saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private  List<String> prepareCallsForSave(){
        List<String> stringCalls = new ArrayList<>(calls);
        return stringCalls;
    }

    @Override
    public void callHistory() {
        List<String> copyOfCalls = new ArrayList<>(calls);
        for (String calls: copyOfCalls) System.out.println(calls);
    }
    public void loadCalls(){
        Path callsDB = Paths.get("resources" + File.separator + "calls.txt");
        try {
            List<String>callLines = Files.readAllLines(callsDB);
            for (String callRecord: callLines){
                calls.add(callRecord);
                batteryLifeRemaining-=2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
