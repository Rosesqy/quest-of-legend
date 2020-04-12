public class FireSpell extends Spell{
//A class represents Fire spells, a kind of spells.
	FireSpell(String sname, int sprice, int sreLv, int sdamage, int scostMana){
		super(sname, sprice, sreLv, sdamage, scostMana);
	}

	public String toString(){
		return "(Fire)" + super.toString();
	}

	public void deterioration(Monster monster){
	//Fire spells reduce the defense.
		monster.addDefense( (int)(-0.1*monster.getDefense()));
		System.out.println("Reduce 10% defense for " + monster.getName());
	}
} 