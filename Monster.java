// A class represents the monsters.
public class Monster extends Creatures{
	// String name;
	// int x;
	// int y;
	// int level;
	// int hp;
	int damage;
	// int defense;
	protected float dodgeRate;
	int monsterIdx;

	Monster(String mName, int mLv, int mDam, int mDef, int mDodge){
		super(mName, mLv, mDef);
		// name = mName;
		// level = mLv;
		damage = mDam;
		// defense = mDef;
		dodgeRate = (float)mDodge/100;
		// hp = 100 * level;
		// x = 0;
		// y = 0;
		monsterIdx = 1;
	}

	// public int getX(){
	// 	return x;
	// }

	// public int getY(){
	// 	return y;
	// }

	// public String getName(){
	// 	return name;
	// }

	// public int getHp(){
	// 	return hp;
	// }

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
}