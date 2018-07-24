package me.ivanlis.shrinker.services;

import static me.ivanlis.shrinker.utils.JsonSerialiser.JSON_SERIALISER;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ivanlis.shrinker.data.Link;
import me.ivanlis.shrinker.repositories.LinkRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkConsumer {

    private final LinkRepository linkRepository;

    @KafkaListener(topics = LinkPublisher.TOPIC)
    public void listen(@Payload String message) {
        log.info("Received message to persist. {}", message);
        try {
            Link link = JSON_SERIALISER.fromJson(message, Link.class);
            linkRepository.save(link).subscribe();
        } catch (Exception exc) {
            log.error("Could not persist link. {}", message);
            throw new RuntimeException("Failed to persist given link.", exc);
        }
    }

}
