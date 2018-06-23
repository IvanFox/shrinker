package me.ivanlis.shrinker.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.repositories.LinkRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkConsumer {

    private final static String GROUP_ID = "SHRINKER";

    private final LinkRepository linkRepository;

    @KafkaListener(topics = LinkPublisher.TOPIC)
    public void listen(@Payload String message) {
        log.info("Received message to persist. {}", message);
        System.out.println(message);
    }

}
