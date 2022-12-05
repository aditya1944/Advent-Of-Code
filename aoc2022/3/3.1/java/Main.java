import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        var prioritySum = 0;

        var sc = new Scanner(new File("../../input"));
        
        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            prioritySum += calculatePriority(line);
        }

        System.out.println(prioritySum);
    }

    private static int calculatePriority(String str) {
        var set = new HashSet<Character>();
        for (int index = 0; index < str.length()/2; index+=1) {
            set.add(str.charAt(index));
        }

        for (int index = str.length()/2; index < str.length(); index+=1) {
            if (set.contains(str.charAt(index))) {
                // calculate priority
                if (Character.isLowerCase(str.charAt(index))) {
                    return str.charAt(index) - 'a' + 1;
                } else {
                    return str.charAt(index) - 'A' + 27;
                }
            }
        }
        return -1;
    } 
}