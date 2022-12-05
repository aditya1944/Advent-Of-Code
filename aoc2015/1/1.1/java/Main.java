import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        var sc = new Scanner(new File("../../input"));
        var input = sc.nextLine();
        var floor = 0;
        for(int index = 0; index < input.length(); index+=1) {
            if (input.charAt(index) == '(') {
                floor+=1;
            } else {
                floor-=1;
            }
        }
        System.out.println(floor);
    }
}