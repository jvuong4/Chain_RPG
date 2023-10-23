public class Magician extends Player
{
   public Magician(String n)
   {
      HP = MaxHP = 26;
      BaseAtk = 18;
      BaseDef = 5;
      Spd = 10;
      
      name = n;
      job = "Magician";
      
      ModAtk = ModDef = 0;
      
      setUpMoves();
   } 
//public Magician(File file)

   private void setUpMoves()
   {
   //1
   moves = new MoveNode(new Heal("Heal", 10, 1, 0)); 
   //2
   moves.addNode(new MoveNode(new HPCostAttack("Scorch", 20, 0)));
   //3
   moves.addNode(new MoveNode(new HPCostGroupAttack("Explosion", 0, 1))); 
   //4
   moves.addNode(new MoveNode(new Attack("Drain", 5, 3)));
   //5
   moves.addNode(new MoveNode(new HPCostGroupAttack("Thunder", 10, 0))); 
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