package com.example.e_kethi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class pricepriduction extends AppCompatActivity {
    Spinner spn;
    Button predictbtn;
    TextView txtprice;
    String link = "http://192.168.43.238:2000/test";
  //  private String upload_URL = "http://192.168.0.116/user_module/uploads.php";
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricepriduction);
        spn = findViewById(R.id.month);
        predictbtn = findViewById(R.id.predict);
        txtprice = findViewById(R.id.price);
        predictbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String month = spn.getSelectedItem().toString();
                Log.d("month",month);
                predict(month);

            }
        });

    }
    private void predict( String month){
        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("data", month);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = jsonObject.toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VolleyABC", "got response " + response);
                String out="Rs "+response.toString()+"/kg";
                txtprice.setText(out);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                try {
                    Log.i("VolleyABC", error.toString());
                    Log.i("VolleyABC", Integer.toString(error.networkResponse.statusCode));
                    Toast.makeText(pricepriduction.this, "", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                } catch (Exception e) {
                    Log.i("VolleyABC", e.toString());
                    Toast.makeText(pricepriduction.this, "Check Network", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            //sending JSONObject String to server starts
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        //sending JSONObject String to server ends
        int socketTimeout = 80000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest); // get response from server

    }

}




