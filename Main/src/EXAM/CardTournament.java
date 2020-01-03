package EXAM;

public class CardTournament {

    @CardPlayer(playername = "Tom", cardFace = CardFaces.CardFace.ACE)
    public static int player1 (){
        return 11;
    }

    @CardPlayer(playername = "Don", cardFace = CardFaces.CardFace.FIVE)
    public static int player2 (){
        return 5;
    }

    @CardPlayer(playername = "Tom", cardFace = CardFaces.CardFace.KING)
    public static int player3 (){
        return 10;
    }

    @CardPlayer(playername = "Don", cardFace = CardFaces.CardFace.SEVEN)
    public static int player4 (){
        return 7;
    }
}
