import java.util.*;
import java.io.*;

public class Quest{
//The class represents the quest game.

	static WorldMap theMap;
	static Scanner scan = new Scanner(System.in);
	static Market theMarket;
	Fight theFight;
	static Abyss theAbyss;
	Pub thePub;
	Hero[] theHeros;

	Team team = new Team();
	ArrayList<Monster> theMons = new ArrayList<Monster>();

	int xPosNow;
	int yPosNow;
	String herosSign = "H"; //"H" represents the current location of heros on the world map.
	boolean continueFlag;
	boolean herosMove;
	double engageFightRate = 0.2;
	static int round;

	public Quest(){
		System.out.println("Welcome to the Great Quest!");
		continueFlag = true;
		round = 1;
		this.createHero();
		this.createAbyss();
		this.createMap();
		this.createMarket();
		this.createStartPoint();
	}

	public void addRound(){
		round ++;
	}

	public void heroTeamTurn(){
		System.out.println("Round " + round + " for Heros:");
		int i = 0;
		while(i < 3 && continueFlag){
			if(heroAction(theHeros[i])){
				if (!continueFlag)
					break;
				showWorld();
				i ++;
			}
			else
				continue;
		}
	}

	public void monsterTeamTurn(){
		boolean infight = false;
		for(Monster m:theMons){
			for(Hero h:theHeros){
				if(checkFight(m,h)){
					infight = true;
					startFight();
				}
			}
			if(!infight){
				m.setX(m.getX()+1);
				m.setY(m.getY()+1);
			}
		}
		
	}

	public void createAbyss(){
		System.out.print("Generate the monsters...");
		theAbyss = new Abyss();
		int hilevel = 0;
		for(Hero h:theHeros){
			hilevel = Math.max(hilevel,h.getLevel());
		}
		Collections.addAll(theMons,theAbyss.generateMonster(3, hilevel));

		System.out.println("done.");
		System.out.println("current monsters:");
		for(Monster m:theMons){
			System.out.println(m);
		}
	}

	public void createStartPoint(){
		for(int i = 0; i < theHeros.length ; i ++)
			theHeros[i].setHeroPosition(theMap, 7, i*3);
	}

	public void createMap(){
		theMap = new WorldMap();
		theMap.createMap();
	}

	public void createHero(){
		herosMove = false;
		int heroNum = 3;
		System.out.print("Summon the heros...");
		// thePub = new Pub();
		// theHeros = thePub.generateHero(heroNum);


		// ArrayList<Hero> allheroes = new ArrayList<Hero>();
		// System.out.println("\nchoose your 3 heroes");
		// Explain.showHero(allheroes);
		// do{
		// 	boolean hasHero = false;
		// 	boolean included = false;
		// 	try {
		// 		System.out.println("\nEnter the hero's name: ");
		// 		String name = scan.next();
		// 		for (Hero hero:allheroes){
		// 			if(hero.name.equals(name)){
		// 				if(team.getSize()>=1){
		// 					included = team.checkrepeat(name);
		// 				}
		// 				if(!included){
		// 					System.out.println("valid name");
		// 					team.add(hero);	
		// 				}
		// 				hasHero = true;
		// 				break;
		// 			}
		// 		}
		// 		if(!hasHero||included){
		// 			System.out.println("we dont have this guy or you've selected him/her");
		// 			continue;
		// 		}	
		// 	}catch (Exception e) {
		// 		System.err.println("Invalid. Error: " + e.getMessage());
		// 		continue;
		// 	}
		// }while(team.getSize() < 3);
		// System.out.println("done.");
		// Explain.showTeam(team.getTeam());
		// theHeros = team.getTeam().toArray(theHeros);

		
		Hero[] theHeros= {new Warrior("Solonor_Thelandira",300,750,650,700,2500,7,0),new Warrior("test2",300,750,700,600,2500,7,0),new Warrior("test3",250,650,600,350,2500,4,0)};


		// for(int i = 0; i < heroNum; i++){
		// 	System.out.println(theHeros[i].getHeroType() + ", " + theHeros[i].getName());
		// }
		for(Hero h:theHeros){
			System.out.println(h);
		}
		System.out.println("Join the team.");
	}

	public void createMarket(){
		System.out.print("Build the market...");
		theMarket = new Market(theHeros);
		System.out.println("done.");
	}

	public void showWorld(){
		theMap.showMap(xPosNow,yPosNow);
	}

	public void showTeamStatus(){
		for(int i = 0; i< theHeros.length ; i ++){
			System.out.print("Hero " + (i+1) + ": ");
			System.out.println(theHeros[i].toStringFull());
		}
	}

	public void showSingleStatus(Hero hero){
		System.out.println("Hero " + hero.getHeroIdx() + " status:" + hero.toStringFull()); 
	}

	public void openItemMenu(){
		String heroTipsStr = "Select whose items?[0-"+ ((theHeros.length)-1) + "], press other keys to return:";
		String errorStr = "Invalid input.";
		// Scanner scan = new Scanner(System.in);
		while(true){
			System.out.print(heroTipsStr);
			if(!(scan.hasNextInt())){
				return;
			}
		int heroNum = scan.nextInt();
			if(heroNum < 0 || heroNum > ((theHeros.length)-1)){
				System.out.println(errorStr);
				continue;
			}
		System.out.print("For " + theHeros[heroNum].getName() + ", [A]Armor [P]Potion [W]weapon, press other keys to return:");
		String input = scan.next();
		if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){ 
				//Open the armor menu.
				secondItemMenu(theHeros[heroNum],1);
				return;
			}else if (input.charAt(0) == 'P' || input.charAt(0) == 'p'){
				//Open the potion menu.
				secondItemMenu(theHeros[heroNum],2);
				return;
			}else if(input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				//Select the item menu.
				secondItemMenu(theHeros[heroNum],0);
				return;
			}
		return;
		}
	}

	public void openSingleItemMenu(Hero hero){
		System.out.print("For " + hero.getName() + ", [A]Armor [P]Potion [W]weapon, press other keys to return:");
		// Scanner scan = new Scanner(System.in);
		String input = scan.next();
		if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){ 
				//Open the armor menu.
				secondItemMenu(hero,1);
				return;
			}else if (input.charAt(0) == 'P' || input.charAt(0) == 'p'){
				//Open the potion menu.
				secondItemMenu(hero,2);
				return;
			}else if(input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				//Select the item menu.
				secondItemMenu(hero,0);
				return;
			}
		return;
	}

	public void secondItemMenu(Hero hero,int i){
		List<Item> tmpList = hero.getList(i);
		if(hero.checkShowAnyItem(tmpList)){
			String errorStr = "Invalid input.";
			// Scanner scan = new Scanner(System.in);
			while(true){
				System.out.print("Choose one item to use(equip) [0-"+(tmpList.size()-1) + "], press other keys to return:");
				if(!(scan.hasNextInt())){
					return;
				}
				int itemNum = scan.nextInt();
				if(itemNum < 0 || itemNum > (tmpList.size()-1)){
					System.out.println(errorStr);
					continue;
				}
				if(hero.useItem(tmpList.get(itemNum)))
					hero.removeItem(tmpList, itemNum);
				return;
			}
		}else
			return;
	}

	public boolean heroAction(Hero hero){
		//Single action for a hero in one round.
		theMap.showMap(hero.getX(), hero.getY());

		String tipsEdge = "----------------------------------------------------------------------------";
		String tipsStr1 = "[W]Move Up     [S]Move Down     [A]Move left     [D]Move right     [Z]Status";
		String tipsStr2 = "[T]Teleport    [I]Info         [B]Back to nexus [Q]Quit the game";
		String tipsInput = "What will you do? Enter your action (the first letter):";
		System.out.println(tipsEdge);
		System.out.println("Hero " + hero.getHeroIdx() + ": " + hero.getName());
		System.out.println(tipsStr1);
		System.out.println(tipsStr2);
		System.out.print(tipsInput);
		boolean actionFlag = checkInput(hero);
		return actionFlag;
	}

	public boolean checkInput(Hero hero){
		String tipsBound = "Heros can't cross the edge of the world. Try another action:";
		String tipsNonAcc = "Oops! The cell heros want to go is non-accessible or existed a hero already. Try another action:";
		String tipsInvalid = "Invalid input. Try another action:";
		// Scanner scan = new Scanner(System.in);
		herosMove = false;
		while(true){
			String input = scan.next();
			if (input.charAt(0) == 'Q' || input.charAt(0) == 'q'){ 
				//Quit the game.
				System.out.println("Quit the game, bye-bye!");
				this.setGameContinue(false);
				return true;
			}else if (input.charAt(0) == 'Z' || input.charAt(0) == 'z'){
				//Show the heros team status.
				this.showSingleStatus(hero);
				return false;
			}else if(input.charAt(0) == 'I' || input.charAt(0) == 'i'){
				//Select the item menu.
				System.out.println(hero);
				this.openSingleItemMenu(hero);
				return false;
			}else if (input.charAt(0) == 'T' || input.charAt(0) == 't'){
				//Hero teleports.
				System.out.print("Teleport to which lane?[1-3]:");
				while(!(scan.hasNextInt())) {
					System.out.print("Invalid input, must be [1-3], try again:");
					scan.next();
				}
				int inputLane = scan.nextInt();
				switch(inputLane){
					case 1:
						if(hero.getY()==0||hero.getY()==1){
							System.out.println("Hero "+ hero.getHeroIdx() + " is in Top Land already.");
							return false;
						}else{
							if(!theMap.checkNonAccess(hero.getX(), 0))
								hero.setHeroPosition(theMap,hero.getX(), 0);
							else if (!theMap.checkNonAccess(hero.getX(), 1))
								hero.setHeroPosition(theMap,hero.getX(), 1);
							else if(hero.getX()==7)
								hero.setHeroPosition(theMap,hero.getX()-1, 0);
							else
								hero.setHeroPosition(theMap,hero.getX()+1, 0);
							return true;
						}
					case 2:
						if(hero.getY()==3||hero.getY()==4){
							System.out.println("Hero "+ hero.getHeroIdx() + " is in Mid Land already.");
							return false;
						}else{
							if(!theMap.checkNonAccess(hero.getX(), 3))
								hero.setHeroPosition(theMap,hero.getX(), 3);
							else if (!theMap.checkNonAccess(hero.getX(), 4))
								hero.setHeroPosition(theMap,hero.getX(), 4);
							else if(hero.getX()==7)
								hero.setHeroPosition(theMap,hero.getX()-1, 3);
							else
								hero.setHeroPosition(theMap,hero.getX()+1, 3);
							return true;
						}
					case 3:
						if(hero.getY()==6||hero.getY()==7){
							System.out.println("Hero "+ hero.getHeroIdx() + " is in Bot Land already.");
							return false;
						}else{
							if(!theMap.checkNonAccess(hero.getX(), 6))
								hero.setHeroPosition(theMap,hero.getX(), 6);
							else if (!theMap.checkNonAccess(hero.getX(), 7))
								hero.setHeroPosition(theMap,hero.getX(), 7);
							else if(hero.getX()==7)
								hero.setHeroPosition(theMap,hero.getX()-1, 6);
							else
								hero.setHeroPosition(theMap,hero.getX()+1, 6);
							return true;
						}
					default:
						System.out.println("Invalid input.");
						return false;
				}
			}else if (input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				//Move up.
				if(hero.getX() == 0){
					System.out.print(tipsBound);
					continue;
				}else if(theMap.checkNonAccess(hero.getX()-1 , hero.getY())){
					System.out.print(tipsNonAcc);
					continue;
				}else{
					hero.setHeroPosition(theMap,hero.getX()-1, hero.getY());
					return true;
				}
			}else if (input.charAt(0) == 'S' || input.charAt(0) == 's'){
				//Move down.
				if(hero.getX() == 7){
					System.out.print(tipsBound);
					continue;
				}else if(theMap.checkNonAccess(hero.getX()+1 , hero.getY())){
					System.out.print(tipsNonAcc);
					continue;
				}else{
					hero.setHeroPosition(theMap,hero.getX()+1, hero.getY());
					return true;
				}
			}else if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){
				//Move left.
				if(hero.getY() == 0){
					System.out.print(tipsBound);
					continue;
				}else if(theMap.checkNonAccess(hero.getX() , hero.getY()-1)){
					System.out.print(tipsNonAcc);
					continue;
				}else{
					hero.setHeroPosition(theMap, hero.getX() , hero.getY()-1);
					return true;
				}
			}else if (input.charAt(0) == 'D' || input.charAt(0) == 'd'){
				//Move right.
				if(yPosNow == 7){
					System.out.print(tipsBound);
					continue;
				}else if(theMap.checkNonAccess(hero.getX() , hero.getY()+1)){
					System.out.print(tipsNonAcc);
					continue;
				}else{
					hero.setHeroPosition(theMap, hero.getX() , hero.getY()+1);
					return true;
				}
			}else if (input.charAt(0) == 'B' || input.charAt(0) == 'b'){
				//Back to nexus.
				if(theMap.checkNonAccess(7,(hero.getHeroIdx()-1)*3)){
					System.out.print(tipsNonAcc);
					continue;
				}else{
					hero.setHeroPosition(theMap, 7, (hero.getHeroIdx()-1)*3);
					return true;
				}
			}else{
				System.out.print(tipsInvalid);
				continue;
			}
		}
	}

	public boolean getGameContinue(){
		return continueFlag;
	}

	public void setGameContinue(boolean flag){
		continueFlag = flag;
	}

	public boolean checkFight(Monster m,Hero h){
		// Heros engage in a fight only when they visit a common cell.
		// if(herosMove && theMap.getCellLabel(xPosNow, yPosNow)==" "){
		// 	double tmp = Math.random();
		// 	if(tmp < engageFightRate){
		// 		return true;
		// 	}
		// }
		// return false;
		
		if((h.getX()==m.getX()&&Math.abs(h.getY()-m.getY())<=1) || ((h.getY()==m.getY()&&Math.abs(h.getX()-m.getX())<=1))){
			return true;
		}
		
		return false;
	}

	public boolean checkMarket(){
		if(herosMove && theMap.getCellLabel(xPosNow, yPosNow)=="M"){
			return true;
		}
		return false;
	}

	public boolean checkWinEnding(){
		//Check whether a hero or a monster reach the opposite nexus.
		for(int i = 0; i< theHeros.length; i ++){
			if (theHeros[i].getX()== 0){
				System.out.println(theHeros[i].getName() + " gets to the dark nexus!");
				System.out.println("*******************************");
				System.out.println("* Congratulations! Heros win! *");
				System.out.println("*******************************");
				return true;
			}
		}
		return false;
	}

	public void startFight(){
		theFight = new Fight(theHeros, theAbyss);
		theFight.singleFight();
	}

	public void visitMarket(){
		theMarket.visitMarket();
	}

	public static void main(String[] args){
		Quest theQuest = new Quest();
		theQuest.showWorld();
		while (theQuest.getGameContinue()){
			theQuest.heroTeamTurn();
			theQuest.monsterTeamTurn();
			theQuest.addRound();
			if(round==8){
				theQuest.createAbyss();
			}
			if(theQuest.checkWinEnding()){
				return;
			}
		}
		// 	theQuest.checkInput();
		// 	if(theQuest.checkFight()){
		// 		//Heros engage a fight.
		// 		System.out.println("Monsters appear!");
		// 		theQuest.startFight();
		// 		if(theQuest.checkWinEnding()){
		// 		//Players win the game.
		// 		System.out.println("*****************************");
		// 		System.out.println("* Congratulations! You win! *");
		// 		System.out.println("*****************************");
		// 		return;
		// 	}
		// 	}else if (theQuest.checkMarket()){
		// 		//Heros visit a market.
		// 		System.out.println("Heros visit a market.");
		// 		theQuest.visitMarket();
		// 	}
		// }
	}

}