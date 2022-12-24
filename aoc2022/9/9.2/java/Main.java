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
        var location1 = new Point(0, 0);
        var location2 = new Point(0, 0);
        var location3 = new Point(0, 0);
        var location4 = new Point(0, 0);
        var location5 = new Point(0, 0);
        var location6 = new Point(0, 0);
        var location7 = new Point(0, 0);
        var location8 = new Point(0, 0);
        var tailLocation = new Point(0, 0);
        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            var input = line.split(" ");
            var dir = input[0].charAt(0);
            var distance = Integer.valueOf(input[1]);
            switch (dir) {
                case 'U' -> {
                    var step = 0;
                    while (step != distance) {
                        headLocation.y += 1;
                        movePoint(headLocation, location1);
                        movePoint(location1, location2);
                        movePoint(location2, location3);
                        movePoint(location3, location4);
                        movePoint(location4, location5);
                        movePoint(location5, location6);
                        movePoint(location6, location7);
                        movePoint(location7, location8);
                        moveTail(location8, tailLocation, visited);
                        step += 1;
                    }
                }
                case 'D' -> {
                    var step = 0;
                    while (step != distance) {
                        headLocation.y -= 1;
                        movePoint(headLocation, location1);
                        movePoint(location1, location2);
                        movePoint(location2, location3);
                        movePoint(location3, location4);
                        movePoint(location4, location5);
                        movePoint(location5, location6);
                        movePoint(location6, location7);
                        movePoint(location7, location8);
                        moveTail(location8, tailLocation, visited);
                        step += 1;
                    }
                }
                case 'L' -> {
                    var step = 0;
                    while (step != distance) {
                        headLocation.x -= 1;
                        movePoint(headLocation, location1);
                        movePoint(location1, location2);
                        movePoint(location2, location3);
                        movePoint(location3, location4);
                        movePoint(location4, location5);
                        movePoint(location5, location6);
                        movePoint(location6, location7);
                        movePoint(location7, location8);
                        moveTail(location8, tailLocation, visited);
                        step += 1;
                    }
                }
                case 'R' -> {
                    var step = 0;
                    while (step != distance) {
                        headLocation.x += 1;
                        movePoint(headLocation, location1);
                        movePoint(location1, location2);
                        movePoint(location2, location3);
                        movePoint(location3, location4);
                        movePoint(location4, location5);
                        movePoint(location5, location6);
                        movePoint(location6, location7);
                        movePoint(location7, location8);
                        moveTail(location8, tailLocation, visited);
                        step += 1;
                    }
                }
            }
        }
        System.out.println(visited.size());
    }

    static void movePoint(Point headLocation, Point tailLocation) {
        // head is already
        while (!Point.IsAdjacent(headLocation, tailLocation)) {

            if (tailLocation.x != headLocation.x && tailLocation.y != headLocation.y) {
                // move diagonally
                moveDiagonal(headLocation, tailLocation);
            } else {
                // move in straight line
                if (tailLocation.x != headLocation.x) {
                    // thier y must be same
                    // x should be moved
                    if (tailLocation.x < headLocation.x) {
                        tailLocation.x += 1;
                    } else if (tailLocation.x > headLocation.x) {
                        tailLocation.x -= 1;
                    }
                } else {
                    // their x must be same
                    // y should be moved
                    if (tailLocation.y < headLocation.y) {
                        tailLocation.y += 1;
                    } else if (tailLocation.y > headLocation.y) {
                        tailLocation.y -= 1;
                    }
                }
            }
            // System.out.printf("x: %d , y: %d\n", tailLocation.x, tailLocation.y);
        }
    }

    private static void moveDiagonal(Point headLocation, Point tailLocation) {
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
    }

    static void moveTail(Point headLocation, Point tailLocation, HashSet<RPoint> visited) {
        // head is already
        while (!Point.IsAdjacent(headLocation, tailLocation)) {
            // keep moving tail
            // they are not adjacent
            // take the shortest path

            if (tailLocation.x != headLocation.x && tailLocation.y != headLocation.y) {
                // move diagonally
                moveDiagonal(headLocation, tailLocation);
            } else {
                // move in straight line
                if (tailLocation.x != headLocation.x) {
                    // thier y must be same
                    // x should be moved
                    if (tailLocation.x < headLocation.x) {
                        tailLocation.x += 1;
                    } else if (tailLocation.x > headLocation.x) {
                        tailLocation.x -= 1;
                    }
                } else {
                    // their x must be same
                    // y should be moved
                    if (tailLocation.y < headLocation.y) {
                        tailLocation.y += 1;
                    } else if (tailLocation.y > headLocation.y) {
                        tailLocation.y -= 1;
                    }
                }
            }
            System.out.printf("x: %d , y: %d\n", tailLocation.x, tailLocation.y);
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