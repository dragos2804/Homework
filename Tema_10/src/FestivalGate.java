import java.util.Queue;
import java.util.LinkedList;

public class FestivalGate {
    private final Queue<TicketType> queue = new LinkedList<>();

    public synchronized void addTicket(TicketType ticketType) {
        queue.add(ticketType);
        notifyAll();
    }

    public synchronized TicketType validateTicket() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }

    public synchronized int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return false;
    }
}

