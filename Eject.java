import java.util.ArrayList;

public class Eject extends Move
{  
   public Eject()
   {
      isSingleTarget = false;
      moveName = "Eject"; //desperation
      description = "Launch the target off and away. Damage increased by 10% of the user's max hp. Lowers the user's defense by 10.";
   }
   
   public int getPower() //probably deleting this
   {
      return 0;
   }

   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      int damage = user.getAtk() + user.getMaxHP() / 10 - target.getDef();
      if(damage <= 0)
      {
         damage = 1;
      }
      target.alterHP(damage * -1);
      user.addDef(-10);
   }
}