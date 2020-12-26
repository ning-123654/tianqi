package com.app.map.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.map.demo.base.BaseActivity;
import com.app.map.demo.bean.User;
import com.app.map.demo.utils.StrUtil;
import com.app.map.demo.utils.Xutils;


import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.tv_regi)
    TextView tv_regi;
    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(LoginActivity.this);
        super.onCreate(savedInstanceState);

    }

    protected void initData() {
        try {
            User user=  Xutils.initDbConfiginit().selector(User.class).where("zhuangt","=",1).findFirst();
            if(user!=null)
            {


                Intent intent=new Intent(myContext, HomeActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();



            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    protected void initListener() {

        tv_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//


                Intent intent=new Intent(myContext, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();
                if (StrUtil.isEmpty(tempPhone) || StrUtil.isEmpty(tempPwd)) {
                    Toast.makeText(myContext,"账号号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

//                startActivity(new Intent(myContext, MainActivity.class));
//                finish();
                loginhttpbendi( tempPhone , tempPwd);


            }
        });
    }



    public void loginhttpbendi(String username , String password)
    {
        try {
            User li33st= Xutils.initDbConfiginit().selector(User.class).where("username","=" ,username)
                    .and("password","=",password).findFirst();
            if(li33st!=null)
            {
                li33st.setZhuangt(1);
                Xutils.initDbConfiginit().update(li33st);
                Intent intent=new Intent(myContext, HomeActivity.class);
                intent.putExtra("user",li33st);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(myContext,"登陆失败", Toast.LENGTH_SHORT).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
