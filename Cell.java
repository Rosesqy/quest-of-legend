public class Cell {
// A class represents a  cell of the world map.
	
	protected String label;//"X" for non-accessible, " " for common cells, "M" for market.
	protected int isHero;//"0" for non hero, 1~3 represents a hero.
	protected int isMonster;//"0" for non hero, 1~n represents a monster.

	public Cell(){
		this.label = "P";
		isHero = 0;
		isMonster = 0;
	}

	public Cell(String str){
		this.label = str;
		isHero = 0;
		isMonster = 0;
	}

	public String getLabel(){
		return label;
	}

	public void setLabel(String str){
		label = str;
	}

	public int getIsHero(){
		return isHero;
	}

	public int getIsMonster(){
		return isMonster;
	}

	public void setIsHero(int heroIdx){
		isHero = heroIdx;
	}

	public void setIsMonster(int monsterIdx){
		isMonster = monsterIdx;
	}

	public boolean access(){
		return true;
	}
	
}