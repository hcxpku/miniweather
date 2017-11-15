package com.example.hcx.myapplication.miniweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.hcx.myapplication.R;
import com.example.hcx.myapplication.app.MyApplication;
import com.example.hcx.myapplication.bean.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hcx on 2017/10/20.
 */

public class selectCity extends Activity implements View.OnClickListener{
    private ImageView mBackBtn;
    private ListView mlist;
    private SimpleAdapter myadapter;
    private List<City> cityList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        mBackBtn=(ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        initViews();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.title_back:
                Intent i=new Intent();
                i.putExtra("cityCode","101160101");
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }
    private void initViews(){
        mlist=(ListView)findViewById(R.id.title_list);
        MyApplication myApplication=(MyApplication)getApplication();
        cityList=myApplication.getCityList();
        List<Map<String,Object>>  data=new ArrayList<>();
       for(City city:cityList){

           Map<String,Object> item=new HashMap<>();
           item.put("city_name",city.getCity());
           item.put("city_number",city.getNumber());
           data.add(item);
        }
        myadapter=new SimpleAdapter(this,data, R.layout.select_city,
                new String[] {"city_name","city_number"},new int[] {R.id.city_name,R.id.city_number});

        mlist.setAdapter(myadapter);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int position,long id) {
                ListView listView=(ListView)adapterView;
                Map<String,Object> map=(Map<String, Object>) listView.getItemAtPosition(position);
                String num=(String)map.get("city_number");
                Intent i = new Intent();
                i.putExtra("cityCode",num);
                setResult(RESULT_OK, i);
                finish();
            }

        });
    }
}
