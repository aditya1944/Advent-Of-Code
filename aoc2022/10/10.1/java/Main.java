import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{

    static int sumSignalStrength = 0;

    public static void main(String[] args) throws FileNotFoundException{
        var scanner = new Scanner(new File("../../input"));
        var cycleCount = 0;
        var registerValue = 1;
        while (scanner.hasNextLine()) {
            var input = scanner.nextLine();
            var splitInput = input.split(" ");
            if ("addx".equals(splitInput[0])) {
                cycleCount+=1;
                sumSignalStrength(cycleCount, registerValue);
                cycleCount+=1;
                sumSignalStrength(cycleCount, registerValue);
                registerValue += Integer.valueOf(splitInput[1]);
            } else {
                // must be noop
                cycleCount+=1;
                sumSignalStrength(cycleCount, registerValue);
            }
        }
        System.out.println(sumSignalStrength);
    }
    private static void sumSignalStrength(int cycleCount, int registerValue) {
        if (cycleCount == 20 || cycleCount == 60 || cycleCount == 100 || cycleCount == 140 || cycleCount == 180 || cycleCount == 220) {
            sumSignalStrength += (cycleCount * registerValue);
            //System.out.printf("cycleCount: %d , registerValue: %d , sumSignalStrength: %d\n", cycleCount,  registerValue, sumSignalStrength);
        }
    }
}