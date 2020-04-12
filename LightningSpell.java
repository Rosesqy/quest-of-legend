public class LightningSpell extends Spell{
//A class represents Lightning spells, a kind of spells.
	LightningSpell(String sname, int sprice, int sreLv, int sdamage, int scostMana){
		super(sname, sprice, sreLv, sdamage, scostMana);
	}

	public String toString(){
		return "(Lightning)" + super.toString();
	}

	public void deterioration(Monster monster){
	//Lightning spells reduce the dodge rate.
		monster.addDodgeRate( (float)(-0.1*monster.getDodgeRate()));
		System.out.println("Reduce 10% dodge rate for " + monster.getName());
	}
} 