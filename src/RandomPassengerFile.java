import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomPassengerFile {
    private final int FIX_SIZE = 30;

    public RandomPassengerFile() throws FileNotFoundException {
    }

    RandomAccessFile randomAccessFile = new RandomAccessFile("PassengerFile.dat", "rw");


    //write new passenger to file
    public void writePassengerToFile(String str) throws IOException {
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
}
