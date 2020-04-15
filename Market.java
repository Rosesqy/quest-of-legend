import java.util.*;
import java.io.*;

public class Market{
//A class represents the market.
	static List<Spell> spellList;
	static List<Item> weaponList;
	static List<Item> armorList;
	static List<Item> potionList;
	Hero[] theHeros;
	Hero theHero;

	static 	Scanner scan = new Scanner(System.in);

	// Market(Hero[] heros){
	// 	spellList = new ArrayList<Spell>();
	// 	weaponList = new ArrayList<Item>();
	// 	armorList = new ArrayList<Item>();
	// 	potionList = new ArrayList<Item>();
	// 	theHeros = heros;
	// 	this.initial();
	// }

	public Market(){
		spellList = new ArrayList<Spell>();
		weaponList = new ArrayList<Item>();
		armorList = new ArrayList<Item>();
		potionList = new ArrayList<Item>();
		this.initial();
	}


	public void initial(){
		Spell pspell;
		Item pitem;
		try {
			InputStream is = new FileInputStream("The_Quest/IceSpells.txt");
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(ir);
			String str;
			String line[];
			bf.readLine();
			str = bf.readLine();
			do{
				if(str == null)
					break;
				line = str.split("\\s+");
				pspell = new IceSpell(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				spellList.add(pspell);
			}while ((str = bf.readLine()) != null);
			// while ((str = bf.readLine()) != null) {
			// 	line = str.split("\\s+");
			// 	pspell = new IceSpell(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
			// 	spellList.add(pspell);
			// }
			ir.close();
			is = new FileInputStream("The_Quest/FireSpells.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			str = bf.readLine();
			do{
				if(str == null)
					break;
				line = str.split("\\s+");
				pspell = new FireSpell(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				spellList.add(pspell);
			}while((str = bf.readLine()) != null);
			// while ((str = bf.readLine()) != null) {
			// 	line = str.split("\\s+");
			// 	pspell = new FireSpell(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
			// 	spellList.add(pspell);
			// }
			ir.close();
			is = new FileInputStream("The_Quest/LightningSpells.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			str = bf.readLine();
			do{
				if(str == null)
					break;
				line = str.split("\\s+");
				pspell = new LightningSpell(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				spellList.add(pspell);
			}while((str = bf.readLine()) != null);
			// while ((str = bf.readLine()) != null) {
			// 	line = str.split("\\s+");
			// 	pspell = new LightningSpell(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
			// 	spellList.add(pspell);
			// }
			ir.close();
			is = new FileInputStream("The_Quest/Weaponry.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			str = bf.readLine();
			do{
				if(str == null)
					break;
				line = str.split("\\s+");
				pitem = new Weapon(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
				weaponList.add(pitem);
			}while ((str = bf.readLine()) != null);
			// while ((str = bf.readLine()) != null) {
			// 	line = str.split("\\s+");
			// 	pitem = new Weapon(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
			// 	weaponList.add(pitem);
			// }
			ir.close();
			is = new FileInputStream("The_Quest/Armory.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			str = bf.readLine();
			do{
				if(str == null)
					break;
				line = str.split("\\s+");
				pitem = new Armor(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
				armorList.add(pitem);
			}while ((str = bf.readLine()) != null);
			// while ((str = bf.readLine()) != null) {
			// 	line = str.split("\\s+");
			// 	pitem = new Armor(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
			// 	armorList.add(pitem);
			// }
			ir.close();
			is = new FileInputStream("The_Quest/Potions.txt");
			ir = new InputStreamReader(is);
			bf = new BufferedReader(ir);
			bf.readLine();
			str = bf.readLine();
			int k = 0;
			do{
				if(str == null)
					break;
				line = str.split("\\s+");
				pitem = new Potion(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]),k);
				potionList.add(pitem);
				k ++;
			}while ((str = bf.readLine()) != null);
			// while ((str = bf.readLine()) != null) {
			// 	line = str.split("\\s+");
			// 	pitem = new Potion(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]),k);
			// 	potionList.add(pitem);
			// 	k ++;
			// }
			bf.close();
			ir.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void showSpellList(){
		for(int i = 0; i < spellList.size(); i++)
			System.out.println(i + ". "+(spellList.get(i)).toString());
	}

	public void showItemList(List<Item> itemList){
		for(int i = 0; i < itemList.size(); i++)
			System.out.println(i + ". "+(itemList.get(i)).toStringShopping());
	}

	public void visitMarket(Hero hero){
		String mainTipsStr = "What you want to do? [B]Buy   [S]Sell   [L]Leave the market:";
		do{
			System.out.print(mainTipsStr);
			String input = scan.next();
			if (input.charAt(0) == 'B' || input.charAt(0) == 'b'){
				//show the second menu of buy.
				this.buyMainMenu(hero);
			}else if (input.charAt(0) == 'S' || input.charAt(0) == 's'){
				//show the second menu of sell.
				this.sellMainMenu(hero);
			}else if (input.charAt(0) == 'L' || input.charAt(0) == 'l'){
				//Leave the market.
				System.out.println("-------------------------------------------------------------------------");
				return;
			}else{
				System.out.print("Invalid action. ");
			}
		}while(true);
	}

	public void sellMainMenu(Hero hero){
		String errorStr = "Invalid input.";
		String sellTipsStr = "Which one? [P]Potion  [A]Armor  [W]Weapon  [B]Back to market:";
		do{
			System.out.print(sellTipsStr);
			String input = scan.next();
			if (input.charAt(0) == 'P' || input.charAt(0) == 'p'){
				//Sell potions.
				hero.sellItem(2);
			}else if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){
				//Sell armors.
				hero.sellItem(1);
			}else if (input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				//Sell weapons.
				hero.sellItem(0);
			}else if (input.charAt(0) == 'B' || input.charAt(0) == 'b'){
				return;
			}else{
				System.out.print(errorStr);
			}
		}while(true);
	}

	public void buyMainMenu(Hero hero){
		String buyTipsStr = "Which one? [S]Spell  [P]Potion  [A]Armor  [W]Weapon  [B]Back to market:";
		do{
			System.out.print(buyTipsStr);
			String input = scan.next();
			if (input.charAt(0) == 'S' || input.charAt(0) == 's'){
				//Buy spells.
				this.buySpell(hero);
			}else if (input.charAt(0) == 'P' || input.charAt(0) == 'p'){
				//Buy potions.
				this.buyItem(hero,potionList);
			}else if (input.charAt(0) == 'A' || input.charAt(0) == 'a'){
				//Buy armors.
				this.buyItem(hero,armorList);
			}else if (input.charAt(0) == 'W' || input.charAt(0) == 'w'){
				//Buy weapons.
				this.buyItem(hero,weaponList);
			}else if (input.charAt(0) == 'B' || input.charAt(0) == 'b'){
				return;
			}else{
				System.out.print("Invalid action. ");
			}
		}while(true);
	}

	public void buyItem(Hero hero, List<Item> itemList){
		String buyTipsStr = "Choose one to buy, using its sequence number [0-" + (itemList.size()-1) + "], press other keys to return:";
		String errorStr = "Invalid input.";
		do{
			this.showItemList(itemList);
			System.out.print(buyTipsStr);
			if(!(scan.hasNextInt())){
				return;
			}
			int buyNum = scan.nextInt();
			if(buyNum < 0 || buyNum > (itemList.size()-1)){
				System.out.println(errorStr);
				continue;
			}
			Item buyItem = itemList.get(buyNum);
			if(this.heroBuyItem(hero, buyItem)){
				System.out.println(hero.getName() + " buy " + buyItem.getName() + "!");
				hero.addMoney(-1*buyItem.getPrice());
				itemList.remove(buyNum);
				buyItem.addToHero(hero);				
			}
			System.out.println("-------------------------------------------------------------------------");
			continue;
		}while(true);
	}

	public boolean heroBuyItem(Hero hero, Item item){
		if(item.ownedByHero(hero)){
			System.out.println(hero.getName()+" has owned a" + item.getName() +".");
			return false;
		}
		int heroLevel = hero.getLevel();
		int requiredLevel = item.getRequiredLevel();
		if(heroLevel < requiredLevel){
			System.out.println(hero.getName()+" has not reached level " + requiredLevel +", can't buy the " + item.getName() + ".");
			return false;
		}
		int heroMoney = hero.getMoney();
		int price = item.getPrice();
		if(heroMoney < price){
			System.out.println(hero.getName()+" doesn't have enough coins to buy the item.");
			return false;
		}
		return true;
	}

	public void buySpell(Hero hero){
		String buySpellTipsStr;
		String errorStr = "Invalid input.";
		do{
			if (spellList.size() == 0){
				//No spell in the spellList.
				System.out.println("The heros have learned all the spells!");
				return;
			}
			this.showSpellList();
			buySpellTipsStr = "Choose one spell to buy, using its sequence number [0-" + (spellList.size()-1) + "], press other keys to return:";
			System.out.print(buySpellTipsStr);
			if(!(scan.hasNextInt())) {
				return;
				}
			int buyNum = scan.nextInt();
			if(buyNum < 0 || buyNum > (spellList.size()-1)){
				System.out.println(errorStr);
				continue;
			}
			Spell buySpell = spellList.get(buyNum);
			if(this.heroBuySpell(hero,buySpell)){
				System.out.println(hero.getName() + " learned " + buySpell.getName() + "!");
				hero.addMoney(-1*buySpell.getPrice());
				hero.learnSpell(buySpell);
				spellList.remove(buyNum);				
			}
			System.out.println("-------------------------------------------------------------------------");
			continue;
		}while(true);
	}

	public boolean heroBuySpell(Hero hero, Spell spell){
		if (hero.checkLearnedSpell(spell)){
			System.out.println(hero.getName()+" has learned " + spell.getName() +".");
			return false;
		}
		int heroLevel = hero.getLevel();
		int requiredLevel = spell.getRequiredLevel();
		if(heroLevel < requiredLevel){
			System.out.println(hero.getName()+" has not reached level " + requiredLevel +", can't learn the " + spell.getName() + ".");
			return false;
		}
		int heroMoney = hero.getMoney();
		int price = spell.getPrice();
		if(heroMoney < price){
			System.out.println(hero.getName()+" doesn't have enough coins to learn the spell.");
			return false;
		}
		return true;
	}

}