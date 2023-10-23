import java.util.ArrayList;

public class Multishot extends Move
{
   private int power;
   private int numHits;

   public Multishot(String n, int p, int num)
   {
      isSingleTarget = false;
      moveName = n;
      power = p;
      numHits = num;
      description = "Attack "+ num +" random enemies with " + power + " base damage and a 0.5x multiplier. Lowers the user's defense by 5."; 
   }
   
   public int getPower()
   {
      return power;
   }

   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      ArrayList<Character> targetables = new ArrayList<Character>();
      for(Character enemy : team)
      {
         if(enemy.getHP() > 0)
            targetables.add(enemy);
      }
      int index = 0;
      for(int i = 0; i < numHits; i++)
      {
         //calculates a pseudorandom enemy.
         index = (int)(Math.random() * targetables.size());
         int damage = (int)(((user.getAtk() + power) - targetables.get(index).getDef()) * 0.5) ;
         if(damage < 1)
         {
            damage = 1;
         }
         targetables.get(index).alterHP(damage * -1);
         if(targetables.get(index).getHP() <= 0)
         {
            targetables.remove(index);
            if(targetables.size() == 0)
               break;
         }
      }
      user.addDef(-5);
   }
}