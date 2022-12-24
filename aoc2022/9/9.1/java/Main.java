import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;


public class Main {

    record RPoint(int x, int y) {
    }

    public static void main(String[] args) throws FileNotFoundException {
        var sc = new Scanner(new File("../../input"));
        var visited = new HashSet<RPoint>();
        visited.add(new RPoint(0, 0));
        var headLocation = new Point(0, 0);
        var tailLocation = new Point(0, 0);
        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            var input = line.split(" ");
            var dir = input[0].charAt(0);
            var distance = Integer.valueOf(input[1]);
            switch (dir) {
                case 'U' -> headLocation.y += distance;
                case 'D' -> headLocation.y -= distance;
                case 'L' -> headLocation.x -= distance;
                case 'R' -> headLocation.x += distance;
            }
            moveTail(headLocation, tailLocation, visited);
        }
        System.out.println(visited.size());
    }

    static void moveTail(Point headLocation, Point tailLocation, HashSet<RPoint> visited) {
        // head is already
        while (!Point.IsAdjacent(headLocation, tailLocation)) {
            // keep moving tail
            // they are not adjacent
            // take the shortest path

            if (tailLocation.x < headLocation.x) {
                tailLocation.x += 1;
            } else if (tailLocation.x > headLocation.x) {
                tailLocation.x -= 1;
            }

            if (tailLocation.y < headLocation.y) {
                tailLocation.y += 1;
            } else if (tailLocation.y > headLocation.y) {
                tailLocation.y -= 1;
            }
            // System.out.printf("x: %d , y: %d\n", tailLocation.x, tailLocation.y);
            visited.add(new RPoint(tailLocation.x, tailLocation.y));
        }
    }
}


class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean IsAdjacent(Point p1, Point p2) {
        var xDistance = Math.abs(p1.x - p2.x);
        var yDistance = Math.abs(p1.y - p2.y);

        if (xDistance > 1 || yDistance > 1) {
            return false;
        }
        return true;
    }
}