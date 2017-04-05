package com.chat.mica.chatappv8888;

import android.util.Log;
import android.widget.Toast;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

/**
 * Created by Mica on 4/1/2017.
 */
public class MultiPnCallback extends SubscribeCallback {
    @Override
    public void status(PubNub pubnub, PNStatus status) {

    }

    @Override
    public void message(PubNub pubnub, PNMessageResult message) {


        //Toast.makeText(MultiPnCallback.this, "", Toast.LENGTH_SHORT).show();
        //Toast.makeText(MultiPnCallback.this, "", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void presence(PubNub pubnub, PNPresenceEventResult presence) {

    }
}
