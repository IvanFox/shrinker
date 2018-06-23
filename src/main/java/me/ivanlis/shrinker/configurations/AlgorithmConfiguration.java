package me.ivanlis.shrinker.configurations;

import me.ivanlis.shrinker.algos.CRC16;
import me.ivanlis.shrinker.algos.Shortener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgorithmConfiguration {

    @Bean
    public Shortener shortener() {
        return new CRC16();
    }

}
