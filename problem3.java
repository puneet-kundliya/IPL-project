import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class problem3 {
    public static void main(String[] args) {

        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<String,Integer> map2 = new HashMap<>();

        BufferedReader br1 = null;
        BufferedReader br2 = null;

        String deliveriescsvFile = "/home/puneet/Downloads/deliveries.csv";
        String matchescsvFile = "/home/puneet/Downloads/matches.csv";
        String line1 = "";
        String line2 = "";
        String cvsSplitBy = ",";
        int skip1 = 0;
        int skip2 = 0;
        int matchId1 = 0;

        try {
            br1 = new BufferedReader(new FileReader(matchescsvFile));

            while((line1 = br1.readLine()) != null){
                if (skip1 == 0) {
                    ++skip1;
                }
                else {
                    String[] match = line1.split(cvsSplitBy);
                    map1.put(match[1], Integer.parseInt(match[0]));
                    if(Objects.equals(match[1], "2016")){
                        matchId1++;
                    }
                }
            }
            int matchId2 = map1.get("2016");
            matchId1 = matchId2 - matchId1+1;

            br2 = new BufferedReader(new FileReader(deliveriescsvFile));

            while((line2 = br2.readLine()) != null) {
                if (skip2 == 0) {
                    ++skip2;
                } else {
                    String[] match = line2.split(cvsSplitBy);

                    if(Integer.parseInt(match[0]) >= matchId1 && Integer.parseInt(match[0])<=matchId2){
                        if(map2.containsKey(match[3])){
                            map2.put(match[3],map2.get(match[3])+Integer.parseInt(match[16]));
                        }
                        else{
                            map2.put(match[3], 0);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("For the year 2016 get the extra runs conceded per team : " + map2);
        System.out.println(map1);
    }
}
