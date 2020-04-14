import java.util.*;
import java.io.*;

public class Pub{
//A class stores all the information of heros, random pick heros when a game begin.
	
	Hero[] paladinList;
	Hero[] warriorList;
	Hero[] sorcererList;
	int maxNum;

	Pub(){
		Hero phero;
		maxNum = 5;
		paladinList = new Hero[maxNum];
		warriorList = new Hero[maxNum];
		sorcererList = new Hero[maxNum];
		try {
			InputStream is = new FileInputStream("The_Quest/Paladins.txt");
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(ir);
			String str;
			String line[];
			bf.readLine();
			int i = 0;
			while ((str = bf.readLine()) != null) {
				line = str.split("\\s+");
				phero = new Paladin(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]),Integer.parseInt(line[5]),Integer.parseInt(line[6]),0);
				paladinList[i] = phero;
				i ++;
			}
			ir.close();
			is = new FileInputStream("The_Quest/Warriors.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			i = 0;
			while ((str = bf.readLine()) != null) {
				line = str.split("\\s+");
				phero = new Warrior(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]),Integer.parseInt(line[5]),Integer.parseInt(line[6]),0);
				warriorList[i] = phero;
				i ++;
			}
			i = 0;
			ir.close();
			is = new FileInputStream("The_Quest/Sorcerers.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			while ((str = bf.readLine()) != null) {
				line = str.split("\\s+");
				phero = new Sorcerer(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]),Integer.parseInt(line[5]),Integer.parseInt(line[6]),0);
				sorcererList[i] = phero;
				i ++;
			}
			bf.close();
			ir.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public Hero[] generateHero(int num){
		Hero[] hero = new Hero[num];
		int a = 0;
		int b = 0;
		int c = 0;
		for(int i = 0; i < num; i ++ ){
			double tmp = Math.random();
			if (tmp < 0.33){
				//Generate a paladin.
				hero[i] = paladinList[a];
				a ++ ;
			}else if (tmp < 0.67){
				//Generate a warrior.
				hero[i] = warriorList[b];
				b ++ ;
			}else{
				//Generate a sorcerer.
				hero[i] = sorcererList[c];
				c ++ ;
			}
			hero[i].setHeroIdx(i+1);
		}
		return hero;
	}

	public static void main(String args[]){
		Pub thePub= new Pub();
		int num = 3;
		Hero[] theHero = thePub.generateHero(num);
		for(int i = 0; i < num; i++)
			System.out.println(theHero[i].toString());
	}
}