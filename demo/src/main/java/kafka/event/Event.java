package kafka.event;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Event {

  private final Producer<String, String> producer;

  public Event() {
    producer = creteProducer();
  }

  private Producer<String, String> creteProducer() {
    if(producer != null){
      return producer;
    }

    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("serializer.class", "kafka.serializer.DefaultSerializer");

    return new KafkaProducer<String,String>(props);
  }

  public void execute(){
    String key = UUID.randomUUID().toString();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String message = sdf.format(new java.util.Date());
    message += " - " + key;

    ProducerRecord<String, String> record = new ProducerRecord<String,String>("RegEvent", key, message);

    Logger logger = LoggerFactory.getLogger(Event.class);
    logger.info("Sending message...");

    producer.send(record);
    producer.flush();
    producer.flush();

    logger.info("Message sent.");
  }
}
