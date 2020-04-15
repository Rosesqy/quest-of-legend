public class IceSpell extends Spell{
//A class represents Ice spells, a kind of spells.
	public IceSpell(String sname, int sprice, int sreLv, int sdamage, int scostMana){
		super(sname, sprice, sreLv, sdamage, scostMana);
	}
	
	public String toString(){
		return "(Ice)" + super.toString();
	}

	public void deterioration(Monster monster){
	//Ice spells reduce the damage.
		monster.addDamage( (int)(-0.1*monster.getDamage()));
		System.out.println("Reduce 10% damage for " + monster.getName());
	}
} 