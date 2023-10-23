import java.util.ArrayList;
import java.lang.Math;

public class Thief extends Enemy
{
   public Thief(int l)
   {
      HP = MaxHP = 20 + (l-1) * 2;
      BaseAtk = 13 + (l-1) + (l-1) / 2;
      BaseDef = 8 + (l-1) ;
      Spd = 20 + (l-1) + (l-1) / 2;
      level = l;
      name = "Thief";
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new Status("Steal Defense", 0, -3, true, 4)); //steal Defense
      moves.add(new Status("Steal Strength", -3, 0, true, 3)); //steal Strength
      moves.add(new Attack("Steal Health", 5, 3)); //steal HP
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //30% chance to use Steal Defenses if target's total defense > 0
      if(Math.random() <= 0.3 && target.getDef() > 0)
      {
         return 0;
      }
      //30% chance to use Steal Strength if target's total atk > 0
      else if(Math.random() <= 0.3 && target.getAtk() > 0)
      {
         return 1;
      }
      //use Steal HP as default
      else
      {
         return 2;
      }
   }
}  