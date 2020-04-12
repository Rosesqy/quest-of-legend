import java.util.*;
import java.io.*;

public class Explain{
    
    static String path = Quest.class.getResource(".").getPath().replace("%20"," ");
    static Scanner stdin = new Scanner(System.in);
    static Random rand = new Random();

    public static void showHero(ArrayList<Hero> allheroes){
        String[] n; //each hero info
        String p; //each printing string
        String[] list = {"Warriors","Sorcerers","Paladins"};

        for(String l:list){
            try{
                System.out.println("\nAvailable "+l);
                File info = new File( path + "info/"+l+".txt");
                Scanner reader = new Scanner(info);
                System.out.println(reader.nextLine());
                while(reader.hasNextLine()){
                    p = reader.nextLine();
                    if(p.trim().length()>0){
                        System.out.println(p);
                        n = p.split("\\s+");
                        switch(l){
                            case "Warriors":
                                allheroes.add(new Warrior(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4]),Integer.parseInt(n[5]),Integer.parseInt(n[6]),0));
                                
                                break;
                            case "Sorcerers":
                                allheroes.add(new Sorcerer(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4]),Integer.parseInt(n[5]),Integer.parseInt(n[6]),0));
                                break;
                            case "Paladins":
                                allheroes.add(new Paladin(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4]),Integer.parseInt(n[5]),Integer.parseInt(n[6]),0));
                                break;
                        }   
                    }
                }
                reader.close();
            }catch(Exception e){
                System.out.println("error here: "+e.getMessage());
            }
        }
    }

    public static void showRule(){
        System.out.println("\n W - move forward \n A - move left \n D - move right \n S - move backwards \n You cannot move diagonally! \n I - show information \n \n You can have at most 3 heroes \n\n You can fight, buy, sell, use and item follow the instructions given during the name \n You can go back to the real world by saying Q/QUIT whenever you are to make a move");
    }

    public static void showWelcome(){
        System.out.println("\nIf it is the first time you come to this magical world, I suggest you to enter yes to see the law here; but if you are an experienced hero, you can say no to enter the world directly!");
    }

    public static void showTeam(ArrayList<Hero> team){
        System.out.println("\n[Your team member(s)]");
        String heroformat = "%-20s %-3s %-3s %-3s %-3s %-3s %-3s %-3s %n";
        System.out.printf(heroformat, "Name","mana", "hp","strength", "agility", "dexterity", "exp", "level");
        System.out.println("-".repeat(100));
		for(Hero hero:team){
			System.out.println(hero);
		}
    }
   
    public static void parseProducts(ArrayList<Weapon> mktweapon,ArrayList<Armor> mktarm,ArrayList<Potion> mktpotion,ArrayList<FireSpell> mktfspell,ArrayList<IceSpell> mktispell, ArrayList<LightningSpell> mktlspell){
        String[] n; //each product info
        String p; //each printing string
        String[] productlist = {"Weaponry","Armory","Potions","FireSpells","IceSpells","LightningSpells"};

        for(String l:productlist){
            try{
                
                File info = new File( path + "info/"+l+".txt");
                Scanner reader = new Scanner(info);
                reader.nextLine();
                while(reader.hasNextLine()){
                    p = reader.nextLine();
                    if(p.trim().length()>0){
                        n = p.split("\\s+");
                        switch(l){
                            case "Weaponry":
                                mktweapon.add(new Weapon(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                            case "Armory":
                                mktarm.add(new Armor(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3])));
                                break;
                            case "Potions":
                                mktpotion.add(new Potion(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3])));
                                break;
                            case "FireSpells":
                                mktfspell.add(new FireSpell(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                            case "IceSpells":
                                mktispell.add(new IceSpell(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                            case "LightningSpells":
                                mktlspell.add(new LightningSpell(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                        }   
                    }
                }
                reader.close();
            }catch(Exception e){
                System.out.println("error here: "+e.getMessage());
            }
        }
    }

    public static void parseMonster(ArrayList<Monster> allmonsters){
        String[] n; //each product info
        String p; //each printing string
        String[] monsterlist = {"Dragons","Exoskeletons","Spirits"};

        for(String l:monsterlist){
            try{
                File info = new File( path + "info/"+l+".txt");
                Scanner reader = new Scanner(info);
                reader.nextLine();
                while(reader.hasNextLine()){
                    p = reader.nextLine();
                    if(p.trim().length()>0){
                        n = p.split("\\s+");
                        switch(l){
                            case "Dragons":
                                allmonsters.add(new Dragon(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                            case "Exoskeletons":
                                allmonsters.add(new Exoskeleton(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                            case "Spirits":
                                allmonsters.add(new Spirit(n[0],Integer.parseInt(n[1]), Integer.parseInt(n[2]),Integer.parseInt(n[3]),Integer.parseInt(n[4])));
                                break;
                        }   
                    }
                }
                reader.close();
            }catch(Exception e){
                System.out.println("error here: "+e.getMessage());
            }
        }
    }

    //generate ranomd monsters
    public static void showMonster(ArrayList<Monster> allmonsters, ArrayList<Monster> monsterhere, int limit, int num){
        ArrayList<Monster> tempmon = new ArrayList<Monster>();
        for(Monster m:allmonsters){
            if(m.getLevel()<=limit){
                tempmon.add(m);
            }
        }
        System.out.println("Monster you'll fight with: ");
        for(int i=0; i<num; i++){
            int tempind = rand.nextInt(tempmon.size()-1);
            monsterhere.add(tempmon.get(tempind));
            System.out.println(tempmon.get(tempind));
            tempmon.remove(tempind);
        }
        
    }

    //to buy things in market
    public static Item showProduct(ArrayList<Weapon> mktweapon,ArrayList<Armor> mktarm,ArrayList<Potion> mktpotion,ArrayList<FireSpell> mktfspell,ArrayList<IceSpell> mktispell, ArrayList<LightningSpell> mktlspell, int choice, int limit){
        Item item = new Item();
        ArrayList<Item> prod = new ArrayList<Item>();
        ArrayList<Spell> spells = new ArrayList<Spell>();
        switch(choice){
            case 1:
                prod = new ArrayList<Item>(mktweapon);
                System.out.println("Available weapon");
                break;
            case 2:
                prod = new ArrayList<Item>(mktarm);
                System.out.println("Available weapon");
                break;
            case 3:
                prod = new ArrayList<Item>(mktpotion);
                System.out.println("Available weapon");
                break;
            case 4:
                spells.addAll(mktfspell);
                spells.addAll(mktispell);
                spells.addAll(mktlspell);
                System.out.println("Available weapon");
                break;
        }
        for(Item p:prod){
            if(p.getRequiredLevel()<=limit){
                System.out.print(p);
            }
        }

        for(Spell s:spells){
            if(s.getRequiredLevel()<=limit){
                System.out.print(s);
            }
        }

        System.out.println("Which one do you want to purchase?");
        String productname = stdin.nextLine();
        for(Item p: prod){
            if(p.getName().equals(productname)){
                item = p;
                break;
            }
        }

        //if use, need to consider the spell situation

        return item;
    }

    public static void showBag(ArrayList<Item> bag){
        System.out.println("Your bag items");
        for (Item e:bag){
            System.out.println(e);
        }
    }

    public static void showmyspell(ArrayList<Item>bag){
        System.out.println("Your spells");
        for (Item e:bag){
            if (Spell.class.isInstance(e)){
                System.out.println(e);
            }
        }
    }

    public static void showmypotion(ArrayList<Item>bag){
        System.out.println("Your potions");
        for (Item e:bag){
            if (Potion.class.isInstance(e)){
                System.out.println(e);
            }
        }
    }
}
