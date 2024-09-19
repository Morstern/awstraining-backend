package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.awstraining.backend.business.notifyme.NotifyMeDO;
import com.awstraining.backend.business.notifyme.Translator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslatorImpl implements Translator {

    private static final Logger LOGGER = LogManager.getLogger(TranslatorImpl.class);
    

    
    // TODO: lab2
    //  1. Inject AWS AmazonTranslate from configuration TranslatorConfig.

    private final AmazonTranslate amazonTranslate;

    @Autowired
    public TranslatorImpl(AmazonTranslate amazonTranslate) {
        this.amazonTranslate = amazonTranslate;
    }
    
    @Override
    public String translate(NotifyMeDO notifyMeDO) {
        // TODO: lab2
        //  1. Create translate text request.
        TranslateTextRequest translateTextRequest = new TranslateTextRequest();
        translateTextRequest.setText(notifyMeDO.text());
        translateTextRequest.setSourceLanguageCode(notifyMeDO.sourceLc());
        translateTextRequest.setTargetLanguageCode(notifyMeDO.targetLc());
        //  2. Call translate.
        TranslateTextResult translateTextResult = amazonTranslate.translateText(translateTextRequest);
        //  3. Log information about successful translated message.
        LOGGER.info("Successfully translated text: " + translateTextResult.getTranslatedText());
        //  4. Return translated message
        return translateTextResult.getTranslatedText();
    }
}
