package com.example.texstrelra.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.texstrelra.R;
import com.example.texstrelra.databinding.ActivityAuthBinding;
import com.example.texstrelra.databinding.FragmentFirstBinding;
import com.example.texstrelra.utils.server.CryptUtils;
import com.example.texstrelra.utils.server.GetQuestions;
import com.example.texstrelra.utils.server.HttpUtils;

import org.json.JSONException;

import java.io.IOException;

public class AuthActivity extends AppCompatActivity {
    private EditText getlogin, getpassword;
    private View auth_inc;
    private String login, password;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        auth_inc= findViewById(R.id.inc_id);

        getlogin = auth_inc.findViewById(R.id.enter_login);
        getpassword = auth_inc.findViewById(R.id.enter_password);
        btn = auth_inc.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new Thread("ScraitHttpThread") {
//                    @Override
//                    public void run() {
//                        login = getlogin.getText().toString();
//                        password = getlogin.getText().toString();
//                        Log.i("бубуб", login + " " + password);
//                        Log.i("mytag", "we in run method");
//                        try {
//                            Log.i("checkaaaaaapon", String.valueOf(HttpUtils.auth(login,password)));
//                            Log.i("mytag", "we in run method");
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }.start();

                GetQuestions getQuestions = new GetQuestions(AuthActivity.this, 28);
                getQuestions.execute();
            }


        });



    }

}
