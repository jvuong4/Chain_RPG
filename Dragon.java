import java.util.ArrayList;
import java.lang.Math;

public class Dragon extends Enemy
{
   public Dragon(int l)
   {
      HP = MaxHP = 40 + (l - 1) * 2 + (l - 1) / 3;
      BaseAtk =  10 + (l - 1) + (l - 1) / 3;
      BaseDef = 15 + (l - 1) + (l - 1) / 3;
      Spd = 1 + (l - 1);
      
      level = l;
      name = "Dragon";
      
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new FlameBreath());
      moves.add(new Status("Roar", 0, -5, true , 1)); //roar
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //has a 60% chance to use roar, if the user's ModAtk < 3 OR the player's ModDef > 0
      if((user.getModAtk() < 0 || target.getModDef() > 0) && Math.random() < 0.60)
      {
         return 1; //roar
      }
      return 0;
   }
   
}