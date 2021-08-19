package com.koushik.codeathon;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CodeathonApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CodeathonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
