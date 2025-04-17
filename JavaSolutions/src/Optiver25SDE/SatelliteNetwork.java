package Optiver25SDE;

import jdk.jfr.Event;

import java.util.*;

/**
 A SATELLITE NETWORK IS EXPERIMENTING WITH A DISTRIBUTED COMMUNICATION PROTOCOL BASED ON SATELLITE CONNECTIONS.
 DURING SYSTEM TESTS, THE TEAM WANTS TO ENSURE THAT MESSAGES THEY SENT ARE PROCESSED IN THE CORRECT ORDER. THE PROTOCOL WORKS LIKE SO:
 1. MESSAGE IS SENT FROM EARTH DIRECTLY TO ONE OR MORE SATELLITES
 2. EVERY SATELLITE THAT RECEIVES THE MESSAGE FORWARDS IT ALL ITS DIRECT CONNECTIONS, IN INCREASING ORDER OF SATELLITEID, AS LONG AS THEY HAVEN'T RECEIVED IT YET.
 3. ONCE ALL ITS DIRECT CONNECTIONS HAVE RECEIVED THE MESSAGE, A SATELLITE PROCESSES THE MESSAGE AND REPORTS BACK TO EARTH.

 PROBLEM STATEMENT:
 YOU WILL RECEIVE THE STREAM OF N INSTRUCTIONS. EACH INSTRUCTION CAN BE ONE OF THE FOLLOWING ACTIONS:
 1. SATELLITECONNECTED - INDICATES THAT A SATELLITE IS CONNECTED TO THE NETWORK.
 - PARAMETERS: SATELLITEID.
 2. RELATIONSHIPESTABLISHED - INDICATES A (TWO-WAY) RELATIONSHIP BETWEEN SATELLITES.
 - PARAMETERS: SATELLITEID1, SATELLITEID2.
 3. MESSAGERECEIVED - INDICATES THAT A SET OF M SATELLITES RECEIVED A MESSAGE.
 - PARAMETERS: NUMBER OF NOTIFIED SATELLITES M, FOLLOWED BY M SATELLITE IDS BEING SIMULTANEOUSLY NOTIFIED.

 IMPORTANT NOTES:
 1. IT TAKES 10 SECONDS FOR A SATELLITE TO FORWARD A MESSAGE TO ONE OF ITS CONNECTIONS.THIS PROCEDURE IS SYNCHRONOUS AND ATOMIC, I.E. FOR EVERY SENDER, ONLY ONE MESSAGE CAN BE FORWARDED AT A TIME AND IT TAKES EXACTLY1OS FOR IT TO COMPLETE.
 2. AS SOON AS THE FORWARDING PROCEDURE IS COMPLETE, YOU MAY ASSUME THAT THE ENTIRE NETWORK KNOWS THAT THE RECEIVER SATELLITE HAS BEEN NOTIFIED, I.E.NO FURTHER ATTEMPTS TO NOTIFY IT MUST BE PERFORMED.
 3. WHILE THE FORWARDING PROCEDURE IS NOT COMPLETE, ANY OTHER SATELLITE MAY ATTEMPT TO FORWARD MESSAGES TO THE SAME CONNECTION. IN THIS CASE, THE SAME 10 SECONDS ARE SPENT BY SENDER.
 4. SATELLITE WILL NEVER TRY TO NOTIFY THE SATELLITE THAT NOTIFIED IT.
 5. IT TAKES A SATELLITE 30 SECONDS TO PROCESS THE MESSAGE AND REPORT BACK TO EARTH.
 6. IF TWO SATELLITES REPORT BACK TO EARTH AT THE SAME TIME, THE ONE WITH SMALLER SATELLITEID ARRIVES FIRST.

 FUNCTION DESCRIPTION:
 COMPLETE THE FUNCTIONS SATELLITECONNECTED, RELATIONSHIPESTABLISHED AND MESSAGERECEIVED OF THE SATELLITENETWORK CLASS, WHICH CORRESPOND TO THE INSTRUCTIONS DESCRIBED ABOVE.
 CONSIDER THE BELOW FUNCTIONS AVAILABLE TO YOU AND MUST BE CALLED IN THE CORRECT ORDER:
 1. ONSATELLITEREPORTEDBACK: SHOULD BE CALLED TO NOTIFY THAT A SATELLITE REPORTED BACK TO EARTH, IN ORDER.
 - PARAMETERS: SATELLITEID.
 2. ERRDUPLICATESATELLITE: SHOULD BE CALLED IF A SATELLITE CONNECTS MORE THAN ONCE.
 - PARAMETERS: SATELLITEID.
 3. ERRINVALIDSATELLITE: SHOULD BE CALLED IF A REFERENCED SATELLITEID DOES NOT EXIST. THE WHOLE INSTRUCTION REFERRING TO THIS SATELLITE MUST BE SKIPPED.
 - PARAMETERS: SATELLITEID.

 CONSTRAINTS:
 1. 0 <= SATELLITEID < 2^16
 2. 0 <= N < 2^32
 3. 0 <= M < 2^32

 */
public class SatelliteNetwork {

    // 卫星连接图（邻接表）
    private final Map<Long, Set<Long>> graph = new HashMap<>();

    // 是否已经连接
    private final Set<Long> connected = new HashSet<>();

    // 是否已被通知
    private final Set<Long> notified = new HashSet<>();

    // 记录每个卫星被通知的时间
    private final Map<Long, Integer> notifyTime = new HashMap<>();

    // 卫星连接
    public void satelliteConnected(long satelliteId){
        // TODO
        if (connected.contains(satelliteId)) {
            errDuplicateSatellite(satelliteId);
            return;
        }
        connected.add(satelliteId);
        graph.putIfAbsent(satelliteId, new TreeSet<>()); // 使用 TreeSet 确保传播顺序按 ID 升序
    }

    public void relationshipEstablished(long satellite1, long satellite2){
        //TODO
        if (!connected.contains(satellite1)) {
            errInvalidSatellite(satellite1);
            return;
        }
        if (!connected.contains(satellite2)) {
            errInvalidSatellite(satellite2);
            return;
        }
        graph.get(satellite1).add(satellite2);
        graph.get(satellite2).add(satellite1);
    }

    public void messageReceived(List<Long> notifiedSatellites){
        //TODO
        PriorityQueue<Event> pq = new PriorityQueue<>(); // min-heap：处理时间，id 越小优先

        Queue<Propagation> q = new LinkedList<>();

        for (Long sat : notifiedSatellites) {
            if (!connected.contains(sat)) {
                errInvalidSatellite(sat);
                continue;
            }
            if (notified.contains(sat)) continue;

            notified.add(sat);
            notifyTime.put(sat, 0); // 起始时间
            pq.offer(new Event(30, sat)); // 30s 后报告
            for (Long neighbor : graph.getOrDefault(sat, new TreeSet<>())) {
                if (!notified.contains(neighbor)) {
                    q.offer(new Propagation(sat, neighbor, 10)); // 起始传播
                }
            }
        }

        while (!q.isEmpty()) {
            Propagation p = q.poll();
            // 如果接收方已经被通知过，跳过
            if (notified.contains(p.to)) continue;

            notified.add(p.to);
            notifyTime.put(p.to, p.time);
            pq.offer(new Event(p.time + 30, p.to));

            for (Long neighbor : graph.getOrDefault(p.to, new TreeSet<>())) {
                if (!notified.contains(neighbor)) {
                    q.offer(new Propagation(p.to, neighbor, p.time + 10));
                }
            }
        }

        // 所有事件按时间升序 & ID 升序输出
        while (!pq.isEmpty()) {
            onSatelliteReportedBack(pq.poll().satelliteId);
        }
    }

    // 调用的方法（题目已实现）
    public void onSatelliteReportedBack(long satelliteId) {
        System.out.println(satelliteId);
    }

    public void errDuplicateSatellite(long satelliteId) {
        System.err.println("DUPLICATE: " + satelliteId);
    }

    public void errInvalidSatellite(long satelliteId) {
        System.err.println("INVALID: " + satelliteId);
    }

    // 内部类：传播事件
    private static class Propagation {
        long from, to;
        int time;

        Propagation(long from, long to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    // 内部类：回报事件
    private static class Event implements Comparable<Event> {
        int time;
        long satelliteId;

        Event(int time, long satelliteId) {
            this.time = time;
            this.satelliteId = satelliteId;
        }

        @Override
        public int compareTo(Event o) {
            if (this.time != o.time) return Integer.compare(this.time, o.time);
            return Long.compare(this.satelliteId, o.satelliteId);
        }
    }
}
