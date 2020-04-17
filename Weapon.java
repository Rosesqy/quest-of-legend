import java.util.*;

public class Weapon extends Item{
//A class represents weapons, which can increase hero's damage.

	int requiredHand;

	public Weapon(String wname, int wprice, int wreLv, int wvalue, int wreHand){
		super(wname, wprice, wreLv, wvalue);
		requiredHand = wreHand;
	}

	public void unEquip(Hero hero){
		int oriWeaponDamage = hero.getWeaponDamage();
		super.unEquip(hero);
		hero.addWeaponDamage(-1*value);
		System.out.println(hero.getName() + " unequipped the " + name + ".\nWeapon damage changes:" + oriWeaponDamage + "=>" + hero.getWeaponDamage());
	}


	public boolean beUsed(Hero hero){	
		int oriWeaponDamage = hero.getWeaponDamage();
		if (equipped){
			equipped = false;
			hero.addWeaponDamage(-1*value);
			System.out.println(hero.getName() + " unequipped the " + name + ".\nWeapon damage changes:" + oriWeaponDamage + "=>" + hero.getWeaponDamage());
			return false;
		}
		List<Item> weaponList = hero.getList(0);
		for(int i = 0; i < weaponList.size() ; i++){
			if((weaponList.get(i)).getEquipped()){
				weaponList.get(i).setEquipped(false);
				hero.addWeaponDamage(-1*(weaponList.get(i).getValue()));
			}
		}
		equipped = true;
		hero.addWeaponDamage(value);
		System.out.println(hero.getName() + " equipped the " + name + ".\nWeapon damage changes:" + oriWeaponDamage + "=>" + hero.getWeaponDamage());
		return false;
	}

	public int getRequiredHand(){
		return requiredHand;
	}

	public String toStringShopping(){
		return super.toStringShopping() + " damage:" + value + " requiredhands:" + requiredHand;
	}

	public void addToHero(Hero hero){
		hero.gotWeapon(this);
	}

	public boolean ownedByHero(Hero hero){
		return hero.checkOwnedWeapon(this);
	}

	public String toStringNormal(){
		String equ = "";
		if (equipped)
			equ = "(equipped)";
		return equ + name + " Price:" + price + " damage:" + value;
	}
}