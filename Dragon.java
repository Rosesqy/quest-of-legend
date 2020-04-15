public class Dragon extends Monster{
// A class represents dragons, a kind of monster.
	private String monsterType;

	public Dragon(String mName, int mLv, int mDam, int mDef, int mDodge){
		super(mName, mLv, mDam, mDef, mDodge);
		this.monsterType = "Dragon";
	}

	public String toString(){
		return "(" + this.monsterType + ")" + super.toString();
	}
}