package Susquehanna;

import java.util.*;

/**
 * Think about a team chat with numerous users writing messages in it. The chat supports two types of actions.
 * "MESSAGE" - Messages a set of users. The format looks as follows: ["MESSAGE", "<timestamp>", "<mentions>"]. Mentions string can contain the following space-separated tokens.
 * - id<number> , where <number> is an integer in range [1:999] - mentioning of individual users
 * - ALL - mentioning all users
 * - HERE -mentioning active users
 * - "OFFLINE" - Makes a user with a given id inactive for 60 time ticks -the user will be active again a time <timestamp> + 60. It is guaranteed that the user with given <id> will be active when this action is applied. The format looks as follows. ["OFFLINE", "<timestamp>", "<id>"] ,where id is a single individual mention.
 * Note: all the events are sorted by their timestamp.
 * Examples of events
 * ["MESSAGE"，"0"，"id1"] - mentioning user with id 'id1'
 * ["MESSAGE","9","HERE id3"] - mentioning all the active users and user with id 'id3'
 * ["MESSAGE"，"6"，"ALL"] - mentioning all the users
 * ["MESSAGE", "100" ,""] - message, without mentioning any user
 * ["OFFLINE", “200”, "id3"] - making user with id 'id3' inactive
 * Please note, that mentions do not contain any other text, but only a list of ids or aliases separates by a space.
 * Your task is to calculate mention statistics. Given a list of users in the group chat and a list of chat events, count the number of message events that each user is mentioned in.
 * Return the results in an array of stings, with each string following this format: "[user id]=[mentions count]". The array should be sorted lexicographically by user id in ascending order.
 * Note: You are not expected to provide the most optimal solution,but a solution with time complexity not worse than o(members.length * events.length) will fit within the execution time limit.
 *
 * Example:
 * For members = ["id42"，"id158","id23"] and
 * events = [
 * ["MESSAGE", "0", "ALL id158 id42"],
 * ["OFFLINE", "1", "id158"],
 * ["MESSAGE", "2", "id158 1d158"],
 * ["OFFLINE”, "3"，"id23"],
 * ["MESSAGE", "60", "HERE id158 id42 id23"],
 * ["MESSAGE", "61", "HERE"],
 * ]
 *
 * The output should be:
 * solution(members, events) = ["id158=4", "id23=2", "id42=3"]
 *
 * Explanation:
 * In the first event at time 0, all users are mentioned with all alias. Note that id158 and id42 are mentioned twice—once by ALL alias and once by their ids, but they should be counted once.
 * In the second event at time 1, the user id158 becomes inactive.
 * In the third event at time 2, the user id158 is mentioned, and they get notified even when inactive. Note that id158 is mentioned twice. but should be counted once.
 * In the fourth event at time 3, the user id23 becomes inactive.
 * In the fifth event at time 60, all active users and id158, id42, and id23 are mentioned. Note that id158 and id23 are mentioned by their username, so they will be notified even when they are inactive, and id42 is mentioned twice—once by HERE alias and once by their id, but it should be counted once.
 * In the last event at time 61, the user with id158 is back online again, as they have been offline for 60 time ticks already. Here all the active users are mentioned, which are id42 and id158 .
 * So, the output should be ["id158=4"，"id23=2"，"id42=3"]

 */
public class teamChat {
    public static String[] solution3(String[] members, String[][] events) {
        Map<String, Integer> mentionCount = new HashMap<>();
        Map<String, Integer> inactiveUntil = new HashMap<>();
        Set<String> memberSet = new HashSet<>(Arrays.asList(members));

        for (String member : members) {
            mentionCount.put(member, 0);
            inactiveUntil.put(member, -1);
        }

        for (String[] event : events) {
            String type = event[0];
            int time = Integer.parseInt(event[1]);

            if (type.equals("OFFLINE")) {
                String user = event[2];
                inactiveUntil.put(user, time + 60);
            } else if (type.equals("MESSAGE")) {
                Set<String> mentionedThisMsg = new HashSet<>();
                String[] mentions = event[2].split(" ");

                boolean isAll = false;
                boolean isHere = false;

                for (String token : mentions) {
                    if (token.equals("ALL")) {
                        isAll = true;
                    } else if (token.equals("HERE")) {
                        isHere = true;
                    } else if (memberSet.contains(token)) {
                        mentionedThisMsg.add(token);
                    }
                }

                if (isAll) {
                    mentionedThisMsg.addAll(memberSet);
                }

                if (isHere) {
                    for (String user : members) {
                        if (inactiveUntil.get(user) <= time) {
                            mentionedThisMsg.add(user);
                        }
                    }
                }

                for (String user : mentionedThisMsg) {
                    mentionCount.put(user, mentionCount.get(user) + 1);
                }
            }
        }

        // 排序输出
        List<String> result = new ArrayList<>();
        Arrays.sort(members);
        for (String user : members) {
            result.add(user + "=" + mentionCount.get(user));
        }

        return result.toArray(new String[0]);
    }
}
