package com.example;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import groovy.util.logging.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Flux<String> flux = Flux.just("one", "two", "three");
        Flux<String> newFlux = flux.map(String::toUpperCase).filter(s -> s.startsWith(s)).delayElements(Duration.ofSeconds(1));
        newFlux.subscribe(System.out::println);
    }
}