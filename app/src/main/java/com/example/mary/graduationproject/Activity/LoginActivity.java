package com.example.mary.graduationproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mary.graduationproject.MainActivity;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.View.CustomDialog;
import com.example.mary.graduationproject.bean.MyUser;
import com.example.mary.graduationproject.utils.ShareUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //注册按钮
    private Button btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;

    private CustomDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        dialog = new CustomDialog(this,300,300,R.layout.dialog_loding,R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);

        //屏幕外点击无效
        dialog.setCanceledOnTouchOutside(false);

        //设置选中的状态
        boolean isCheck = ShareUtils.getBoolean(this,"keepPass",false);
        keep_password.setChecked(isCheck);
        if(isCheck){
            //设置密码
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_password.setText(ShareUtils.getString(this,"password",""));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_registered:
                Toast.makeText(this, "已点击注册按钮", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.btnLogin:
                //获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //判断是否为空
                if(!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)){
                    dialog.show();
                    //登录
                    final MyUser user = new MyUser();
                    if (user.Username.equals(name) && user.Password.equals(password)){
                        dialog.dismiss();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

//                    user.login(new SaveListener<MyUser>() {
//                        @Override
//                        public void done(MyUser myUser, BmobException e) {
//                            dialog.dismiss();
//                            //判断结果
//                            if(e == null){
//                                //判断邮箱是否验证
//                                if(user.getEmailVerified()){
//                                    //跳转
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                    finish();
//                                }else{
//                                    Toast.makeText(LoginActivity.this,"请前往邮箱验证"+e.toString(),Toast.LENGTH_SHORT).show();
//                                }
//                            }else{
//                                Toast.makeText(LoginActivity.this,"登录失败"+e.toString(),Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                }else{
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //假设我现在输入用户名和密码，但是我不点击登录，而是直接退出了
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this,"keepPass",keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()) {
            //记住用户名和密码
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password.getText().toString().trim());
        }else{
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }
}