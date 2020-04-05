package ro.siit;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Phone phone = new SamsungGalaxy7("black","plastic", 3433232);
        //exemplele de mai just sunt cerute a fi create in tema (chiar daca pt toate exercitiile nu va fi nevoie de ele
        Phone phone2 = new SamsungGalaxy9("white","metal", 3433665);
        Phone phone3 = new Iphone7("blue","metal", 6553256);
        Phone phone4 = new Iphone9("silver","titanium", 6553567);

        boolean quit = false;
        int choice ;

        phone.loadContactList();
        phone.loadMessages();
        phone.loadCalls();
      //  phone.loadBatteryLifeRemaining();
        /*in tema ni se cere sa implementam metodele de tipul addContact/sendMessage/etc(nr de parametrii). Intrucat
        am mai facut asemenea teme, unde cream direct in main toate obiectele, de data asta am optat pentru meniu
         cu scanner si 3 fisiere .txt unde se memoreaza calls, contacts si messages. */
        phoneMenu();
        while (!quit) {
            System.out.println("Enter phone option: ");
            //aici pot folosi try/catch pt cazul in care nu se introduce o cifra si primim InputMismatchException
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    phone.addContact();
                    break;
                case 2:
                    phone.viewContactList();
                    break;
                case 3:
                    phone.sendTextMessage();
                    break;
                case 4:
                    phone.seeAllMessagesFromContact();
                    break;
                case 5:
                    phone.makeCall();
                    break;
                case 6:
                    phone.callHistory();
                    break;
                case 7:
                    System.out.println("Phone closed.");
                    quit = true;
                    break;
                default:
                    System.out.println("Please enter option 1-7");
            }
        }

    }

    public static void phoneMenu(){
        System.out.println("\n Press ");
        System.out.println("\t 1 - To add new Contact.");
        System.out.println("\t 2 - To print the list of Contacts.");
        System.out.println("\t 3 - To send a text message.");
        System.out.println("\t 4 - To see all messages from a specific contact");
        System.out.println("\t 5 - To make a call");
        System.out.println("\t 6 - To see all call history");
        System.out.println("\t 7 - To close phone");
    }
}
