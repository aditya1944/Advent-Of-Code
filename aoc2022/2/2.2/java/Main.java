import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;

public class Main{
    private static int calculatePoints(char opponentInput, char ownInput) {
        var winMap = Map.of('A', 2, 'B', 3, 'C', 1);
        var drawMap = Map.of('A', 1, 'B', 2, 'C', 3);
        var loseMap = Map.of('A', 3, 'B', 1, 'C', 2);
        if (ownInput == 'X') {
            // we need to lose
            return loseMap.get(opponentInput);
        } else if (ownInput == 'Y') {
            // we need to draw
            return 3 + drawMap.get(opponentInput);
        } else {
            // we need to win
            return 6 + winMap.get(opponentInput);
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        var totalPoints = 0;
        var scanner = new Scanner(new File("../../input"));

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var opponentInput = line.charAt(0);
            var ownInput = line.charAt(2);
            totalPoints += calculatePoints(opponentInput, ownInput);
        }
        System.out.println(totalPoints);
    }
}