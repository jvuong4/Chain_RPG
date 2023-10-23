public class Healer extends Player
{
   public Healer(String n)
   {
   
      HP = MaxHP = 30;
      BaseAtk = 12;
      BaseDef = 7;
      Spd = 12;
      
      name = n;
      job = "Healer";
      
      ModAtk = ModDef = 0;
      
      setUpMoves();
   }  
//public Healer(File file)

   private void setUpMoves()
   {
    //1
   moves = new MoveNode(new Heal("Heal", 15, 1, 0)); 
   //2
   moves.addNode(new MoveNode(new Attack("Staff Jab", 8, 2)));
   //3
   moves.addNode(new MoveNode(new GroupAttack("Leech Life", 0, 1))); 
   //4
   moves.addNode(new MoveNode(new GroupStatus("Banish", -5, -5, 3))); 
   //5
   moves.addNode(new MoveNode(new Heal("Rest", -5, 3, 1))); 
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