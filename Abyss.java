import java.util.*;
import java.io.*;

public class Abyss{
//A class stores all the information of monsters, random pick monsters when a fighting comes.
	
	Monster[] dragonList;
	Monster[] spiritList;
	Monster[] skeletonList;
	int maxLevel;

	public Abyss(){
		Monster pmonster;
		maxLevel = 10;
		dragonList = new Monster[maxLevel];
		spiritList = new Monster[maxLevel];
		skeletonList = new Monster[maxLevel];
		try {
			InputStream is = new FileInputStream("The_Quest/Dragons.txt");
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(ir);
			String str;
			String line[];
			bf.readLine();
			bf.readLine();
			do{
				if ((str = bf.readLine()) == null)
					break;
				line = str.split("\\s+");
				pmonster = new Dragon(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				dragonList[(Integer.parseInt(line[1]))-1] = pmonster;
			}while((str = bf.readLine()) != null);

			ir.close();
			is = new FileInputStream("The_Quest/Spirits.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			bf.readLine();
			do{
				if ((str = bf.readLine()) == null)
					break;
				line = str.split("\\s+");
				pmonster = new Spirit(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				spiritList[(Integer.parseInt(line[1]))-1] = pmonster;
			}while((str = bf.readLine()) != null);
			
			ir.close();
			is = new FileInputStream("The_Quest/Exoskeletons.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			bf.readLine();
			do{
				if ((str = bf.readLine()) == null)
					break;
				line = str.split("\\s+");
				pmonster = new Exoskeleton(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				skeletonList[(Integer.parseInt(line[1]))-2] = pmonster;
			}while((str = bf.readLine()) != null);
			
			bf.close();
			ir.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public Monster[] generateMonster(int num, int level){
		Monster[] monster = new Monster[num];
		for(int i = 0; i < num; i ++ ){
			double tmp = Math.random();
			if (tmp < 0.33)
				//Generate a dragon.
				monster[i] = new Dragon(dragonList[level-1].getName(),level,dragonList[level-1].getDamage(),dragonList[level-1].getDefense(),(int)(dragonList[level-1].getDodgeRate()*100));
			else if (tmp < 0.67)
				//Generate a spirit.
				monster[i] = new Spirit(spiritList[level-1].getName(),level,spiritList[level-1].getDamage(),spiritList[level-1].getDefense(),(int)(spiritList[level-1].getDodgeRate()*100));
			else
				monster[i] = new Exoskeleton(skeletonList[level-1].getName(),level,skeletonList[level-1].getDamage(),skeletonList[level-1].getDefense(),(int)(skeletonList[level-1].getDodgeRate()*100));
			monster[i].setY(i*3+1);
		}
		return monster;
	}

	// public static void main(String args[]){
	// 	Abyss theAbyss = new Abyss();
	// 	int num = 3;
	// 	int level = 10;
	// 	Monster[] theMonster = theAbyss.generateMonster(num, level);
	// 	for(int i = 0; i < num; i++)
	// 		System.out.println(theMonster[i].toString());
	// }
}