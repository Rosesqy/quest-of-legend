import java.util.*;

public class Armor extends Item{
//A class represents armors, which can reduce the damage from the monsters.

	Armor(String wname, int wprice, int wreLv, int wvalue){
		super(wname, wprice, wreLv, wvalue);
	}

	public void unEquip(Hero hero){
		int oriDefense = hero.getDefense();
		super.unEquip(hero);
		hero.addDefense(-1*value);
		System.out.println(hero.getName() + " unequipped the " + name + ".\nDefense changes:" + oriDefense + "=>" + hero.getDefense());
	}

	public boolean beUsed(Hero hero){	
		int oriDefense = hero.getDefense();
		if (equipped){
			equipped = false;
			hero.addDefense(-1*value);
			System.out.println(hero.getName() + " unequipped the " + name + ".\nDefense changes:" + oriDefense + "=>" + hero.getDefense());
			return false;
		}
		List<Item> armorList = hero.getList(1);
		for(int i = 0; i < armorList.size() ; i++){
			if((armorList.get(i)).getEquipped()){
				armorList.get(i).setEquipped(false);
				hero.addDefense(-1*(armorList.get(i).getValue()));
			}
		}
		equipped = true;
		hero.addDefense(value);
		System.out.println(hero.getName() + " equipped the " + name + ".\nDefense changes:" + oriDefense + "=>" + hero.getDefense());
		return false;
	}

	public String toStringShopping(){
		return super.toStringShopping() + " reducedamage:" + value;
	}

	public void addToHero(Hero hero){
		hero.gotArmor(this);
	}

	public String toStringNormal(){
		String equ = "";
		if (equipped)
			equ = "(equipped)";
		return equ + name + " Price:" + price + " reducedamage:" + value;
	}

	public boolean ownedByHero(Hero hero){
		return hero.checkOwnedArmor(this);
	}
}