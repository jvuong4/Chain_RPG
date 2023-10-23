import java.util.ArrayList;
import java.lang.Math;

public class King extends Enemy
{
   public King(int l)
   {
      HP = MaxHP = 200 + (l - 1) * 3;
      BaseAtk = 20 + (l - 1);
      BaseDef = 15 + (l - 1);
      Spd = 5 + (l - 1);
      
      level = l;
      name = "Evil King";
      
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new GroupStatus("Fortify", 0, 10, 1)); //fortify
      moves.add(new GroupStatus("Call to Arms", 10, 0, 2)); //call to arms
      moves.add(new GroupHeal("Royal Feast", 0, 1, 1)); //royal feast
      moves.add(new HPCostAttack("Panic", 0, 3));
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //90% chance to Panic if theyr the only living unit.
      int allies = 0;
      for(Character ally: team)
      {
         if(ally.getHP() > 0)
         allies++;
      }
      if(allies == 1 && Math.random() < 0.9)
         return 3;
      //50% chance, if self have less than 5 modDef
      else if(Math.random() < 0.5 && getModDef() < 5)
      {
         return 0;
      }
      //80% chance, if self has less than -5 modAtk
      else if(Math.random() < 0.8 && getModAtk() < -5)
      {
         return 1;
      }
      //heal if an ally has less than 30% hp (but still alive)
      else
      {
         for(Character ally : team)
            if(ally.getHP() < ally.getMaxHP() * 0.3 && ally.getHP() > 0)
               return 2;
      }
      
      //by default, use fortify
      return 0;
   }
   
}