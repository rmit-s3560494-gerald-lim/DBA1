import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class dbload {

    public static void main(String[] args) throws IOException {
        int pageSize = Integer.valueOf(args[1]);
        String fileName = args[2]; 
        String newFilename = "heap." + pageSize + ".dat";
        // file for testing
        long start = System.currentTimeMillis();
		long end;

        File file = new File("/Users/gerald/Google Drive/Database Systems/A1/P3/bncsv.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        DataOutputStream os = new DataOutputStream(new FileOutputStream(newFilename));
        String line;
        // this skips the first line of the csv file
        br.readLine();

        int count = 0;
        while((line = br.readLine()) != null)
        {
            String[] value = line.split("\t");
            String BN_NAME = value[1];
            String BN_STATUS = value[2];
            String BN_REG_DT = value[3];
            String BN_CANCEL_DT = value[4];
            String BN_RENEW_DT = value[5];
            String BN_STATE_NUM = value[6];
            String BN_STATE_OF_REG = value[7];
            String BN_ABN = value[8]; 

            byte[] b_BN_NAME = BN_NAME.getBytes();
            byte[] b_BN_STATUS = BN_STATUS.getBytes();
            byte[] b_BN_REG_DT = BN_REG_DT.getBytes();
            byte[] b_BN_CANCEL_DT = BN_CANCEL_DT.getBytes();
            byte[] b_BN_RENEW_DT = BN_RENEW_DT.getBytes();
            byte[] b_BN_STATE_NUM = BN_STATE_NUM.getBytes();
            byte[] b_BN_STATE_OF_REG = BN_STATE_OF_REG.getBytes();
            byte[] b_BN_ABN = BN_ABN.getBytes();

            count++;
            
        }
        end = System.currentTimeMillis();
        System.out.println("Number of records: " + count);
		System.out.println("Time taken to load is: "+ (end - start)+ "ms");
        br.close();
    }

}