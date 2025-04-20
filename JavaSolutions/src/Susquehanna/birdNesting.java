package Susquehanna;

import java.util.ArrayList;
import java.util.List;

/**
 * You are helping the bird build its nest.You are given an array 'forest' , containing positive integers and zeros, and a non-negative integer 'bird' , representing the bird's initial position.
 * Each positive integer within the forest is considered to be a stick, where the forest[i] represents the length of the ith stick. Each zero within forest[i] represents that this place is empty.
 * Initially, the bird is located at forest[bird] , which is guaranteed to be zero. The bird builds its nest, following the algorithm.
 * - The bird flies to the right until it finds a stick;
 * - The bird flies back to its initial position and attaches the found stick to the nest;
 * - The bird repeats steps one and two, but changes the flight direction. It now flies to the left;
 * - The bird will repeat steps one and two, changing to the opposite direction each time, until the total length of the sticks in the nest reaches 100.
 *
 * It is guaranteed that the total length of all sticks in the forest is greater or equal to 100 . More formally, it is guaranteed that sum(forest) >= 100.
 * The forest is large, so we can safely guarantee that the bird will never reach the end of the forest during the process. More formally,if you follow the algorithm, you will never meet any of these situations:
 * - the bird flies to the right, but forest[i] = 0 for every i > bird;
 * - The bird flies to the left, but forest[i] = 0 for every i < bird;
 * Follow the process and return the array containing 0-based indices within the initial forest array of every stick found by the bird, sorted in the order in which it found them.
 * Note: You are not expected to provide the most optimal solution. but a solution with time complexity not worse than o(forest.length^2) will fit within the execution time limit.
 *
 * Example:
 * For forest = [25, 0, 50, 0, 0, 0, 0, 15, 0, 0, 45] and bird = 4,the output should be solution(forest, bird) = [7, 2, 10]
 * Explanation:
 * - At the beginning, the bird is located at forest[bird] = forest[4] = 0
 * - Following the algorithm described above, the bird fies to the right, meaning it checks only indices i > bird , until it finds the first stick. The first stick to the right is forest[7] = 15 . The bird takes this stick and returns it to the nest. The found stick positions are pos = [7] .The nest contains a single stick of length 15 .
 * - The bird changes its direction and fies to the left, meaning it checks only indices i < bird. It finds the second stick at forest[2] = 50 .The found stick positions are pos = [7, 2] . The total length of sticks in the nest is 15 + 50 = 65.
 * - The bird changes its direction again and files to the right. The last stick it finds is forest[10] = 45 . The found stick positions are pos = [7, 2, 10] . The total length of sticks in the nest is 15 + 50 + 45 = 110 . Since the total length of the found sticks reached 100 , the search stops. The answer is [7,2,10].
 */
public class birdNesting {
    public static int[] solution2(int[] forest, int bird) {
        List<Integer> result = new ArrayList<>();
        int totalLength = 0;

        boolean goRight = true;
        boolean[] used = new boolean[forest.length];

        while (totalLength < 100) {
            if (goRight) {
                for (int i = bird + 1; i < forest.length; i++) {
                    if (forest[i] > 0 && !used[i]) {
                        result.add(i);
                        used[i] = true;
                        totalLength += forest[i];
                        break;
                    }
                }
            } else {
                for (int i = bird - 1; i >= 0; i --) {
                    if (forest[i] > 0 && !used[i]) {
                        result.add(i);
                        used[i] = true;
                        totalLength += forest[i];
                        break;
                    }
            }
        }
            goRight = !goRight;
    }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
