package com.chat.mica.chatappv8888;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mica on 4/5/2017.
 */
public class CustomchatAdapter extends BaseAdapter {

    ChatAreaActivity gact;
    TextView txtmsg;
    ArrayList usermessage;

    CustomchatAdapter(ChatAreaActivity act,ArrayList message)
    {
        gact=act;
        usermessage=message;
    }
    @Override


    public int getCount() {
        return usermessage.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        view =gact.getLayoutInflater().inflate(R.layout.chatdisplay,null);

        txtmsg= (TextView) view.findViewById(R.id.textView);
        txtmsg.setText(usermessage.get(i).toString());
        return view;
    }
}
