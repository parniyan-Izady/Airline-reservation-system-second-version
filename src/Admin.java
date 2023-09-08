import java.io.IOException;
import java.util.Scanner;

public class Admin {
    Scanner scanner = new Scanner(System.in);

    RandomFlightFile randomFlightFile;
    private final String adminUsername = "admin";
    private final String adminPassword = "admin";

    public Admin(RandomFlightFile randomFlightFile) {
        this.randomFlightFile = randomFlightFile;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }


    //Admin SignIn
    public int adminSignIn() {
        int z = 1;
        System.out.println("Please enter your username ");
        System.out.print(">>");
        String testUsername = scanner.next().toLowerCase().trim();
        System.out.println("Please enter your password ");
        System.out.print(">>");
        String testPassword = scanner.next().toLowerCase().trim();
        if (!(testUsername.equals(getAdminUsername()) && testPassword.equals(getAdminPassword()))) {
            System.out.println("Entered username or password doesn't match!");
            z = 0;
        }
        return z;
    }


    //adding flight
    public void addFlight() throws IOException {
        randomFlightFile.randomAccessFile.seek(randomFlightFile.randomAccessFile.length());


        System.out.println("Flight Id?");
        System.out.print(">>");
        randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


        System.out.println("Origin?");
        System.out.print(">>");
        randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


        System.out.println("Destination?");
        System.out.print(">>");
        randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


        System.out.println("Date?");
        System.out.print(">>");
        randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


        System.out.println("Time?");
        System.out.print(">>");
        randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


        System.out.println("Price?");
        System.out.print(">>");
        randomFlightFile.randomAccessFile.writeDouble(scanner.nextDouble());


        randomFlightFile.randomAccessFile.writeInt(100);


        System.out.println();
    }


    //updating flights
    public void updateFlight() throws IOException {
        System.out.println("Please Enter The Flight Id?");
        System.out.print(">>");


        String flightId = scanner.next().toLowerCase().trim();
        int numberOfFlight;
        numberOfFlight = randomFlightFile.findFlight(flightId);


        if (numberOfFlight != 200) {


            randomFlightFile.randomAccessFile.seek(numberOfFlight * 312);
            for (int i = numberOfFlight * 312; i < numberOfFlight * 312 + 312; i++) {
                randomFlightFile.randomAccessFile.writeChars(" ");
            }


            randomFlightFile.randomAccessFile.seek(numberOfFlight * 312);


            System.out.println("New Flight Id?");
            System.out.print(">>");
            randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


            System.out.println("New Origin?");
            System.out.print(">>");
            randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


            System.out.println("New Destination?");
            System.out.print(">>");
            randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


            System.out.println("New Date?");
            System.out.print(">>");
            randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


            System.out.println("New Time?");
            System.out.print(">>");
            randomFlightFile.writeFlightToFile(scanner.next().toLowerCase().trim());


            System.out.println("New Price?");
            System.out.print(">>");
            randomFlightFile.randomAccessFile.writeDouble(scanner.nextDouble());


            randomFlightFile.randomAccessFile.writeInt(100);
        } else {
            System.out.println("Sorry This Flight Id Doesn't Exist!");
        }
    }


    //removing flights
    public void removeFlight() throws IOException {
        int length;
        length = (int) randomFlightFile.randomAccessFile.length() / 312;
        String str = "";


        System.out.println("Please Enter the Flight Id?");
        System.out.print(">>");
        String flightId;
        flightId = scanner.next();


        int i;
        for (i = 0; i < length; i++) {
            str = "";
            for (int j = 312 * i; j < 312 * i + 60; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
                if (flightId.equals(str.trim())) {
                    randomFlightFile.randomAccessFile.seek(312 * i);
                    for (int k = 312 * i; k < 312 * (i + 1); k = k + 2) {
                        randomFlightFile.randomAccessFile.writeChars(" ");
                    }
                }
                break;
            }
            if (i == length) System.out.println("This flight id doesn't exist!");
        }
    }


    //showing flights scheduales
    public void showFlightsSchedules() throws IOException {
        int s;
        s = (int) randomFlightFile.randomAccessFile.length() / 312;
        randomFlightFile.randomAccessFile.seek(0);


        int i;
        for (i = 0; i < s; i++) {

            String str = "";

            System.out.println("Flight Id     |Origin        |Destination   |Date        |Time        |Seats   |Price     ");


            str = "";
            for (int p = 312 * i; p < 312 * i + 60; p = p + 2)
                str = str + randomFlightFile.randomAccessFile.readChar();
            System.out.print(str.trim());
            for (int z = 0; z < 14 - str.trim().length(); z++)
                System.out.print(" ");
            System.out.print("|");


            str = "";
            for (int p = 312 * i + 0; p < 312 * i + 60; p = p + 2)
                str = str + randomFlightFile.randomAccessFile.readChar();
            System.out.print(str.trim());
            for (int z = 0; z < 14 - str.trim().length(); z++)
                System.out.print(" ");
            System.out.print("|");


            str = "";
            for (int p = 312 * i + 0; p < 312 * i + 60; p = p + 2)
                str = str + randomFlightFile.randomAccessFile.readChar();
            System.out.print(str.trim());
            for (int z = 0; z < 14 - str.trim().length(); z++)
                System.out.print(" ");
            System.out.print("|");


            str = "";
            for (int p = 312 * i + 0; p < 312 * i + 60; p = p + 2)
                str = str + randomFlightFile.randomAccessFile.readChar();
            System.out.print(str.trim());
            for (int z = 0; z < 12 - str.trim().length(); z++)
                System.out.print(" ");
            System.out.print("|");


            str = "";
            for (int p = 312 * i + 0; p < 312 * i + 60; p = p + 2)
                str = str + randomFlightFile.randomAccessFile.readChar();
            System.out.print(str.trim());
            for (int z = 0; z < 12 - str.trim().length(); z++)
                System.out.print(" ");
            System.out.print("|");


            randomFlightFile.randomAccessFile.seek(312 * i + 308);
            int remainedSeats = randomFlightFile.randomAccessFile.readInt();
            if (remainedSeats == 100) System.out.print(remainedSeats + "     ");
            if (remainedSeats < 100) System.out.print(remainedSeats + "      ");
            if (remainedSeats < 10) System.out.print(remainedSeats + "       ");
            System.out.print("|");


            randomFlightFile.randomAccessFile.seek(312 * i + 300);
            System.out.print(randomFlightFile.randomAccessFile.readDouble());


            randomFlightFile.randomAccessFile.seek(312 * (i + 1));


            System.out.println();
        }
        if (i > s) System.out.println("Sorry ! We couldn't find any Flights !");
    }
}