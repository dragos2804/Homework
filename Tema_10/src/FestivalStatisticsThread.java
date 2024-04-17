public class FestivalStatisticsThread extends Thread{
    private final FestivalGate gate;

    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                printStatistics();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void printStatistics() throws InterruptedException {
        synchronized (gate) {
            if (!gate.isEmpty()) {
                int totalPeople = gate.getSize();
                int fullTickets = 0, fullVipTickets = 0, freePasses = 0, oneDayPasses = 0, oneDayVipPasses = 0;

                for (int i = 0; i < totalPeople; i++) {
                    TicketType ticket = gate.validateTicket();
                    switch (ticket) {
                        case FULL:
                            fullTickets++;
                            break;
                        case FULL_VIP:
                            fullVipTickets++;
                            break;
                        case FREE_PASS:
                            freePasses++;
                            break;
                        case ONE_DAY:
                            oneDayPasses++;
                            break;
                        case ONE_DAY_VIP:
                            oneDayVipPasses++;
                            break;
                    }
                    gate.addTicket(ticket);
                }
                System.out.println("----- Festival Statistics -----");
                System.out.println(totalPeople + " people entered");
                System.out.println(fullTickets + " have full tickets");
                System.out.println(fullVipTickets + " have full VIP passes");
                System.out.println(freePasses + " have free passes");
                System.out.println(oneDayPasses + " have one-day passes");
                System.out.println(oneDayVipPasses + " have one-day VIP passes");
            }
        }
    }
}

