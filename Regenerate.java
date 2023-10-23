import java.util.ArrayList;

public class Regenerate extends Move
{
   private int power;
   private String description;
   
   public Regenerate()
   {
      isSingleTarget = false;
      moveName = "Regenerate";
      power = 5;
      description = "The user loses a significant amount of their Max Health, then is fully healed.";
   }
   
   public int getPower()
   {
      return power;
   }

   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!\n" + user.getName() +" lost " + ((user.getMaxHP() / 4) + 5)+ " Max Health!");
      user.setMaxHP(user.getMaxHP() - (user.getMaxHP() / 4 + 5));
      user.alterHP(user.getMaxHP() - user.getHP());
   }
}