import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("../../input"));
        //var currIndex = 1;
        var currCalories = 0;
        //var maxIndex = -1;
        var maxFirstCalories = 0;
        var maxSecondCalories = 0;
        var maxThirdCalories = 0;
        while (sc.hasNextLine()) {
           var line = sc.nextLine();
           if (line.length() == 0) {
                if (currCalories >= maxFirstCalories) {
                    maxThirdCalories = maxSecondCalories;
                    maxSecondCalories = maxFirstCalories;
                    maxFirstCalories = currCalories;
                    //maxIndex = currIndex;
                } else if (currCalories < maxFirstCalories && currCalories >= maxSecondCalories) {
                    maxThirdCalories = maxSecondCalories;
                    maxSecondCalories = currCalories;
                } else if (currCalories < maxSecondCalories && currCalories >= maxThirdCalories){
                    maxThirdCalories = currCalories;
                }
                //currIndex+=1;
                currCalories = 0;
           } else {
                currCalories += Integer.valueOf(line);
           }
        }
        System.out.printf("top 3 elf has %d calories", maxFirstCalories + maxSecondCalories + maxThirdCalories);
    }
}