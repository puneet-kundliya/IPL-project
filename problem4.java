import com.sun.source.tree.Tree;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class problem4 {

    public static void main(String[] args) {

        Map<String, Float> bowlerandrun = new TreeMap<>();
        Map<String,Float> bowlerover = new TreeMap<>();
        Map<String,Float> result = new TreeMap<>();

        HashMap<String,Integer> map = new HashMap<>();

        String matchescsvFile = "/home/puneet/Downloads/matches.csv";
        String deliveriescsvFile = "/home/puneet/Downloads/deliveries.csv";
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String line1 = "";
        String line2 = "";
        String cvsSplitBy = ",";
        int skip = 0;
        int x = 0;
        int matchId1 = 0;

        try {
            br1 = new BufferedReader(new FileReader(matchescsvFile));
            while((line1 = br1.readLine()) != null) {
                if (skip == 0) {
                    ++skip;
                } else {
                    String[] match = line1.split(cvsSplitBy);
                    map.put(match[1], Integer.parseInt(match[0]));
                    if(Objects.equals(match[1], "2015")){
                        matchId1++;
                    }
                }
            }
            int matchId2 = map.get("2015");
            matchId1 = matchId2 - matchId1+1;

            br2 = new BufferedReader(new FileReader(deliveriescsvFile));
            while((line2 = br2.readLine()) != null) {
                if (x == 0) {
                    ++x;
                } else {
                    String[] deliveries = line2.split(cvsSplitBy);
                    int id = (Integer.parseInt(deliveries[0]));

                    if(id >= matchId1 && id <= matchId2) {
                        if (bowlerandrun.containsKey(deliveries[8])) {
                            bowlerandrun.put(deliveries[8], bowlerandrun.get(deliveries[8]) + Integer.parseInt(deliveries[17]));
                        } else {
                            bowlerandrun.put(deliveries[8], 0.f);
                        }
                        if(bowlerover.containsKey(deliveries[8])){
                            bowlerover.put(deliveries[8],bowlerover.get(deliveries[8])+(int)(Integer.parseInt(deliveries[5])/6));
                        }
                        else{
                            bowlerover.put(deliveries[8],0.0f);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String,Float> entry : bowlerandrun.entrySet()){
            result.put(entry.getKey(),(bowlerandrun.get(entry.getKey())/bowlerover.get(entry.getKey())));
        }
//        System.out.println(bowlerandrun);
//        System.out.println(bowlerandrun.size());
//        System.out.println(bowlerover);
//        System.out.println(bowlerover.size());
//        Collection<Integer> over = bowlerover.values();
        System.out.println(result);

    }
}
