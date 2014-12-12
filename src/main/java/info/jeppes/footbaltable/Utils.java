/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jeppe
 */
public class Utils {
    
    private static TreeMap<Calendar, Match> allCachedMaches = null;
    
    public static Match getMatch(int matchID) {
        JSONObject json;
        try {
            json = new JSONObject(readURL("http://178.62.164.18:8080/foosball/api/match/get/" + matchID));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        JSONArray matches = json.getJSONArray("matches");
        JSONObject matchJSON = matches.getJSONObject(0);

        Match match = new Match(matchJSON.getInt("id"), matchJSON.getInt("startTime") * 1000l);
        JSONArray goalsJSON = matchJSON.getJSONArray("goals");

        for (int i = 0; i < goalsJSON.length(); i++) {
            JSONObject goal = goalsJSON.getJSONObject(i);
            int player = goal.getInt("player");
            int time = goal.getInt("time");
            match.addGoal(player, time * 1000l);
        }
        return match;
    }
    
    public static TreeMap<Calendar, Match> getAllMatches() {
        return getAllMatches(false);
    }
    
    public static TreeMap<Calendar, Match> getAllMatches(boolean ignoreEmpty) {
        return getAllMatches(ignoreEmpty, true);
    }
    
    public static TreeMap<Calendar, Match> getAllMatches(boolean ignoreEmpty, boolean refresh) {
        if(!refresh && allCachedMaches != null){
            return allCachedMaches;
        }
        JSONObject json;
        try {
            json = new JSONObject(readURL("http://178.62.164.18:8080/foosball/api/match/getAll"));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        TreeMap<Calendar, Match> matches = new TreeMap();
        
        JSONArray matchesJSON = json.getJSONArray("matches");
        for(int i = 0; i < matchesJSON.length(); i++){
            JSONObject matchJSON = matchesJSON.getJSONObject(i);
            Match match = new Match(matchJSON.getInt("id"), matchJSON.getInt("startTime") * 1000l);
            JSONArray goalsJSON = matchJSON.getJSONArray("goals");

            for (int k = 0; k < goalsJSON.length(); k++) {
                JSONObject goal = goalsJSON.getJSONObject(k);
                int player = goal.getInt("player");
                int time = goal.getInt("time");
                match.addGoal(player, time * 1000l);
            }
            
            if(ignoreEmpty && match.getGoalsMap().isEmpty()){
                continue;
            }
            matches.put(match.getOldestGoalTime(), match);
        }
        allCachedMaches = matches;
        return allCachedMaches;
    }
    
    public static String readURL(String address) throws IOException{
        StringBuilder response = new StringBuilder();
        URL url = new URL(address);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));
        
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        
        return response.toString();
    }

}
