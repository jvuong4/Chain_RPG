import java.util.ArrayList;

public class HPCostAttack extends Move
{
   private int power;
   private int effect;
   private int HPCost;
   
   public HPCostAttack(String n, int p, int e)
   {
      isSingleTarget = true;
      moveName = n;
      power = p;
      effect = e;
      switch(effect)
      {
         case 0: //Scorch
            description = "10 HP Cost. Fire Magic with " + power + " base damage.";
            HPCost = 10;
            break;
         case 1: //Nefarious Hex
            description = "5 HP Cost. Evil Magic with " + power + " base damage.";
            HPCost = 5;
            break;
         case 2: //Dark Magic
            description = "15 HP Cost. Evil Magic with damage based on the target's attack stat.";
            HPCost = 15;
            break;
         case 3:
            description = "At most 50 HP Cost, reduces user's Attack and Defense by 30. 0 Base Power. HP cannot go below 1.";
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
      if(user.getHP() > HPCost)
      {
         if(effect == 2)
         {
            power = target.getAtk() / 2;
         }
         System.out.println(user.getName() + " used " + getName() + "!");
         int damage = user.getAtk() + power - target.getDef();
         if(damage <= 0)
         {
            damage = 1;
         }
         target.alterHP(damage * -1);
         user.alterHP(HPCost * -1);
         
         if(effect == 3)
         {
            if(user.getHP() > 50)
            {
               user.alterHP(-50);
            }
            else
            {
               user.alterHP(user.getHP() * -1 + 1);
            }
            user.addAtk(-30);
            user.addDef(-30);
         }
      }
      else
      {
         System.out.println(user.getName() + " failed to use " + getName() + "! Insufficient health!");
      }
   }
}