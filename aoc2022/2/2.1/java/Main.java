import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{

    private static int calculatePoints(char opponent, char own) {
        var shapePoint = own - 'X' + 1;
        return switch (opponent) {
            case 'A' -> {
                var score = 0;
                if (own == 'X') {
                    //draw
                    score =  3;
                } else if (own == 'Y') {
                    // own won
                    score = 6;
                }
                yield score + shapePoint;
            }
            case 'B' -> {
                var score = 0;
                if (own == 'Y') {
                    // draw
                    score = 3;
                } else if (own == 'Z') {
                    // own won
                    score = 6;
                }
                yield score + shapePoint;
            }
            case 'C' -> {
                var score = 0;
                if (own == 'Z') {
                    // draw
                    score = 3;
                } else if (own == 'X') {
                    score = 6;
                }
                yield score + shapePoint;
            }
            default -> {
                throw new IllegalArgumentException();
            }
        };
    }

    public static void main(String[] args) throws FileNotFoundException {
        var totalPoints = 0;

        // read file
        var sc = new Scanner(new File("../../input"));
        while (sc.hasNextLine()){
            var input = sc.nextLine();
            var opponentInput = input.charAt(0);
            var ourInput = input.charAt(2);
            totalPoints += calculatePoints(opponentInput, ourInput);
        }

        System.out.println(totalPoints);
    }
}