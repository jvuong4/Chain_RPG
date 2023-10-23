import java.util.ArrayList;

public class FlameBreath extends Move
{
   
   public FlameBreath()
   {
      isSingleTarget = false;
      moveName = "Flame Breath"; //desperation
      description = "A dragon's fiery breath with 0 power. Ignores half of the target's defense. Lowers the user's attack by 10.";
   }
   
   public int getPower() //probably deleting this
   {
      return 0;
   }

   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      int damage = user.getAtk() - target.getDef() / 2;
      if(damage <= 0)
      {
         damage = 1;
      }
      target.alterHP(damage * -1);
      user.addAtk(-10);
   }
}