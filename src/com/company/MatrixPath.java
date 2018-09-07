package com.company;

public class MatrixPath {

    private boolean hasPath(char[][] matrix, char[] str) {
        if (matrix == null || str == null || str.length == 0) {
            return false;
        }
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                visited[i][j] = false;
            }
        }
        int pathLength = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (hasPathCore(matrix, row, col, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPathCore(char[][] matrix, int row, int col, char[] str, int pathLength, boolean[][] visited) {
        if (pathLength == str.length) {
            return true;
        }
        boolean hasPath = false;
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length
                && matrix[row][col] == str[pathLength] && !visited[row][col]) {
            pathLength++;
            visited[row][col] = true;
            hasPath = hasPathCore(matrix, row - 1, col, str, pathLength, visited)
                    || hasPathCore(matrix, row + 1, col, str, pathLength, visited)
                    || hasPathCore(matrix, row, col - 1, str, pathLength, visited)
                    || hasPathCore(matrix, row, col + 1, str, pathLength, visited);
            if (!hasPath) {
                pathLength--;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }


}
