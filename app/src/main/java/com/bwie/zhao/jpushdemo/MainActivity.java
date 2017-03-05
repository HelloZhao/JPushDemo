package com.bwie.zhao.jpushdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyReceiver.OnRefresh{

    private ListView mListView;
    private List<String> strList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        MyReceiver.flush(this);
    }

    @Override
    public void onFlush(String content) {
        strList.add(content);
        mListView.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,strList));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyReceiver.remove(this);
    }
}
