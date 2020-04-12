public class Exoskeleton extends Monster{
// A class represents exoskeletons, a kind of monster.
	String monsterType;

	Exoskeleton(String mName, int mLv, int mDam, int mDef, int mDodge){
		super(mName, mLv, mDam, mDef, mDodge);
		monsterType = "Exoskeleton";
	}

	public String toString(){
		return "(" + monsterType + ")" + super.toString();
	}
}