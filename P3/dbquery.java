import java.io.*;
import java.util.Arrays;

public class dbquery
{
    public static void main(String[] args) throws FileNotFoundException
    {
        long start = System.currentTimeMillis();
        String search = args[0];
        //search = search.toLowerCase();
        int pageSize = Integer.valueOf(args[1]);
        String newFilename = "heap." + pageSize + ".dat";
        int recordSize = 200 + 12 + 10 + 10 + 10 + 10 + 3 + 20;
        // current position in file
        int currPos = 0;
        // current record
        int cRecord = 0;
        // current Page
        int cPage = 0;
        int recordPerPage = pageSize / recordSize;
        // copy of search to ensure same whitespace/null characters
        byte[] e = new byte[200];
        RandomAccessFile raf = null;
        // declared attributes here
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
            raf = new RandomAccessFile(newFilename, "r");
            while(true)
            {
                int currOffset = cRecord * recordSize + cPage * pageSize;
                raf.seek(currOffset);
                byte[] b_BN_NAME = new byte[200];
                raf.read(b_BN_NAME);
                BN_NAME = new String(b_BN_NAME);
                if(BN_NAME.toLowerCase().contains(search.toLowerCase()))
                {
                    // allocate enough bytes for each attribute
                    byte[] b_BN_STATUS = new byte[12];
                    byte[] b_BN_REG_DT = new byte[10];
                    byte[] b_BN_CANCEL_DT = new byte[10];
                    byte[] b_BN_RENEW_DT = new byte[10];
                    byte[] b_BN_STATE_NUM = new byte[10];
                    byte[] b_BN_STATE_OF_REG = new byte[3];
                    byte[] b_BN_ABN = new byte[20];
                    
                    raf.seek(currPos + 200);
                    raf.read(b_BN_STATUS);
                    BN_STATUS = new String(b_BN_STATUS);

                    raf.seek(currPos + 200 + 12);
                    raf.read(b_BN_REG_DT);
                    BN_REG_DT = new String(b_BN_REG_DT);

                    raf.seek(currPos + 200 + 12 + 10);
                    raf.read(b_BN_CANCEL_DT);
                    BN_CANCEL_DT = new String(b_BN_CANCEL_DT);

                    raf.seek(currPos + 200 + 12 + 10 + 10);
                    raf.read(b_BN_RENEW_DT);
                    BN_RENEW_DT = new String(b_BN_RENEW_DT);

                    raf.seek(currPos + 200 + 12 + 10 + 10 + 10);
                    raf.read(b_BN_STATE_NUM);
                    BN_STATE_NUM = new String(b_BN_STATE_NUM);

                    raf.seek(currPos + 200 + 12 + 10 + 10 + 10 + 10);
                    raf.read(b_BN_STATE_OF_REG);
                    BN_STATE_OF_REG = new String(b_BN_STATE_OF_REG);
                    
                    raf.seek(currPos + 200 + 12 + 10 + 10 + 10 + 10 + 3);
                    raf.read(b_BN_ABN);
                    BN_ABN = new String(b_BN_ABN);

                    System.out.println("BN_NAME: " + BN_NAME + "\nBN_STATUS: " + BN_STATUS + "\nBN_REG_DT: " + BN_REG_DT + "\nBN_CANCEL_DT: " + BN_CANCEL_DT + "\nBN_RENEW_DT: " + BN_RENEW_DT + "\nBN_STATE_NUM: " + BN_STATE_NUM + "\nBN_STATE_OF_REG: " + BN_STATE_OF_REG + "\nBN_ABN: " + BN_ABN);
                    
                }
                // next record
                cRecord++;
                if(cRecord == recordPerPage)
                {
                    // reset record to 0 and go to next page
                    cRecord = 0;
                    cPage++;
                }
                // find eof
                if (Arrays.equals(b_BN_NAME, e))
                {
                    // stop searching
                    break;
                }
            }
        } catch (FileNotFoundException f)
        {
            f.printStackTrace();
        } catch (IOException i)
        {
            i.printStackTrace();
        } finally
        {
            long end = System.currentTimeMillis();
            System.out.println("Time taken to load is: "+ (end - start)+ "ms");
        }
    }
}