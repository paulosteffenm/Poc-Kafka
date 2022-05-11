package kafka.producer;

import kafka.event.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppProducer {
  public static void main(String[] args) {
    AppProducer app = new AppProducer();
    app.init();
  }

  private void init() {
    Logger logger = LoggerFactory.getLogger(AppProducer.class);
    logger.info("Starting producer...");
    Event event = new Event();
    event.execute();
  }
}
