import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;

import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 

public class gameRunner
{
   public static Scanner input = new Scanner(System.in);
   private static TreeSet<String> names = new TreeSet<String>();
   
   public static void fillNames() throws IOException
   {
      Scanner fileScan = new Scanner(new File("characterNames.dat"));
      
      while(true) //keep adding names until it crashes, I suppose
      {
         try
         {
            names.add(fileScan.nextLine());
         }
         catch(Exception e)
         {
            break;
         }
      }  
   }
   
   
   public static void main(String[] args)
   {
      CharacterQueue ring;
      Character player = new Paladin("dummy");
      ArrayList<Character> enemies = new ArrayList<Character>();
      //scan characterNames.txt and add it to the names set
      try
      {
         fillNames();
      }
      catch(IOException e)
      {
         System.out.println("[No Save Files Found]");
      }
      
      //display the set
      int i = 0;
      for(String name : names)
      {
         i++;
         if(i > 5)
         {
            i = 0;
            System.out.println();
         }
         System.out.print(name);
         for(int x = name.length(); x < 20; x++)
         {
            System.out.print(" ");
         }
      }
      //allow player to choose a name
      System.out.println("\n\nWelcome! Please select one of the pre-given names to load a save file.\nAlternatively, enter a new name to create a new save file.");
      
      String name = input.nextLine();
      
      //choose to use that character's save file (if applicable)
      boolean isNew = true;
      if(names.contains(name))
      {
         System.out.println("Continue with "+name+"'s save file? Enter no to start overwrite file and start anew.\n");
         player = loadCharacter(name);
         System.out.println(player.displayStatus() + "\n");
         isNew = !yesOrNo();
      }
      //character creation
      char response;
      if(isNew)
      {
         System.out.println("Okay, "+name+"! Your new character will be created!");
         //dennis does this part
         System.out.println("Enter a letter associated with either of the following:");
         System.out.println("A) Paladin - A well-balanced class that focuses on building defense.");
         System.out.println("B) Healer - An extremely sturdy class that can quickly heal HP and inflict status effects.");
         System.out.println("C) Warrior - An extremely offensive oriented class that focuses on killing the enemy quickly.");
         System.out.println("D) Archer - A dexterious class that can spread out powerful status inflicting shots on multiple enemies.");
         System.out.println("E) Magician - A high-risk high-reward class that uses their own HP to cast powerful magic moves.");
         System.out.println("F) Vagabond - A well-rounded class that can hold its own in any battle.");
         response = getResponse(new char[] {'A','B','C','D','E','F'});
         switch(response)
         {
            case 'A':
               player = new Paladin(name);
               System.out.println("\nYou feel blessed with divine invigoration. You became a paladin!");
               break;
            case 'B':
               player = new Healer(name);
               System.out.println("\nYou sense the desperation and suffering of the wounded with your staff. You became a healer!");
               break;
            case 'C':
               player = new Warrior(name);
               System.out.println("\nYou sharpen your dual blades with a thirst for battle. You became a warrior!");
               break;
            case 'D':
               player = new Archer(name);
               System.out.println("\nYou tie your bowstring and notch an arrow onto your trusty bow. You became an archer!");
               break;
            case 'E':
               player = new Magician(name);
               System.out.println("\nYou memorize your last few magical incantations with a whip of your wand. You became a magician!");
               break;
            case 'F':
               player = new Vagabond(name);
               System.out.println("\nYou walk into the unknown, prepared for what lies ahead. You became a vagabond!");
               break;
         }
      }
      
      int minBoost = 0;
      int jackpotBonus = 0;
      boolean isPlaying = true;
      while(isPlaying)
      {
         //status screen; allow user to check out their character
         System.out.println("\n\n\nStatus...\n\n"+player.displayStatus() + "\n");
         System.out.println("Press enter to continue...");
         input.nextLine();
         //battle preparation (maybe?)
         //ex: choose a location, and that determines the random teams of enemies?
         ArrayList<java.lang.Character> options = new ArrayList<java.lang.Character>();
         //bosses are only encountered at level 5+
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
         System.out.println("Please select one of the following locations to visit:");
         double chance = 0;
         options.add('A');
         System.out.println("A) Abandoned Forest");    //even distribution of all 5 minor enemies
         if(player.getLevel() >= 5)
         {
            options.add('B');
            System.out.println("B) Brackish Marsh");     //mostly slimes, witches. Boss: Dark Sorcerer
         }
         if(player.getLevel() >= 10)
         {
            options.add('C');
            System.out.println("C) Creepy Castle");    //mostly thieves, some witches. Boss: Evil King + Evil Knights
         }
         if(player.getLevel() >= 15)
         {
            options.add('D');
            System.out.println("D) Deep Caverns");     //mostly stone golems, goblins. some thieves. Boss: Dragon
         }
         if(player.getLevel() >= 20)
         {
            options.add('E');
            System.out.println("E) Enigmatic City");   //inverted distribution, rare minor enemies show up more. Boss: Imposter
         }
         if(player.getLevel() >= 25)
         {
            options.add('F');
            System.out.println("F) Boss Duel"); //25% chance for each boss.
         }
         //empty enemy list
         while(enemies.size() > 0)
         {
            enemies.remove(0);
         }
         //add enemies. This placeholder is a very unbalanced team that the player should not be able to beat
         options.add('J');//secret devtool. Fight an enemy of the uer's choosing.
         char[] arrayOptions = new char[options.size()];
         for(i = 0; i < arrayOptions.length; i++)
         {
            arrayOptions[i] = options.get(i);
         }
         char option = getResponse( arrayOptions);   
         
         
         switch(option)
         {
            case 'A':  
               minBoost = 0;
               jackpotBonus = 0;
               if(player.getLevel() >= 5 && Math.random() < 0.2)
               {
                  chance = Math.random();
                  if(chance < 0.25)
                  {
                     enemies.add(new Dragon(player.getLevel() + ((int)(Math.random() * 5) - 2)));
                  }
                  else if(chance < 0.5)
                  {
                     enemies.add(new Imposter(player));
                  
                  }
                  else if(chance < 0.75)
                  {
                     enemies.add(new DarkSorcerer(player.getLevel() + ((int)(Math.random() * 5) - 2)));
                  
                  }
                  else
                  {
                     enemies.add(new King(player.getLevel() + ((int)(Math.random() * 5))));
                     enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() * 5) - 4)));
                     if(player.getLevel() >= 10)
                        enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() * 5) - 4)));
                     if(player.getLevel() >= 15)
                        enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() * 5) - 4)));
                  }
               }
               else if(player.getLevel() >= 15 && Math.random() < 0.4)
               {
                  for(i = 4; i > 0; i--)
                  {
                     chance = Math.random();
                     if(chance < 0.2)
                     {
                        enemies.add(new Slime(player.getLevel() + ((int)(Math.random() * 7 - 7))));
                     }
                     else if(chance < 0.4)
                     {
                        enemies.add(new Witch(player.getLevel()+ ((int)(Math.random() * 7 - 7))));
                     }
                     else if(chance < 0.6)
                     {
                        enemies.add(new StoneGolem(player.getLevel()+ ((int)(Math.random() * 7 - 7))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new Thief(player.getLevel()+ ((int)(Math.random() * 7 - 7))));
                     }
                     else
                     {
                        enemies.add(new Goblin(player.getLevel()+ ((int)(Math.random() * 7 - 7))));
                     }
                  }
               }  
               else if(player.getLevel() >= 10 && Math.random() < 0.7)
               {
                  for(i = 3; i > 0; i--)
                  {
                     chance = Math.random();
                     if(chance < 0.2)
                     {
                        enemies.add(new Slime(player.getLevel() + ((int)(Math.random() * 6 - 5))));
                     }
                     else if(chance < 0.4)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() * 6 - 5))));
                     }
                     else if(chance < 0.6)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() * 6 - 5))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() * 6 - 5))));
                     }
                     else
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() * 6 - 5))));
                     }
                  }
               }
               else if(player.getLevel() >= 5 && Math.random() < 0.5)
               {
                  for(i = 2; i > 0; i--)
                  {
                     chance = Math.random();
                     if(chance < 0.2)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() * 5 - 3))));
                     }
                     else if(chance < 0.4)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() * 5 - 3))));
                     }
                     else if(chance < 0.6)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() * 5 - 3))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() * 5 - 3))));
                     }
                     else
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() * 5 - 3))));
                     }
                  }
               }
               else
               {
                  chance = Math.random();
                  if(chance < 0.2)
                  {
                     enemies.add(new Slime(player.getLevel()));
                  }
                  else if(chance < 0.4)
                  {
                     enemies.add(new Witch(player.getLevel()));
                  }
                  else if(chance < 0.6)
                  {
                     enemies.add(new StoneGolem(player.getLevel()));
                  }
                  else if(chance < 0.8)
                  {
                     enemies.add(new Thief(player.getLevel()));
                  }
                  else
                  {
                     enemies.add(new Goblin(player.getLevel()));
                  }
               }
                  
               break;
            case 'B':
               minBoost = 0;
               jackpotBonus = 1;
               chance = Math.random();
               if(Math.random() < 0.3)
               {
                  enemies.add(new DarkSorcerer(player.getLevel() + (int)(Math.random() * 4 - 2)));
                  if(Math.random() < 0.6)
                  {
                     enemies.add(new Slime(enemies.get(0).getLevel() - 1));
                  }
               }
               else if(Math.random() < 0.4 && player.getLevel() >= 15)
               {
                  enemies.add(new Witch(player.getLevel() + (int)(Math.random() * 7 - 6)));
                  ((Enemy)(enemies.get(0))).changeName("Witch Leader");
                  for(i = 0; i < 3; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.35)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  7 - 7))));
                     }
                     else if(chance < 0.7)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  7 - 7))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  7 - 7))));
                     }
                     else if(chance < 0.9)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  7 - 7))));
                     }
                     else
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  7 - 7))));
                     }
                  }
               }
               else if(Math.random() < 0.7 && player.getLevel() >= 10)
               {
                  if(Math.random() < 0.5)
                  {
                     enemies.add(new Witch(player.getLevel() + (int)(Math.random() * 6 - 4)));
                     ((Enemy)(enemies.get(0))).changeName("Witch Leader");
                  }
                  else
                  {
                     enemies.add(new Slime(player.getLevel() + (int)(Math.random() * 6 - 4)));
                     ((Enemy)(enemies.get(0))).changeName("Marsh Slime");
                  }
                  for(i = 0; i < 2; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.35)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  6 - 5))));
                     }
                     else if(chance < 0.7)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  6 - 5))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  6 - 5))));
                     }
                     else if(chance < 0.9)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  6 - 5))));
                     }
                     else
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  6 - 5))));
                     }
                  }
               }
               else if(Math.random() < 0.5)
               {
                  if(Math.random() < 0.5)
                  {
                     enemies.add(new Witch(player.getLevel() + (int)(Math.random() * 5 - 2)));
                     ((Enemy)(enemies.get(0))).changeName("Witch Leader");
                  }
                  else
                  {
                     enemies.add(new Slime(player.getLevel() + (int)(Math.random() * 5 - 2)));
                     ((Enemy)(enemies.get(0))).changeName("Marsh Slime");
                  }
                  chance = Math.random();
                  if(chance < 0.35)
                  {
                     enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  5 - 3))));
                  }
                  else if(chance < 0.7)
                  {
                     enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  5 - 3))));
                  }
                  else if(chance < 0.8)
                  {
                     enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  5 - 3))));
                  }
                  else if(chance < 0.9)
                  {
                     enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  5 - 3))));
                  }
                  else
                  {
                     enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  5 - 3))));
                  }
                  
               }
               else
               {
                  if(Math.random() < 0.5)
                  {
                     enemies.add(new Witch(player.getLevel() + (int)(Math.random() * 5 - 1)));
                     ((Enemy)(enemies.get(0))).changeName("Witch Leader");
                  }
                  else
                  {
                     enemies.add(new Slime(player.getLevel() + (int)(Math.random() * 5 - 1)));
                     ((Enemy)(enemies.get(0))).changeName("Marsh Slime");
                  }
               }
               
               
               break;
            case 'C':
               minBoost = 0;
               jackpotBonus = 2;
               if(Math.random() < 0.3)
               {
                  chance = Math.random();
                  enemies.add(new King(player.getLevel() + (int)(Math.random() * 4 - 1)));
                  if(chance < 0.01) //1% chance of absolutely no allies
                  {
                     ((Enemy)(enemies.get(0))).changeName("Ambushed King");
                  }
                  else if(chance < 0.05)
                  {
                     enemies.add(new EvilKnight(enemies.get(0).getLevel() - 2));
                  }
                  else if(chance < 0.50)
                  {
                     enemies.add(new EvilKnight(enemies.get(0).getLevel() + ((int)(Math.random() *  2 - 3))));
                     chance = Math.random();
                     if(chance < 0.1)
                     {
                        enemies.add(new Witch(enemies.get(0).getLevel()+ ((int)(Math.random() *  2 - 3))));
                     }
                     else if(chance < 0.3)
                     {
                        enemies.add(new Thief(enemies.get(0).getLevel() + ((int)(Math.random() *  2 - 3))));
                     }
                     else if(chance < 0.35)
                     {
                        enemies.add(new Goblin(enemies.get(0).getLevel() + ((int)(Math.random() *  2 - 3))));
                     }
                     else
                     {
                        enemies.add(new EvilKnight(enemies.get(0).getLevel() + ((int)(Math.random() *  2 - 3))));
                     }
                  }
                  else if(chance < 0.95)
                  {
                     enemies.add(new EvilKnight(enemies.get(0).getLevel() + ((int)(Math.random() *  3 - 5))));
                     for(i = 0; i < 2; i++)
                     {
                        chance = Math.random();
                        if(chance < 0.1)
                        {
                           enemies.add(new Witch(enemies.get(0).getLevel()+ ((int)(Math.random() *  3 - 5))));
                        }
                        else if(chance < 0.3)
                        {
                           enemies.add(new Thief(enemies.get(0).getLevel() + ((int)(Math.random() *  3 - 5))));
                        }
                        else if(chance < 0.35)
                        {
                           enemies.add(new Goblin(enemies.get(0).getLevel() + ((int)(Math.random() *  3 - 5))));
                        }
                        else
                        {
                           enemies.add(new EvilKnight(enemies.get(0).getLevel() + ((int)(Math.random() *  3 - 5))));
                        }
                     }
                  }
                  else
                  {
                     for(i = 0; i < 2; i++)
                     {
                        enemies.add(new EvilKnight(enemies.get(0).getLevel() + ((int)(Math.random() *  5 - 7))));
                        chance = Math.random();
                        if(chance < 0.1)
                        {
                           enemies.add(new Witch(enemies.get(0).getLevel()+ ((int)(Math.random() *  5 - 7))));
                        }
                        else if(chance < 0.3)
                        {
                           enemies.add(new Thief(enemies.get(0).getLevel() + ((int)(Math.random() *  5 - 7))));
                        }
                        else if(chance < 0.35)
                        {
                           enemies.add(new Goblin(enemies.get(0).getLevel() + ((int)(Math.random() *  5 - 7))));
                        }
                        else
                        {
                           enemies.add(new EvilKnight(enemies.get(0).getLevel() + ((int)(Math.random() *  5 - 7))));
                        }
                     }
                  }
               }
               else if(Math.random() < 0.4 && player.getLevel() >= 15)
               {
                  enemies.add(new EvilKnight(player.getLevel() + (int)(Math.random() * 7 - 5)));
                  ((Enemy)(enemies.get(0))).changeName("Knight Captain");
                  for(i = 0; i < 3; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.05)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  7 - 6))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Castle Slime");
                     }
                     else if(chance < 0.3)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  7 - 6))));
                     }
                     else if(chance < 0.35)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  7 - 6))));
                     }
                     else if(chance < 0.75)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  7 - 6))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  7 - 6))));
                     
                     }
                     else
                     {
                        enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() *  7 - 6))));
                     }
                  }
               }
               else if(Math.random() < 0.7)
               {
                  enemies.add(new EvilKnight(player.getLevel() + (int)(Math.random() * 6 - 3)));
                  ((Enemy)(enemies.get(0))).changeName("Knight Captain");
                  for(i = 0; i < 2; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.05)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  6 - 4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Castle Slime");
                     }
                     else if(chance < 0.3)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  6 - 4))));
                     }
                     else if(chance < 0.35)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  6 - 4))));
                     }
                     else if(chance < 0.75)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  6 - 4))));
                     }
                     else if(chance < 0.8)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  6 - 4))));
                     
                     }
                     else
                     {
                        enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() *  6 - 4))));
                     }
                  }
               }
               else if(Math.random() < 0.5)
               {
                  enemies.add(new EvilKnight(player.getLevel() + (int)(Math.random() * 5 - 1)));
                  ((Enemy)(enemies.get(0))).changeName("Knight Captain");
                  chance = Math.random();
                  if(chance < 0.05)
                  {
                     enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  5 - 2))));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Castle Slime");
                  }
                  else if(chance < 0.3)
                  {
                     enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
                  else if(chance < 0.35)
                  {
                     enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
                  else if(chance < 0.75)
                  {
                     enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
                  else if(chance < 0.8)
                  {
                     enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                     
                  }
                  else
                  {
                     enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
               }
               else
               {
                  chance = Math.random();
                  if(chance < 0.05)
                  {
                     enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  5 - 2))));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Castle Slime");
                  }
                  else if(chance < 0.3)
                  {
                     enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
                  else if(chance < 0.35)
                  {
                     enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
                  else if(chance < 0.75)
                  {
                     enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
                  else if(chance < 0.8)
                  {
                     enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                     
                  }
                  else
                  {
                     enemies.add(new EvilKnight(player.getLevel() + ((int)(Math.random() *  5 - 2))));
                  }
               }
               break;
            case 'D':
               minBoost = 0;
               jackpotBonus = 3;
               if(Math.random() < 0.3)
               {
                  enemies.add(new Dragon(player.getLevel() + (int)(Math.random() * 3)));
                  if(Math.random() < 0.3)
                  {
                     enemies.add(new Dragon(enemies.get(0).getLevel() - 10));
                     ((Enemy)(enemies.get(1))).changeName("Baby Dragon");
                  }
               }
               else if(Math.random() < 0.4)
               {
                  for(i = 0; i < 4; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.03)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  4 - 4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Crystal Slime");
                     }
                     else if(chance < 0.1)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  4 - 4))));
                     }
                     else if(chance < 0.4)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  4 - 4))));
                     }
                     else if(chance < 0.57)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  4 - 4))));
                     }
                     else if(chance < 0.87)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  4 - 4))));
                     }
                     else
                     {
                        enemies.add(new Dragon(player.getLevel()+ ((int)(Math.random() *  4 - 14))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Baby Dragon");
                     }
                  }
               }
               else if(Math.random() < 0.7)
               {
                  for(i = 0; i < 3; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.03)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  4 - 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Crystal Slime");
                     }
                     else if(chance < 0.1)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                     }
                     else if(chance < 0.4)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                     }
                     else if(chance < 0.57)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                     }
                     else if(chance < 0.87)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                     }
                     else
                     {
                        enemies.add(new Dragon(player.getLevel()+ ((int)(Math.random() *  4 - 12))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Baby Dragon");
                     }
                  }
               }
               else
               {
                  for(i = 0; i < 2; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.03)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  3))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Crystal Slime");
                     }
                     else if(chance < 0.1)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  3))));
                     }
                     else if(chance < 0.4)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  3))));
                     }
                     else if(chance < 0.57)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  3))));
                     }
                     else if(chance < 0.87)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  3))));
                     }
                     else
                     {
                        enemies.add(new Dragon(player.getLevel()+ ((int)(Math.random() *  3 - 10))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Baby Dragon");
                     }
                  }
               }
               break;
            case 'E': //amogus
               minBoost = 1;
               jackpotBonus = 3;
               if(Math.random() < 0.3)
               {
                  enemies.add(new Imposter(player, ((Math.random() * 0.2) + 1)));
                  if(Math.random() < 0.3)
                  {
                     enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.2)));
                     ((Enemy)(enemies.get(1))).changeName("Mini Crewmate");
                  }
               }
               else if(Math.random() < 0.3)
               {  //5 imposters
                  enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.2)));
                  ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Red Crewmate");
                  enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.2)));
                  ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Blue Crewmate");
                  enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.2)));
                  ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Yellow Crewmate");
                  enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.2)));
                  ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Green Crewmate");
                  enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.2)));
                  ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Pink Crewmate");
               }
               else if(Math.random() < 0.5)
               {
                  for(i = 0; i < 4; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.2004)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  4 - 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Space Slime");
                     }
                     else if(chance < 0.3449)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Techno Magician");
                     }
                     else if(chance < 0.5550)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Automaton");
                     }
                     else if(chance < 0.6899)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Cyberthief");
                     }
                     else if (chance < 0.9)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  4 - 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Goblin.exe");
                     }
                     else
                     {
                        enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.6)));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Mini Crewmate");
                     }
                  }
               }
               else if(Math.random() < 0.7)
               {
                  for(i = 0; i < 3; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.2004)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Space Slime");
                     }
                     else if(chance < 0.3449)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Techno Magician");
                     }
                     else if(chance < 0.5550)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Automaton");
                     }
                     else if(chance < 0.6899)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Cyberthief");
                     }
                     else if (chance < 0.9)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  4))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Goblin.exe");
                     }
                     else
                     {
                        enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.7)));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Mini Crewmate");
                     }
                  }
               }
               else
               {
                  for(i = 0; i < 2; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.2004)
                     {
                        enemies.add(new Slime(player.getLevel()+ ((int)(Math.random() *  3 + 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Space Slime");
                     }
                     else if(chance < 0.3449)
                     {
                        enemies.add(new Witch(player.getLevel() + ((int)(Math.random() *  3 + 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Techno Magician");
                     }
                     else if(chance < 0.5550)
                     {
                        enemies.add(new StoneGolem(player.getLevel() + ((int)(Math.random() *  3 + 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Automaton");
                     }
                     else if(chance < 0.6899)
                     {
                        enemies.add(new Thief(player.getLevel() + ((int)(Math.random() *  3 + 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Cyberthief");
                     }
                     else if (chance < 0.9)
                     {
                        enemies.add(new Goblin(player.getLevel() + ((int)(Math.random() *  3 + 2))));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Goblin.exe");
                     }
                     else
                     {
                        enemies.add(new Imposter(player, ((Math.random() * 0.2) + 0.8)));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Mini Crewmate");
                     }
                  }
               }
               break;
            case 'F':
               minBoost = 2;
               jackpotBonus = 3;
               if(Math.random() < 0.5)
               {
                  for(i = 0; i < 2; i++)
                  {
                     chance = Math.random();
                     if(chance < 0.25)
                     {
                        enemies.add(new Dragon(player.getLevel() + ((int)(Math.random() * 4) )));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Elder Dragon");
                     }
                     else if(chance < 0.5)
                     {
                        enemies.add(new Imposter(player, (Math.random() * 0.1) + 0.85));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Doppelganger");
                     }
                     else if(chance < 0.75)
                     {
                        enemies.add(new DarkSorcerer(player.getLevel() + ((int)(Math.random() * 4) )));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Spellmaster");
                     }
                     else
                     {
                        enemies.add(new King(player.getLevel() + ((int)(Math.random() * 4) )));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Emperor");
                        enemies.add(new EvilKnight(enemies.get(enemies.size() - 1).getLevel() - 20));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Imperial Knight");
                        enemies.add(new EvilKnight(enemies.get(enemies.size() - 2).getLevel() - 20));
                        ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Imperial Knight");
                     }
                  }
               }
               else
               {
                  chance = Math.random();
                  if(chance < 0.25)
                  {
                     enemies.add(new Dragon(player.getLevel() + ((int)(Math.random() * 4) + 2)));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Elder Dragon");
                  }
                  else if(chance < 0.5)
                  {
                     enemies.add(new Imposter(player, (Math.random() * 0.2) + 1.10));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Doppelganger");
                  }
                  else if(chance < 0.75)
                  {
                     enemies.add(new DarkSorcerer(player.getLevel() + ((int)(Math.random() * 4) + 2)));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Spellmaster");
                  }
                  else
                  {
                     enemies.add(new King(player.getLevel() + ((int)(Math.random() * 4) + 2)));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Emperor");
                     enemies.add(new EvilKnight(enemies.get(enemies.size() - 1).getLevel() - 20));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Imperial Knight");
                     enemies.add(new EvilKnight(enemies.get(enemies.size() - 2).getLevel()- 20));
                     ((Enemy)(enemies.get(enemies.size() - 1))).changeName("Imperial Knight");
                  }
               }
               break;         
            case 'J':   
               minBoost = 0;
               jackpotBonus = 0;  
               isPlaying = true;
               i = 0;
               double mult = 0;
               while(isPlaying)
               {
                  System.out.println("Enter one of the following enemies to fight.");
                  System.out.println("A) Slime") ;  
                  System.out.println("B) Witch") ; 
                  System.out.println("C) Stone Golem") ; 
                  System.out.println("D) Thief") ; 
                  System.out.println("E) Goblin") ; 
               
                  System.out.println("F) Dragon") ; 
                  System.out.println("G) Imposter") ; 
                  System.out.println("H) Dark Sorcerer") ; 
                  System.out.println("I) Evil King") ; 
                  System.out.println("J) Evil Knight") ; 
               
               
                  response = getResponse(new char[] {'A','B','C','D','E','F','G','H','I','J'});
               
                  if(response == 'G')
                  {
                     System.out.println("\nEnter the imposter's multiplier value (decimals allowed)...");
                     String val = input.nextLine();
                  
                     try
                     {
                        mult = Double.parseDouble(val);
                     }
                     catch(Exception e)
                     {
                        System.out.println("Not a decimal! Defaulting to level " + player.getLevel());
                        mult = 1;
                     }
                  }
                  else
                  {
                     System.out.println("\nEnter the enemy's level (Note that the player is currently level "+player.getLevel()+")");
                     String val = input.nextLine();
                     try
                     {
                        i = Integer.parseInt(val);
                     }
                     catch(Exception e)
                     {
                        System.out.println("Not an int! Defaulting to level " + player.getLevel());
                        i = player.getLevel();
                     }
                  }
               
                  
                  switch(response)
                  {
                     case 'A':
                        System.out.println("Added level "+ i + " Slime to enemy team");
                        enemies.add(new Slime(i));
                        break;
                     case 'B':
                        System.out.println("Added level "+ i + " Witch to enemy team");
                        enemies.add(new Witch(i));
                        break;
                     case 'C':
                        System.out.println("Added level "+ i + " Stone Golem to enemy team");
                        enemies.add(new StoneGolem(i));
                        break;
                     case 'D':
                        System.out.println("Added level "+ i + " Thief to enemy team");
                        enemies.add(new Thief(i));
                        break;
                     case 'E':
                        System.out.println("Added level "+ i + " Goblin to enemy team");
                        enemies.add(new Goblin(i));
                        break;
                        //boosses
                     case 'F':
                        System.out.println("Added level "+ i + " Dragon to enemy team");
                        enemies.add(new Dragon(i));
                        break;
                     case 'G':
                        System.out.println("Added level "+ (int)(player.getLevel() * mult) + " Imposter to enemy team");
                        enemies.add(new Imposter(player, mult));
                        break;
                     case 'H':
                        System.out.println("Added level "+ i + " Dark Sorcerer to enemy team");
                        enemies.add(new DarkSorcerer(i));
                        break;
                     case 'I':
                        System.out.println("Added level "+ i + " Evil King to enemy team");
                        enemies.add(new King(i));
                        break;
                     case 'J':
                        System.out.println("Added level "+ i + " Evil Knight to enemy team");
                        enemies.add(new EvilKnight(i));
                        break;
                  }
                  
                  System.out.println("Are you done selecting enemies?");
                  isPlaying = !yesOrNo();
               }
               break; 
         }   
         if(enemies.size() == 0)
            enemies.add(new Dragon(player.getLevel() + 2));
         
         
         
         //enemies.add(new EvilKnight(player.getLevel() - 2));
         //battle setup
         //System.out.println(player.getName() + " added to ring's first node");
         ring = new CharacterQueue(null,player);
         for(Character enemy : enemies)
         {
            //System.out.println(enemy.getName() + " added to ring");
            ring.add(new CharacterQueue(null, enemy));
            //System.out.println(enemy.getName() + " added to ring complete");
         }
         ring.makeRing();
         //System.out.println("ring created");
         //battle
         int combatants = enemies.size();
         while(player.getHP() > 0 && combatants > 0)
         {
            if(ring.getCharacter().getHP() > 0)
            {
               System.out.println(ring.getCharacter().getName() + "'s turn!\n");
               pause();
               ring.getCharacter().act(player, enemies);
               
               pause();
            }
            ring = ring.getNext();
            combatants = 0;
            for(Character enemy : enemies)
            {
               if(enemy.getHP() > 0)
               {
                  combatants++;
               }
            }
         }
         
         
         //end of battle
         
         //if player.getHP() > 0
         if(player.getHP() > 0)
         {
            //gain experience and level up i guess
            player.resetModifiers();
            System.out.println("\n\n\n\nYay! " + player.getName() + " leveled up!\n");
            ((Player)(player)).levelUp();
            System.out.println(player.displayStatus());
            System.out.println("\nWhich stat would you like to increase?");
            System.out.println("A) Health\tB) Attack\tC) Defense\tD) Speed");
            
            option = getResponse(new char[] {'A','B','C','D'});
            i = (int)(Math.random() * (4 + jackpotBonus) + minBoost);
            if(i > 3)
            {
               i = 3;
            }
            switch(i)
            {
               case 0:
                  System.out.print("Oh no! " + player.getName() + "'s ");
                  break;
               case 1:
                  System.out.print("Nice! " + player.getName() + "'s ");
                  break;
               case 2:
                  System.out.print("Woah! " + player.getName() + "'s ");
                  break;
               case 3:
                  System.out.print("Jackpot! " + player.getName() + "'s ");
                  break;
            }
            player.boostStat(i, option);
         }
         else
         {
               //you lose
            player.resetModifiers();
            System.out.println("Don't worry, " + player.getName() + ". You'll get them next time!");
         }
         System.out.println("Would you like to continue playing?");
         isPlaying = yesOrNo();
         //else
            //boohoo you lose
            //ends run without saving
            //alternatively, offer to restore hp to max and try again?
      }
      System.out.println("Would you like to save " + player.getName() + "'s data?");
      isPlaying = yesOrNo();
      if(isPlaying)
         saveCharacter(player);
      
   }
   
   public static boolean yesOrNo()
   {
      String text;
      while(true)
      {
         System.out.println("Please select either Yes or No.");
         text = input.nextLine();
         if(text.toLowerCase().indexOf("y") > -1)
         {
            return true;
         }
         else if(text.toLowerCase().indexOf("n") > -1)
         {
            return false;
         }
      }
   }
   
   public static char getResponse(char[] options)
   {
      String response;
      while(true)
      {
         response = input.nextLine();
         if(response.length() > 0)
            for(char option : options)
               if(option == response.toUpperCase().charAt(0))
                  return response.toUpperCase().charAt(0);
         System.out.println("please enter one of the following responses:");
         for(char option : options)
         {
            System.out.print(option + " ");
         }
         System.out.println();
      }
   }
   
   public static void pause()
   {
      System.out.println("Press enter to continue...");
      input.nextLine();
   }
   
   //load character
   public static Character loadCharacter(String id)
   {
      try
      {
         Scanner scan = new Scanner(new File(id + ".txt"));
         String text = scan.nextLine();
         Player player = new Paladin(id);
         switch(text)
         {
            case "Paladin":
               player = new Paladin(id);
               break;
            case "Healer":
               player = new Healer(id);
               break;
            case "Warrior":
               player = new Warrior(id);
               break;
            case "Archer":
               player = new Archer(id);
               break;
            case "Magician":
               player = new Magician(id);
               break;
            case "Vagabond":
               player = new Vagabond(id);
               break;
         }
         
         //System.out.println(
         scan.nextLine();
         
         player.loadLevel(scan.nextInt());
         scan.nextLine();
         
         player.loadHP(scan.nextInt());
         scan.nextLine();
         
         player.loadAtk(scan.nextInt());
         scan.nextLine();
         
         player.loadDef(scan.nextInt());
         scan.nextLine();
         
         player.loadSpd(scan.nextInt());
         
         return player;
         
      }
      catch(IOException e)
      {
         System.out.println("File Does Not Exist...? Creating new Paladin named " + id);
         return new Paladin(id);
      }
   }
   
   //save or overwrite character
   public static void saveCharacter(Character user)
   {
      try 
      {
         File saveFile = new File(user.getName() + ".txt");
         if (saveFile.createNewFile())  
         {  //overwrite file
         }
         FileWriter saveEdit = new FileWriter(user.getName() + ".txt");
         saveEdit.write(((Player)(user)).getJob());
         saveEdit.write("\n"+ ((Player)(user)).getName());
         saveEdit.write("\n"+ ((Player)(user)).getLevel());
         saveEdit.write("\n"+ ((Player)(user)).getMaxHP());
         saveEdit.write("\n"+ ((Player)(user)).getBaseAtk());
         saveEdit.write("\n"+ ((Player)(user)).getBaseDef());
         saveEdit.write("\n"+ ((Player)(user)).getSpd());
         saveEdit.close();
         
         saveEdit = new FileWriter("characterNames.dat");
         saveEdit.write(((Player)(user)).getName());
         for(String name : names)
         {
            saveEdit.write("\n" + name);
         }
         saveEdit.close();
      } 
      catch (IOException e) {}
   }
   
   /*
   Character file format:
   
   character class
   character name
   level
   max hp
   current hp
   base atk
   base def
   base spd
   */
}