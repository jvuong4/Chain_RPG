import java.util.ArrayList;

public class Desperation extends Move
{
   private int effect;
   
   public Desperation()
   {
      isSingleTarget = true;
      moveName = "Desperation"; //desperation
      description = "A knight's attack. Damage is based on the user's defense. However, the user loses 20 defense.";
   }
   
   public int getPower() //probably deleting this
   {
      return 0;
   }

   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      int damage = user.getAtk() + user.getDef() - target.getDef();
      if(damage <= 0)
      {
         damage = 1;
      }
      target.alterHP(damage * -1);
      user.addDef(-20);
   }
}