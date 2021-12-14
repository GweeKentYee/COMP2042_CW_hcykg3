package Game.Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Leaderboard {

    public static ArrayList<JSONObject> PlayerList() throws IOException, ParseException{

        FileReader file = new FileReader("src/Game/Model/Leaderboard.json");
        JSONTokener token = new JSONTokener(file);
        JSONArray list = new JSONArray (token);

        ArrayList<JSONObject> ranking = new ArrayList<>();   

        for (Object player: list){

            JSONObject playerjson = (JSONObject) player;

            ranking.add((JSONObject) playerjson);
            
        }

        ranking.sort(new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {

                String llevel = (String) lhs.get("level");
                String rlevel = (String) rhs.get("level");
                // Here you could parse string id to integer and then compare.
                return Integer.compare(Integer.parseInt(rlevel), Integer.parseInt(llevel));
            }
        });

        return ranking;

    }

    public static boolean Check(int level, int score, String time) throws IOException, ParseException {

        ArrayList<JSONObject> playerlist = PlayerList();

        String [] currentTimeStr = time.split(":");
        int currentTime = Integer.parseInt(currentTimeStr[0]) * 60 + Integer.parseInt(currentTimeStr[1]);

        for(JSONObject player: playerlist){

            String [] previousTimeStr = ((String) player.get("time")).split(":");
            int previousTime = Integer.parseInt(previousTimeStr[0]) * 60 + Integer.parseInt(previousTimeStr[1]);

            if (level > Integer.parseInt((String) player.get("level"))) {

                return true;

            } else if (level == Integer.parseInt((String) player.get("level")) && score > Integer.parseInt((String) player.get("score"))){

                return true;

            } else if (score == Integer.parseInt((String) player.get("score")) && currentTime > previousTime ){

                return true;

            }         

        }

        return false;

    }

    public static void AddPlayer(String s, Integer level, Integer score, String time) throws IOException, ParseException{

        JSONObject newPlayer = new JSONObject();
        newPlayer.put("name",s);
        newPlayer.put("level", level.toString());
        newPlayer.put("score",score.toString());
        newPlayer.put("time", time);

        JSONArray newLeaderBaord = new JSONArray();

        ArrayList<JSONObject> playerlist = PlayerList();

        boolean execute = false;

        String [] currentTimeStr = time.split(":");
        int currentTime = Integer.parseInt(currentTimeStr[0]) * 60 + Integer.parseInt(currentTimeStr[1]);

        int x = 0;

        for (int i = 0; i < 7; i++){

            String [] previousTimeStr = ((String) playerlist.get(i).get("time")).split(":");
            int previousTime = Integer.parseInt(previousTimeStr[0]) * 60 + Integer.parseInt(previousTimeStr[1]);


            if (!execute && level > Integer.parseInt((String) playerlist.get(i).get("level"))){

                newLeaderBaord.put(i, newPlayer);
                execute = true;
                i++;
                if (i >= 7 ){
                    break;
                }
                newLeaderBaord.put(i, playerlist.get(x));
                x++;

            } else if(!execute && level == Integer.parseInt((String) playerlist.get(i).get("level")) && Integer.parseInt((String) newPlayer.get("score")) > Integer.parseInt((String) playerlist.get(i).get("score"))){

                newLeaderBaord.put(i, newPlayer);
                execute = true;
                i++;
                if (i >= 7 ){
                    break;
                }
                newLeaderBaord.put(i, playerlist.get(x));
                x++;
                
            } else if (!execute && level == Integer.parseInt((String) playerlist.get(i).get("level")) && Integer.parseInt((String) newPlayer.get("score")) == Integer.parseInt((String) playerlist.get(i).get("score")) && currentTime > previousTime){

                newLeaderBaord.put(i, newPlayer);
                execute = true;
                i++;
                if (i >= 7 ){
                    break;
                }
                newLeaderBaord.put(i, playerlist.get(x));
                x++;

            } else {

                newLeaderBaord.put(i,playerlist.get(x));
                x++;

            }
             
        }

        try (FileWriter file = new FileWriter("src/Game/Model/Leaderboard.json")) {
            file.write(newLeaderBaord.toString());
            file.flush();
        }
        
    }
    

}
