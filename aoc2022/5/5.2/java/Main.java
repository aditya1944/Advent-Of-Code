import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        var scanner = new Scanner(new File("../../input"));
        
        var map = new HashMap<Integer, ArrayDeque<Character>>();
        
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.length() !=0 && line.charAt(1) != '1' && line.charAt(0) != 'm') {
                fillStacks(map, line);
            } else if (line.length() !=0 && line.charAt(0) == 'm'){
                // these are commands
                var tokens = line.split(" ");
                var quantity = Integer.valueOf(tokens[1]);
                var srcStack = Integer.valueOf(tokens[3]);
                var destStack = Integer.valueOf(tokens[5]);
                // now move quantity from src stack to dest stack
                move(map, quantity, srcStack, destStack);
            }
        }

        var sb = new StringBuilder();
        for (var pair: map.entrySet()) {
            if (pair.getValue().size() > 0) {
                sb.append(pair.getValue().getFirst());
            }
        }
        System.out.println(sb);
    }

    public static void move(Map<Integer, ArrayDeque<Character>> map, int quantity, int src, int dest) {
        var temp = new ArrayDeque<Character>();
        var srcStack = map.get(src);
        while (quantity!=0) {
            temp.addFirst(srcStack.pollFirst());
            quantity-=1;
        }
        var destStack = map.get(dest);
        while (temp.size()!=0) {
            destStack.addFirst(temp.removeFirst());
        }
    }

    public static void fillStacks(Map<Integer, ArrayDeque<Character>> map, String line) {
        var count = 1;
        for (int index = 0; index < line.length(); index+=4) {
            var ch = line.charAt(index+1);
            if (Character.isLetter(ch)) {
                var stack = map.getOrDefault(count, new ArrayDeque<Character>());
                stack.addLast(ch);
                map.put(count, stack);
            }
            count +=1;
        }
    }

}