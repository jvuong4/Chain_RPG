import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;

public abstract class Character
{
   //level
   protected int level = 1;
   //base stats
   protected int HP;
   protected int MaxHP;
   protected int BaseAtk;
   protected int BaseDef;
   protected int Spd;
   //name
   protected String name;
   //stat modifiers
   protected int ModAtk;
   protected int ModDef;
   
   public String display()
   {  //ui text
      return name + "\tLv. " + + level + "\n" + HP + "/" + MaxHP + " Health"; 
   }
   public String displayMini()
   {  //very compressed and small text, only one line
      return name + "\t" + HP + "/" + MaxHP;
   }
   public String displayStatus()
   {  //status, very in depth
      String str = name;
      str = str + "\nLevel " + level;
      str = str + "\n" + HP + "/" + MaxHP + " Health";
      if(ModAtk >= 0)
      {
         str = str + "\n" + BaseAtk + " (+" + ModAtk + ") Attack";
      }
      else
      {
         str = str + "\n" + BaseAtk + " (" + ModAtk + ") Attack";
      }
      
      if(ModDef >= 0)
      {
         str = str + "\n" + BaseDef + " (+" + ModDef + ") Defense";
      }
      else
      {
         str = str + "\n" + BaseDef + " (" + ModDef + ") Defense";
      }
      str = str + "\n" + Spd + " Speed";
      return str; //more detailed status display
   }
   
   //get bases
   public int getMaxHP()
   {
      return MaxHP;
   }
   public int getBaseAtk()
   {
      return BaseAtk;
   }
   public int getBaseDef()
   {
      return BaseDef;
   }
   //get level
   public int getLevel()
   {
      return level;
   }
   
   public void boostStat(int val, char stat)
   {
      switch(stat)
      {
         case 'A':
            MaxHP += val;
            HP += val;
            System.out.println("Health increased by " + val + "!");
            break;
         case 'B':
            BaseAtk += val;
            System.out.println("Attack increased by " + val + "!");
            break;
         case 'C':
            BaseDef += val;
            System.out.println("Defense increased by " + val + "!");
            break;
         case 'D':
            Spd += val;
            System.out.println("Speed increased by " + val + "!");
            break;
      }
   }
   
   //set maxHP
   public void setMaxHP(int h)
   {
      MaxHP = h;
   }
   
   //get Stat modifier
   public int getModAtk()
   {
      return ModAtk;
   }
   public int getModDef()
   {
      return ModDef;
   }
   //true stat
   public int getAtk()
   {
      if(BaseAtk + ModAtk < 0)
      {
         return 0;
      }
      return BaseAtk + ModAtk;
   }
   public int getDef()
   {
      if(BaseDef + ModDef < 0)
      {
         return 0;
      }
      return BaseDef + ModDef;
   }
   public int getSpd()
   {
      return Spd;
   }
   public int getHP()
   {
      if(HP < 0)
         HP = 0;
      return HP;
   }
   //name and level
   public String getName()
   {
      return name;
   }
   //reset modifiers
   public void resetModifiers()
   {
      ModAtk = 0;
      ModDef = 0;
      HP = MaxHP;
   }
   //balance modifiers
   public void balanceModifiers()
   {
      if(ModAtk < 0)
      {
         ModAtk += 1;
      }
      else if(ModAtk > 0)
      {
         ModAtk -= 1;
      }
      if(ModDef < 0)
      {
         ModDef += 1;
      }
      else if(ModDef > 0)
      {
         ModDef -= 1;
      }
   }
   //modify stats
   public void addAtk(int n)
   {
      if(n * ModAtk < 0 || (ModAtk == 0 && n != 0))//if they have opposite signs
      {
         ModAtk += n;
         if(n > 0)
            System.out.println(name + "'s Attack Modifier boosted to " + ModAtk);
         else
            System.out.println(name + "'s Attack Modifier lowered to " + ModAtk);
      }
      //should have the same sign
      else if(n > 0)
      {
         if(ModAtk < n)
         {
            ModAtk = n;
            System.out.println(name + "'s Attack Modifier boosted to " + ModAtk);
         }
         else //no change in ModAtk
         {
            System.out.println(name + "'s Attack Modifier not affected, still " + ModAtk);
         }
      }
      else if( n < 0)
      {
         if(ModAtk > n)
         {
            ModAtk = n;
            System.out.println(name + "'s Attack Modifier lowered to " + ModAtk);
         
         }
         else //no change in ModAtk
         {
            System.out.println(name + "'s Attack Modifier not affected, still " + ModAtk);
         }
      }
   }
   //modify Defense
   public void addDef(int n)
   {
      if(n * ModDef < 0 || (ModDef == 0 && n != 0))//if they have opposite signs
      {
         ModDef += n;
         if(n > 0)
            System.out.println(name + "'s Defense Modifier boosted to " + ModDef);
         else
            System.out.println(name + "'s Defense Modifier lowered to " + ModDef);
      }
      //should have the same sign
      else if(n > 0)
      {
         if(ModDef < n)
         {
            ModDef = n;
            System.out.println(name + "'s Defense Modifier boosted to " + ModDef);
         }
         else //no change in ModDef
         {
            System.out.println(name + "'s Defense Modifier not affected, still " + ModDef);
         }
      }
      else if (n < 0)
      {
         if(ModDef > n)
         {
            ModDef = n;
            System.out.println(name + "'s Defense Modifier lowered to " + ModDef);
         
         }
         else //no change in ModDef
         {
            System.out.println(name + "'s Defense Modifier not affected, still " + ModDef);
         }
      }
   }
   //modify health values (current hp)
   public void setHP(int n)
   {
      HP = MaxHP;
   }
   public void alterHP(int n)
   {
      HP += n;
      if(n < 0)
      {
         System.out.println(name + " took " + n * -1 + " damage!");
         if(HP <= 0)
         {
            System.out.println(name + " is down!");
            HP = 0;
         }
      }
      else if( n > 0)
      {
         System.out.println(name + " recovered " + n + " health!");
         if(HP > MaxHP)
         {
            HP = MaxHP;
            System.out.println("Health Overflow; HP set to " + HP);
         }
      }  
   }
   
   //for each character's turn, they will act().
   public abstract void act(Character player, ArrayList<Character> team);
}

abstract class Player extends Character
{
   //moveNode
   protected String job;
   protected MoveNode moves;
   //private MoveNode action
   
   public String displayStatus()
   {  //status, very in depth
      String str = name;
      str = str + "\n" + job;
      str = str + "\nLevel " + level;
      str = str + "\n" + HP + "/" + MaxHP + " Health";
      if(ModAtk >= 0)
      {
         str = str + "\n" + BaseAtk + " (+" + ModAtk + ") Attack";
      }
      else
      {
         str = str + "\n" + BaseAtk + " (" + ModAtk + ") Attack";
      }
      
      if(ModDef >= 0)
      {
         str = str + "\n" + BaseDef + " (+" + ModDef + ") Defense";
      }
      else
      {
         str = str + "\n" + BaseDef + " (" + ModDef + ") Defense";
      }
      str = str + "\n" + Spd + " Speed";
      return str; //more detailed status display
   }
   public String getJob()
   {
      return job;
   }
   public void act(Character player, ArrayList<Character> team)
   {
      if(HP > 0)
      {
         balanceModifiers();
         System.out.println("\n\n\n\n\n\n\n");//blank space
         System.out.println(display() + "\n\n");
         for(Character enemy : team)
         {
            System.out.println(enemy.display() + "\n");
         }
         char choice = 'S';
         while(choice == 'S' || choice == 's')
         {
            System.out.println("\nA) "+ moves.getPrev().getValue().getName());
            System.out.println("\t\t"+ moves.getPrev().getValue().getDescription());
            
            System.out.println("\nB) "+ moves.getNext().getValue().getName());
            System.out.println("\t\t"+ moves.getNext().getValue().getDescription());
            
            System.out.println("\nWhat will "+ getName() +" do? \nEnter the letter associated with the option.");
            System.out.println();
            System.out.println("Alternatively, if you enter [S], you will see more in-depth stats. This will not end the turn.");
         
         int enemies = 0;
         int index = 0;
         int i;
               for(i = 0; i< team.size(); i++)
               {
                  if(team.get(i).getHP() > 0)
                  {
                     enemies++;
                     index = i;
                  }
               }
            choice = gameRunner.getResponse(new char[]{'A','B','S'});
            if(choice == 'A' || choice == 'a') //a goes back
            {
               moves = moves.getPrev();
               
               if(moves.getValue().getTargetType() && enemies > 1)
               { //ask for a target
                  System.out.println("Against which target would you like to use " + moves.getValue().getName() + " on?");
                  for(i = 1; i <= team.size(); i++)
                  {
                     if(team.get(i-1).getHP() > 0)
                     {
                        System.out.println(i +") " +team.get(i-1).displayMini());
                     }
                  }
                  moves.getValue().use(this,team.get(getIntResponse(1,team.size()) - 1),team);
               }
               else //either target self, target random enemy, or target enemy team
               {
                  moves.getValue().use(this,team.get(index),team);
                  //the second parameter does not matter.
               }
            }
            else if (choice == 'B' || choice == 'b') //b goes forwards
            {
               moves = moves.getNext();
               if(moves.getValue().getTargetType() && enemies > 1)
               {
                  System.out.println("Against which target would you like to use " + moves.getValue().getName() + " on?");
                  for(i = 1; i <= team.size(); i++)
                  {
                     if(team.get(i-1).getHP() > 0)
                     {
                        System.out.println(i +") " +team.get(i-1).displayMini());
                     }
                  }
                  moves.getValue().use(this,team.get(getIntResponse(1,team.size()) - 1),team);
               }
               else
               {
                  moves.getValue().use(this,team.get(index),team);
                  //the second parameter does not matter.
               }
            }
            else //status check
            {
               while(true)
               {
                  System.out.println("Check status of which combatant? Note that 0 refers to the player.");
                  System.out.println("0) " + displayMini());
           
                  for(i = 1; i <= team.size(); i++)
                  {
                     if(team.get(i-1).getHP() > 0)
                     {
                        System.out.println(i +") " +team.get(i-1).displayMini());
                     }
                  }
                  i = getIntResponse(0,team.size());
                  if(i == 0)
                     System.out.println(displayStatus());
                  else
                     System.out.println(team.get(i-1).displayStatus());
                  System.out.println("\nAre you done for now?");
                  if(gameRunner.yesOrNo())
                  {
                     break;
                  }
               }
            }
         }
      }
      else
      {
         System.out.println("player dead");
      }  
   }
   
   public void levelUp()
   {
      HP += 2;
      MaxHP += 2;
      BaseAtk += 1;
      BaseDef += 1;
      Spd += 1;
      
      level += 1;
   }
   
   //when loading a save file, sets the character's stats based on the provided value
   public void loadLevel(int x)
   {
      level = x;
   }
   public void loadAtk(int x)
   {
      BaseAtk = x;
   }
   public void loadHP(int x)
   {
      HP = MaxHP = x;
   }
   public void loadDef(int x)
   {
      BaseDef = x;
   }
   public void loadSpd(int x)
   {
      Spd = x;
   }
   
   public int getIntResponse(int minValue, int maxValue)
   {  //get a value somewhere between
      String response;
      int index;
      Scanner input = new Scanner(System.in);
      while(true)
      {
         response = input.nextLine();
         try
         {
            index = Integer.parseInt(response);
            if(index >= minValue && index <= maxValue)
            {
               return index;
            }
         }
         catch(Exception n)
         {}
         
         System.out.println("please enter a value from " + minValue + " to " + maxValue + ".");
      }
   }
}

abstract class Enemy extends Character
{
   //moveNode
   protected ArrayList<Move> moves;
   //private MoveNode action
   
   public void changeName(String n)
   {
      name = n;
   }
   
   public void act(Character player, ArrayList<Character> team)
   {
      if(HP > 0)
      {
         //System.out.println("A)"+moveNode.getPrev().getName());
         //System.out.println("B)"+moveNode.getNext().getNext());
         balanceModifiers();
         int option = determineAction(this, player, team);
         moves.get(option).use(this,player,team);
      }
      else
      {
         System.out.println("enemy dead");
      }  
   }
   
   public abstract int determineAction(Character user, Character target, ArrayList<Character> team);
   
   public void levelUp()
   {
      HP += 2;
      MaxHP += 2;
      BaseAtk += 1;
      BaseDef += 1;
      Spd += 1;
      
      
   }
   
   //when loading a save file, sets the character's stats based on the provided value

}