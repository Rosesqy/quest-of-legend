import java.util.*;
public class Fight{
// A class represents the fight.
	int continueFlag; // "2" represents fight continue, "1" means heros win, "0" means monsters win. 
	private Hero singleHero;
	private Monster singleMonster;
	static Scanner scan = new Scanner(System.in);

	public Fight(Hero hero, Monster monster){
		singleHero = hero;
		singleMonster = monster;
	}

	public void singleMonsterAttack(){
		singleMonster.attack(singleHero);
	}

	public void singleHeroAttack(){
		if(singleHero.getHp() > 0){
			System.out.print("Turn of H"+ singleHero.getHeroIdx() + singleHero.getName()+ ":[A]Attack [S]Spell [P]Potion [W]Weapon [R]Armor:");
			this.singleHeroDo();
		}	
	}

	public void singleHeroDo(){
		do{
			String input = scan.next();
			if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){
				if(!singleMonster.isDead()){
					System.out.print(singleHero.getName() + " attacks " + singleMonster.getName() + ".");
					singleHero.attack(singleMonster);
					if(singleMonster.getHp()== 0){
						System.out.println(singleMonster.getName() + " was defeated!");
					}
				}
				return;
			}else if (input.charAt(0) == 'S' || input.charAt(0) == 's'){
				if(singleHero.checkEnoughManaAnySpell() && singleHero.showLearnedSpell()){
					do{
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
							// this.heroSpellMonster(singleHero, singleMonster, useSpell);
							singleHero.spellAttack(singleMonster, useSpell);
							if(singleMonster.getHp()== 0){
								System.out.println(singleMonster.getName() + " was defeated!");
							}
						}
						else{
							if(!singleMonster.isDead()){
								System.out.print(singleHero.getName() + " casts " + useSpell.getName() + " to " + singleMonster.getName() + ".");
								// this.heroSpellMonster(singleHero, singleMonster, useSpell);
								singleHero.spellAttack(singleMonster, useSpell);
								if(singleMonster.getHp()== 0){
									System.out.println(singleMonster.getName() + " was defeated!");	
								}
								break;
							}
						}
						return;
					}while(true);
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
		}while(true);
	
	}

	public boolean checkEnd(){
		if(singleHero.getHp() <= 0){
			System.out.println(" the hero faint, lose the fight.");
			continueFlag = 0;
			fightReward();
			return true;
		}
		else if(singleMonster.getHp() <= 0){
			System.out.println("The heros win the fight. Congratulation!");
			continueFlag = 1;
			fightReward();
			fightPenalty();
			return true;
		}
		return false;
	}

	public boolean heroUseItem(Hero hero,int i){
		List<Item> itemList = hero.getList(i);
		if(hero.checkShowAnyItem(itemList)){
			String errorStr = "Invalid input.";
			do{
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
			}while(true);
		}
		return false;
	}

	// public void heroSpellMonster(Hero hero, Monster monster, Spell spell){
	// 	hero.addMana(-1*(spell.getCostMana()));
	// 	int realDamage = (int)((((float)(hero.getDexterity())/10000)+1)*spell.getDamage());
	// 	//The spell damage is the real damage.
	// 	if(realDamage >= monster.getHp()){
	// 		//Beat the monster, the damage overflow.
	// 		realDamage = monster.getHp();
	// 		monster.addHp( -1 * realDamage);
	// 		System.out.print(" Cause " + realDamage + " damage to " + monster.getName() + ". ");
	// 		return;
	// 	}
	// 	monster.addHp( -1 * realDamage);
	// 	System.out.print(" Cause " + realDamage + " damage to " + monster.getName() + ". ");
	// 	spell.deterioration(monster);
	// }

	public void fightReward(){
		if(singleHero.getHp()>0){
			singleHero.addMoney(150);
			singleHero.addExperience(2);
			if(singleHero.checkLevelUp())
				singleHero.levelUp();
		}else{
			singleHero.getRevived();
		}
		
	}

	public void fightPenalty(){
		singleHero.addMoney((int)(-0.5*singleHero.getMoney()));
		singleHero.getRevived();
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