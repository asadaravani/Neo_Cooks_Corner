package kg.beganov.CooksCorner.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {
    @Async
    void send(String to, String email);

    String buildEmail(String name, String link);
}
