package service.event.listeners;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class EventListener {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println(message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return this.latch;
    }

}
