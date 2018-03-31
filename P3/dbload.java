import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


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
        int freeSpace = pageSize % recordSize;
        int count = 0;
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
        try
        {
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
                b_BN_STATUS = BN_STATUS.getBytes("UTF-8");
                b_BN_REG_DT = BN_REG_DT.getBytes("UTF-8");
                b_BN_CANCEL_DT = BN_CANCEL_DT.getBytes("UTF-8");
                b_BN_RENEW_DT = BN_RENEW_DT.getBytes("UTF-8");
                // set to null if it was previously null
                try 
                {
                    b_BN_STATE_NUM = BN_STATE_NUM.getBytes("UTF-8");
                } catch (NullPointerException i)
                {
                    b_BN_STATE_NUM = null;
                }
                try 
                {
                    b_BN_STATE_OF_REG = BN_STATE_OF_REG.getBytes("UTF-8");
                } catch (NullPointerException i)
                {
                    b_BN_STATE_OF_REG = null;
                }
                try 
                {
                    b_BN_ABN = BN_ABN.getBytes("UTF-8");
                } catch (NullPointerException i)
                {
                    b_BN_ABN = null;
                }
                // each os.write(44); is a comma    
                os.write(b_BN_NAME);
                os.write(44);
                os.write(b_BN_STATUS);
                os.write(44);
                os.write(b_BN_REG_DT);
                os.write(44);
                os.write(b_BN_CANCEL_DT);
                os.write(44);
                os.write(b_BN_RENEW_DT);
                os.write(44);
                os.write(b_BN_STATE_NUM);
                os.write(44);
                os.write(b_BN_STATE_OF_REG);
                os.write(44);
                os.write(b_BN_ABN);
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
            // end timing
            end = System.currentTimeMillis();
            System.out.println("Number of records: " + count);
            // simple count of pages
            noPages = count/noRecords;
            System.out.println("Number of pages: " + noPages);
            System.out.println("Time taken to load is: "+ (end - start)+ "ms");
        } catch (FileNotFoundException i){
            i.printStackTrace();
        }
    }
}