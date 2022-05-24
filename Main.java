import service.Deliveries;
import service.Match;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static final String csvSplitBy = ",";
    static final String csvFileMatches = "/home/puneet/Downloads/matches.csv";
    static final String csvFileDeliveries = "/home/puneet/Downloads/deliveries.csv";
    static String line;

    public static void main(String[] args) {

        List<Match> everyMatchData = getMatchesData();
        List<Deliveries> everyDeliveriesData = getDeliveriesData();

        numberOfMatchPerYear(everyMatchData);
        numberOfMatchesWonByTeam(everyMatchData);
        extraRunsConcededPerTeamInYear2016(everyDeliveriesData,everyMatchData);
        economicalBowlerForYear2015(everyDeliveriesData,everyMatchData);
    }
    static List<Match> getMatchesData(){
        List<Match> matchesData = new ArrayList();

        BufferedReader br = null;
        int skip = 0;
        try {
            br = new BufferedReader(new FileReader(csvFileMatches));

            while ((line = br.readLine()) != null) {

                if(skip == 0){
                    skip++;
                    continue;
                }
                else {
                    String[] eachLineData = line.split(csvSplitBy);
                    Match match = new Match();
                    match.setId(Integer.parseInt(eachLineData[0]));
                    match.setSeason(Integer.parseInt(eachLineData[1]));
                    match.setCity(eachLineData[2]);
                    match.setDate(eachLineData[3]);
                    match.setTeam1(eachLineData[4]);
                    match.setTeam2(eachLineData[5]);
                    match.setTossWinner(eachLineData[6]);
                    match.setTossDecision(eachLineData[7]);
                    match.setResult(eachLineData[8]);
                    match.setDlApplied(eachLineData[9]);
                    match.setWinner(eachLineData[10]);
                    match.setWinByRuns(Integer.parseInt(eachLineData[11]));
                    match.setWinByWickets(Integer.parseInt(eachLineData[12]));
                    match.setPlayerOfMatch(eachLineData[13]);
                    match.setVenue(eachLineData[14]);
//                    match.setUmpire1(eachLineData[15]);
//                    match.setUmpire2(eachLineData[16]);
//                    match.setUmpire3(eachLineData[17]);

                    matchesData.add(match);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return matchesData;

    }
    private static List<Deliveries> getDeliveriesData(){
        List<Deliveries> deliveriesData = new ArrayList<>();

        BufferedReader br = null;
        int skip = 0;

        try {
            br = new BufferedReader(new FileReader(csvFileDeliveries));
            while ((line = br.readLine()) != null) {
                if(skip == 0){
                    skip++;
                    continue;
                }
                String[] eachLineData = line.split(csvSplitBy);
                Deliveries deliveries = new Deliveries();

                deliveries.setMatchId(Integer.parseInt(eachLineData[0]));
                deliveries.setInning(Integer.parseInt(eachLineData[1]));
                deliveries.setBattingTeam(eachLineData[2]);
                deliveries.setBowlingTeam(eachLineData[3]);
                deliveries.setOver(Integer.parseInt(eachLineData[4]));
                deliveries.setBall(Integer.parseInt(eachLineData[5]));
                deliveries.setBatsman(eachLineData[6]);
                deliveries.setNonStriker(eachLineData[7]);
                deliveries.setBowler(eachLineData[8]);
                deliveries.setIsSuperOver(Integer.parseInt(eachLineData[9]));
                deliveries.setWideRuns(Integer.parseInt(eachLineData[10]));
                deliveries.setByeRuns(Integer.parseInt(eachLineData[11]));
                deliveries.setLegByeRuns(Integer.parseInt(eachLineData[12]));
                deliveries.setNoballRuns(Integer.parseInt(eachLineData[13]));
                deliveries.setPenaltyRuns(Integer.parseInt(eachLineData[14]));
                deliveries.setBatsmanRuns(Integer.parseInt(eachLineData[15]));
                deliveries.setExtraRuns(Integer.parseInt(eachLineData[16]));
                deliveries.setTotalRuns(Integer.parseInt(eachLineData[17]));
//                deliveries.setPlayerDismissed(eachLineData[18]);
//                deliveries.setDismissalKind(eachLineData[19]);
//                deliveries.setFielder(eachLineData[20]);

                deliveriesData.add(deliveries);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return deliveriesData;
    }
    private static void numberOfMatchPerYear(List<Match> everyMatchData) {

        ListIterator<Match> it = everyMatchData.listIterator();
        TreeMap<Integer, Integer> matchesPerYear = new TreeMap<>();

        while(it.hasNext()){
            int next = it.next().getSeason();
            if(matchesPerYear.containsKey(next)){
                matchesPerYear.put(next, matchesPerYear.get(next)+1);
            }
            else {
                matchesPerYear.put(next, 1);
            }
        }
        System.out.println("Number of Matches per year played per Year are : " + matchesPerYear);
    }
    private static void numberOfMatchesWonByTeam(List<Match> everyMatchesData) {

        ListIterator<Match> it = everyMatchesData.listIterator();

        TreeMap<String,Integer> matchesWonByTeam = new TreeMap<>();

        while (it.hasNext()) {

            String winner = it.next().getWinner();
            try{
                if(winner.isBlank()){
                    continue;
                }
                if(matchesWonByTeam.containsKey(winner)) {
                    matchesWonByTeam.put(winner, matchesWonByTeam.get(winner) + 1);
                }
                else {
                    matchesWonByTeam.put(winner, 1);
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }

        }
        System.out.println("Number of Matches won by Team are :" + matchesWonByTeam);
    }
    private static void extraRunsConcededPerTeamInYear2016(List<Deliveries> everyDeliveriesData, List<Match> everyMatchData) {

        ListIterator<Deliveries> deliveriesIterator = everyDeliveriesData.listIterator();
        ListIterator<Match> matchIterator = everyMatchData.listIterator();
        TreeMap<String,Integer> extraRunsCountPerTeam2016 = new TreeMap<>();

        int firstMatchId2016;
        int lastMatchId2016 = 0;
        int counterForMatch2016 = 0;

        while(matchIterator.hasNext()){
            int matchYear = matchIterator.next().getSeason();

            if(matchYear == 2016){
                lastMatchId2016 = matchIterator.next().getId();
                counterForMatch2016++;
            }
        }
        firstMatchId2016 = lastMatchId2016 - counterForMatch2016 + 1;

        while(deliveriesIterator.hasNext()){
            Deliveries deliveryData = deliveriesIterator.next();
            if(deliveryData.getMatchId() >= firstMatchId2016 && deliveryData.getMatchId() <= lastMatchId2016){
                if(extraRunsCountPerTeam2016.containsKey(deliveryData.getBowlingTeam())){
                    extraRunsCountPerTeam2016.put(deliveryData.getBowlingTeam(), extraRunsCountPerTeam2016.get(deliveryData.getBowlingTeam()) + deliveryData.getExtraRuns());
                }
                else{
                    extraRunsCountPerTeam2016.put(deliveryData.getBowlingTeam(), deliveryData.getExtraRuns());
                }
            }
        }
        System.out.println("Extra Run count in Year 2016 is " + extraRunsCountPerTeam2016);
    }
    private static void economicalBowlerForYear2015(List<Deliveries> everyDeliveriesData, List<Match> everyMatchData) {

        ListIterator<Deliveries> deliveriesIterator = everyDeliveriesData.listIterator();
        ListIterator<Match> matchIterator = everyMatchData.listIterator();

        TreeMap<String, Integer> runsGivenByBowler2015 = new TreeMap<>();
        TreeMap<String, Integer> totalOverByBowler2015 = new TreeMap<>();

        float economyBest = 100.0f;
        String economicalBowler = "";
        int firstMatchId2015;
        int lastMatchId2015 = 0;
        int counterForMatch2015 = 0;

        while(matchIterator.hasNext()){
            int matchYear = matchIterator.next().getSeason();

            if(matchYear == 2015){
                lastMatchId2015 = matchIterator.next().getId();
                counterForMatch2015++;
            }
        }

        firstMatchId2015 = lastMatchId2015 - counterForMatch2015 + 1;

        while(deliveriesIterator.hasNext()){
            Deliveries deliveryData = deliveriesIterator.next();
            if(deliveryData.getMatchId() >= firstMatchId2015 && deliveryData.getMatchId() <= lastMatchId2015){
                if(runsGivenByBowler2015.containsKey(deliveryData.getBowler())){
                    runsGivenByBowler2015.put(deliveryData.getBowler(), runsGivenByBowler2015.get(deliveryData.getBowler()) + deliveryData.getTotalRuns());
                }
                else{
                    runsGivenByBowler2015.put(deliveryData.getBowler(), deliveryData.getTotalRuns());
                }
                if(totalOverByBowler2015.containsKey(deliveryData.getBowler())){
                    totalOverByBowler2015.put(deliveryData.getBowler(), totalOverByBowler2015.get(deliveryData.getBowler()) + (int)(deliveryData.getBall()/6));
                }
                else{
                    totalOverByBowler2015.put(deliveryData.getBowler(),0);
                }
            }
        }

        for(Map.Entry<String,Integer> entry : runsGivenByBowler2015.entrySet()){
            String bowler = entry.getKey();
            Float economy = (float)(runsGivenByBowler2015.get(entry.getKey()) / totalOverByBowler2015.get(entry.getKey()));

            if(economy < economyBest){
                economicalBowler = bowler;
                economyBest = economy;
            }
        }
        System.out.println("The best economical Bowler is " + economicalBowler + "and the economy is " + economyBest);
    }
    
}
