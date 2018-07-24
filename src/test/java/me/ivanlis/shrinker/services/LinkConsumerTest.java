package me.ivanlis.shrinker.services;

import static me.ivanlis.shrinker.utils.JsonSerialiser.JSON_SERIALISER;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import me.ivanlis.shrinker.data.Link;
import me.ivanlis.shrinker.repositories.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

public class LinkConsumerTest {

    @Mock
    LinkRepository linkRepository;

    LinkConsumer linkConsumer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        linkConsumer = new LinkConsumer(linkRepository);
    }

    @Test
    public void messageShouldBeSerialisedAndPersisted() {
        // GIVEN
        String message = 	"{\"shortUrl\": \"dec7\",\"url\": \"eesti.ee\"}";
        Link link = JSON_SERIALISER.fromJson(message, Link.class);

        when(linkRepository.save(link)).thenReturn(Mono.just(link));

        // WHEN
        linkConsumer.listen(message);

        verify(linkRepository, times(1)).save(link);

    }

}