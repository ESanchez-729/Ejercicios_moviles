package com.example.ejercicio_sms;

import static com.example.ejercicio_sms.MainActivity.texto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MySMSReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

            for(SmsMessage mensaje : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {

                MainActivity.texto.setText(mensaje.getDisplayMessageBody());
            }
        }

    }
}
