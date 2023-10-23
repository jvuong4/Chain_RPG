import java.util.ArrayList;

public class GroupAttack extends Move
{
   private int power;
   private int effect;
   
   public GroupAttack(String n, int p, int e)
   {
      isSingleTarget = false;
      moveName = n;
      power = p;
      effect = e;
      switch(effect)
      {
         case 0: //Wide Slash
            description = "A far reaching slash dealing " + power + " base damage. Hits all enemies.";
            break;
         case 1: //Leech Life
            description = "A " + power + " power attack that heals the user for half the damage dealt.";
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
      int damage;
      for(Character tar : team)
      {
         if(tar.getHP() > 0)  
         {
            damage = user.getAtk() + power - tar.getDef();
            if(damage <= 0)
            {
               damage = 1;
            }
            tar.alterHP(damage * -1);
            if(effect == 1)
            {
               user.alterHP(damage / 2 + damage % 2);
            }
         }
      }
              
   }
}
