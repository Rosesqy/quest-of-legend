public class Potion extends Item{
//A class represent potions, one-use items.

	int statis;//0:hp  1:str 2:mana 3:agi 4:dex 5:exp

	public Potion(String pname, int pprice, int preLv, int pvalue){
		super(pname, pprice, preLv, pvalue);
	}

	public Potion(String pname, int pprice, int preLv, int pvalue, int pstat){
		super(pname, pprice, preLv, pvalue);
		statis = pstat;
	}

	public boolean beUsed(Hero hero){
		switch(statis){
			case 0:
				hero.addHp(value);
				System.out.println(hero.getName() + " increases " + value + " HP!");
				break;
			case 1:
				hero.addStrength(value);
				System.out.println(hero.getName() + " increases " + value + " Strength!");
				break;
			case 2:
				hero.addMana(value);
				System.out.println(hero.getName() + " increases " + value + " Mana!");
				break;
			case 3:
				hero.addAgility(value);
				System.out.println(hero.getName() + " increases " + value + " Agility!");
				break;
			case 4:
				hero.addDexterity(value);
				System.out.println(hero.getName() + " increases " + value + " Dexterity!");
				break;
			case 5:
				hero.addExperience(value);
				System.out.println(hero.getName() + " gains " + value + " Experience!");
				break;
		}
		return true;
	}

	public String toStringShopping(){
		String statisStr = "";
		switch(statis){
			case 0:
				statisStr = "(HP)";
				break;
			case 1:
				statisStr = "(Str)";
				break;
			case 2:
				statisStr = "(Mana)";
				break;
			case 3:
				statisStr = "(Agi)";
				break;
			case 4:
				statisStr = "(Dex)";
				break;
			case 5:
				statisStr = "(Exp)";
				break;
		}
		return statisStr + super.toStringShopping() + " Increase:" + value;
	}

	public String toStringNormal(){
		return this.toStringShopping();
	}

	public void addToHero(Hero hero){
		hero.gotPotion(this);
	}

	public boolean ownedByHero(Hero hero){
		return hero.checkOwnedPotion(this);
	}
}