import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class dbload 
{
    public static void main(String[] args) throws IOException 
    {
        // page size declared in arguments
        int pageSize = Integer.valueOf(args[1]);
        // file to load declared here
        
        String fileName = args[2];
        // new output name
        String newFilename = "heap." + pageSize + ".dat";
        // start timing
        long start = System.currentTimeMillis();
        long end;
        int noPages;
        // each number is for the size allocated
        int recordSize = 200 + 12 + 10 + 10 + 10 + 10 + 3 + 20; 
        int noRecords = pageSize / recordSize;
        //System.out.println(noRecords);
        // remainder of space after allocating max records
        int freeSpace = pageSize % recordSize;
        int count = 0;

        // strings for attribute names
        String BN_NAME;
        String BN_STATUS;
        String BN_REG_DT;
        String BN_CANCEL_DT;
        String BN_RENEW_DT;
        String BN_STATE_NUM;
        String BN_STATE_OF_REG;
        String BN_ABN;
        byte[] b_BN_NAME;
        byte[] b_BN_STATUS;
        byte[] b_BN_REG_DT;
        byte[] b_BN_CANCEL_DT;
        byte[] b_BN_RENEW_DT;
        byte[] b_BN_STATE_NUM;
        byte[] b_BN_STATE_OF_REG;
        byte[] b_BN_ABN;
        // temp byte arrays
        byte[] t_BN_STATE_NUM;
        byte[] t_BN_STATE_OF_REG;
        byte[] t_BN_ABN;

        //File file = new File("/Users/gerald/Google Drive/Database Systems/A1/P3/bncsv.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        DataOutputStream os = new DataOutputStream(new FileOutputStream(newFilename));
        String line;
        // this skips the first line of the csv file
        br.readLine();
        while((line = br.readLine()) != null)
        {
            String[] value = line.split("\t");
            BN_NAME = value[1];
            BN_STATUS = value[2];
            BN_REG_DT = value[3];
            BN_CANCEL_DT = value[4];
            BN_RENEW_DT = value[5];

            // set to null if string is empty
            try {
                BN_STATE_NUM = value[6];
            } catch (IndexOutOfBoundsException i)
            {
                BN_STATE_NUM = "";
            }
            try {
                BN_STATE_OF_REG = value[7];
            } catch (IndexOutOfBoundsException i)
            {
                BN_STATE_OF_REG = "";
            }
            try {
                BN_ABN = value[8];
            } catch (IndexOutOfBoundsException i)
            {
                BN_ABN = "";
            }

            b_BN_NAME = BN_NAME.getBytes("UTF-8");
            byte[] t_BN_NAME = Arrays.copyOf(b_BN_NAME, 200);
            os.write(t_BN_NAME);
            
            b_BN_STATUS = BN_STATUS.getBytes("UTF-8");
            byte[] t_BN_STATUS = Arrays.copyOf(b_BN_STATUS, 12);
            os.write(t_BN_STATUS);
            
            b_BN_REG_DT = BN_REG_DT.getBytes("UTF-8");
            byte[] t_BN_REG_DT = Arrays.copyOf(b_BN_REG_DT, 10);
            os.write(t_BN_REG_DT);
            
            b_BN_CANCEL_DT = BN_CANCEL_DT.getBytes("UTF-8");
            byte[] t_BN_CANCEL_DT = Arrays.copyOf(b_BN_CANCEL_DT, 10);
            os.write(t_BN_CANCEL_DT);
            
            b_BN_RENEW_DT = BN_RENEW_DT.getBytes("UTF-8");
            byte[] t_BN_RENEW_DT = Arrays.copyOf(b_BN_RENEW_DT, 10);
            os.write(t_BN_RENEW_DT);
            
            // set to null if it was previously null
            try 
            {
                b_BN_STATE_NUM = BN_STATE_NUM.getBytes("UTF-8");
                t_BN_STATE_NUM = Arrays.copyOf(b_BN_STATE_NUM, 10);
                os.write(t_BN_STATE_NUM);
                
            } catch (NullPointerException i)
            {
                b_BN_STATE_NUM = null;
                t_BN_STATE_NUM = Arrays.copyOf(b_BN_STATE_NUM, 10);

            }
            try 
            {
                b_BN_STATE_OF_REG = BN_STATE_OF_REG.getBytes("UTF-8");
                t_BN_STATE_OF_REG = Arrays.copyOf(b_BN_STATE_OF_REG, 3);
                os.write(t_BN_STATE_OF_REG);
                
            } catch (NullPointerException i)
            {
                b_BN_STATE_OF_REG = null;
                t_BN_STATE_OF_REG = Arrays.copyOf(b_BN_STATE_OF_REG, 3);
            }
            try 
            {
                b_BN_ABN = BN_ABN.getBytes("UTF-8");
                t_BN_ABN = Arrays.copyOf(b_BN_ABN, 20);
                os.write(t_BN_ABN);
            } catch (NullPointerException i)
            {
                b_BN_ABN = null;
                t_BN_ABN = Arrays.copyOf(b_BN_ABN, 20);
            }  
            count++;
            
            if(count == recordSize)
            {
                for(int i = 0; i < freeSpace; i ++)
                {
                    // write null to the spaces that are not filled up
                    os.write(0);
                }
            }
        }
        br.close();
        os.close();
        // end timing
        end = System.currentTimeMillis();
        System.out.println("Number of records: " + count);
        // simple count of pages
        noPages = count/noRecords;
        System.out.println("Number of pages: " + noPages);
        System.out.println("Time taken to load is: "+ (end - start)+ "ms");
    }
}