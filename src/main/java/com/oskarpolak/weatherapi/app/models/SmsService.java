package com.oskarpolak.weatherapi.app.models;

import org.springframework.stereotype.Service;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.exception.SmsapiException;

@Service
public class SmsService {

    public void sendSms(String message, String number){
        try {
            OAuthClient authClient = new OAuthClient("7O5nsvMxWu2CV7BwDSKRVz8gKKFdlCCPoyVvrr6H");
            SmsFactory smsFactory = new SmsFactory(authClient);

            SMSSend action = smsFactory.actionSend()
                    .setTo(number)
                    .setText(message);

            action.execute();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }

    }

}
