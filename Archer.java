public class Archer extends Player
{
   public Archer(String n)
   {
      HP = MaxHP = 16;
      BaseAtk = 15;
      BaseDef = 7;
      Spd = 17;
      
      name = n;
      job = "Archer";
      
      ModAtk = ModDef = 0;
   
      setUpMoves();
   }  
//public Archer(File file)

   private void setUpMoves()
   {
    //1
   moves = new MoveNode(new Heal("Heal", 5, 2, 0)); 
   //2
   moves.addNode(new MoveNode(new Attack("Bow Shot", 8, 5))); 
   //3
   moves.addNode(new MoveNode(new Multishot("Multishot", 0, 5))); 
   //4
   moves.addNode(new MoveNode(new GroupStatus("Weakness Scan", 0, -10, 0)));
   //5
   moves.addNode(new MoveNode(new Attack("Trickshot", 25, 6))); 
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