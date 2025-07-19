package com.kafka.kafkatest.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class KafkaConfigTest {

  private KafkaConfig kafkaConfig;

  @BeforeEach
  void setUp() {
    kafkaConfig = new KafkaConfig();

    // Setar valores via reflexão (porque @Value não funciona fora do contexto Spring)
    setField(kafkaConfig, "bootstrapAddressConsumer", "localhost:9092");
    setField(kafkaConfig, "bootstrapAddressProducer", "localhost:9092");
    setField(kafkaConfig, "groupId", "test-group");
  }

  private void setField(Object target, String fieldName, Object value) {
    try {
      var field = KafkaConfig.class.getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(target, value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testKafkaConsumerProperties() {
    Properties props = kafkaConfig.kafkaConsumerProperties();

    assertEquals("localhost:9092", props.getProperty("bootstrap.servers"));
    assertEquals("test-group", props.getProperty("group.id"));
    assertEquals("true", props.getProperty("enable.auto.commit"));
    assertEquals("org.apache.kafka.common.serialization.StringDeserializer", props.getProperty("key.deserializer"));
    assertEquals("org.apache.kafka.common.serialization.StringDeserializer", props.getProperty("value.deserializer"));
    assertEquals("earliest", props.getProperty("auto.offset.reset"));
  }

  @Test
  void testKafkaConsumerCreation() {
    Properties props = kafkaConfig.kafkaConsumerProperties();
    KafkaConsumer<String, String> consumer = kafkaConfig.kafkaConsumer(props);
    assertNotNull(consumer);
    consumer.close();
  }

  @Test
  void testProducerFactoryCreation() {
    ProducerFactory<String, String> factory = kafkaConfig.producerFactory();
    assertNotNull(factory);

    var config = factory.getConfigurationProperties();
    assertEquals("localhost:9092", config.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
    assertEquals(StringSerializer.class, config.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
    assertEquals(StringSerializer.class, config.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
  }

  @Test
  void testKafkaTemplateCreation() {
    KafkaTemplate<String, String> template = kafkaConfig.kafkaTemplate();
    assertNotNull(template);
  }
}
