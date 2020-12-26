package com.app.map.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;
    @ViewInject(R.id.et_pwd2)
    EditText et_pwd2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(RegisterActivity.this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
//        try {
//            User user=  db.selector(User.class).findFirst();
//            if(user!=null)
//            {
//                Intent intent=new Intent(myContext, MainActivity.class);
//                intent.putExtra("user",user);
//                startActivity(intent);
//                finish();
//            }
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void initListener() {

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();

                if (StrUtil.isEmpty(et_phone.getText().toString())) {
                    Toast.makeText(myContext,"账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (StrUtil.isEmpty(et_pwd.getText().toString())) {
                    Toast.makeText(myContext,"请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (StrUtil.isEmpty(et_pwd2.getText().toString())) {
                    Toast.makeText(myContext,"请再次输入密码", Toast.LENGTH_SHORT).show();

                    return;
                }


                if (!et_pwd.getText().toString().equals(et_pwd2.getText().toString())) {
                    Toast.makeText(myContext,"两次密码不一致", Toast.LENGTH_SHORT).show();

                    return;
                }

                User user=new User();
                user.setUsername(et_phone.getText().toString());
                user.setPassword(et_pwd .getText().toString());

                loginhttpbend(user);


            }
        });
    }

    public void loginhttpbend(User user)
    {
        try {
            Xutils.initDbConfiginit().save(user);
        } catch (DbException e) {
            e.printStackTrace();
        }

        Intent intent=new Intent(myContext, LoginActivity.class);
        startActivity(intent);

        finish();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
