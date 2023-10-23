public class Vagabond extends Player
{
      public Vagabond(String n)
      {
      HP = MaxHP = 22;
      BaseAtk = 14;
      BaseDef = 10;
      Spd = 12;
      
      name = n;
      job = "Vagabond";
      
      ModAtk = ModDef = 0;
      
      setUpMoves();
      }
 
//public Vagabond(File file)

private void setUpMoves()
{
   //1
   moves = new MoveNode(new Heal("Heal", 5, 2, 0)); //how does effect work....
   //2
   moves.addNode(new MoveNode(new Attack("Sword Attack", 10))); 
   //3
   moves.addNode(new MoveNode(new Heal("Rest", -5, 3, 1))); //what is the mult of rest
   //4
   moves.addNode(new MoveNode(new Attack("Trickshot", 25, 6))); // 50% to hit for 0 damage needs special case 
   //5
   moves.addNode(new MoveNode(new Status("Glare", -8, -8, true, 0))); // ask jonathan if single target... effects....
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