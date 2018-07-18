package com.ihognqiqu.aac.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ihognqiqu.aac.R;

public class LiveDataActivity extends AppCompatActivity{
    
    
    private TextView tvUsername;
    private MutableLiveData<String> liveDataUsernameStr;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        tvUsername=findViewById(R.id.tv_username);
        initDatas();
    }
    
    private void initDatas(){
        liveDataUsernameStr=new MutableLiveData<>();
        liveDataUsernameStr.observe(this, new Observer<String>(){
            @Override
            public void onChanged(@Nullable String s){
                tvUsername.setText(s);
            }
        });
    }
    
    public void onClickMainThread(View view){
        liveDataUsernameStr.setValue("lixiaodaaaaMain");
    }
    
    public void onClickThread(View view){
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                liveDataUsernameStr.postValue("lixxiaodaaaaThread");
            }
        }).start();
    }
}
