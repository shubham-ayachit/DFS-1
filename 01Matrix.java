class Solution {

    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Initialize a distance matrix with a large value
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        // Perform DFS for each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0; // Distance for `0` cells is always 0
                } else {
                    distance[i][j] = dfs(mat, distance, i, j);
                }
            }
        }

        return distance;
    }

    private int dfs(int[][] mat, int[][] distance, int row, int col) {
        // Base cases
        if (row < 0 || row >= mat.length || col < 0 || col >= mat[0].length) {
            return Integer.MAX_VALUE; // Out of bounds
        }
        if (mat[row][col] == 0) {
            return 0; // Found a `0`
        }
        if (distance[row][col] != Integer.MAX_VALUE) {
            return distance[row][col]; // Already computed
        }

        // Temporarily mark the cell as visited to prevent cycles
        mat[row][col] = -1;

        // Explore all 4 directions
        int minDistance = Integer.MAX_VALUE;
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            minDistance = Math.min(minDistance, dfs(mat, distance, newRow, newCol));
        }

        // Restore the original value of the cell
        mat[row][col] = 1;

        // Update the distance matrix for the current cell
        distance[row][col] = minDistance == Integer.MAX_VALUE ? Integer.MAX_VALUE : minDistance + 1;

        return distance[row][col];
    }
}
