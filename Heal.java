import java.util.ArrayList;

public class Heal extends Move
{
   private int power;
   private int mult;
   private int effect;
   
   public Heal(String n, int p, int m, int e)
   {
      isSingleTarget = false;
      moveName = n;
      power = p;
      mult = m;
      effect = e;
       switch(effect)
      {
      case 0: //Heal
      description = "Self-recovering move based on attack. Has " + power + " base power.";
      break;
      case 1: //Rest
      description = "The user takes a rest. Boosts attack and defense by 5. Heals with " + power + " base power.";
      break;
      case 2: //Reconstruct
         description = "The user rebuilds its body, sacrificing 15 defense to heal itself. ";
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
      int rec = user.getAtk() / mult + power;
      if(rec <= 0)
      {
         rec = 1;
      }
      
      
      if(effect == 2)
      {
         rec += user.getDef() / 2;
         user.addDef(-1 * (user.getBaseDef() / 2 + 20));
      }
      
      user.alterHP(rec);
      if(effect == 1)
      {
         user.addAtk(5);
         user.addDef(5);
      }
      
   }
}