import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    record Pair(int start, int end){}

    public static void main(String[] args) throws FileNotFoundException{
        var sc = new Scanner(new File("a"));
        var count = 0;
        while (sc.hasNextLine()) {
            var line = sc.nextLine().split(",");
            var firstPair = calculatePair(line[0]);
            var secondPair = calculatePair(line[1]);
            
            if (partialOverlap(firstPair, secondPair)) {
                count+=1;
            }
        }
        System.out.println(count);
    }
    private static boolean partialOverlap(Pair first, Pair second) {
       if (first.end < second.start || second.end < first.start) {
            return false;
       }
       return true;
    
    }
    private static Pair calculatePair(String str) {
        var rangeVals = str.split("-");
        return new Pair(Integer.valueOf(rangeVals[0]), Integer.valueOf(rangeVals[1]));
    }
}