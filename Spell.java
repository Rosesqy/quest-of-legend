public class Spell implements Buyable{
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

	public boolean moneyEnough(Hero h){
		return h.getMoney() >= this.price;
	}

	public boolean levelEnough(Hero h){
		return h.getLevel() >= this.requiredLevel;
	}

	public boolean buyable(Hero hero){
		if (hero.checkLearnedSpell(this)){
			System.out.println(hero.getName()+" has learned " + this.getName() +".");
			return false;
		}
		if(!this.levelEnough(hero)){
			System.out.println(hero.getName()+" has not reached level " + requiredLevel +", can't learn the " + this.getName() + ".");
			return false;
		}
		if(!this.moneyEnough(hero)){
			System.out.println(hero.getName()+" doesn't have enough coins to learn the this.");
			return false;
		}
		return true;
	}
}