import java.util.ArrayList;



public class Attack extends Move
{
   private int power;
   private int effect;
      
   public Attack(String n, int p, int e)
   {
      isSingleTarget = true;
      moveName = n;
      power = p;
      effect = e;
      switch(effect)
      {
         case 0: //Sword Slash, Slime Bomb, Smash
            description = "A basic attack with " + power + " base damage.";
            break;
         case 1: //Shield Bash
            description = "0 base damage, but increases user's defense by 5.";
            break;
         case 2: //Staff Jab, Curse of Terror
            description = "An attack with " + power + " power. Decreases target's attack by 5.";
            break;
         case 3: //Drain, Lifesteal, Steal Health
            description = "A " + power + " power attack that heals the user for damage dealt.";
            break;
         case 4: //Sword Flurry
            description = "A multiple slash move adding to " + power + " damage that decreases users def by 5.";
            break;
         case 5: //Bow Shot, Corrosion
            description = "An attack with " + power + " base damage, lowering enemy def by 5.";
            break;
         case 6: //Trickshot
            description = "A shot with " + power + " base damage and 50% accuracy. More likely to hit the faster the user is than the target.";
            break;
      }
   }
   
   public Attack(String n, int p)
   {
      isSingleTarget = true;
      moveName = n;
      power = p;
      description = "A basic attack with " + power + " base damage.";
      effect = 0;
}
   
   public int getPower()
   {
      return power;
   }
   
   public void use(Character user, Character target, ArrayList<Character> team)
   {
      
      System.out.println(user.getName() + " used " + getName() + "!");
      int damage = user.getAtk() + power - target.getDef();
      if(damage <= 0)
      {
         damage = 1;
      }
      if(effect == 6 && (Math.random() < 0.5 - user.getSpd() * 0.01 + target.getSpd() * 0.01 ))
      {
         System.out.println(user.getName() + " missed!");
      }
      else
      {
         target.alterHP(damage * -1);
      }
      switch(effect)
      {
         case 1: //Shield Bash
            user.addDef(5);
            break;
         case 2: //Staff Jab, Curse of Terror
            target.addAtk(-5);
            break;
         case 3: //Drain, Lifeteal, Steal Health
            user.alterHP(damage);
            break;
         case 4: //Sword Flurry
            target.alterHP(damage * -1);
            user.addDef(-5);
            break;
         case 5: //Bow Shot, Corrosion
            target.addDef(-5);
            break;
         
      }
   }
}
