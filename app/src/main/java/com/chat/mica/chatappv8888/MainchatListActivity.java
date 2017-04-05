package com.chat.mica.chatappv8888;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.collect.ImmutableMap;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainchatListActivity extends AppCompatActivity {


    PNConfiguration  cnfg;

    ScheduledExecutorService mScheduleTaskExecutor;

    ArrayList userlist =new ArrayList();

    PubNub obj;
    String name;
    ListView lw;

    MultiPnCallback  multiPnCallback =new MultiPnCallback();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainchat_list);
        lw= (ListView) findViewById(R.id.listView);
        name =getIntent().getStringExtra("username");

        initpubnub();
        initChannels();
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent objint  =new  Intent(MainchatListActivity.this,ChatAreaActivity.class);

                startActivity(objint);
            }
        });
    }

    private void initChannels() {

        //Subscribe
        obj.subscribe().channels(Arrays.asList("MainChat")).withPresence().execute();
        //check presence  wheather   online  or  offline

        obj.hereNow().channels(Arrays.asList("MainChat")).async(new PNCallback<PNHereNowResult>() {
            @Override
            public void onResponse(PNHereNowResult result, PNStatus status) {

                for (Map.Entry<String, PNHereNowChannelData> entry : result.getChannels().entrySet()) {
                    for (PNHereNowOccupantData occupant : entry.getValue().getOccupants()) {
                        //MainActivity.this.mPresence.add(new PresencePojo(occupant.getUuid(), "join", DateTimeUtil.getTimeStampUtc()));

                     userlist.add(occupant.getUuid());

                    }
                }
                ArrayAdapter adapter =new ArrayAdapter(MainchatListActivity.this,android.R.layout.simple_list_item_1,userlist);
                lw.setAdapter(adapter);

            }
        });





       /* mScheduleTaskExecutor = Executors.newScheduledThreadPool(1);
        mScheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      String toChannel="MainChat";
                      final Map<String, String> message = ImmutableMap.<String, String>of("sender", "abc", "message", "test", "timestamp", "3-1-2017");
                     obj.publish().channel(toChannel).message(message)
                             .async(new PNCallback<PNPublishResult>() {
                                 @Override
                                 public void onResponse(PNPublishResult result, PNStatus status) {

                                     Toast.makeText(MainchatListActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                                 }
                             });

                  }
              });
            }
        },0,15, TimeUnit.SECONDS);*/


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
