import java.util.ArrayList;
import java.lang.Math;

public class Witch extends Enemy
{
   public Witch(int l)
   {
      HP = MaxHP = 20 + (l-1) * 2 + (l-1) / 2;
      BaseAtk = 10 + (l-1) * 2 + (l-1) / 2;
      BaseDef = 7 + (l-1) * 2;
      Spd = 7 + (l-1) * 2;
      level = l;
      name = "Witch";
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new GroupHeal("Healing Spell", 2, 3, 0)); //healing spell
      moves.add(new HPCostAttack("Nefarious Hex", 3, 1)); //nefarious hex
      moves.add(new Attack("Curse of Terror", -10, 2)); //debilitating curse
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //80% chance to use Healing Spell if each ally has less than or equal to 50% of its maxHP remaining 
      if(Math.random() <= 0.8)
      {
         for(Character ally : team)
         {
            if(ally.getHP() < ally.getMaxHP() * 0.5)
            {
               return 0;
            }
         }
      }
      //40% chance to use Nefarious Hex if Witch has more than 5 HP OR no allies are remaining
      if((Math.random() <= 0.4 || team.size() == 1) && HP > 5) 
      {
         return 1;
      }
      //use Debilitating Curse as default
      else
      {
         return 2;
      }
   }
}