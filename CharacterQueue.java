
public class CharacterQueue
{
   private CharacterQueue next;
   private Character value;
   
   public CharacterQueue(CharacterQueue n, Character v)
   {
      next = n;
      value = v;
   }
   
   public void add(CharacterQueue n)
   {
      CharacterQueue temp1 = this;
      CharacterQueue temp2 = temp1;
      while(temp1 != null && temp1.getCharacter().getSpd() >= n.getCharacter().getSpd())
      {
         temp2 = temp1;
         temp1 = temp1.getNext();
      }
      if(temp1 != temp2)
      { //normal case
      n.setNext(temp1);
      temp2.setNext(n);
      }
      else if(temp2.getCharacter().getSpd() >= n.getCharacter().getSpd())
      { //if the new character is slower than the first
        temp2.setNext(n);
      }
      else
      {
         //add a node behind this node
         temp1 = this;
         temp2 = n;
         while(temp1 != null)
         {
            temp2.setNext(new CharacterQueue(null, temp1.getCharacter()));
            temp2 = temp2.getNext();
            temp1 = temp1.getNext();
         }
         //replace this with n
         value = n.getCharacter();
         next = n.getNext();
      }
   }
   
   public CharacterQueue getNext()
   {
      return next;
   }
   public Character getCharacter()
   {
      return value;
   }
   public void setNext(CharacterQueue n)
   {
      next = n;
   }
   public void setValue(Character v) //this method probably has no purpose haha
   {
      value = v;
   }
   public void makeRing()
   {
      CharacterQueue temp1 = this;
      CharacterQueue temp2 = temp1;
      while(temp1 != null)
      {
         temp2 = temp1;
         //System.out.println("passing " + temp1.getCharacter().getName());
         temp1 = temp1.getNext();
      }
      temp2.setNext(this);
   }
}