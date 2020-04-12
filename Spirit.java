public class Spirit extends Monster{
// A class represents spirits, a kind of monster.
	String monsterType;

	Spirit(String mName, int mLv, int mDam, int mDef, int mDodge){
		super(mName, mLv, mDam, mDef, mDodge);
		monsterType = "Spirit";
	}

	public String toString(){
		return "(" + monsterType + ")" + super.toString();
	}
}