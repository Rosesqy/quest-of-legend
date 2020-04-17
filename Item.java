public class Item implements Sellable, Buyable{
//A class represents the items sold in market.
	protected String name;
	protected int price;
	protected int requiredLevel;
	protected int value;
	boolean equipped;//false means un-equipped.

	public Item(){
	}

	public Item(String iname, int iprice, int ireLv, int ivalue){
		name = iname;
		price = iprice;
		requiredLevel = ireLv;
		value = ivalue;
		equipped = false;
	}

	public void unEquip(Hero hero){
		this.setEquipped(false);
	}

	public void setEquipped(boolean flag){
		equipped = flag;
	}

	public boolean getEquipped(){
		return equipped;
	}

	public boolean beUsed(Hero hero){
		return false;
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

	public int getValue(){
		return value;
	}

	public String toStringNormal(){
		return null;
		//return name + " Price:" + price + " Lv:" + requiredLevel;
	}

	public String toStringShopping(){
		return name + " Price:" + price + " Lv:" + requiredLevel;
	}

	public void addToHero(Hero hero){

	}

	public boolean levelEnough(Hero h){
		return h.getLevel() >= this.requiredLevel;
	}

	public boolean moneyEnough(Hero h){
		return h.getMoney() >= this.price;
	}

	public boolean ownedByHero(Hero hero){
		return false;
	}

	public boolean buyable(Hero hero){
		if(this.ownedByHero(hero)){
			System.out.println(hero.getName()+" has owned a" + this.getName() +".");
			return false;
		}
		if(!this.levelEnough(hero)){
			System.out.println(hero.getName()+" has not reached level " + this.getRequiredLevel() +", can't buy the " + this.getName() + ".");
			return false;
		}
		if(!this.moneyEnough(hero)){
			System.out.println(hero.getName()+" doesn't have enough coins to buy the item.");
			return false;
		}
		return true;
	}
}