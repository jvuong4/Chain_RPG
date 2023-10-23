import java.util.ArrayList;

public class HPCostGroupAttack extends Move
{
   private int power;
   private int effect;
   private int HPCost;
   
   public HPCostGroupAttack(String n, int p, int e)
   {
      isSingleTarget = false;
      moveName = n;
      power = p;
      effect = e;
      switch(effect)
      {
         case 0: //Thunder
            description = "10 HP Cost. Electric Magic with " + power + " base damage. Hits all enemies.";
            HPCost = 10;
            break;
         case 1: //Explosion
            description = "Deal damage equal to half of the user's health to all combatants.";
            HPCost = 0;
            break;
      }
   }
   
   public int getPower()
   {
      return power;
   }

   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      if(effect == 1)
      {
         HPCost = user.getHP() / 2;
      }
      if(user.getHP() > HPCost && HPCost > 0)
      {
         System.out.println(user.getName() + " used " + getName() + "!");
         for(Character enemy : team)
         {
            if(enemy.getHP() > 0)
            {
            
               int damage = user.getAtk() + power - enemy.getDef();
               if(effect == 1)
               {
                  damage = user.getHP() / 2;
               }  
               if(damage <= 0)
               {
                  damage = 1;
               }
               enemy.alterHP(damage * -1);
               
            }
         }
         user.alterHP(HPCost * -1);
      }
      else
      {
         System.out.println(user.getName() + " failed to use " + getName() + "! Insufficient health!");
      }
   }
}