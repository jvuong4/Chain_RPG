public class Warrior extends Player
{
      public Warrior(String n)
      {
      HP = MaxHP = 18;
      BaseAtk = 15;
      BaseDef = 11;
      Spd = 15;
      
      name = n;
      job = "Warrior";
      
      ModAtk = ModDef = 0;
      
      setUpMoves();
}  
//public Warrior(File file)

private void setUpMoves()
{
   //1
   //1
   moves = new MoveNode(new Heal("Heal", 5, 2, 0)); 
   //2
   moves.addNode(new MoveNode(new Attack("Sword Slash", 10, 0)));
   //3
   moves.addNode(new MoveNode(new Attack("Sword Flurry", 10, 4)));
   //4
   moves.addNode(new MoveNode(new GroupAttack("Wide Slash", 2, 0)));
   //5
   moves.addNode(new MoveNode(new Status("Sharpen Sword", 10, 0, false, 0)));
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