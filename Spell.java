public class Spell implements Sellable, Buyable{
//A class represents the spells which heros can cast.
	protected String name;
	protected int price;
	protected int requiredLevel;
	protected int damage;
	protected int costMana;

	public Spell(String sname, int sprice, int sreLv, int sdamage, int scostMana){
		name = sname;
		price = sprice;
		requiredLevel = sreLv;
		damage = sdamage;
		costMana = scostMana;
	}

	public String getName(){
		return name;
	}

	public int getPrice(){
		return price;
	}

	public int getRequiredLevel(){
		return requiredLevel;
	}

	public int getDamage(){
		return damage;
	}

	public int getCostMana(){
		return costMana;
	}

	public String toString(){
		return name + " Price:" + price + " Lv:" + requiredLevel + " Damage:" + damage + " CostMana:" + costMana;
	}

	public void deterioration(Monster monster){
	//Each spell will reduce one of the monster's skill.
	}
}