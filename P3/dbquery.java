import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class dbquery
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String search = args[0];
        String pageSize = args[1];
        String newFilename = "heap." + pageSize + ".dat";
        int recordSize = 200 + 12 + 10 + 10 + 10 + 10 + 3 + 20;
        // current position in file
        int currPos = 0;
        // declared byte arrays for each attribute
        byte[] b_BN_NAME = new byte[200];
        byte[] b_BN_STATUS = new byte[12];
        byte[] b_BN_REG_DT = new byte[10];
        byte[] b_BN_CANCEL_DT = new byte[10];
        byte[] b_BN_RENEW_DT = new byte[10];
        byte[] b_BN_STATE_NUM = new byte[10];
        byte[] b_BN_STATE_OF_REG = new byte[3];
        byte[] b_BN_ABN = new byte[20];
        // search converted to byte array
        byte[] b_search = search.getBytes();
        // copy of search to ensure same whitespace/null characters
        byte[] bc_search = Arrays.copyOf(b_search, 200);
        // strings for each attribute declared here
        String BN_NAME;
        String BN_STATUS;
        String BN_REG_DT;
        String BN_CANCEL_DT;
        String BN_RENEW_DT;
        String BN_STATE_NUM;
        String BN_STATE_OF_REG;
        String BN_ABN;
        
        try 
        {
            RandomAccessFile raf = new RandomAccessFile(newFilename, "r");
            long pos = raf.length();
            long startingPos = 0;
            long start = System.currentTimeMillis();
            long end;
            while(startingPos < pos)
            {
                startingPos += 1;
                raf.seek(0);
                byte[] r = b_BN_NAME;
                raf.read(r);
                if(Arrays.equals(r, bc_search))
                {
                    BN_NAME = search;
                    
                    raf.seek(currPos + 200);
                    BN_STATUS = raf.read(b_BN_STATUS.length);
                }
            }
            end = System.currentTimeMillis();
            System.out.println("Time taken to search is: " + (end - start) + "ms");
        } catch (IOException i)
        {
            i.printStackTrace();
        }

    }
}