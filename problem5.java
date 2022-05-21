import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

public class problem5 {
    public static void main(String[] args) {

        TreeMap<String, Integer> map = new TreeMap<>();

        BufferedReader br = null;

        String csvFile = "/home/puneet/Downloads/deliveries.csv";
        String line = "";
        String cvsSplitBy = ",";
        int skip = 0;

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                if (skip == 0) {
                    ++skip;
                } else {
                    String[] match = line.split(cvsSplitBy);
                    if(map.containsKey(match[6])){
                        map.put(match[6],map.get(match[6])+Integer.parseInt(match[15]));
                    }
                    else{
                        map.put(match[6],0);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }
}
