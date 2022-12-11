import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

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
        var maxScore = 1;
        
        for (int rowIndex = 1; rowIndex < matx.length-1; rowIndex+=1) {
            for (int colIndex = 1; colIndex < matx[0].length-1; colIndex+=1) {
                maxScore = Math.max(calculateScore(matx, rowIndex, colIndex), maxScore);
            }
        }
        System.out.println(maxScore);
    }

    private static int calculateScore(int[][] matx, int rowIndex, int colIndex) {
        var height = matx[rowIndex][colIndex];
        // go left
        int currColIndex = colIndex, currRowIndex = rowIndex;
        int left = 0, right = 0, up = 0, down = 0;
        while (currColIndex-1 >= 0) {
            left +=1;
            if (matx[currRowIndex][currColIndex-1] >= height) {
                break;
            }
            currColIndex-=1;
        }
        // go right
        currColIndex = colIndex;
        while (currColIndex+1 < matx[0].length ) {
            right += 1;
            if (matx[currRowIndex][currColIndex+1] >= height) {
                break;
            }
            currColIndex+=1;
        }
        // go up
        currRowIndex = rowIndex;
        currColIndex = colIndex;
        while (currRowIndex-1 >= 0) {
            up += 1;
            if (matx[currRowIndex-1][currColIndex] >= height) {
                break;
            }
            currRowIndex-=1;
        }
        // go down
        currRowIndex = rowIndex;
        while (currRowIndex+1 < matx.length ) {
            down += 1;
            if (matx[currRowIndex+1][currColIndex] >= height) {
                break;
            }
            currRowIndex+=1;
        }
        return up * down * left * right;
    }

}