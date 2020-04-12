public class Paladin extends Hero{
// A class represents paladins, a kind of hero.

	String heroType;

	Paladin(String hName, int hMana, int hStr,  int hAgi, int hDex, int hMoney, int hExp, int hIdx){
		super(hName, hMana, hStr, hAgi, hDex, hMoney, hExp, hIdx);
		heroType = "Paladin";
	}

	public void levelUp(){
	//Paladins are favored on strength and dexterity.
		super.levelUp();
		strength = (int)(strength * 1.1);
		dexterity = (int)(dexterity * 1.1);
		agility = (int)(agility * 1.05);
	}

	public String getHeroType(){
		return heroType;
	}

	public String toString(){
		return "(" + heroType + ")" + super.toString(); 
	}

}