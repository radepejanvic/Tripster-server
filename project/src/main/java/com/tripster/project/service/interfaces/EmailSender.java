package com.tripster.project.service.interfaces;

public interface EmailSender {
    void send (String to, String subject,String body);
}
