package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Leaderboard {

    public static ArrayList<JSONObject> PlayerList() throws IOException, ParseException{

        FileReader file = new FileReader("src/Leaderboard.json");
        JSONTokener tokener = new JSONTokener(file);
        JSONArray list = new JSONArray (tokener);

        ArrayList<JSONObject> ranking = new ArrayList<>();   

        for (Object player: list){

            JSONObject playerjson = (JSONObject) player;

            ranking.add((JSONObject) playerjson);
            
        }

        Collections.sort(ranking, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {

                String lscore = (String) lhs.get("score");
                String rscore = (String) rhs.get("score");
                // Here you could parse string id to integer and then compare.
                return Integer.compare(Integer.parseInt(rscore), Integer.parseInt(lscore));
            }
        });

        return ranking;

    }

    public static boolean Check(int score) throws IOException, ParseException {

        ArrayList<JSONObject> playerlist = PlayerList();

        for(Object player: playerlist){

            JSONObject haha = (JSONObject) player;

            if (score > Integer.parseInt((String) haha.get("score"))){

                return true;

            }          

        }

        return false;

    }

    public static void AddPlayer(String s, Integer newscore) throws IOException, ParseException{

        JSONObject newPlayer = new JSONObject();
        newPlayer.put("name",s);
        newPlayer.put("score",newscore.toString());

        JSONArray newLeaderBaord = new JSONArray();

        ArrayList<JSONObject> playerlist = PlayerList();

        boolean execute = false;

        for (int i = 0; i < 10; i++){

            if (!execute && (Integer.parseInt((String) newPlayer.get("score")) > Integer.parseInt((String) playerlist.get(i).get("score")))){

                newLeaderBaord.put(i, newPlayer);
                execute = true;
                i++;
                if (i >= 10 ){
                    break;
                }
                newLeaderBaord.put(i, playerlist.get(i));
                
            } else {

                newLeaderBaord.put(i,playerlist.get(i));

            }
             
        }

        try (FileWriter file = new FileWriter("src/Leaderboard.json")) {
            file.write(newLeaderBaord.toString());
            file.flush();
        }
        
    }
    

}
