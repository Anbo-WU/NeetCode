package Susquehanna;

import java.util.HashMap;
import java.util.Map;

/**
 * Imagine you are given a number line represented by [0, length - 1], and you are coloring the coordinates along the line.
 * Specifically, you are given a list of queries that determines how to color coordinates along the line in the following format:
 * [coord, color] - color the coordinate
 * <coord> with color <color> . All existing colors are overwritten.
 * After processing each query, record the number of consecutive pairs of coordinates which currently have the same color on the number line. Your task is to return an array of length queries. length containing these records after processing all queries.
 * Example:
 * For length = 7 and queries = [ [1, 2], [0, 2], [3, 5], [3, 2], [2, 2], [6, 1], [1, 3] ] , the output should be:
 * solution (length, queries) = [0, 1, 1, 1, 3, 3, 1].
 * • Expand to see the example video: Resources/CoordinateColorPairs.mp4
 *   Or you can access this link: https://codesignal.s3.amazonaws.com/uploads/1644759491405/1dc40026-8cd2-11ec-a8a3-0242ac120002.mp4
 *   <p>Video:<a href="https://codesignal.s3.amazonaws.com/uploads/1644759491405/1dc40026-8cd2-11ec-a8a3-0242ac120002.mp4">CLike here</a></p>
 *
 * Let's consider all queries:
 * • [1, 2] - color the coordinate 1 with color 2 . After this query, the number line becomes: [0, 2, 0, 0, 0, 0, 0] . There is just 1 colored coordinate on the line, so record 0
 * • [0, 2] - color the coordinate 0 with color 2 . After this query, the number line becomes: [2, 2, 0, 0, 0, 0, 0]. There is one consecutive pair of coordinates with the same color 2 : coordinates[0] and coordinates [1] , so record 1
 * • [3, 5] - color the coordinate 3 with color 5 . After this query, the number line becomes: [2, 2, 0, 5, 0, 0, 0] . There is still just one consecutive pair of coordinates with the same color 2 , so record 1
 * • [3, 2] - color the coordinate 3 with color 2. After this query, the number line becomes: [2, 2, 0, 2, 0, 0, 0] . There is still just one consecutive pair of coordinates with the same color 2, so record 1
 * • [2, 2] - color the coordinate 2 with color 2 . After this query, the number line becomes: [2, 2, 2, 2, 0, 0, 0] . There are 3 consecutive pairs of coordinates with the same color 2 : coordinates[0] and coordinates [1] , coordinates [2] coordinates [1] , coordinates [2] and coordinates [3] . So, record 3
 * • [6, 1] - color the coordinate 6 with color 1. After this query, the number line becomes: [2, 2, 2, 2, 0, 0, 1] . There are still 3 consecutive pairs of coordinates with the same color 2 , so record 3
 * • [1, 3] - color the coordinate 1 with color 3. After this query, the number line becomes: [2, 3, 2, 2, 0, 0, 1] . Now, there is one consecutive pair of coordinates with the same color 2 : coordinates [2] and coordinates [3] , so record 1 .
 * • After processing all queries, the final output iS [0, 1, 1, 1, 3, 3, 1]
 *
 * Input/Output:
 *
 * • [execution time limit] 3 seconds (java)
 * • [memory limit] 1 GB
 * • [input] integer length: The length of the number line represented by [0, length - 1].
 * Guaranteed constraints:
 * 1 ≤ length ≤ 10^9.
 * • [input] array.array.integer queries: An array of queries.
 * Guaranteed constraints:
 * 1 ≤ queries. length ≤ 10^5 ,
 * 0 ≤ queries [i][0] < length,
 * 1 ≤ queries [i][1] ≤ 10^9 .
 *
 * • [output] array.integer
 * Return an array of length <queries.length>, such that the ith element contains the number of consecutive pairs of coordinates with the same color on the number line after the ith query is processed.
 */
public class coordinateColor {
    public static int[] solution8(int length, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        Map<Integer, Integer> colorMap = new HashMap<>();
        int sameColorPairs = 0;

        for (int i = 0; i < n; i++) {
            int coord = queries[i][0];
            int color = queries[i][1];

            int leftCoord = coord - 1;
            int rightCoord = coord + 1;

            // If the coordinate was previously colored, remove pairs contributed by old color
            if (colorMap.containsKey(coord)) {
                int oldColor = colorMap.get(coord);
                if (leftCoord >= 0 && colorMap.containsKey(leftCoord) && colorMap.get(leftCoord) == oldColor) {
                    sameColorPairs--;
                }
                if (rightCoord < length && colorMap.containsKey(rightCoord) && colorMap.get(rightCoord) == oldColor) {
                    sameColorPairs--;
                }
            }

            // Update the color
            colorMap.put(coord, color);

            // Add pairs contributed by new color
            if (leftCoord >= 0 && colorMap.containsKey(leftCoord) && colorMap.get(leftCoord) == color) {
                sameColorPairs++;
            }
            if (rightCoord < length && colorMap.containsKey(rightCoord) && colorMap.get(rightCoord) == color) {
                sameColorPairs++;
            }

            result[i] = sameColorPairs;
        }

        return result;
    }
}
