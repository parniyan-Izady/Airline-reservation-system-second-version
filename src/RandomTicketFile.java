import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomTicketFile {
    int FIX_SIZE = 30;

    RandomAccessFile randomAccessFile = new RandomAccessFile("TicketsFile.dat", "rw");

    public RandomTicketFile() throws FileNotFoundException {
    }


    //write new ticket to file
    public void writeTicketToFile(String str) throws IOException {
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
