import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        var sc = new Scanner(new File("../../input"));
        
        var priority = 0;
        
        while (sc.hasNextLine()) {
            var line1 = sc.nextLine();
            var line2 = sc.nextLine();
            var line3 = sc.nextLine();
            priority += calculatePriority(line1, line2, line3);
        }

        System.out.println(priority);
    }
    private static int calculatePriority(String line1, String line2, String line3) {
        // find common char in all 3 strings
        var commonChar = findCommon(line1, findCommon(line2, line3)).charAt(0);
        if (Character.isLowerCase(commonChar)) {
            return commonChar - 'a' + 1;
        }
        return commonChar - 'A' + 27;
        
    }
    private static String findCommon(String a, String b) {
        var set = new HashSet<Character>();
        var sb = new StringBuilder();
        for (int index = 0; index < a.length(); index+=1) {
            set.add(a.charAt(index));
        }
        for (int index = 0; index < b.length(); index+=1) {
            var ch = b.charAt(index);
            if (set.contains(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}