package Susquehanna;

/**
 * You are given an array of songs 'songs', where each song is represented as a string in the following format: <songName>:<songLength>.
 * You are also given an array of animations 'animations', where each animation is represented in a similar format: <animationName>:<animationLength>.
 * Your task is to select an appropriate animation for each song. Specifically, an animation can only be matched to a song if its length is an integer divisor of the song's length.
 * If multiple animations can fit a song, select the one with the lowest index within the animations array.
 *
 * Return an array of animations for songs, ordered in the same way as the corresponding songs.
 * Each element of the array must be a string in the following format: <animationName>:<timesToBePlayed>, where timesToBePlayed is the number of times the animation must be played to match the song's length.
 * You can match the same animation to multiple songs. It is guaranteed that an appropriate animation exists for all given songs.
 *
 * Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than O(songs.length × animations.length) will fit within the execution time limit.
 *
 * Example:
 * For songs = ["notion:180", "voyage:185", "sample:180"] and animations = ["circles:360", "squares:180", "lines:37"],
 * the output should be solution(songs, animations) = ["squares:1", "lines:5", "squares:1"].
 *
 * Explanation:
 * For songs[0] = "notion:180", animation[0] = "circles:360" won't fit because the animation's length shouldn't exceed the song's length.
 * For songs[0] = "notion:180", animation[1] = "squares:180" is appropriate because their lengths are equal. So, the animation for the first song is animation[1] = "squares:180", which will be played just once.
 * For songs[1] = "voyage:185", animation[0] = "circles:360 won't fit because the animation's length shouldn't exceed the song's length.
 * For songs[1] = "voyage:185", animation[1] = "squares:180 won't fit because its length of 180 is not an integer divisor of the song's length of 185.
 * For songs[1] = "voyage:185", animation[2] = "lines:37" is appropriate because its length of 37 is an integer divisor of the song's length of 185. This animation can be repeated 5 times, which will be equal to song's length: 37 * 5 = 185.
 * For songs[2] = "sample:180", animation[1] = "squares:180" is appropriate, following the same logic as for the songs[0] = "notion:180".
 * The resulting array is ["squares:1", "lines:5", "squares:1"].
 *
 * Input/Output:
 * [execution time limit] 3 seconds (java)
 * [memory limit] 1 GB
 * [input] array.string 'songs'
 * An array of strings representing songs, with each song in this format: "<songName>:<songLength>". <songName> consists of English letters, and <songLength> consists of integer values.
 *
 * Guaranteed constraints:
 * 1 ≤ songs.length ≤ 100,
 * 1 ≤ songs[i].length ≤ 16,
 * 1 ≤ songLength ≤ 50000.
 *
 * [input] array.string animations
 * An array of strings representing animations, with each animation in this format: "<animationName>:<animationLength>". <animationName> consists only of English letters, and <animationLength> consists of integers values.
 *
 * Guaranteed constraints:
 * 1 ≤ animations.length ≤ 100,
 * 1 ≤ animations[i].length ≤ 16,
 * 1 ≤ animationLength ≤ 50000.
 *
 * [output] array.string
 * An array of strings representing the selected animation for each song in songs, and the number of times they need to be played to match the corresponding song length. Each element should be in this format "<animationName>:<timesToBePlayed>".
 */
public class songAnimation {
    public static String[] solution5(String[] songs, String[] animations) {
        String[] result = new String[songs.length];

        for (int i = 0; i < songs.length; i++) {
            String[] songParts = songs[i].split(":");
            String songName = songParts[0];
            int songLength = Integer.parseInt(songParts[1]);

            for (int j = 0; j < animations.length; j++) {
                String[] animParts = animations[j].split(":");
                String animName = animParts[0];
                int animLength = Integer.parseInt(animParts[1]);

                if (songLength % animLength == 0) {
                    int timesToBePlayed = songLength / animLength;
                    result[i] = animName + ":" + timesToBePlayed;
                    break;
                }
            }
        }

        return result;
    }
}
