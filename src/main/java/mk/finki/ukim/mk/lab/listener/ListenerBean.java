package mk.finki.ukim.mk.lab.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionListener;

@Component
public class ListenerBean implements HttpSessionListener {
    @EventListener
    public void handleEvent(Object event) {
        System.out.println("event: "+event);
    }

}
