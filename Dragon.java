public class Dragon extends Monster{
// A class represents dragons, a kind of monster.
	String monsterType;

	Dragon(String mName, int mLv, int mDam, int mDef, int mDodge){
		super(mName, mLv, mDam, mDef, mDodge);
		monsterType = "Dragon";
	}

	public String toString(){
		return "(" + monsterType + ")" + super.toString();
	}
}