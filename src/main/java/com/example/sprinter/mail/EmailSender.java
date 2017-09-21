package com.example.sprinter.mail;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
