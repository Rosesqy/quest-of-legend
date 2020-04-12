public class Warrior extends Hero{
// A class represents warriors, a kind of hero.

	String heroType;

	Warrior(String hName, int hMana, int hStr,int hAgi, int hDex,  int hMoney, int hExp, int hIdx){
		super(hName, hMana, hStr, hAgi, hDex, hMoney, hExp, hIdx);
		heroType = "Warrior";
	}

	public void levelUp(){
	//Warriors are favored on strength and agility.	
		super.levelUp();
		strength = (int)(strength * 1.1);
		dexterity = (int)(dexterity * 1.05);
		agility = (int)(agility * 1.1);
	}

	public String getHeroType(){
		return heroType;
	}

	public String toString(){
		return "(" + heroType + ")" + super.toString(); 
	}
	
}