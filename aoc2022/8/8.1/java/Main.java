import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static enum dir{
        up,
        down,
        left,
        right,
    }

    record Pair(int rowIndex, int colIndex, int val, dir path) {
    }

    public static void main(String[] args) throws IOException {
        var input = Files.readString(Path.of("../../input")).split("\\R");
        var matx = new int[input.length][input[0].length()];

        for (int rowIndex = 0; rowIndex < input.length; rowIndex+=1) {
            for (int colIndex = 0; colIndex < input[rowIndex].length(); colIndex+=1) {
                var ch = input[rowIndex].charAt(colIndex);
                matx[rowIndex][colIndex] = ch - '0';
            }
        }
    
        // count all peripheral trees
        var count = (matx.length * 2) + (2 * (matx[0].length-2));
        
        for (int rowIndex = 1; rowIndex < matx.length-1; rowIndex+=1) {
            for (int colIndex = 1; colIndex < matx[0].length-1; colIndex+=1) {
                if (isVisible(matx, rowIndex, colIndex)) {
                    count +=1;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isVisible(int[][] matx, int rowIndex, int colIndex) {
        var height = matx[rowIndex][colIndex];
        // go left
        int currColIndex = colIndex-1, currRowIndex = rowIndex;
        while (true) {
            if (matx[rowIndex][currColIndex] >= height) {
                break;
            }
            if (currColIndex == 0) {
                return true;
            }
            currColIndex-=1;
        }
        // go right
        currColIndex = colIndex+1;

        while (true) {
            if (matx[rowIndex][currColIndex] >= height) {
                break;
            }
            if (currColIndex == matx[0].length-1) {
                return true;
            }
            currColIndex+=1;
        }
        // go up
        currRowIndex = rowIndex-1;
        while (true) {
            if (matx[currRowIndex][colIndex] >= height) {
                break;
            }
            if (currRowIndex == 0) {
                return true;
            }
            currRowIndex-=1;
        }
        // go down
        currRowIndex = rowIndex+1;
        while (true) {
            if (matx[currRowIndex][colIndex] >= height) {
                break;
            }
            if (currRowIndex == matx.length-1) {
                return true;
            }
            currRowIndex+=1;
        }
        return false;
    }

}