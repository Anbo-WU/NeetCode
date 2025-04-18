package Optiver25SDE;

import java.util.*;

/**
 As a long time live-music fan you have kept a bucket list of concerts you want to see.However, due to scalpers and resellers you struggle to buy online tickets for a
 reasonable price. Luckily, you found a service called BuyFirst, which publishes real-time data about freshly available concert tickets before anybody else. To be fast, they publish the information packet by packet.
 Your bucket list has concerts for the artists you want to see. You have also decided that you are willing to pay up to a certain ticket price, but no more. Finally,concerts are only worth going to with friends, so you want to buy a certain number of available seats per concert.
 BuyFirst will send AvailableTicket messages split into multiple packets. BuyFirst also provides an APl to submit an order to buy a ticket. Your objective is to write a program to listen to available tickets, and buy tickets that match your criteria as soon as they become available.

 Format of AvailableTicket Messages:
 Messages from BuyFirst are not received asa whole, but packet-by-packet:
 - packet 1: message lD and artist lD
 - packet 2: message lD and ticket price
 - packet 3: message lD and available seats
 Note the following:
 - the message lD is the same for all packets of the same message
 the message lD is unique for each message and will not be reused
 the ordering of packets within a message is guaranteed (i.e. for a given message lD the first packet contains the artist iD, the second packet contains the ticket price,etc.)
 since BuyFirst is streaming data from multiple sources, message packets may arrive in arbitrarily interleaved fashion. For example, you may receive the first packet of message 1, then the first packet of message 2, then the second packet of message 2, then the second packet of message 1, then the third packet of
 message 1, etc.

 Format of Order Messages:
 The message to buy tickets also sent packet-by-packet:
 - packet 1: original message lD
 - packet 2: number of seats
 These packets are sent contiguously. They cannot be interleaved.

 Function Description:
 Complete the ConcertTicketBuyer class in the editor below. The class has two methods, OnNewRequirement and ProcessData.

 OnNewRequirement has the following parameters:
 - artist ID: an integer
 - ticket price: an integer
 - available seats: an integer
 OnNewRequirement is called when a requirement is added and its parameters correspond to the fields of the AvailableTicket message.

 ProcessData is called for every incoming packet of an AvailableTicket message and has the following parameters:
 - message lD: an integer which is a message identifier; 0 means "no data"
 - data: an integer which is the payload of the AvailableTicket message; will indicate one of the artist lD, ticket price, or available seats when the message ID is not 0
 ProcessData returns:
 - integer: packet of an Order message to send, or 0 if there is nothing to send

 An AvailableTicket matches a requirement if:
 - the ticket's artist ID is equal to the required artist ID
 - the ticket's ticket price is lower than or equal to the required ticket price
 - the ticket's available seats is higher than or equal to the required available seats

 When sending an order note that:
 - some requirements can be fulfilled before all packets of an AvailableTicket message have arrived, and requirements must be fulfilled as soon as possible
 - the order must reference the messageID of the AvailableTicket message you want to buy in the original message ID field
 - only one order can be sent per AvailableTicket message
 - a single requirement can only be fulfilled against a single AvailableTicket
 - when returning packets of an order from ProcessData (): if a valid(nonzero) original_message_id packet has been returned then the number_of_seats packet must be returned from the next immediate call
 - newly received requirements cannot be fulfilled against already fully received AvailableTicket messages since the buying opportunity is assumed to be gone

 Constraints:
 0 <= message lD < 2^32
 0 <= artistID < 2^32
 0 <= ticket price < 2^32
 1 <= available seats < 2^32
 1 <= original message ID < 2^32
 1 <= number of seats < 2^32

 */
public class ConcertTicketBuyer {
    private static class Requirement {
        int artistId;
        int maxPrice;
        int minSeats;

        Requirement(int artistId, int maxPrice, int minSeats) {
            this.artistId = artistId;
            this.maxPrice = maxPrice;
            this.minSeats = minSeats;
        }
    }

    private static class TicketMessage {
        Integer artistId = null;
        Integer price = null;
        Integer seats = null;
        boolean isOrdered = false;
    }

    private final List<Requirement> requirements = new ArrayList<>();
    private final Map<Integer, TicketMessage> messageMap = new HashMap<>();
    private Integer pendingOrderMessageId = null;
    private Integer pendingOrderSeats = null;

    public ConcertTicketBuyer() {}

    public void OnNewRequirement(Integer artistId, Integer ticketPrice, Integer availableSeats){
        requirements.add(new Requirement(artistId, ticketPrice, availableSeats));
    }

    public Integer ProcessData(Integer messageId, Integer data){
        // Step 1: Return the second packet of an Order if pending
        if (pendingOrderMessageId != null && pendingOrderSeats != null) {
            Integer toReturn = pendingOrderSeats;
            pendingOrderMessageId = null;
            pendingOrderSeats = null;
            return toReturn;
        }

        // Step 2: If no message, return 0
        if (messageId == 0) {
            return 0;
        }

        // Step 3: Get or create the TicketMessage object
        TicketMessage msg = messageMap.computeIfAbsent(messageId, k -> new TicketMessage());

        // Step 4: Determine which field to fill
        if (msg.artistId == null) {
            msg.artistId = data;
        } else if (msg.price == null) {
            msg.price = data;
        } else if (msg.seats == null) {
            msg.seats = data;
        }

        // Step 5: After each update, try to match if complete
        if (!msg.isOrdered && msg.artistId != null && msg.price != null && msg.seats != null) {
            for (Requirement r : requirements) {
                if (Objects.equals(msg.artistId, r.artistId)
                        && msg.price <= r.maxPrice
                        && msg.seats >= r.minSeats) {

                    msg.isOrdered = true;
                    pendingOrderMessageId = messageId;
                    pendingOrderSeats = r.minSeats;
                    return messageId;
                }
            }
        }

        return 0;
    }
}
