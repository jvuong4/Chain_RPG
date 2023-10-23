import java.util.ArrayList;
import java.lang.Math;

public class DarkSorcerer extends Enemy
{
   public DarkSorcerer(int l)
   {
      HP = MaxHP = 70 + (l - 1) * 2 + (l - 1) / 2;
      BaseAtk = 10 + (l - 1) + (l - 1) / 2;
      BaseDef = 10 + (l - 1);
      Spd = 10 + (l - 1);
      
      level = l;
      name = "Dark Sorcerer";
      
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new HPCostAttack("Dark Magic", 0, 2)); //dark magic
      moves.add(new Attack("Lifesteal", 10, 3)); //lifesteal has same effect id as drain, just a different name and power
      moves.add(new Status("Invoke Wrath", 10, -15, true, 0)); //invoke wrath (enemy +10 atk and -15 def)
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //has a 10% chance to use, if hp < 50% (low activation chance for balance purposes)
      if(Math.random() < 0.1 && HP > MaxHP / 2.0)
      {
         return 1;
      }
      //has a 90% chance to use, if hp > 15, and hp > 35% 
      else if(Math.random() < 0.9 && HP > 15 && HP > MaxHP * 0.35)
      {
         return 0;
      }
      //has a 90% chance to use, if target's defMod > -3
      else if(Math.random() < 0.9 && target.getModDef() > -3)
      {
         return 2;
      }
      //if hp > 15
      else if (HP > 15)
      {
         return 0;
      }  
      //if none of the criteria are met, use Lifesteal
      return 1;
   }
   
}