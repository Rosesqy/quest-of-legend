// A class represents the heros.
import java.util.*;

public abstract class Hero extends Creatures{

	// protected String name;
	// protected int x;
	// protected int y;
	protected int heroIdx;
	// protected int level;
	// protected int hp;
	protected int mana;
	protected int strength;
	protected int dexterity;
	protected int agility;
	protected int money;
	protected int experience;
	// protected int defense;
	protected int weaponDamage;
	List<Spell> learnedSpell;
	List<Item> ownedWeapon;
	List<Item> ownedArmor;
	List<Item> ownedPotion;

	public Hero(String hName, int hMana, int hStr, int hAgi, int hDex, int hMoney, int hExp, int hIdx){
		super(hName, 1, 0);
		// name = hName;
		level = 1;
		// hp = 100 * level;
		mana = hMana;
		strength = hStr;
		agility = hAgi;
		dexterity = hDex;
		money = hMoney;
		experience = hExp;
		learnedSpell = new ArrayList<Spell>();
		ownedWeapon = new ArrayList<Item>();
		ownedArmor = new ArrayList<Item>();
		ownedPotion = new ArrayList<Item>();
		defense = 0;
		weaponDamage = 0;
		heroIdx = hIdx;
		x = 7;
		// y = 0;
	}

	public int getHeroIdx(){
		return heroIdx;
	}

	public void setHeroIdx(int hIdx){
		heroIdx = hIdx;
	}

	public abstract String getHeroType();

	public void setHeroPosition(WorldMap theMap,int xPos, int yPos){
		leaveHeroPosition(theMap);
		theMap.setCellIsHero(heroIdx, xPos, yPos); //enter the new cell
		this.x = xPos;
		this.y = yPos;
		String celltype = theMap.getCellLabel(x,y);
		if(celltype.equals("B")){
			this.dexterity += this.dexterity*0.1;
		}else if(celltype.equals("C")){
			this.agility += this.agility*0.1;
		}else if(celltype.equals("K")){
			this.strength += this.strength*0.1;
		}
		
	}


	public void leaveHeroPosition(WorldMap theMap){
		theMap.setCellIsHero(0, x, y); //leave from the previous cell
		String celltype = theMap.getCellLabel(x,y);
		if(celltype.equals("B")){
			this.dexterity -= this.dexterity*0.1;
		}else if(celltype.equals("C")){
			this.agility -= this.agility*0.1;
		}else if(celltype.equals("K")){
			this.strength -= this.strength*0.1;
		}
	}

	public void sellItem(int i){
		List<Item> itemList = this.getList(i);
		if(this.checkShowAnyItem(itemList)){
			String sellTipsStr = "Choose one to sell, using its sequence number [0-" + (itemList.size()-1) + "], press other keys to return:";
			String errorStr = "Invalid input.";
			Scanner scan = new Scanner(System.in);
			while(true){
				System.out.print(sellTipsStr);
				if(!(scan.hasNextInt())){
					return;
				}
				int sellNum = scan.nextInt();
				if(sellNum < 0 || sellNum > (itemList.size()-1)){
					System.out.println(errorStr);
				continue;
				}
				Item sellItem = itemList.get(sellNum);
				if(sellItem.getEquipped()){
					sellItem.unEquip(this);
				}
				itemList.remove(sellNum);
				System.out.println(name + " sell the " + sellItem.getName() + "!");
				this.addMoney(sellItem.getPrice()/2);
				return;
			}
		}
	}

	public int getWeaponDamage(){
		return weaponDamage;
	}

	public void addWeaponDamage(int adWD){
		weaponDamage = weaponDamage + adWD;
	}

	public void addDefense(int adDef){
		defense = defense + adDef;
	}

	public boolean beUsed(Hero hero){
		return false;
	}

	public void removeItem(List<Item> itemList, int i){
		itemList.remove(i);
	}

	public boolean useItem(Item item){
	//If the beUsed returns "true", the item is one-use and must be removed.
		return item.beUsed(this);
	}

	public List<Item> getList(int i){
		switch(i){
			case 0:
				return ownedWeapon;
			case 1:
				return ownedArmor;
			case 2:
				return ownedPotion;
		}
		return null;
	}


	public boolean checkShowAnyItem(List<Item> itemList){
		//Check whether the hero has any item of this kind. If there exists at least one, show it.
		if(itemList.size() == 0){
			System.out.println(name + " has nothing!");
			return false;
		}
		for(int i = 0; i < itemList.size(); i ++){
			System.out.println(i + "." + itemList.get(i).toStringNormal());
		}
		return true;
	}

	public boolean checkOwnedPotion(Potion potion){
		//Check whether the potion is owned.
		for (int i = 0; i < ownedPotion.size();i++){
			if((ownedPotion.get(i).getName()).equals(potion.getName()))
				return true;
		}
		return false;
	}

	public boolean checkOwnedWeapon(Weapon weapon){
		//Check whether the weapon is owned.
		for (int i = 0; i < ownedWeapon.size();i++){
			if((ownedWeapon.get(i).getName()).equals(weapon.getName()))
				return true;
		}
		return false;
	}

	public boolean checkOwnedArmor(Armor armor){
		//Check whether the armor is owned.
		for (int i = 0; i < ownedArmor.size();i++){
			if((ownedArmor.get(i).getName()).equals(armor.getName()))
				return true;
		}
		return false;
	}

	public void gotPotion(Item item){
		ownedPotion.add(item);
	}

	public void gotWeapon(Item item){
		ownedWeapon.add(item);
	}

	public void gotArmor(Item item){
		ownedArmor.add(item);
	}

	public Spell getSingleSpell(int i){
		return learnedSpell.get(i);
	}

	public void learnSpell(Spell spell){
		learnedSpell.add(spell);
	}

	public int getLearnSpellNum(){
		return learnedSpell.size();
	}

	public boolean checkLearnedSpell(Spell spell){
	//Check whether the spell has learned;
		if (learnedSpell.size()==0)
			return false;
		for (int i = 0; i < learnedSpell.size();i++){
			if((learnedSpell.get(i).getName()).equals(spell.getName()))
				return true;
		}
		return false;
	}

	public boolean checkEnoughManaAnySpell(){
		if (learnedSpell.size() == 0)
				return true;
		for(int i = 0; i < learnedSpell.size();i++){
			if ((learnedSpell.get(i).getCostMana()) <= mana)
				return true;
		}
		System.out.println(name + " doesn't have enough mana to cast any spell!");
		return false;
	}

	public boolean checkEnoughManaSingleSpell(int i){
		if(mana >= learnedSpell.get(i).getCostMana())
			return true;
		System.out.println(name + " doesn't have enough mana to cast " + learnedSpell.get(i).getName() + "!");
		return false;
	}

	public boolean showLearnedSpell(){
		if (learnedSpell.size()==0){
			System.out.println(name + " has not learned any spell!");
			return false;
		}
		for (int i = 0; i < learnedSpell.size();i++){
			System.out.println(i + ". "+(learnedSpell.get(i)).toString());
		}
		return true;
	}

	public String toString(){
		return this.getHeroType()+" "+name + " HP:" + hp + " Mana:" + mana+"\n";
	}

	public String toStringFull(){
		return this.toString() + " Lv:" + level +" Str:" + strength + " Agi:" + agility + " Dex:" + dexterity + "\nWeapon damage:"+ weaponDamage + " Defense:"+ defense + " Exp:" + experience + " Coins:" + money;   
	}

	public int getAttackDamage(){
		return (int)((strength + weaponDamage) * 0.5);
	}

	public int getSpellDamage(){
		return (int)((float)dexterity/10000);
	}

	public void usePotion(){

	}

	public void changeEquip(){
		
	}

	public boolean checkLevelUp(){
		if(experience >= (level * 10))
			return true;
		else
			return false;
	}

	public void levelUp(){
		System.out.println("Level up! "+ name + " has become level " + (level+1) + "!");
		experience = experience - level * 10;
		level = level + 1;
		hp = level * 100;
		mana = (int)(mana * 1.1);
	}

	// public String getName(){
	// 	return name;
	// }

	// public int getLevel(){
	// 	return level;
	// }

	public int getHp(){
		return hp;
	}

	public int getMana(){
		return mana;
	}

	public int getStrength(){
		return strength;
	}

	public int getDexterity(){
		return dexterity;
	}

	public int getAgility(){
		return agility;
	}

	public int getMoney(){
		return money;
	}
	
	public int getExperience(){
		return experience;
	}

	public int getDefense(){
		return defense;
	}

	public void addHp(int adHp){
		hp = hp + adHp;
	}

	public void addMana(int adMana){
		mana = mana + adMana;
	}

	public void addStrength(int adStr){
		strength = strength + adStr;
	}

	public void addDexterity(int adDex){
		dexterity = dexterity + adDex;
	}

	public void addAgility(int adAgi){
		agility = agility + adAgi;
	}

	public void addMoney(int adMoney){
		if(adMoney > 0)
			System.out.println(name + " gains " + adMoney + " coins.");
		else
			System.out.println(name + " loses " + (-1*adMoney) + " coins.");
		money = money + adMoney;
	}

	public void addExperience(int adExp){
		if(adExp > 0)
			System.out.println(name + " gains " + adExp + " exp.");
		experience = experience + adExp;
	}

	public void getRevived(){
	//If one hero faints and win the fight, he will get revived.
		hp = 50 * level;
		System.out.println(name + " gets revived.");
	}

}