import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    record Pair(int start, int end){}

    public static void main(String[] args) throws FileNotFoundException{
        var sc = new Scanner(new File("../../input"));
        var count = 0;
        while (sc.hasNextLine()) {
            var line = sc.nextLine().split(",");
            var firstPair = calculatePair(line[0]);
            var secondPair = calculatePair(line[1]);
            
            if (completeOverlap(firstPair, secondPair)) {
                count+=1;
            }
        }
        System.out.println(count);
    }
    private static boolean completeOverlap(Pair first, Pair second) {
        // check if first is overlapped by second
        // means second range is greater than first
        if (second.start <= first.start && second.end >= first.end) {
            // second completely overlap first
            return true;
        }

        // check if first completely overlap second
        if (first.start <= second.start && first.end >= second.end) {
            return true;
        }
        return false;
    }
    private static Pair calculatePair(String str) {
        var rangeVals = str.split("-");
        return new Pair(Integer.valueOf(rangeVals[0]), Integer.valueOf(rangeVals[1]));
    }
}