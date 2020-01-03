package EXAM;

import Serialization_Sockets.Book;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import EXAM.CardFaces.CardFace;

public class Main1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int sum = Stream.of(scanner.nextLine().split(" "))
                .takeWhile((X)->!X.equals("done"))
                .map((String X)->CardFace.valueOf(X))
                .distinct()
                .mapToInt(CardFace::getCardValue)
                .sum();
        System.out.println(sum);


    }
}
