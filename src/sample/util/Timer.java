package sample.util;

import sample.Main;
import sample.program.UpdateTableView;
import sample.services.OrderService;

public class Timer implements Runnable
{

    private OrderService orderService;
    private long delay;

    public Timer(OrderService orderService, long delay) {
        this.orderService = orderService;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (Main.isRun){
            UpdateTableView.updateData(orderService);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
