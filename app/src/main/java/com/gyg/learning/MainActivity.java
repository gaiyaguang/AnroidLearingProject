package com.gyg.learning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gyg.learning.canvas.CanvasActivity;
import com.gyg.learning.customview.CusViewActivity;
import com.gyg.learning.xmlpullparser.XmlPullParserActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView= (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }

    //listview item点击监听
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0://XmlPullParser解析xml
                    startActivity(new Intent(MainActivity.this, XmlPullParserActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, CusViewActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, CanvasActivity.class));
                    break;

            }
        }
    }
}
