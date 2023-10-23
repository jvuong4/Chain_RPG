import java.util.ArrayList;
import java.lang.Math;

public class StoneGolem extends Enemy
{
   public StoneGolem(int l)
   {
      HP = MaxHP = 5 + (l-1);
      BaseAtk = 13 + (l-1) ;
      BaseDef = 30 + (l-1) * 2 + (l-1) / 2;
      Spd = 1 + (l-1);
      level = l;
      name = "Stone Golem";
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new Heal("Reconstruct",0, 1, 2)); //reconstruct
      moves.add(new Status("Barricade",0,5,false,0)); //barricade
      moves.add(new Attack("Smash", 0)); //smash
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //use Reconstruct if Stone Golem has less than or equal to 30% of its maxHP remaining AND has more than 15 defense total
      if(HP <= (MaxHP * 0.3) && (BaseDef + ModDef) >= 15)
      {
         return 0;
      }
      //75% chance to use Barricade if Stone Golem has less than 3 ModDef
      else if(Math.random() < 0.75 && ModDef < 3)
      {
         return 1;
      }
      //use Smash as default
      else
      {
         return 2;
      }
   }
}