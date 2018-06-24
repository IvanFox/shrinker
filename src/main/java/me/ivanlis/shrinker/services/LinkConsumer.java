package me.ivanlis.shrinker.services;

import com.google.gson.Gson;
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

    private final static Gson GSON = new Gson();

    private final LinkRepository linkRepository;

    @KafkaListener(topics = LinkPublisher.TOPIC)
    public void listen(@Payload String message) {
        log.info("Received message to persist. {}", message);
        try {
            Link link = GSON.fromJson(message, Link.class);

            // TODO fix this piece of shit that doesnt really save anything
            linkRepository.insert(link).doOnSuccess(l -> {
                log.info("Successfully persisted received message. {}", l);
            }).doOnError(e -> {
                log.error("Error {}", e.getMessage());
            });
        } catch (Exception exc){
            log.error("Could not persist link. {}", message);
        }
    }

}
