import javax.management.StringValueExp;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Passengers {
    Scanner scanner = new Scanner(System.in);
    RandomPassengerFile randomPassengerFile;
    RandomTicketFile randomTicketFile;
    RandomFlightFile randomFlightFile;
    Random random = new Random();

    public Passengers(RandomPassengerFile randomPassengerFile, RandomTicketFile randomTicketFile, RandomFlightFile randomFlightFile) {
        this.randomPassengerFile = randomPassengerFile;
        this.randomTicketFile = randomTicketFile;
        this.randomFlightFile = randomFlightFile;
    }


    //passenger sign up
    public void passengerSignUp() throws IOException {
        int length;
        length = (int) randomPassengerFile.randomAccessFile.length();
        randomPassengerFile.randomAccessFile.seek(length);


        System.out.println("Create Your Username");
        System.out.print(">>");
        randomPassengerFile.writePassengerToFile(scanner.next());


        System.out.println("Create Your Password");
        System.out.print(">>");
        randomPassengerFile.writePassengerToFile(scanner.next());


        randomPassengerFile.randomAccessFile.writeDouble(0);
    }


    //passenger sign in
    public int passengerSignIn() throws IOException {
        int length;
        length = (int) randomPassengerFile.randomAccessFile.length() / 128;
        randomPassengerFile.randomAccessFile.seek(0);


        System.out.println("Please Enter Your Username");
        System.out.print(">>");
        String testUsername = scanner.next();


        System.out.println("Please Enter Your Password");
        System.out.print(">>");
        String testPassword = scanner.next();


        int y;
        for (y = 0; y < length; y++) {
            String password = "";
            String username = "";


            randomPassengerFile.randomAccessFile.seek(128 * y);

            for (int i = 128 * y; i < 128 * y + 60; i = i + 2) {
                username = username + randomPassengerFile.randomAccessFile.readChar();
            }

            for (int i = 128 * y + 60; i < 128 * y + 120; i = i + 2) {
                password = password + randomPassengerFile.randomAccessFile.readChar();
            }

            if (testUsername.equals(username.trim()) && testPassword.equals(password.trim())) {
                return y;
            }
        }
        return 200;
    }


    //booking ticket
    public void bookingTicket(int passengerNumber) throws IOException {
        System.out.println("Please Enter Desired Flight Id ");
        System.out.print(">>");
        String flightId = scanner.next().toLowerCase().trim();


        int length;
        length = (int) randomFlightFile.randomAccessFile.length() / 312;
        String str = "";


        int i;
        for (i = 0; i < length; i++) {
            randomFlightFile.randomAccessFile.seek(312 * i);


            str = "";
            for (int j = 312 * i; j < 312 * i + 60; j = j + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            if (flightId.equals(str.trim())) {
                addBookedTicket(passengerNumber, i, str);
                break;
            }
        }
        if (i == length)
            System.out.println("Sorry! We Couldn't Find Any Flights !");
    }


    //booking ticket
    public void addBookedTicket(int passengerNumber, int flightNumber, String flightId) throws IOException {
        randomPassengerFile.randomAccessFile.seek(128 * passengerNumber + 120);
        randomFlightFile.randomAccessFile.seek(312 * flightNumber + 300);


        if (randomPassengerFile.randomAccessFile.readDouble() >= randomFlightFile.randomAccessFile.readDouble()) {


            String str = "";
            randomTicketFile.randomAccessFile.seek(randomTicketFile.randomAccessFile.length());


            int seatNumber = random.nextInt(100) + 1;
            randomTicketFile.writeTicketToFile(seatNumber + flightId);


            randomTicketFile.randomAccessFile.writeChars(flightId);


            randomFlightFile.randomAccessFile.seek(flightNumber * 312 + 60);


            str = "";
            for (int i = flightNumber * 312 + 60; i < flightNumber * 312 + 120; i = i + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            randomTicketFile.randomAccessFile.writeChars(str);


            str = "";
            for (int i = flightNumber * 312 + 120; i < flightNumber * 312 + 180; i = i + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            randomTicketFile.randomAccessFile.writeChars(str);


            str = "";
            for (int i = flightNumber * 312 + 180; i < flightNumber * 312 + 240; i = i + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            randomTicketFile.randomAccessFile.writeChars(str);


            str = "";
            for (int i = flightNumber * 312 + 240; i < flightNumber * 312 + 300; i = i + 2) {
                str = str + randomFlightFile.randomAccessFile.readChar();
            }
            randomTicketFile.randomAccessFile.writeChars(str);


            randomPassengerFile.randomAccessFile.seek(passengerNumber * 128);
            str = "";
            for (int i = passengerNumber * 128; i < passengerNumber * 128 + 60; i = i + 2) {
                str = str + randomPassengerFile.randomAccessFile.readChar();
            }
            randomTicketFile.randomAccessFile.writeChars(str);


            randomFlightFile.randomAccessFile.seek(312 * flightNumber + 300);
            double flightPrice = randomFlightFile.randomAccessFile.readDouble();
            randomTicketFile.randomAccessFile.writeDouble(flightPrice);


            randomTicketFile.randomAccessFile.writeInt(seatNumber);


            randomPassengerFile.randomAccessFile.seek(128 * passengerNumber + 120);
            double lastCharge = randomPassengerFile.randomAccessFile.readDouble();
            randomPassengerFile.randomAccessFile.seek(128 * passengerNumber + 120);
            randomPassengerFile.randomAccessFile.writeDouble(lastCharge - flightPrice);


            randomFlightFile.randomAccessFile.seek(312 * flightNumber + 308);
            int remainedSeats = randomFlightFile.randomAccessFile.readInt();
            randomFlightFile.randomAccessFile.seek(312 * flightNumber + 308);
            randomFlightFile.randomAccessFile.writeInt(remainedSeats - 1);


            System.out.println("Your Ticket Has Been Reserved! ");
        } else {
            System.out.println("Your Charge Is Not Enough");
        }
    }


    //cancelling ticket
    public void ticketCancellation(int passengerNumber) throws IOException {
        int length;
        length = (int) (randomTicketFile.randomAccessFile.length() / 432);


        System.out.println("Please Enter your Ticket ID");
        System.out.print(">>");
        String ticketId = scanner.next().toLowerCase().trim();
        String str = "";


        int i;
        for (i = 0; i < length; i++) {
            randomTicketFile.randomAccessFile.seek(432 * i);

            str = "";
            for (int j = 432 * i; j < 432 * i + 60; j = j + 2) {
                str = str + randomTicketFile.randomAccessFile.readChar();
            }


            if (str.trim().equals(ticketId)) {


                randomTicketFile.randomAccessFile.seek(432 * i + 420);
                double ticketPrice = randomTicketFile.randomAccessFile.readDouble();


                removeBookedFlight(i, passengerNumber, ticketPrice);


                randomTicketFile.randomAccessFile.seek(432 * i);
                for (int j = 0; j < 432; j = j + 2) {
                    randomTicketFile.randomAccessFile.writeChars(" ");
                }

                System.out.println("Your Ticket Has Been Canceled! ");


                break;
            }
        }
        if (i == length)
            System.out.println("Sorry ! This Ticket Id Doesn't Exist");
    }


    //cancelling ticket
    public void removeBookedFlight(int ticketNumber, int passengerNumber, double flightPrice) throws IOException {


        randomPassengerFile.randomAccessFile.seek(passengerNumber * 128 + 120);
        double passengerCharge = randomPassengerFile.randomAccessFile.readDouble();


        randomPassengerFile.randomAccessFile.seek(passengerNumber * 128 + 120);


        int newPassengerCharge = (int) (passengerCharge + flightPrice);
        randomPassengerFile.randomAccessFile.writeDouble(newPassengerCharge);
        System.out.println("Your Charge Is " + newPassengerCharge + " !");


        randomTicketFile.randomAccessFile.seek(ticketNumber * 432 + 60);
        String flightId = "";
        for (int i = ticketNumber * 432 + 60; i < ticketNumber * 432 + 120; i = i + 2) {
            flightId = flightId + randomTicketFile.randomAccessFile.readChar();
        }
        flightId = flightId.trim();
        int numberOfFlight = randomFlightFile.findFlight(flightId);
        randomFlightFile.randomAccessFile.seek(312 * numberOfFlight + 308);
        int remainedSeatNumber;
        remainedSeatNumber = randomFlightFile.randomAccessFile.readInt();
        remainedSeatNumber++;
        randomFlightFile.randomAccessFile.seek(312 * numberOfFlight + 308);
        randomFlightFile.randomAccessFile.writeInt(remainedSeatNumber);
    }


    //add charge
    public void addCharge(int passengerNumber) throws IOException {
        System.out.println("Please Enter The Desired Amount ");
        System.out.print(">>");
        double addedCharge = scanner.nextDouble();
        randomPassengerFile.randomAccessFile.seek(128 * passengerNumber + 120);
        double lastCharge = randomPassengerFile.randomAccessFile.readDouble();
        randomPassengerFile.randomAccessFile.seek(128 * passengerNumber + 120);
        randomPassengerFile.randomAccessFile.writeDouble(addedCharge + lastCharge);
        randomPassengerFile.randomAccessFile.seek(128 * passengerNumber + 120);
        System.out.println("Your Charge Is " + randomPassengerFile.randomAccessFile.readDouble() + "!");
    }


    //show booked tickets
    public void showBookedTickets(int passengerNumber) throws IOException {
        String str = "";
        String passengerUsername = "";

        randomPassengerFile.randomAccessFile.seek(128 * passengerNumber);
        for (int i = 0; i < 30; i++) {
            passengerUsername = passengerUsername + randomPassengerFile.randomAccessFile.readChar();
        }


        int length;
        length = (int) (randomTicketFile.randomAccessFile.length() / 432);


        int i;
        int r = 0;
        for (i = 0; i < length; i++) {
            randomTicketFile.randomAccessFile.seek(432 * i + 360);


            str = "";
            for (int j = 432 * i + 360; j < 432 * i + 420; j = j + 2) {
                str = str + randomTicketFile.randomAccessFile.readChar();
            }


            if (passengerUsername.trim().equals(str.trim())) {
                r = 3;


                System.out.println("Ticket Id     |Flight Id     |Origin        |Destination   |Date          |Time      |Username         |Seat Number |Price  ");


                randomTicketFile.randomAccessFile.seek(432 * i);


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 14 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 14 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 14 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 14 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 14 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 10 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                str = "";
                for (int p = 432 * i; p < 432 * i + 60; p = p + 2)
                    str = str + randomTicketFile.randomAccessFile.readChar();
                System.out.print(str.trim());
                for (int z = 0; z < 17 - str.trim().length(); z++)
                    System.out.print(" ");
                System.out.print("|");


                randomTicketFile.randomAccessFile.seek(432L * i + 428);
                int seatNumber = randomTicketFile.randomAccessFile.readInt();
                if (seatNumber > 99)
                    System.out.print(seatNumber + "         ");
                else
                    System.out.print(seatNumber + "          ");
                System.out.print("|");


                randomTicketFile.randomAccessFile.seek(432L * i + 420);
                System.out.print(randomTicketFile.randomAccessFile.readDouble());

                System.out.println();
            }
        }
        if (r == 0) {
            System.out.println("Sorry ! We Couldn't Find Any Booked Flights For You !");
        }
    }
}
