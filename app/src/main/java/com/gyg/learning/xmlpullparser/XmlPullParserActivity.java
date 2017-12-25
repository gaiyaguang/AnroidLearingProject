package com.gyg.learning.xmlpullparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gyg.learning.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XmlPullParser学习使用
 */
public class XmlPullParserActivity extends AppCompatActivity {

    private ListView listview;//用于展示解析的数据
    private List<Employee> employees=new ArrayList<>();//解析出来的Employee集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_pull_parser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview= (ListView) findViewById(R.id.listview);
        parseEmployees();//解析xml数据
    }

    //XmlPullParser解析
    private void parseEmployees(){
        try {
            int eventType=-1;//标签类型
            Employee employee=null;//初始化一个Employee对象
            InputStream stream=getAssets().open("employees.xml");//打开employees.xml文件，返回一个输入流
            XmlPullParser xmlPullParser= XmlPullParserFactory.newInstance().newPullParser();//获取一个XmlPullParser对象
            xmlPullParser.setInput(stream,"utf-8");//设置XmlPullParser要解析的数据
            eventType=xmlPullParser.getEventType();
            while (eventType!= XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT://文档开始
                        break;
                    case XmlPullParser.START_TAG://标签结束
                        String tagName=xmlPullParser.getName();//标签名字
                        if (tagName!=null&&tagName.equals("employee")){//一个employee标签开始
                            employee=new Employee();
                            int id=Integer.parseInt(xmlPullParser.getAttributeValue(null,"id"));
                            employee.setId(id);
                        }
                        if (tagName!=null&&tagName.equals("name")){//一个name标签开始
                            String name=xmlPullParser.nextText();
                            employee.setName(name);
                        }
                        if (tagName!=null&&tagName.equals("age")){//一个age标签开始
                            String age=xmlPullParser.nextText();
                            employee.setAge(age);
                        }
                        if (tagName!=null&&tagName.equals("title")){//一个title标签开始
                            String title=xmlPullParser.nextText();
                            employee.setTitle(title);
                        }
                        break;
                    case XmlPullParser.END_TAG://标签结束
                        if (xmlPullParser.getName().equals("employee")){//一个employee标签结束
                            employees.add(employee);
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT://文档结束
                        break;
                    case XmlPullParser.TEXT://内容
                        break;
                }
                eventType=xmlPullParser.next();//循环下一个标签，返回标签的type
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        ArrayList<String> results=new ArrayList<>();
        //打印解析出的所有的employees
        for(Employee employee:employees){
            results.add(employee.toString());
            System.out.println(employee.toString());
        }
        listview.setAdapter(new ArrayAdapter(this,R.layout.simple_list_item_text,results));
    }
}
