package com.controlledthinking;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelayController {

	private static final String template = "Hello, after %d seconds!";
    private final AtomicLong counter = new AtomicLong();
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	@RequestMapping("/delay/{seconds}")
    public Greeting greeting(@PathVariable Integer seconds) {
		try {
			log.info("Value of seconds: " + seconds);
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, seconds));
    }
}
