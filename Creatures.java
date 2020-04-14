import java.util.*;

public class Creatures{
    protected String name;
	protected int x;
	protected int y;
	protected int level;
	protected int hp;
	
	protected int defense;
    
    public Creatures(String cName, int cLv, int cDef){
        this.name = cName;
		this.level = cLv;
        this.defense = cDef;
        this.hp = 100*cLv;
        this.x = 0;
        this.y = 0;
    }

	public void setX(int xPos){
		x = xPos;
	}

	public void setY(int yPos){
		y = yPos;
	}

    public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public String getName(){
		return this.name;
	}

	public int getHp(){
		return this.hp;
    }
    
    public int getLevel(){
        return this.level;
	}
	
	
}