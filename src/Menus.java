
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menus {
    Scanner scanner = new Scanner(System.in);
    Flights flights = new Flights();

    RandomFlightFile randomFlightFile = new RandomFlightFile(flights);

    RandomPassengerFile randomPassengerFile = new RandomPassengerFile();

    RandomTicketFile randomTicketFile = new RandomTicketFile();
    Passengers passengers = new Passengers(randomPassengerFile, randomTicketFile, randomFlightFile);

    Admin admin = new Admin(randomFlightFile);

    public Menus() throws FileNotFoundException {
    }


    //menu
    public void menu() throws IOException {
        while (true) {
            int a = 20;
            int b = 20;
            System.out.println();
            showFirstMenu();
            System.out.print(">>");
            a = scanner.nextInt();


            //passenger part
            if (a == 1) {
                while (b != 0) {
                    System.out.println();
                    showEnterPassengerMenu();
                    System.out.print(">>");
                    b = scanner.nextInt();


                    //passenger sign in
                    if (b == 1) {
                        int y;
                        y = passengers.passengerSignIn();
                        if (y == 200) {
                            System.out.println("This account doesn't exist");
                            break;
                        }
                        int s = 20;
                        while (s != 0) {
                            showPassengerMenuOptions();
                            System.out.print(">>");
                            s = scanner.nextInt();


                            //changing password
                            if (s == 1) {
                                System.out.println("Please Enter Your New Password");
                                System.out.print(">>");


                                randomPassengerFile.randomAccessFile.seek(128 * y + 60);
                                for (int i = 128 * y + 60; i < 128 * y + 120; i = i + 2) {
                                    randomPassengerFile.randomAccessFile.writeChars(" ");
                                }


                                randomPassengerFile.randomAccessFile.seek(128 * y + 60);
                                randomPassengerFile.randomAccessFile.writeChars(scanner.next().toLowerCase().trim());


                                System.out.println("Your Password Changed !");
                            }


                            //search flight based on desired information
                            else if (s == 2) {
                                Search search = new Search(randomFlightFile);
                                search.copyInformation();
                                search.searchFlightTicket();
                            }


                            //booking ticket
                            else if (s == 3) {
                                passengers.bookingTicket(y);
                            }


                            //cancelling ticket
                            else if (s == 4) {
                                passengers.ticketCancellation(y);
                            }


                            //showing booked tickets
                            else if (s == 5) {
                                passengers.showBookedTickets(y);
                            }


                            //adding charge
                            else if (s == 6) {
                                passengers.addCharge(y);
                            }
                        }
                    }


                    //passenger sign up
                    else if (b == 2) {
                        passengers.passengerSignUp();
                    }
                }
            }


            //admin part
            else if (a == 2) {
                int z = admin.adminSignIn();
                int x = 10;

                while (x != 0 && z != 0) {
                    showAdminMenuOptions();
                    System.out.print(">>");
                    x = scanner.nextInt();


                    //adding new flight
                    if (x == 1) {
                        admin.addFlight();
                    }


                    //updating flight
                    else if (x == 2) {
                        admin.updateFlight();
                    }


                    //removing flight
                    else if (x == 3) {
                        admin.removeFlight();
                    }


                    //showing flights schedules
                    else if (x == 4) {
                        admin.showFlightsSchedules();
                    }
                }
            }
        }
    }


    //show first menu
    public void showFirstMenu() {
        System.out.println();
        System.out.println("""
                -------------------------------------
                WELCOME TO AIRLINE RESERVATION SYSTEM
                -------------------------------------
                1> Passenger
                2> Admin
                -------------------------------------""");
    }


    //show enter passenger menu
    public void showEnterPassengerMenu() {
        System.out.println();
        System.out.println("""
                -------------------------------------
                          Passenger Menu
                -------------------------------------
                1> Sign in
                2> Sign up     
                0> Return to previous menu
                -------------------------------------""");

    }


    //show admin menu options
    public void showAdminMenuOptions() {
        System.out.println();
        System.out.println("""
                -------------------------------------
                            Admin Menu
                -------------------------------------
                1> Add
                2> Update
                3> Remove
                4> Flight Schedules
                0> Sign out
                -------------------------------------""");

    }


    //passenger's menu options
    public void showPassengerMenuOptions() {
        System.out.println();
        System.out.println("""
                -------------------------------------
                         Passenger Menu
                -------------------------------------
                1> Change Password
                2> Search Flight Ticket
                3> Booking Ticket
                4> Ticket Cancellation
                5> Booked Tickets
                6> Add charge
                0> Sign out
                -------------------------------------""");
    }
}
