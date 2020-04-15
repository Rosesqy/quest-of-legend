public class Paladin extends Hero{
// A class represents paladins, a kind of hero.

	private String heroType;

	public Paladin(String hName, int hMana, int hStr,  int hAgi, int hDex, int hMoney, int hExp, int hIdx){
		super(hName, hMana, hStr, hAgi, hDex, hMoney, hExp, hIdx);
		this.heroType = "Paladin";
	}

	public void levelUp(){
	//Paladins are favored on strength and dexterity.
		super.levelUp();
		this.strength = (int)(this.strength * 1.1);
		this.dexterity = (int)(this.dexterity * 1.1);
		this.agility = (int)(this.agility * 1.05);
	}

	public String getHeroType(){
		return this.heroType;
	}

	public String toString(){
		return "(" + this.heroType + ")" + super.toString(); 
	}

}