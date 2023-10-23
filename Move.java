import java.util.ArrayList;

public abstract class Move
{
   protected String moveName;
   protected boolean isSingleTarget;
   protected String description;

   public String getName()
   {
      return moveName;
   }
   public String getDescription()
   {
      return description;
   }
   
   public boolean getTargetType()
   {
      return isSingleTarget;
   }
   
   //all Moves WILL use at lest one of these three parameters
   public abstract void use(Character user, Character target, ArrayList<Character> team);
   
      
   
   }

//circular doubly linked list
class MoveNode
{
   private MoveNode next;
   private MoveNode prev;
   
   private Move value;
   //constructors
   public MoveNode(MoveNode n, MoveNode p, Move v)
   {
      next = n;
      prev = p;
      value = v;
   }
   public MoveNode(Move v)
   {
   
      next = prev = null;
      value = v;
   }
   
   //get methods
   public MoveNode getNext()
   {
      return next;
   }
   public MoveNode getPrev()
   {
      return prev;
   }
   public Move getValue()
   {
      return value;
   }
   //set mthods
   public void setNext(MoveNode n)
   {
      next = n;
   }
   public void setPrev(MoveNode p)
   {
      prev = p;
   }
   public void setValue(Move v)
   {
      value = v;
   }
   
   //setup
   public void addNode(MoveNode m)
   {
      MoveNode temp = this;
      while(temp.getNext() != null)
      {
         temp = temp.getNext();
      }  
      temp.setNext(m);
      m.setPrev(temp);
   }
   public void makeRing()
   {
      MoveNode temp = this;
      while(temp.getNext() != null)
      {
         temp = temp.getNext();
      }
      temp.setNext(this);
      setPrev(temp);
   }  
}