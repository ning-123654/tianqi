package com.app.map.demo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.app.map.demo.base.BaseActivity;
import com.app.map.demo.bean.WeBean;
import com.app.map.demo.http.GsonUtil;
import com.app.map.demo.http.HttpHelp;
import com.app.map.demo.http.I_failure;
import com.app.map.demo.http.I_success;

import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {




    String curCity;
    int timest;


    @BindView(R.id.tv_00)
    TextView tv_00;
    @BindView(R.id.tv_di)
    TextView tv_di;
    @BindView(R.id.tv_gao)
    TextView tv_gao;
    @BindView(R.id.tv_yi)
    TextView tv_yi;
    @BindView(R.id.wendu)
    TextView wendu;

    @BindView(R.id.tv_10)
    TextView tv_10;
    @BindView(R.id.tv_11)
    TextView tv_11;
    @BindView(R.id.tv_12)
    TextView tv_12;
    @BindView(R.id.tv_13)
    TextView tv_13;

    @BindView(R.id.tv_20)
    TextView tv_20;
    @BindView(R.id.tv_21)
    TextView tv_21;
    @BindView(R.id.tv_22)
    TextView tv_22;
    @BindView(R.id.tv_23)
    TextView tv_23;

    @BindView(R.id.tv_30)
    TextView tv_30;
    @BindView(R.id.tv_31)
    TextView tv_31;
    @BindView(R.id.tv_32)
    TextView tv_32;
    @BindView(R.id.tv_33)
    TextView tv_33;


    @BindView(R.id.tv_40)
    TextView tv_40;
    @BindView(R.id.tv_41)
    TextView tv_41;
    @BindView(R.id.tv_42)
    TextView tv_42;
    @BindView(R.id.tv_43)
    TextView tv_43;

    @BindView(R.id.tv_city)
    TextView tv_city;

    @BindView(R.id.ioc)
    ImageView ioc;



;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ButterKnife.bind(this);
        curCity=getIntent().getStringExtra("cityst");
        timest=getIntent().getIntExtra("timest",1);



        isRight();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    String http;
    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void isRight() {

        if(curCity==null||curCity.equals(""))
        {
            curCity="北京";
        }

        http = "https://api.jisuapi.com/weather/query?appkey=d36555b08d8f8a2c&city=" + curCity;

        new HttpHelp(new I_success() {
            @Override
            public void doSuccess(String t) throws JSONException {
                WeBean bean = GsonUtil.getInstance().fromJson(t, WeBean.class);

                tv_00.setText(bean.getResult().getDaily().get(0).getDate()+"\n"+bean.getResult().getCity());
                tv_di.setText("最低温度"+bean.getResult().getTemplow()+" ℃");
                tv_gao.setText("最高温度"+bean.getResult().getTemphigh()+" ℃");
                wendu.setText(bean.getResult().getTemp()+" ℃    "+bean.getResult().getWeather());
                tv_yi.setText(bean.getResult().getWinddirect()+" "+bean.getResult().getWindpower());
                ioc.setImageResource(R.drawable.taiyang);


                if(bean.getResult().getWeather().contains("多云"))
                {
                    ioc.setImageResource(R.drawable.duoyun);

                }
                if(bean.getResult().getWeather().contains("阴"))
                {
                    ioc.setImageResource(R.drawable.duoyun);

                }
                if(bean.getResult().getWeather().contains("雨"))
                {
                    ioc.setImageResource(R.drawable.yu);

                }

                tv_10.setText(bean.getResult().getDaily().get(1).getDate()+"\n"+bean.getResult().getDaily().get(1).getWeek());
                tv_11.setText(bean.getResult().getDaily().get(1).getDay().getWeather());
                tv_12.setText(bean.getResult().getDaily().get(1).getDay().getWinddirect());
                tv_13.setText(bean.getResult().getDaily().get(1).getDay().getTemphigh()+" ℃");

                tv_20.setText(bean.getResult().getDaily().get(2).getDate()+"\n"+bean.getResult().getDaily().get(2).getWeek());
                tv_21.setText(bean.getResult().getDaily().get(2).getDay().getWeather());
                tv_22.setText(bean.getResult().getDaily().get(2).getDay().getWinddirect());
                tv_23.setText(bean.getResult().getDaily().get(2).getDay().getTemphigh()+" ℃");

                tv_30.setText(bean.getResult().getDaily().get(3).getDate()+"\n"+bean.getResult().getDaily().get(3).getWeek());
                tv_31.setText(bean.getResult().getDaily().get(3).getDay().getWeather());
                tv_32.setText(bean.getResult().getDaily().get(3).getDay().getWinddirect());
                tv_33.setText(bean.getResult().getDaily().get(3).getDay().getTemphigh()+" ℃");

                tv_40.setText(bean.getResult().getDaily().get(4).getDate()+"\n"+bean.getResult().getDaily().get(4).getWeek());
                tv_41.setText(bean.getResult().getDaily().get(4).getDay().getWeather());
                tv_42.setText(bean.getResult().getDaily().get(4).getDay().getWinddirect());
                tv_43.setText(bean.getResult().getDaily().get(4).getDay().getTemphigh()+" ℃");

            }
        }, new I_failure() {
            @Override
            public void doFailure() {

            }
        }, MainActivity.this, http).getHttp2();
    }







}
