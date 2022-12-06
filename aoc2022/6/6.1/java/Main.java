import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        var sc = new Scanner(new File("../../input"));
        var input = sc.nextLine();

        var windowMap = new HashMap<Character, Integer>();
        var left = 0;
        var right = 0;
        while (right < 3) {
            var ch = input.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
            right+=1;
        }
        
        while (right < input.length()) {
            var ch = input.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
            if (windowMap.size() == 4) {
                break;
            }
            right+=1;
            windowMap.put(input.charAt(left), windowMap.get(input.charAt(left)) - 1);
            windowMap.remove(input.charAt(left), 0);
            left+=1;
        }
        System.out.println(right+1);
    }
}

