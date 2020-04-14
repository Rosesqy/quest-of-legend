import java.util.*;
public class Fight{
// A class represents the fight.

	Hero[] theHeros;
	Monster[] theMonsters;
	int[] heroSurvive; //"0" represents a hero faints.
	int[] monsterSurvive; //"0" represents a monster faints.
	int vsNum;
	int highestLevel;
	int continueFlag; // "2" represents fight continue, "1" means heros win, "0" means monsters win. 
	Abyss theAbyss;

	Hero singleHero;
	Monster singleMonster;

	// Fight(Hero[] heros, Abyss abyss){
	// //Generate the monsters.

	// 	theHeros = heros;
	// 	theAbyss = abyss;
	// 	vsNum = theHeros.length;
	// 	highestLevel = theHeros[0].getLevel();
	// 	for(int i = 1; i < vsNum ; i ++ ){
	// 		if( theHeros[i].getLevel() > highestLevel )
	// 			highestLevel = theHeros[i].getLevel();
	// 	}
		
	// 	heroSurvive = new int[vsNum];
	// 	monsterSurvive = new int[vsNum];
	// 	for(int i = 0; i < vsNum ; i ++ ){
	// 		heroSurvive[i] = (theHeros[i].getHp() == 0)?0:1;
	// 		monsterSurvive[i] = 1;
	// 	}
	// 	theMonsters = theAbyss.generateMonster(vsNum, highestLevel);
	// 	// theMonsters = new Monster[vsNum];
		
	// 	continueFlag = 2;
	// }

	public Fight(Hero hero, Monster monster){
		singleHero = hero;
		singleMonster = monster;

	}

	public void singleMonsterAttack(){
		System.out.println(singleMonster.getName() + " attacks the hero " + singleHero.getName());
		double tmp = Math.random();
		if(tmp < (singleHero.getAgility()*0.001)){
			System.out.println(" Miss!");
			return;
		}
		int monsterDamage = singleMonster.getDamage();
		int realDamage = (monsterDamage > singleHero.getDefense())?( monsterDamage - singleHero.getDefense()):0;
		if(realDamage >= singleHero.getHp()){
			//The hero faints, the damage overflow.
			realDamage = singleHero.getHp();
		}
		System.out.println(" Cause " + realDamage + " damage to " + singleHero.getName() + ".");
		singleHero.addHp( -1 * realDamage);
		return;
	}

	public void singleHeroAttack(){
		
		if(singleHero.getHp() > 0){
			System.out.print("Turn of H"+ singleHero.getHeroIdx() + singleHero.getName()+ ":[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
			this.singleHeroDo();

		}
		
	}

	public void singleHeroDo(){
		Scanner scan = new Scanner(System.in);
		while(true){
			String input = scan.next();
			if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){
				if(singleMonster.getHp()>0){
					System.out.print(singleHero.getName() + " attacks " + singleMonster.getName() + ".");
					this.heroAttackMonster(singleHero,singleMonster);
					if(singleMonster.getHp()== 0){
						System.out.println(singleMonster.getName() + " was defeated!");
						
					}
				}
				else{
					for(int j = 0; j < vsNum ; j ++){
						if(monsterSurvive[j] == 1){
							System.out.print(singleHero.getName() + " attacks " + theMonsters[j].getName() + ".");
							this.heroAttackMonster(singleHero,theMonsters[j]);
							if(theMonsters[j].getHp() == 0){
								System.out.println(theMonsters[j].getName() + " was defeated!");
								monsterSurvive[j] = 0;
							}
							break;
						}
					}
				}
				return;
			}else if (input.charAt(0) == 'S' || input.charAt(0) == 's'){
				if(singleHero.checkEnoughManaAnySpell() && singleHero.showLearnedSpell()){
					while(true){
						System.out.print("Choose which spell to cast [0-" + (singleHero.getLearnSpellNum()-1) + "]:");
						if(!scan.hasNextInt()){
							System.out.print("Invalid input. ");
							continue;
						}
						int spellNum = scan.nextInt();
						if(spellNum < 0 || spellNum > (singleHero.getLearnSpellNum()-1)){
							System.out.print("Invalid input. ");
							continue;
						}
						if(!singleHero.checkEnoughManaSingleSpell(spellNum))
							continue;
						//Cast a spell successfully.
						Spell useSpell = singleHero.getSingleSpell(spellNum);
						if(singleMonster.getHp()>0){
							System.out.print(singleHero.getName() + " casts " + useSpell.getName() + " to " + singleMonster.getName() + ".");
							this.heroSpellMonster(singleHero, singleMonster, useSpell);
							if(singleMonster.getHp()== 0){
								System.out.println(singleMonster.getName() + " was defeated!");
								
							}
						}
						else{
							
								if(singleMonster.getHp()>0){
									System.out.print(singleHero.getName() + " casts " + useSpell.getName() + " to " + singleMonster.getName() + ".");
									this.heroSpellMonster(singleHero, singleMonster, useSpell);
									if(singleMonster.getHp()== 0){
										System.out.println(singleMonster.getName() + " was defeated!");
										
									}
									break;
								}
							
						}
						return;
					}
				}
				else
					System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
					continue;
			}else if (input.charAt(0) == 'P' || input.charAt(0) == 'p'){
				if (this.heroUseItem(singleHero,2))
					return;
				System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}else if (input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				if (this.heroUseItem(singleHero,0))
					return;
				System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}else if (input.charAt(0) == 'R' || input.charAt(0) == 'r'){
				if (this.heroUseItem(singleHero,1))
					return;
				System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}else{
				System.out.print("Invalid action. Try again:[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}
		}
	
	}

	public void showStatus(){
		for(int i = 0; i < vsNum ; i ++){
			System.out.print("Monster " + (i+1) + ": ");
			if(monsterSurvive[i] == 1){
				System.out.println(theMonsters[i].toString());
			}else{
				System.out.println("Defeated");
			}
		}
		for(int i = 0; i< vsNum ; i ++){
			System.out.print("Hero " + (i+1) + ": ");
			if(heroSurvive[i] == 1){
				System.out.println(theHeros[i].toString());
			}else{
				System.out.println("Fainted");
			}
		}
	}

	public void herosTurn(){
		for(int i = 0; i < vsNum ; i ++){
			if(heroSurvive[i] == 1){
				System.out.print("Turn of hero "+ (i + 1) + ":[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				this.heroDo(i);
				if(this.checkEnd())
					break;
			}
		}
	}

	public boolean checkEnd(){
		int heroCal = 0;
		int monsterCal = 0;
		for(int i = 0; i < vsNum ; i ++ ){
			heroCal = heroCal + heroSurvive[i];
			monsterCal = monsterCal + monsterSurvive[i];
		}
		if(heroCal == 0){
			System.out.println("All the heros faint, lose the fight.");
			continueFlag = 0;
			return true;
		}
		if(monsterCal == 0){
			System.out.println("The heros win the fight. Congratulation!");
			continueFlag = 1;
			return true;
		}
		return false;
	}

	public void heroDo(int nowId){
		Scanner scan = new Scanner(System.in);
		while(true){
			String input = scan.next();
			if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){
				if(monsterSurvive[nowId] == 1){
					System.out.print(theHeros[nowId].getName() + " attacks " + theMonsters[nowId].getName() + ".");
					this.heroAttackMonster(theHeros[nowId],theMonsters[nowId]);
					if(theMonsters[nowId].getHp()== 0){
						System.out.println(theMonsters[nowId].getName() + " was defeated!");
						monsterSurvive[nowId] = 0;
					}
				}
				else{
					for(int j = 0; j < vsNum ; j ++){
						if(monsterSurvive[j] == 1){
							System.out.print(theHeros[nowId].getName() + " attacks " + theMonsters[j].getName() + ".");
							this.heroAttackMonster(theHeros[nowId],theMonsters[j]);
							if(theMonsters[j].getHp() == 0){
								System.out.println(theMonsters[j].getName() + " was defeated!");
								monsterSurvive[j] = 0;
							}
							break;
						}
					}
				}
				return;
			}else if (input.charAt(0) == 'S' || input.charAt(0) == 's'){
				if(theHeros[nowId].checkEnoughManaAnySpell() && theHeros[nowId].showLearnedSpell()){
					while(true){
						System.out.print("Choose which spell to cast [0-" + (theHeros[nowId].getLearnSpellNum()-1) + "]:");
						if(!scan.hasNextInt()){
							System.out.print("Invalid input. ");
							continue;
						}
						int spellNum = scan.nextInt();
						if(spellNum < 0 || spellNum > (theHeros[nowId].getLearnSpellNum()-1)){
							System.out.print("Invalid input. ");
							continue;
						}
						if(!theHeros[nowId].checkEnoughManaSingleSpell(spellNum))
							continue;
						//Cast a spell successfully.
						Spell useSpell = theHeros[nowId].getSingleSpell(spellNum);
						if(monsterSurvive[nowId] == 1){
							System.out.print(theHeros[nowId].getName() + " casts " + useSpell.getName() + " to " + theMonsters[nowId].getName() + ".");
							this.heroSpellMonster(theHeros[nowId], theMonsters[nowId], useSpell);
							if(theMonsters[nowId].getHp()== 0){
								System.out.println(theMonsters[nowId].getName() + " was defeated!");
								monsterSurvive[nowId] = 0;
							}
						}
						else{
							for(int j = 0; j < vsNum ; j ++){
								if(monsterSurvive[j] == 1){
									System.out.print(theHeros[nowId].getName() + " casts " + useSpell.getName() + " to " + theMonsters[j].getName() + ".");
									this.heroSpellMonster(theHeros[nowId], theMonsters[j], useSpell);
									if(theMonsters[j].getHp()== 0){
										System.out.println(theMonsters[j].getName() + " was defeated!");
										monsterSurvive[j] = 0;
									}
									break;
								}
							}
						}
						return;
					}
				}
				else
					System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
					continue;
			}else if (input.charAt(0) == 'P' || input.charAt(0) == 'p'){
				if (this.heroUseItem(theHeros[nowId],2))
					return;
				System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}else if (input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				if (this.heroUseItem(theHeros[nowId],0))
					return;
				System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}else if (input.charAt(0) == 'R' || input.charAt(0) == 'r'){
				if (this.heroUseItem(theHeros[nowId],1))
					return;
				System.out.print("[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}else{
				System.out.print("Invalid action. Try again:[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
				continue;
			}
		}
	}

	public boolean heroUseItem(Hero hero,int i){
		List<Item> itemList = hero.getList(i);
		if(hero.checkShowAnyItem(itemList)){
			String errorStr = "Invalid input.";
			Scanner scan = new Scanner(System.in);
			while(true){
				System.out.print("Choose one item to use(equip) [0-"+(itemList.size()-1) + "], press other keys to return:");
				if(!(scan.hasNextInt()))
					return false;
				int itemNum = scan.nextInt();
				if(itemNum < 0 || itemNum > (itemList.size()-1)){
					System.out.println(errorStr);
					continue;
				}
				if(itemList.get(itemNum).getEquipped()){
					System.out.println("This item has been equipped already!");
					return false;
				}
				if(hero.useItem(itemList.get(itemNum)))
					hero.removeItem(itemList, itemNum);
				return true;
			}
		}
		return false;
	}

	public void heroAttackMonster(Hero hero, Monster monster){
		//First check whether the attack is miss.
		double tmp = Math.random();
		if(tmp < monster.getDodgeRate()){
			System.out.println(" Miss!");
			return;
		}
		int heroDamage = hero.getAttackDamage();
		int realDamage = (heroDamage > monster.getDefense())?( heroDamage - monster.getDefense()):0;
		if(realDamage >= monster.getHp()){
			//Beat the monster, the damage overflow.
			realDamage = monster.getHp();
		}
		monster.addHp( -1 * realDamage);
		System.out.println(" Cause " + realDamage + " damage to " + monster.getName() + ".");
	}

	public void heroSpellMonster(Hero hero, Monster monster, Spell spell){
		hero.addMana(-1*(spell.getCostMana()));
		int realDamage = (int)((((float)(hero.getDexterity())/10000)+1)*spell.getDamage());
		//The spell damage is the real damage.
		if(realDamage >= monster.getHp()){
			//Beat the monster, the damage overflow.
			realDamage = monster.getHp();
			monster.addHp( -1 * realDamage);
			System.out.print(" Cause " + realDamage + " damage to " + monster.getName() + ". ");
			return;
		}
		monster.addHp( -1 * realDamage);
		System.out.print(" Cause " + realDamage + " damage to " + monster.getName() + ". ");
		spell.deterioration(monster);
	}

	public void monsterAttackHero(Monster monster, Hero hero){
		//First check whether the attack is miss.
		double tmp = Math.random();
		if(tmp < (hero.getAgility()*0.001)){
			System.out.println(" Miss!");
			return;
		}
		int monsterDamage = monster.getDamage();
		int realDamage = (monsterDamage > hero.getDefense())?( monsterDamage - hero.getDefense()):0;
		if(realDamage >= hero.getHp()){
			//The hero faints, the damage overflow.
			realDamage = hero.getHp();
		}
		System.out.println(" Cause " + realDamage + " damage to " + hero.getName() + ".");
		hero.addHp( -1 * realDamage);
	}

	public void monsterTurn(){
		for(int i = 0; i < vsNum ; i ++){
			if(monsterSurvive[i] == 1){
				this.monsterDo(i);
				if(this.checkEnd())
					break;
			}
		}
	}

	public void monsterDo(int nowId){
		if(heroSurvive[nowId] == 1){
			System.out.print(theMonsters[nowId].getName() + " attacks " + theHeros[nowId].getName() + ".");
			this.monsterAttackHero(theMonsters[nowId],theHeros[nowId]);
			if(theHeros[nowId].getHp()== 0){
				System.out.println(theHeros[nowId].getName() + " faints!");
				heroSurvive[nowId] = 0;
			}
			return;
		}else{
			for(int j = 0; j < vsNum ; j ++){
				if(heroSurvive[j] == 1){
					System.out.print(theMonsters[nowId].getName() + " attacks " + theHeros[j].getName() + ".");
					this.monsterAttackHero(theMonsters[nowId],theHeros[j]);
					if(theHeros[j].getHp()== 0){
						System.out.println(theHeros[j].getName() + " faints!");
						heroSurvive[j] = 0;
					}
					break;
				}
			}
		return;
		}
	}

	public void singleFight(){
		while(continueFlag == 2){
			System.out.println("-------------------------------------------------------------------------");
			this.showStatus();
			System.out.println("-------------------------------------------------------------------------");
			this.herosTurn();
			if(continueFlag != 2){
				System.out.println("-------------------------------------------------------------------------");
				this.fightReward();
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			System.out.println("-------------------------------------------------------------------------");
			this.monsterTurn();
			if(continueFlag != 2){
				this.fightPenalty();
				System.out.println("-------------------------------------------------------------------------");
				break;
			}
			this.herosRegain();
		}
	}

	public void herosRegain(){
		for(int i = 0; i < vsNum ; i ++ ){
			if(heroSurvive[i] == 1){
				theHeros[i].addHp((int)(theHeros[i].getHp()*0.05));
				theHeros[i].addMana((int)(theHeros[i].getMana()*0.05));
			}
		}
	}

	public void fightReward(){
		for(int i = 0; i < vsNum ; i ++ ){
			if(heroSurvive[i] == 1){
				theHeros[i].addMoney(150);
				theHeros[i].addExperience(2);
				if(theHeros[i].checkLevelUp())
					theHeros[i].levelUp();
			}else{
				theHeros[i].getRevived();
			}
		}
	}

	public void fightPenalty(){
		for(int i = 0; i < vsNum ; i ++ ){
			theHeros[i].addMoney((int)(-0.5*theHeros[i].getMoney()));
			theHeros[i].getRevived();
		}
	}

	// public static void main(String args[]){
	// 	Hero[] HerosTeam = new Hero[3];
	// 	HerosTeam[0] = new Paladin("Solonor_Thelandira", 300, 750, 650, 700, 2500, 7);
	// 	HerosTeam[1] = new Warrior("Sehanine_Monnbow", 600, 700, 800, 500, 2500, 8);
	// 	HerosTeam[2] = new Sorcerer("Garl_Glittergold",700, 550, 600, 500, 2500, 7);
	// 	Fight theFight = new Fight(HerosTeam);
	// 	theFight.singleFight();
	// }
}