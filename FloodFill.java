class Solution {

    static int[][] sides = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int m;
    static int n;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        m = image.length;
        n = image[0].length;
        int orig = image[sr][sc];

        // Avoid processing if the original color is already the target color
        if (orig != color) {
            fill(image, orig, color, sr, sc);
        }
        return image;
    }

    private void fill(int[][] image, int orig, int color, int sr, int sc) {
        // Base case: If the current pixel doesn't match the original color, return
        if (sr < 0 || sr >= m || sc < 0 || sc >= n || image[sr][sc] != orig) {
            return;
        }

        // Change the color of the current pixel
        image[sr][sc] = color;

        // Explore all 4 directions
        for (int[] side : sides) {
            fill(image, orig, color, sr + side[0], sc + side[1]);
        }
    }
}
