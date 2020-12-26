package com.app.map.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.app.map.demo.bean.User;
import com.app.map.demo.utils.StrUtil;
import com.app.map.demo.utils.Xutils;

import org.xutils.ex.DbException;

public class HomeActivity extends AppCompatActivity {

    String[] datas = {"上海","北京","广州","苏州","大连","重庆","杭州","无锡","天津","佛山","宁波","南京","成都","东莞","武汉","青岛","沈阳","烟台","唐山","济南","哈尔滨","石家庄","郑州","泉州","温州","长沙","南通","长春","潍坊","绍兴","福州","淄博","大庆","常州","台州","济宁","东营","西安","徐州",
            "临沂","威海","嘉兴","邯郸","洛阳","沧州","金华","昆明"," 南阳","保定"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView gradview=findViewById(R.id.gradview);

        gradview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                intent.putExtra("cityst",datas[position]);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,datas);
        gradview.setAdapter(adapter);



        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    User user=  Xutils.initDbConfiginit().selector(User.class).where("zhuangt","=",1).findFirst();

                    user.setZhuangt(0);
                    Xutils.initDbConfiginit().update(user);
                } catch (DbException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

    }
}
