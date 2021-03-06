import java.util.*;

public class WorldMap{
// A class represents the map of the world.

	private double commonCellRate;
	private double bushCellRate;
	private double koulouCellRate;
	// private double caveCellRate;
	private int size;
	private Cell [][] mapCell;
	// private String mapEdge;
	
	public WorldMap(){
		size = 8;
		commonCellRate = 0.4;
		bushCellRate = 0.2;
		koulouCellRate = 0.2;
		// caveCellRate = 0.2;
	}

	public int getCellIsHero(int xPos, int yPos){
		return mapCell[xPos][yPos].getIsHero();
	}

	public int getCellIsMonster(int xPos, int yPos){
		return mapCell[xPos][yPos].getIsMonster();
	}

	public void setCellIsHero(int id, int xPos, int yPos){
		mapCell[xPos][yPos].setIsHero(id);
	}

	public void setCellIsMonster(int id, int xPos, int yPos){
		mapCell[xPos][yPos].setIsMonster(id);
	}

	public String getCellLabel(int xPos, int yPos){
		return mapCell[xPos][yPos].getLabel();
	}

	public int getSize(){
		return size;
	}
	
	public void createMap(){
		System.out.print("Create the world...");
		mapCell = new Cell [size][size];
		// int totalSize = size*size;
		
		for (int i = 0; i < size ; i++){
			for (int j = 0; j < size ; j++){
				if (( j%8 == 2 )||( j%8 == 5))
					mapCell[i][j] = new Block();
				else if(( i ==7 )||( i ==0))
					mapCell[i][j] = new Nexus();
				else {
					double tmp = Math.random();
					if (tmp < commonCellRate)
						mapCell[i][j] = new Cell();
					else if (tmp < (commonCellRate + bushCellRate))
						mapCell[i][j] = new Bush();
					else if (tmp < (commonCellRate + bushCellRate + koulouCellRate))
						mapCell[i][j] = new Koulo();
					else
						mapCell[i][j] = new Cave();
				}
			}
		}

		System.out.println("done.");
	}

	public void showMap(Hero[] heros, ArrayList<Monster> monsters){
		String line = "";
		String middle = "";
		//System.out.println(mapEdge);
		for (int i = 0; i < size; i++){
			line = "";
			middle = "";
			for (int j = 0; j < size; j ++){
				line = line + mapCell[i][j].getLabel() + " - " + mapCell[i][j].getLabel() + " - " + mapCell[i][j].getLabel() + "  ";
				
				for(int h = 1;h<4;h++){
					if(heros[h-1].getX()==i && heros[h-1].getY()==j){
						mapCell[i][j].setIsHero(h);
					}
				}
				for(int m = 1;m<=monsters.size();m++){
					if(monsters.get(m-1).getX()==i && monsters.get(m-1).getY()==j){
						if(monsters.get(m-1).isDead()){
							mapCell[i][j].setIsMonster(-1);
						}else{
							mapCell[i][j].setIsMonster(m);
						}
					}
				}

				if (mapCell[i][j].getLabel() == "I")
					middle = middle + "| X X X |  ";
				else{
					if(mapCell[i][j].getIsHero()>0 && mapCell[i][j].getIsMonster()>0){
						middle = middle + "|H" + mapCell[i][j].getIsHero() + "M"+mapCell[i][j].getIsMonster();
					}
					else if(mapCell[i][j].getIsHero()>0){
						middle  = middle + "| H" + mapCell[i][j].getIsHero() + " ";
					}else if(mapCell[i][j].getIsMonster()>0){
						middle  = middle + "| M" + mapCell[i][j].getIsMonster() + " ";
					}else{
						middle = middle + "|    ";
					}
					middle = middle + "   |  ";
				}					
			}
			System.out.println(line);
			System.out.println(middle);
			System.out.println(line);
			System.out.print("\n");
		}
	}

	public boolean checkNonAccess(int xPos, int yPos, Hero hero){
		if (mapCell[xPos][yPos].getLabel() == "I")
			return true;
		else if(mapCell[xPos][yPos].getIsHero() > 0)
			return true;
		else if(mapCell[xPos][yPos].getIsMonster()>0)
			return true;
		else
			return false;
	}

	public boolean checkNonAccess(int xPos, int yPos, Monster monster){
		if (mapCell[xPos][yPos].getLabel() == "I")
			return true;
		else if(mapCell[xPos][yPos].getIsMonster() > 0)
			return true;
		else
			return false;
	}

	public static void main(String[] args){
		// A simple test.
		WorldMap theMap = new WorldMap();
		theMap.createMap();
		//theMap.showMap(1,1);
	}
	

	
}