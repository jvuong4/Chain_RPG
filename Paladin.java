import java.lang.Math;

public class Paladin extends Player
{
   public Paladin(String n)
   {
      HP = MaxHP = 20;
      BaseAtk = 10;
      BaseDef = 15;
      Spd = 6;
      
      job = "Paladin";
      
      name = n;
      
      ModAtk = ModDef = 0;
      
      setUpMoves();
   }
   //public Paladin(File file)
   
   private void setUpMoves()
   {
      //1 Heal
      moves = new MoveNode(new Heal("Heal", 5, 2, 0)); //this will need to be changed from null to a Heal class
      //2
      moves.addNode(new MoveNode(new Attack("Sword Slash", 10)));
      //3 Desperation
      moves.addNode(new MoveNode(new Desperation()));
      //4 Shield Bash
      moves.addNode(new MoveNode(new Attack("Shield Bash", 0, 1)));
      //5 Shield Stance
      moves.addNode(new MoveNode(new Status("Shield Stance", 0, 10, false, 0)));
      //connect
      moves.makeRing();
      
      //randomize ring os that the previously selected move is any of the 5 possible moves
      //note that this is only done once.
      for(int i = 0; i < Math.random() * 5; i++)
      {
         moves = moves.getNext();
      }
   }
   
   
}