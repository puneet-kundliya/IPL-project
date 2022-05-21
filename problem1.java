import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class problem1 {
    public static void main(String[] args) {

        HashMap<String , Integer> map = new HashMap<>();

        String csvFile = "/home/puneet/Downloads/matches.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int skip = 0;

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while((line = br.readLine()) != null) {
                if (skip == 0) {
                    ++skip;
                }
                else {
                    String[] match = line.split(cvsSplitBy);

                    if(map.containsKey(match[1])){
                        map.put(match[1], map.get(match[1])+1);
                    }
                    else{
                        map.put(match[1],1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }
}
