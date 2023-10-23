import java.util.ArrayList;
import java.lang.Math;

public class Goblin extends Enemy
{
   public Goblin(int l)
   {
      HP = MaxHP = 18 + (l-1) * 2;
      BaseAtk = 10 + (l-1) + (l-1)/2;
      BaseDef = 12 + (l-1) + (l-1)/2;
      Spd = 7 + (l-1);
      level = l;
      name = "Goblin";
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new GroupStatus("War Cry", 5, 5, 4)); //War Cry
      moves.add(new Attack("Sword Slash", 10)); //Sword Slash
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //50% chance to use War Cry if HP is more than 1 HP, AND (either atk or def modifiers is less than 2)
      if(Math.random() > 0.5 && HP > 1 && (user.getModAtk() < 2 || user.getModDef() < 2))
      {
         return 0;
      }
      //use Sword Slash as default
      else
      {
         return 1;
      }
   }
}