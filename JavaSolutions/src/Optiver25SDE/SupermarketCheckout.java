package Optiver25SDE;

import java.util.*;

/**
 *
 * A supermarket has many customers entering and exiting at various points. They want to keep track of customers and get
 * notification when a customer leaves the store. There are number of checkout lines, where customers with basket of
 * items queue to pay and exit the store. Individual checkout lines and customers are assigned numerical lDs. As happens
 * in life, sometimes customers want to add more items to their baskets and sometimes they realize that they don't need
 * certain items they picked up earlier and remove them from the basket. To enforce checkout priorities a few rules
 * have been implemented in the supermarket:
 * 1. A customer cannot switch the lines before exit once they join a given checkout line.
 * 2. lf a customer increases their items to purchase, they must go to the back of the same line.
 * 3. lf a customer removes items from their basket, they will keep their position in the line(or leave the store if
 *    they don't have any more items).
 * A customer will leave the supermarket as soon as they have no items left to checkout. Note that the lines with
 * smaller lDs are closer to the exit, so if two customers pass the checkout line at the same time, the one closer to
 * the exit would leave the store first.
 *
 * Problem Statement:
 * You will receive the stream of N instructions. Each instruction can be one of the following actions:
 * CustomerEnter - indicates that a customer joined a checkout line.
 * @Attributes: CustomerId, LineNumber and NumItems.
 *
 * BasketChange - indicates that a customer changed number of items in their basket.
 * @Attributes: customerId and NewNumItems.
 *
 * LineService - indicates that several items have been processed in the line.
 * @Attributes: LineNumber and NumProcessedItems.
 *
 * LinesService - indicates that one item has been processed in every line (if there are k lines then in total k items
 * are processed).
 *
 * Important Notes:
 * 1. The NewNumItems attribute of the BasketChange action is the number of total items.
 * 2. Line service calls with no queue in the line should be ignored.
 * Your task is to keep track of customers and notify when a customer leaves the store.
 *
 * Function Description:
 * It is required to implement a class that provides methods OnCustomerEnter, OnBasketChange, OnLineService and
 * onLinesService.
 * These method calls correspond to the instructions described above with the method arguments corresponding to the
 * specified attributes. OnCustomerExit method is implemented for you and should be called to notify that the
 * customer left the store.
 *
 * Constraints:
 * 0 <= N, customerId, LineNumber < 2^32
 * 0 <= NumItems, NewNumItems, NumProcessedItems <= 10^3
 * It is guaranteed that BasketChange will only happen for the customers who are still in the store.
 */


public class SupermarketCheckout {
    // 每条线上的顾客队列
    private final Map<Long, LinkedList<Customer>> lineQueues = new HashMap<>();

    // 所有顾客信息
    private final Map<Long, Customer> customerMap = new HashMap<>();

    private static class Customer {
        long customerId;
        long lineNumber;
        long numItems;         // 当前要处理的商品数
        long initialNumItems;  // 初始加入时的商品数，用于 BasketChange 参考

        public Customer(long customerId, long lineNumber, long numItems) {
            this.customerId = customerId;
            this.lineNumber = lineNumber;
            this.numItems = numItems;
            this.initialNumItems = numItems;
        }
    }

    public SupermarketCheckout() {}

    public void onCustomerEnter(long customerId, long lineNumber, long numItems) {
        Customer customer = new Customer(customerId, lineNumber, numItems);
        lineQueues.putIfAbsent(lineNumber, new LinkedList<>());
        lineQueues.get(lineNumber).addLast(customer);
        customerMap.put(customerId, customer);
    }

    public void onBasketChange(long customerId, long newNumItems) {
        Customer customer = customerMap.get(customerId);
        if (customer == null) return;

        // 实际新商品数 = newNumItems - (initial - current)
        long processedItems = customer.initialNumItems - customer.numItems;
        long newCurrentNum = newNumItems - processedItems;

        // 如果商品清空了，离开队列
        if (newCurrentNum <= 0) {
            lineQueues.get(customer.lineNumber).remove(customer);
            customerMap.remove(customerId);
            onCustomerExit(customerId);
        } else {
            // 更新初始值和当前值
            customer.initialNumItems = newNumItems;
            customer.numItems = newCurrentNum;

            // 若添加了商品，移动到队尾
            LinkedList<Customer> queue = lineQueues.get(customer.lineNumber);
            queue.remove(customer);
            queue.addLast(customer);
        }
    }

    public void onLineService(long lineNumber, long numProcessedItems) {
        LinkedList<Customer> queue = lineQueues.get(lineNumber);
        if (queue == null || queue.isEmpty()) return;

        while (numProcessedItems > 0 && !queue.isEmpty()) {
            Customer front = queue.peekFirst();
            if (front.numItems <= numProcessedItems) {
                numProcessedItems -= front.numItems;
                queue.pollFirst();
                customerMap.remove(front.customerId);
                onCustomerExit(front.customerId);
            } else {
                front.numItems -= numProcessedItems;
                numProcessedItems = 0;
            }
        }
    }

    public void onLinesService() {
        List<Long> sortedLines = new ArrayList<>(lineQueues.keySet());
        Collections.sort(sortedLines);

        for (long lineNumber : sortedLines) {
            LinkedList<Customer> queue = lineQueues.get(lineNumber);
            if (queue == null || queue.isEmpty()) continue;

            Customer front = queue.peekFirst();
            front.numItems -= 1;

            if (front.numItems == 0) {
                queue.pollFirst();
                customerMap.remove(front.customerId);
                onCustomerExit(front.customerId);
            }
        }
    }

    public void onCustomerExit(long customerId) {
        System.out.println(customerId);
    }
}
