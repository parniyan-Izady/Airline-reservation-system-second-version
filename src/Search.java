import java.io.IOException;
import java.util.Scanner;

public class Search {
    Scanner scanner = new Scanner(System.in);
    Flights flights = new Flights();
    RandomFlightFile randomFlightFile;

    public Search(RandomFlightFile randomFlightFile) {
        this.randomFlightFile = randomFlightFile;
    }


    //copying flight information
    public void copyInformation() throws IOException {
        String str = "";


        int length;
        length = (int) randomFlightFile.randomAccessFile.length() / 312;

        randomFlightFile.randomAccessFile.seek(0);


        for (int i = 0; i < length; i++) {

            str = "";
            flights.flight[i] = new Flight();
            for (int j = 312 * i; j < 312 * i + 60; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            flights.flight[i].setFlightId(str.trim());


            str = "";
            for (int j = 312 * i + 60; j < 312 * i + 120; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            flights.flight[i].setOrigin(str.trim());


            str = "";
            for (int j = 312 * i + 120; j < 312 * i + 180; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            flights.flight[i].setDestination(str.trim());


            str = "";
            for (int j = 312 * i + 180; j < 312 * i + 240; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            flights.flight[i].setDate(str.trim());


            str = "";
            for (int j = 380 * i + 240; j < 380 * i + 300; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            flights.flight[i].setTime(str.trim());


            flights.flight[i].setPrice(randomFlightFile.randomAccessFile.readDouble());


            flights.flight[i].setRemainedSeats(randomFlightFile.randomAccessFile.readInt());
        }
    }


    //search flight based on entered information
    public void searchFlightTicket() {
        System.out.println("Enter 0 if you don not need this option!");
        System.out.println();


        System.out.println("Desired origin?");
        System.out.print(">>");
        String testOrigin = scanner.next();
        if (!(testOrigin.equals("0"))) {
            for (int i = 0; i < 100; i++) {
                if (!(flights.flight[i] == null) && !(flights.flight[i].getOrigin().equals(testOrigin))) {
                    flights.flight[i] = null;
                }
            }
        }
        System.out.println("Desired destination?");
        System.out.print(">>");
        String testDestination = scanner.next();
        if (!(testDestination.equals("0"))) {
            for (int i = 0; i < 100; i++) {
                if (!(flights.flight[i] == null) && !(flights.flight[i].getDestination().equals(testDestination))) {
                    flights.flight[i] = null;
                }
            }
        }
        System.out.println("Desired Date?");
        System.out.print(">>");
        String testDate = scanner.next();
        if (!(testDate.equals("0"))) {
            for (int i = 0; i < 100; i++) {
                if (!(flights.flight[i] == null) && !(flights.flight[i].getDate().equals(testDate))) {
                    flights.flight[i] = null;
                }
            }
        }
        System.out.println("Desired Time?");
        System.out.print(">>");
        String testTime = scanner.next();
        if (!(testTime.equals("0"))) {
            for (int i = 0; i < 100; i++) {
                if (!(flights.flight[i] == null) && !(flights.flight[i].getTime().equals(testTime))) {
                    flights.flight[i] = null;
                }
            }
        }
        System.out.println("Desired Price?");
        System.out.print(">>");
        double testPrice = scanner.nextDouble();
        if (!(testPrice == 0)) {
            for (int i = 0; i < 100; i++) {
                if (!(flights.flight[i] == null) && !(flights.flight[i].getPrice() <= testPrice)) {
                    flights.flight[i] = null;
                }
            }
        }
        int p = 0;
        for (int i = 0; i < 100; i++) {
            if (!(flights.flight[i] == null)) {
                System.out.println("Flight Id    |Origin       |Destination  |Date        |Time    |seat |Price  ");
                showSearchedTicket(i);
                p = 4;
            }
        }
        if (p == 0) {
            System.out.println("Sorry! We couldn't Find Any flights !");
        }
    }


    //showing searched flights
    public void showSearchedTicket(int i) {
        System.out.print(flights.flight[i].getFlightId());
        for (int p = 0; p < 13 - flights.flight[i].getFlightId().length(); p++)
            System.out.print(" ");
        System.out.print("|");


        System.out.print(flights.flight[i].getOrigin());
        for (int p = 0; p < 13 - flights.flight[i].getOrigin().length(); p++)
            System.out.print(" ");
        System.out.print("|");


        System.out.print(flights.flight[i].getDestination());
        for (int p = 0; p < 13 - flights.flight[i].getDestination().length(); p++)
            System.out.print(" ");
        System.out.print("|");


        System.out.print(flights.flight[i].getDate());
        for (int p = 0; p < 12 - flights.flight[i].getDate().length(); p++)
            System.out.print(" ");
        System.out.print("|");


        System.out.print(flights.flight[i].getTime());
        for (int p = 0; p < 8 - flights.flight[i].getTime().length(); p++)
            System.out.print(" ");
        System.out.print("|");


        System.out.print(flights.flight[i].getRemainedSeats() + "  ");
        if (flights.flight[i].getRemainedSeats() < 100)
            System.out.print(" ");
        System.out.print("|");


        System.out.print(flights.flight[i].getPrice());


        System.out.println();
    }
}
