public class Sorcerer extends Hero{
// A class represents sorcerers, a kind of hero.

	String heroType;

	Sorcerer(String hName, int hMana, int hStr, int hAgi, int hDex, int hMoney, int hExp, int hIdx){
		super(hName, hMana, hStr, hAgi, hDex, hMoney, hExp, hIdx);
		heroType = "Sorcerer";
	}

	public void levelUp(){
	//Sorcerers are favored on dexterity and agility.	
		super.levelUp();
		strength = (int)(strength * 1.05);
		dexterity = (int)(dexterity * 1.1);
		agility = (int)(agility * 1.1);
	}

	public String getHeroType(){
		return heroType;
	}

	public String toString(){
		return "(" + heroType + ")" + super.toString(); 
	}

}