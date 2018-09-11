package com.example.shwetatripathi.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    private static final int REQ_CODE=9001;
    private Button btn_otp;
    String mob_otp;
    private static final String REGISTER_URL = "http://35.154.50.44:8080/Sms-Service/SmsService/signup";

    TextView mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mob=findViewById(R.id.mob);
        mob_otp=mob.getText().toString();
        btn_otp=findViewById(R.id.btn_otp);
       final String OTP_URL=REGISTER_URL+ "/"+mob_otp;

        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String, String> params = new HashMap();

                JSONObject parameters = new JSONObject(params);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);  //
                // prepare the Request
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, OTP_URL, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();

                                Intent  intent = new Intent(MainActivity.this, OtpActivity.class);


                                startActivity(intent);
                                finish();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();

                            }
                        }
                );

// add it to the RequestQueue
                queue.add(getRequest);




                // Adding request to request queue

                Volley.newRequestQueue(MainActivity.this).add(jsonRequest);
                Log.d("Json result","value--"+parameters.toString());
                //  Log.d("Json result","Request---"+jsonObjReq.toString());

            }
        });
    }

}
