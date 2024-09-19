package com.awstraining.backend.business.notifyme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyMeService {

    // TODO: lab1
    //  1. Inject MessageSender.
    // TODO lab2
    //  1. Inject Translator
    // TODO lab3
    //  1. Inject sentiment detector
    
    private final MessageSender messageSender;
    private final Translator translator;
    private final Sentiment sentiment;
    
    @Autowired
    public NotifyMeService(MessageSender messageSender, Translator translator, Sentiment sentiment) {
        this.messageSender = messageSender;
        this.translator = translator;
        this.sentiment = sentiment;
    }
    
    public String notifyMe(NotifyMeDO notifyMe) {
      
        // TODO: lab1
        //  1. Send text using sender.
        //  2. Return sent message.
        // TODO: lab2
        //  1. Translate text from using translator.
        String translatedText = translator.translate(notifyMe);
        //  2. Change sending of text to "translated text" and return it.
        // TODO: lab3
        //  1. Detect sentiment of translated message.
        String sentimentValue = sentiment.detectSentiment(translatedText, notifyMe.targetLc());

        String resultText = "%s: %s".formatted(sentimentValue, translatedText);
        messageSender.send(resultText);
        //  2. Change sending of text to "setiment: translated text" and return it.
        return resultText;
    }
    
}
