package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.awstraining.backend.business.notifyme.Sentiment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SentimentDetectorImpl implements Sentiment {

    private static final Logger LOGGER = LogManager.getLogger(SentimentDetectorImpl.class);
    

    
    // TODO: lab3
    //  1. Inject AWS AmazonComprehend from configuration ComprehendSentimentConfig.

    private final AmazonComprehend amazonComprehend;

    @Autowired
    public SentimentDetectorImpl(AmazonComprehend amazonComprehend) {
        this.amazonComprehend = amazonComprehend;
    }

    // TODO: lab3
    @Override
    public String detectSentiment(String language, String text) {
    //  1. Create detect sentiment text request.
        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest();
        detectSentimentRequest.setText(text);
        detectSentimentRequest.setLanguageCode(language);
    //  2. Call detection
        DetectSentimentResult detectSentimentResult = amazonComprehend.detectSentiment(detectSentimentRequest);
    //  3. Log information about sentiment.
        LOGGER.info("Detected sentiment: {}", detectSentimentResult.getSentiment());
    //  4. Return sentiment.
        return detectSentimentResult.getSentiment();
    }
}
