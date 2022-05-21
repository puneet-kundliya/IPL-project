import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class problem2 {

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
                } else {
                    String[] match = line.split(cvsSplitBy);

                    if(map.containsKey(match[10])){
                        map.put(match[10], map.get(match[10])+1);
                    }
                    else{
                        map.put(match[10], 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.remove("");
        System.out.println("Number of matches won of all teams over all the years of IPL : " + map);
    }
}
