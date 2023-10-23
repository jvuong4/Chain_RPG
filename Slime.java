import java.util.ArrayList;
import java.lang.Math;

public class Slime extends Enemy
{
   public Slime(int l)
   {
      HP = MaxHP = 15 + (l-1) * 3;
      BaseAtk = 10 + (l-1) ;
      BaseDef = 2 + (l-1);
      Spd = 8 + (l-1);
      level = l;
      name = "Slime";
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new Regenerate()); //regenerate
      moves.add(new Attack("Corrosion", 3 , 5)); //corrosion
      moves.add(new Attack("Slime Bomb", 7)); //slimebomb
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //use Regenerate if Slime has less than or equal to 70% of its maxHP remaining, more likely to occur th lower its hp is.
      if(HP <= (MaxHP*Math.random() * 0.7) && MaxHP > (user.getMaxHP() / 4 + 5))
      {
         return 0;
      }
      //80% chance to use Corrosion if target has more than 0 total def
      else if(Math.random() <= 0.8 && (target.getBaseDef() + target.getModDef() > 0)) 
      {
         return 1;
      }
      //use Slimebomb as default
      else
      {
         return 2;
      }
   }
}