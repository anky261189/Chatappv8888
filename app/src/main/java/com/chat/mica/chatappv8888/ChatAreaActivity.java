package com.chat.mica.chatappv8888;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.collect.ImmutableMap;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ChatAreaActivity extends AppCompatActivity {

    PNConfiguration  cnfg;
    PubNub obj;
    ImageView img;
    View footview;

    CustomchatAdapter customchatAdapter;
    ListView lw;

    ArrayList usermsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_area);
        lw= (ListView) findViewById(R.id.listView2);
        usermsg=new ArrayList();
        customchatAdapter=new CustomchatAdapter(ChatAreaActivity.this,usermsg);
        lw.setAdapter(customchatAdapter);
         footview=findViewById(R.id.footerview);
        img= (ImageView) footview.findViewById(R.id.imgmsgsend);


        initpubnub();


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publish();
            }
        });

      obj.subscribe().channels(Arrays.asList("MainChat")).execute();
        obj.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {

            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {

                Log.d("demo",message.getMessage().toString());

               usermsg.add(message.getMessage().toString());

               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       customchatAdapter.notifyDataSetChanged();
                   }
               });
                //Toast.makeText(ChatAreaActivity.this, "test", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
    }

    private void publish() {

        final EditText mMessage = (EditText) footview.findViewById(R.id.edtmessage);

        final Map<String, String> message = ImmutableMap.<String, String>of("sender", "Girish", "message", mMessage.getText().toString(), "timestamp", "4/3/2017");

        obj.publish().channel("MainChat").message(message).async(
                new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        try {
                            if (!status.isError()) {
                                mMessage.setText("");
                               // Log.v(TAG, "publish(" + JsonUtil.asJson(result) + ")");
                            } else {
                               // Log.v(TAG, "publishErr(" + JsonUtil.asJson(status) + ")");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

    private void initpubnub() {
        cnfg =new PNConfiguration();
        cnfg.setPublishKey("pub-c-19b265c6-d687-4718-84c6-a1e7ee5b78eb");
        cnfg.setSubscribeKey("sub-c-899baf4a-184e-11e7-894d-0619f8945a4f");
        cnfg.setUuid("Girish");
        obj =new PubNub(cnfg);
        //subscribe


    }


}