import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class dbload {

    public static void main(String[] args) throws IOException {
        /*int pageSize = Integer.valueOf(args[1]);
        String fileName = args[2]; 
        String newFilename = "heap." + pageSize;*/

        // file for testing
        File file = new File("/Users/gerald/Google Drive/Database Systems/A1/P3/bncsv.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        br.readLine();
        ArrayList<Record> record = new ArrayList<Record>();
        
        while((line = br.readLine()) != null)
        {
            String[] value = line.split("\t");
            char[] BN_NAME = value[1].toCharArray();
            char[] BN_STATUS = value[2].toCharArray();
            char[] BN_REG_DT = value[3].toCharArray();
            char[] BN_CANCEL_DT = value[4].toCharArray();
            char[] BN_RENEW_DT = value[5].toCharArray();
            char[] BN_STATE_NUM = value[6].toCharArray();
            char[] BN_STATE_OF_REG = value[7].toCharArray();
            char[] BN_ABN = value[8].toCharArray();
            record.add(new Record(BN_NAME, BN_STATUS, BN_REG_DT, BN_CANCEL_DT, BN_RENEW_DT, BN_STATE_NUM, BN_STATE_OF_REG, BN_ABN));
        }
    }

}