import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class problem3 {
    public static void main(String[] args) {

        HashMap<String,Integer> map = new HashMap<>();

        BufferedReader br = null;

        String csvFile = "/home/puneet/Downloads/deliveries.csv";
        String line = "";
        String cvsSplitBy = ",";
        int skip = 0;

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while((line = br.readLine()) != null) {
                if (skip == 0) {
                    ++skip;
                } else {
                    String[] match = line.split(cvsSplitBy);

                    if(Integer.parseInt(match[0]) > 576){
                        if(map.containsKey(match[3])){
                            map.put(match[3],map.get(match[3])+Integer.parseInt(match[16]));
                        }
                        else{
                            map.put(match[3], 0);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("For the year 2016 get the extra runs conceded per team : " + map);
    }
}
