import java.util.*;
import java.io.*;

public class Team{
    //need to change hero to living creatures 
    private ArrayList<Hero> team = new ArrayList<Hero>();
    public Team(){
        
    }

    public int getSize(){
        return this.team.size();
    }

    public void add(Hero h){

    }

    public boolean checkrepeat(String name){
        for(Hero member:team){
            if(member.name.equals(name)){
                System.out.println("already selected");
                return true;
            }
        }
        return false;
    }

    public ArrayList<Hero> getTeam(){
        return this.team;
    }

    public Hero get(int i){
        return team.get(i);
    }
}