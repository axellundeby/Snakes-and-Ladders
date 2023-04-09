package no.uib.inf101.sem2.model;


public class PatternedPlayerFactory implements PlayerFactory {
    private final String str;
    private int index;

   public PatternedPlayerFactory(String str){
       this.str=str;
       this.index = 0;
   }

   @Override
   public Player getNext() {
    Player tetro = Player.newPlayer(str.charAt(index));
       index = (index + 1) % str.length();
       return tetro;
   }
}
