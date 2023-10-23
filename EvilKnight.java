import java.util.ArrayList;
import java.lang.Math;

public class EvilKnight extends Enemy
{
   public EvilKnight(int l)
   {
      HP = MaxHP = 100 + (l - 1) * 2 + (l - 1) / 3;
      BaseAtk = 10 + (l - 1) + (l - 1) / 3;
      BaseDef = 10 + (l - 1) + (l - 1) / 3;
      Spd = 12 + (l - 1);
      
      level = l;
      name = "Evil Knight";
      
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new Desperation());//desp
      moves.add(new Status("Shield Stance", 0, 10, false, 0)); //shield stance
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //if user's hp < 50%, automatically just use despearation
      if(HP < MaxHP / 2.0)
      {
         return 0;
      }
      //50% chance to use if user's def < 5
      else if(Math.random() < 0.5 && getModDef() < 5)
      {
         return 1;
      }
      return 0;
   }
   
}