import java.util.ArrayList;

public class GroupHeal extends Move
{
   private int power;
   private int mult;
   private int effect;
   
   public GroupHeal(String n, int p, int m, int e)
   {
      isSingleTarget = false;
      moveName = n;
      power = p;
      mult = m;
      effect = e;
      switch(effect)
      {
         case 0: //Healing Spell
            description = "A sage-like ability that heals all allies with " + power + " base power.";
            break;
         case 1: //Royal Feast
            description = "All allies enjoy a meal, healing a lot. At a cost of the users attack.";
            break;
      }
   }
   
   public int getPower()
   {
      return power;
   }
   public String getName()
   {
      return moveName;
   }
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      int healing;
      if(effect == 0)
      {
         mult = 1;
         for(Character tar : team)
         {
            if(tar.getHP() > 0)  
            {
               mult++;
            }
         }
      }
      for(Character tar : team)
      {
         if(tar.getHP() > 0)  
         {
            healing = user.getAtk() / mult + power;
            if(healing <= 0)
            {
               healing = 1;
            }
            tar.alterHP(healing);
         }
      }
      if(effect == 1)
      {
         user.addAtk(-50);
      }
      else
      {
         power--;
      }
   }
}
