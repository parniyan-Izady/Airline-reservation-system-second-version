import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFlightFile {
    private final int FIX_SIZE = 30;
    Flights flights;

    public RandomFlightFile(Flights flights) throws FileNotFoundException {
        this.flights = flights;
    }

    RandomAccessFile randomAccessFile = new RandomAccessFile("flightFile.dat", "rw");


    //fix to write
    public void writeFlightToFile(String str) throws IOException {
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeChars(fixToWrite(str));
    }


    //fix to write
    public String fixToWrite(String str) {
        while (str.length() < FIX_SIZE) {
            str += " ";
        }
        return str.substring(0, FIX_SIZE);
    }


    //find the number of flight based on flightId
    public int findFlight(String flightId) throws IOException {
        int length;
        length = (int) (randomAccessFile.length() / 312);


        String str;
        int i;
        for (i = 0; i < length; i++) {
            randomAccessFile.seek(312 * i);
            str = "";
            for (int j = 312 * i; j < 312 * i + 60; j = j + 2) {
                str = str + randomAccessFile.readChar();
            }
            if (flightId.trim().equals(str.trim()))
                return i;
        }
        return 200;
    }
}
