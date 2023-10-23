import java.util.ArrayList;
import java.lang.Math;

public class Imposter extends Enemy
{
   public Imposter(Character player)
   {
      HP = MaxHP = player.getMaxHP();
      BaseAtk = player.getBaseAtk();
      BaseDef = player.getBaseDef();
      Spd = player.getSpd() - 1;
      
      name = "Imposter";
      if(Math.random() < 0.2)
      {  //20% chance to have a different name. purely aesthetic, has no effect on gameplay
         name = "Crewmate";
      }
      level = player.getLevel();
      
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new Status("Sabotage", -4, -4, true, 0)); //sabotage
      moves.add(new Status("Vent", 0, 10, false, 2));   //vent
      moves.add(new Eject());  //eject
      moves.add(new Status("Arouse Suspicion", 10, -10, false, 0)); //arouse suspicion
   }
   
   public Imposter(Character player, double mult)
   {
      HP = MaxHP = (int)(player.getMaxHP() * mult);
      BaseAtk =(int) (player.getBaseAtk() * mult);
      BaseDef = (int)(player.getBaseDef() * mult);
      Spd = (int)(player.getSpd() * mult - 1);
      
      name = "Imposter";
      if(Math.random() < 0.2)
      {  //20% chance to have a different name. purely aesthetic, has no effect on gameplay
         name = "Crewmate";
      }
      level = (int)(player.getLevel() * mult);
      
      ModAtk = ModDef = 0;
      
      moves = new ArrayList<Move>();
      moves.add(new Status("Sabotage", -4, -4, true, 0)); //sabotage
      moves.add(new Status("Vent", 0, 10, false, 2));   //vent
      moves.add(new Eject());  //eject
      moves.add(new Status("Arouse Suspicion", 10, -10, false, 0)); //arouse suspicion
   }
   
   public int determineAction(Character user, Character target, ArrayList<Character> team)
   {
      //use Eject if the player has only 10% hp remaining Eject
      if(target.getMaxHP() / 10.0 >= target.getHP())
      {
         return 2; //eject
      }
      //has an 80% chance to use vent, if the imposter has at least 10 damage, and the imposter's ModDef < 5
       else if(Math.random() < 0.8 && MaxHP - HP >= 10 && getModDef() < 5)
      {
         return 1; //vent
      }
      //has an 80% chance to arouse suspicion, if the imposter has less than 5 atk and at least 2 moddef
      else if(Math.random() < 0.8 && getModAtk() < 5 && getModDef() > 2)
      {
         return 3; //arouse suspicion
      }
      //has an 80% chance to Sabotage if the enemy has at least 0 ModAtk and 0 ModDef
      else if(target.getModAtk() >= 0 && target.getModDef() >= 0)
      {
         return 0; //sabotage
      }
      //if none of the criterion are met, simply use Eject.
      return 2; //eject
   }
   
}