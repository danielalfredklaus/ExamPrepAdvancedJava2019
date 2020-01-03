package EXAM;

import Reflections_and_Annotations.AnAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class Main2B {
    public static void main(String[] args) throws Exception {
        Class tournamentClass = CardTournament.class;

        Method[] methods =
                tournamentClass.getMethods();
        HashMap<String, Integer> playerScores = new HashMap<>();
        for(Method method : methods){
            Annotation[] methodAnnotations = method.getDeclaredAnnotations();
            for(Annotation annotation : methodAnnotations){
                if(annotation instanceof CardPlayer) {
                    CardPlayer myAnnotation = (CardPlayer) annotation;
                    if ((Integer) method.invoke(new CardTournament(), null) == myAnnotation.cardFace().getCardValue()) {
                        if (playerScores.containsKey(myAnnotation.playername())) {
                            int score = playerScores.get(myAnnotation.playername());
                            score += myAnnotation.cardFace().getCardValue();
                            playerScores.put(myAnnotation.playername(), score);
                        } else {
                            playerScores.put(myAnnotation.playername(),
                                    myAnnotation.cardFace().getCardValue());
                        }
                    } else {
                        System.out.println("Player cheated");
                    }
                }
            }
        }
        System.out.println(playerScores);
        String winner = Collections.max(playerScores.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        System.out.println(winner);
    }

}
