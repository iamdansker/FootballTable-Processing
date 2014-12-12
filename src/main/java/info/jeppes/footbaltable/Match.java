/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author jeppe
 */
public class Match implements Comparable<Match>{

    private int id;
    private Calendar startTime;
    private TreeMap<Calendar,Integer> goals;

    public Match() {
        this(-1);
    }

    public Match(int id) {
        this(id, System.currentTimeMillis());
    }

    public Match(int id, long startTime) {  
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(startTime);
        
        this.startTime = calendar;
        this.id = id;
        goals = new TreeMap();
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
    
    public Calendar getOldestGoalTime(){
        Set<Calendar> dates = getGoalsTimeStamps();
        Iterator<Calendar> iterator = dates.iterator();
        Calendar next = getStartTime();
        if(iterator.hasNext()){
            next = iterator.next();
        }
        return next;
    }
    
    public Calendar getNewestGoalTime(){
        Set<Calendar> dates = getGoalsTimeStamps();
        Iterator<Calendar> iterator = dates.iterator();
        Calendar next = getStartTime();
        while(iterator.hasNext()){
            next = iterator.next();
        }
        return next;
    }
    
    public void addGoal(int player, long time){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(time);
        getGoalsMap().put(calendar, player);
    }
    
    public Map<Calendar,Integer> getGoalsMap(){
        return goals;
    }
    
    public Integer[] getGoals(){
        return getGoalsMap().values().toArray(new Integer[getGoalsMap().size()]);
    }
    
    public Set<Calendar> getGoalsTimeStamps(){
        return getGoalsMap().keySet();
    }
    
    public HashMap<Calendar, Integer> getGoalsByPlayer(int playerID){
        HashMap<Calendar,Integer> goalsByPlayer = new HashMap();
        for(Calendar timeStamp : getGoalsTimeStamps()){
            if(getGoalsMap().get(timeStamp) == playerID){
                goalsByPlayer.put(timeStamp, playerID);
            }
        }
        return goalsByPlayer;
    }
    
    @Override
    public int compareTo(Match otherMatch){
        return getOldestGoalTime().compareTo(otherMatch.getOldestGoalTime());
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", startTime=" + startTime + ", goals=" + goals + '}';
    }
    
}
