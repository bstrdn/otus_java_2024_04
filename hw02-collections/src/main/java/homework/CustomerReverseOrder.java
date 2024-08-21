package homework;

import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final Deque<Customer> queue = new LinkedList<>();

    public void add(Customer customer) {
        queue.push(customer);
    }

    public Customer take() {
        return queue.pollLast();
    }
}
