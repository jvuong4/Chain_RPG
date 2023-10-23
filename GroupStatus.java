import java.util.ArrayList;

public class GroupStatus extends Move
{
   private int atkAlt;
   private int defAlt;
   private int effect;
   
   public GroupStatus(String n, int atk, int def, int e)
   {
      isSingleTarget = false;
      moveName = n;
      atkAlt=atk;
      defAlt=def;
      effect = e;
      switch(effect)
      {
         case 0: //Weakness Scan
            description = "A scanning manuever that lowers every enemy scanned defense by " + def + " points.";
            break;
         case 1: //Fortify
            description = "A positioning move that increases all ally defense by " + def + " points.";
            break;
         case 2: //Call to Arms
            description = "A chant that increases all ally attack by " + atk + " points.";
            break;
         case 3: //Banish
            description = "A spell that lowers all enemy attack and defense by " + atk + " points.";
            break;
         case 4: //War Cry
            description = "A loud encouraging scream that increases party atk and def by " + atk + "points at a cost of 1 health.";
            break;
      }
   }
   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      for(Character tar : team)
      {
         if(tar.getHP() > 0)  
         {
            tar.addDef(defAlt);
            tar.addAtk(atkAlt);
         }
      }
      if(effect == 4)
      {
         user.alterHP(-1);
      }
   
   }
   
}
