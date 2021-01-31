package com.distributed.tracing.CheckoutService.producer;

import com.distributed.tracing.CheckoutService.config.ApplicationProps;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderProducer {

    @Autowired
    private ApplicationProps props;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void prodcuerOrder(String key, String value) {

        List<Header> headers = buildKafkaHeaders();

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(props.getKafka().getTopicName(), null, key, value, headers);

        ListenableFuture<SendResult<String, String>> result = this.kafkaTemplate.send(record);

        result.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error("Unable to publish order for.", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                LOGGER.error("Order published successfully");
            }
        });

    }

    private List<Header> buildKafkaHeaders() {

        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("event-timestamp", String.valueOf(new Date().getTime()).getBytes(StandardCharsets.UTF_8)));
        return headers;

    }

}
