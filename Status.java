import java.util.ArrayList;

public class Status extends Move
{
   private int altAtk;
   private int altDef;
   private int effect;
   
   public Status(String n, int atk, int def, boolean single, int e)
   {
      //if false then use on self// if true then use on target (referring to isSingleTarget)
      isSingleTarget = single;
      moveName = n;
      altAtk=atk;
      altDef=def;
      effect = e;
      switch(effect)
      {
         case 0: //Default, normal
            if(isSingleTarget)
            {
               description = "A status move that ";
               if(altAtk > 0)
               {
                     description = description + "increases the target's attack by " + altAtk;
               }
               else if(altAtk < 0)
               {
                  description = description + "decreases the target's attack by " + altAtk * -1;
               }
               if(altAtk != 0 && altDef != 0)
               {
                  description = description + " and ";
               }  
               if(altDef > 0)
               {
                  description = description + "increases the target's defense by " + altDef;
               }
               else if(altDef < 0)
               {
                  description = description + "decreases the target's defense by " + altDef;
               }
               description = description + ".";
            }
            else
            {
               description = "A status move that ";
               if(altAtk > 0)
               {
                     description = description + "increases the user's attack by " + altAtk;
               }
               else if(altAtk < 0)
               {
                  description = description + "decreases the user's attack by " + altAtk * -1;
               }
               if(altAtk != 0 && altDef != 0)
               {
                  description = description + " and ";
               }  
               if(altDef > 0)
               {
                  description = description + "increases the user's defense by " + altDef;
               }
               else if(altDef < 0)
               {
                  description = description + "decreases the user's defense by " + altDef;
               }
               description = description + ".";
            }
            break;
         case 1: //Roar
            description = "Lowers the target's defense by 5 and increases the user's attack by 5.";
            break;
         case 2: //Vent
            description = "Increases the user's defense by 10 and recovers some health with 10 base power.";
            break;   
         case 3: //Steal Strength
            description = "Steals 3 of the target's attack";
            break;
         case 4: //Steal Defenses
            description = "Steals 3 of the target's defense";
            break; 
      //to be done
      }
   }
   
   public String getName()
   {
      return moveName;
   }
   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      System.out.println(user.getName() + " used " + getName() + "!");
      if(isSingleTarget)
      {
         target.addDef(altDef);
         target.addAtk(altAtk);
      }
      else
      {
         user.addDef(altDef);
         user.addAtk(altAtk);
      }
      switch(effect)
      {
         case 1: //Roar
            user.addAtk(5);
            break;
         case 2: //Vent
            user.alterHP(user.getAtk() / 3 + 10);
            break;   
         case 3: //Steal Strength
            user.addAtk(3);
            break;
         case 4: //Steal Defenses
            user.addDef(3);
            break; 
      }
   }
}