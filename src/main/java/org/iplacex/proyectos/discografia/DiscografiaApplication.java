package org.iplacex.proyectos.discografia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscografiaApplication implements CommandLineRunner {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    public static void main(String[] args) {
        SpringApplication.run(DiscografiaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String safeUri = mongoUri.replaceAll(
                "://([^:]+):([^@]+)@",
                "://$1:****@"
        );

        System.out.println("========================================");
        System.out.println("MONGO URI: " + safeUri);
        System.out.println("========================================");
    }
}
