import java.util.LinkedList;

public class QueueProblems {
    /** Number of Islands problem:
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * Example 1:
     * Input:
     * 11110
     * 11010
     * 11000
     * 00000
     *
     * Output: 1
     *
     *
     * Example 2:
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * Output: 3
     * */
    public int numIslands(char[][] grid) {
        this.printGrid(grid);
        /**
         * pseudocode:
         * for each location
         *      if visited, skip
         *      if it is a land
         *          increment num of islands
         *          push into a queue
         *      if it is water
         *          mark as visited
         *          go process next location
         *
         *      while queue not empty
         *          get first land, mark as visited
         *          check right, check bottom
         *              if land, push to queue
         *              if water, mark visited
         */
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int height = grid.length;
        int width = grid[0].length;
        boolean[][] statusMap = new boolean[height][width];
        for (boolean[] rowStatus: statusMap) {
            for (boolean landStatus: rowStatus) {
                landStatus = false;
            }
        }
        LinkedList<int[]> island = new LinkedList<int[]>();
        int numOfIslands = 0;
        int numOfOps = 0;

        for (int h = 0; h < grid.length; h++) {
            for (int w = 0; w < grid[h].length; w++) {
                if (statusMap[h][w]) {
                    // System.out.println("SKIP");
                    continue;
                }
                numOfOps++;
                if (grid[h][w] == '1') {
                    numOfIslands++;
                    int[] coordinate = {h, w};
                    island.push(coordinate);
                }
                statusMap[h][w] = true;

                while(!island.isEmpty()) {
                    numOfOps++;
                    int[] coordinate = island.pop();
                    int coordH = coordinate[0], coordW = coordinate[1];
                    statusMap[coordH][coordW] = true;
                    if (coordH - 1 >= 0 && !statusMap[coordH - 1][coordW]) { //check top
                        if (grid[coordH - 1][coordW] == '1') {
                            int[] coord = {coordH - 1, coordW};
                            island.push(coord);
                        }
                        statusMap[coordH - 1][coordW] = true;
                    }
                    if (coordW - 1 >= 0 && !statusMap[coordH][coordW - 1]) { //check left
                        if (grid[coordH][coordW - 1] == '1') {
                            int[] coord = {coordH, coordW - 1};
                            island.push(coord);
                        }
                        statusMap[coordH][coordW - 1] = true;
                    }
                    if (coordH + 1 < height && !statusMap[coordH + 1][coordW]) { //check bottom
                        if (grid[coordH + 1][coordW] == '1') {
                            int[] coord = {coordH + 1, coordW};
                            island.push(coord);
                        }
                        statusMap[coordH + 1][coordW] = true;
                    }
                    if (coordW + 1 < width && !statusMap[coordH][coordW + 1]) { //check right
                        if (grid[coordH][coordW + 1] == '1') {
                            int[] coord = {coordH, coordW + 1};
                            island.push(coord);
                        }
                        statusMap[coordH][coordW + 1] = true;
                    }
                }
            }
        }
        // System.out.println("# of operations: " + numOfOps);
        return numOfIslands;
    }

    private void printGrid(char[][] grid) {
        System.out.println("Grid:");
        for (int h = 0; h < grid.length; h++) {
            for (int w = 0; w < grid[h].length; w++) {
                System.out.print(grid[h][w] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        QueueProblems p = new QueueProblems();
        char[][] a = new char[4][5];
        a[0][0] = '1'; a[0][1] = '1'; a[0][2] = '1'; a[0][3] = '1'; a[0][4] = '0';
        a[1][0] = '1'; a[1][1] = '1'; a[1][2] = '0'; a[1][3] = '1'; a[1][4] = '0';
        a[2][0] = '1'; a[2][1] = '1'; a[2][2] = '0'; a[2][3] = '0'; a[2][4] = '0';
        a[3][0] = '0'; a[3][1] = '0'; a[3][2] = '0'; a[3][3] = '0'; a[3][4] = '0';
        // char[][] a = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        int num = p.numIslands(a);
        System.out.println("Output is: " + num);
    }
}