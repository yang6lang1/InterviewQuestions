import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    /** Open the Lock problem:
     * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
     * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around:
     * for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
     * The lock initially starts at '0000', a string representing the state of the 4 wheels.
     * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
     * the wheels of the lock will stop turning and you will be unable to open it.
     * Given a target representing the value of the wheels that will unlock the lock, return the minimum
     * total number of turns required to open the lock, or -1 if it is impossible.
        Example 1:
        Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
        Output: 6
        Explanation:
        A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
        Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
        because the wheels of the lock become stuck after the display becomes the dead end "0102".

        Example 2:
        Input: deadends = ["8888"], target = "0009"
        Output: 1
        Explanation:
        We can turn the last wheel in reverse to move from "0000" -> "0009".

        Example 3:
        Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
        Output: -1
        Explanation:
        We can't reach the target without getting stuck.

        Example 4:
        Input: deadends = ["0000"], target = "8888"
        Output: -1

        Note:
        The length of deadends will be in the range [1, 500].
        target will not be in the list deadends.
        Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
     */
    public int openLock(String[] deadends, String target) {
        //Process dependents
        Set<Integer> deadendSet = new HashSet<Integer>();
        for (String deadend : deadends) {
            int val = Integer.parseInt(deadend);
            deadendSet.add(val);
        }

        int currentVal = 0, targetVal = Integer.parseInt(target), steps = 0;
        if (currentVal == targetVal) {
            return 0;
        }
        if (deadendSet.contains(currentVal)) {
            return -1;
        }
        LinkedList<Integer> checkList = new LinkedList<Integer>();
        int[] distance = new int[10000];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = -1;
        }
        distance[currentVal] = steps;
        checkList.push(currentVal);
        while (!checkList.isEmpty()) {
            currentVal = checkList.pollLast();
            steps = distance[currentVal];
            if (currentVal == targetVal) {
                return steps;
            }

            //Check eight sibling nodes
            int digitOne = (int)(currentVal / 1000);
            int digitTwo = (int)((currentVal / 100) % 10);
            int digitThree = (int)((currentVal / 10) % 10);
            int digitFour = (int)(currentVal % 10);

            int resultOne = (digitOne + 1) % 10 * 1000 + digitTwo * 100 + digitThree * 10 + digitFour;
            this.updateDistanceMapAndCheckList(resultOne, steps + 1, deadendSet, distance, checkList);

            int resultTwo = digitOne * 1000 + (digitTwo + 1) % 10 * 100 + digitThree * 10 + digitFour;
            this.updateDistanceMapAndCheckList(resultTwo, steps + 1, deadendSet, distance, checkList);

            int resultThree = digitOne * 1000 + digitTwo * 100 + (digitThree + 1) % 10 * 10 + digitFour;
            this.updateDistanceMapAndCheckList(resultThree, steps + 1, deadendSet, distance, checkList);

            int resultFour = digitOne * 1000 + digitTwo * 100 + digitThree * 10 + (digitFour + 1) % 10;
            this.updateDistanceMapAndCheckList(resultFour, steps + 1, deadendSet, distance, checkList);

            int resultFive = (digitOne + 9) % 10 * 1000 + digitTwo * 100 + digitThree * 10 + digitFour;
            this.updateDistanceMapAndCheckList(resultFive, steps + 1, deadendSet, distance, checkList);

            int resultSix = digitOne * 1000 + (digitTwo + 9) % 10 * 100 + digitThree * 10 + digitFour;
            this.updateDistanceMapAndCheckList(resultSix, steps + 1, deadendSet, distance, checkList);

            int resultSeven = digitOne * 1000 + digitTwo * 100 + (digitThree + 9) % 10 * 10 + digitFour;
            this.updateDistanceMapAndCheckList(resultSeven, steps + 1, deadendSet, distance, checkList);

            int resultEight = digitOne * 1000 + digitTwo * 100 + digitThree * 10 + (digitFour + 9) % 10;
            this.updateDistanceMapAndCheckList(resultEight, steps + 1, deadendSet, distance, checkList);
        }
        return -1;
    }

    private void updateDistanceMapAndCheckList(int val, int steps, Set<Integer> deadendSet, int[] distance, LinkedList<Integer> checklist) {
        if (distance[val] == -1) { //never visited
            distance[val] = steps;
            if (!deadendSet.contains(val)) {
                checklist.push(val);
            }
        }
    }

    public static void main(String[] args) {
        QueueProblems p = new QueueProblems();
        // char[][] a = new char[4][5];
        // a[0][0] = '1'; a[0][1] = '1'; a[0][2] = '1'; a[0][3] = '1'; a[0][4] = '0';
        // a[1][0] = '1'; a[1][1] = '1'; a[1][2] = '0'; a[1][3] = '1'; a[1][4] = '0';
        // a[2][0] = '1'; a[2][1] = '1'; a[2][2] = '0'; a[2][3] = '0'; a[2][4] = '0';
        // a[3][0] = '0'; a[3][1] = '0'; a[3][2] = '0'; a[3][3] = '0'; a[3][4] = '0';
        // // char[][] a = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        // int num = p.numIslands(a);
        // System.out.println("Output is: " + num);

        // String[] deadends = {"0201","0101","0102","1212","2002"}
        // int result = p.openLock(deadends, "0202");
        // String[] deadends = {"8888"};
        // int result = p.openLock(deadends, "0009");
        // String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        // int result = p.openLock(deadends, "8888");
        String[] deadends = {"0000"};
        int result = p.openLock(deadends, "8888");
        System.out.println("steps taken: " + result);
    }
}