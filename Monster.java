// A class represents the monsters.

public class Monster extends Creatures implements Attack{
	
	protected int damage;
	// int defense;
	protected float dodgeRate;
	protected int monsterIdx;

	public Monster(String mName, int mLv, int mDam, int mDef, int mDodge){
		super(mName, mLv, mDef);
		damage = mDam;
		dodgeRate = (float)mDodge/100;
		monsterIdx = 1;
	}

	public void setMonsterPosition(WorldMap theMap,int xPos, int yPos){
		theMap.setCellIsMonster(0, x, y);
		theMap.setCellIsMonster(monsterIdx, xPos, yPos);
		x = xPos;
		y = yPos;
	}

	public int getDamage(){
		return damage;
	}

	public int getDefense(){
		return defense;
	}

	public float getDodgeRate(){
		return dodgeRate;
	}

	public String toString(){
		return name + " HP:" + hp + " Damage:" + damage + " Defense:" + defense + " DodgeRate:" + dodgeRate;
	}

	public void addHp(int adHp){
		hp = hp + adHp;
	}

	public void addDamage(int adDam){
		if(adDam < 0){
			System.out.println("Reduces " + (-1*adDam) + " damage of " + name + ".");
		}
		damage = damage + adDam;
	}

	public void addDefense(int adDef){
		if(adDef < 0){
			System.out.println("Reduces " + (-1*adDef) + " defense of " + name + ".");
		}
		defense = defense + adDef;
	}

	public void addDodgeRate(float adDR){
		if(adDR < 0){
			System.out.println("Reduces " + (-1*adDR) + " dodge rate of " + name + ".");
		}
		dodgeRate = dodgeRate + adDR;
	}

	public boolean isDead(){
		if(this.hp <= 0){
			this.monsterIdx = -1;
			return true;
		}
		return false;
	}

	public void attack(Creatures hero){
		System.out.println(this.getName() + " attacks the hero " + hero.getName());
		double tmp = Math.random();
		if(tmp < (hero.getAgility()*0.001)){
			System.out.println(" Miss!");
			return;
		}
		int monsterDamage = this.getDamage();
		int realDamage = (monsterDamage > hero.getDefense())?( monsterDamage - hero.getDefense()):0;
		if(realDamage >= hero.getHp()){
			//The hero faints, the damage overflow.
			realDamage = hero.getHp();
		}
		System.out.println(" Cause " + realDamage + " damage to " + hero.getName() + ".");
		hero.addHp( -1 * realDamage);
		return;
	}
}