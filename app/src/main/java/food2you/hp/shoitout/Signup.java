package food2you.hp.shoitout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Signup extends Activity {

    private final Activity activity = Signup.this;

    private NestedScrollView nestedScrollView;
    RelativeLayout layout;
    RequestQueue queue;

    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;
    private EditText textInputEditTextAddress;
    private EditText textInputEditTextPhone;
    private Button Register;

    LinearLayout signup;
    String Flag,flag2;
    SharedPreferences sharedPreferences;
    String username,email,password,confirmpassword,address,phone;
    JSONObject obj = null;
    JSONObject json=null;
    String token = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        queue = Volley.newRequestQueue(Signup.this);
        textInputEditTextName = (EditText) findViewById(R.id.snpFull_name);
        signup = (LinearLayout) findViewById(R.id.signup_layout);
        textInputEditTextEmail = (EditText) findViewById(R.id.snpEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.snppassword);
        textInputEditTextConfirmPassword = (EditText) findViewById(R.id.snpconfirm_password);
    sharedPreferences = getSharedPreferences("Authtoken", MODE_PRIVATE);

        Register = (Button) findViewById(R.id.btnSignUp);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = String.valueOf(textInputEditTextName.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                confirmpassword = String.valueOf(textInputEditTextConfirmPassword.getText());

                email = String.valueOf(textInputEditTextEmail.getText());


                if (username.length()  == 0 || password.length()  == 0 ||confirmpassword.length()  == 0 || email.length()  == 0){
                    Snackbar snackbar = Snackbar
                            .make(signup, "Please fill all fields.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {

                    if(password.matches(confirmpassword)) {
                        try {

                            if(confirmpassword.length()<6)
                            {
                                Snackbar snackbar = Snackbar
                                        .make(signup, "Minimum 6 character or digit  for password!", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }

                            else {
                                PostRequestProduct();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else
                    {
                        Snackbar snackbar = Snackbar
                                .make(signup, "Password Mismatch!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }


            }
        });


    }


    private void PostRequestProduct() throws JSONException {
        String URL ="http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/users/create_account";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();



        //removed views initialization from here.
//you need to initialize views in oncreate() / oncreateView() method.
/*first create json object in here.
then set keys as per required body format with values
*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        final String requestBody = jsonObject.toString();


        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        try {
                            obj = new JSONObject(ServerResponse);

                           // JSONObject ResponseObject = new JSONObject(Response);
                            token= obj.getJSONObject("data").getString("token");
                             json= obj.getJSONObject("data");

                            Log.d("LogUpResponse:",json.toString());

                            Flag=obj.getString("msg");
                            flag2=obj.getString("success");

                        // Hiding the progress dialog after all task complete.

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(flag2.matches("true"))
                        {
                            AlertDialog.Builder  networkAlert = new AlertDialog.Builder(Signup.this);
                            networkAlert.setTitle("ShoutOut");
                            networkAlert.setMessage(Flag);
                            networkAlert .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    SharedPreferences.Editor editor = sharedPreferences.edit();





                                    editor.putString("token", token);
                                    editor.apply();
                                    // continue with delete
                                    Intent intent = new Intent(Signup.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            networkAlert.show();
                        }


                        else
                        {
                            AlertDialog.Builder  networkAlert = new AlertDialog.Builder(Signup.this);
                            networkAlert.setTitle("ShoutOut");
                            networkAlert.setMessage(Flag);
                            networkAlert .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                }
                            });
                            networkAlert.show();
                        }
                        pDialog.dismiss();
                        AlertDialog.Builder  networkAlert = new AlertDialog.Builder(Signup.this);
                        networkAlert.setTitle("ShoutOut");
                        networkAlert.setMessage(Flag);
                        networkAlert .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                SharedPreferences.Editor editor = sharedPreferences.edit();



                                editor.putString("token", token);
                                editor.apply();
                                // continue with delete
                                Intent intent = new Intent(Signup.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        networkAlert.show();
                        // Showing response message coming from server.

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        AlertDialog.Builder  networkAlert = new AlertDialog.Builder(Signup.this);
                        networkAlert.setTitle("ShoutOut");
                        networkAlert.setMessage("Email or username already exist!");
                        networkAlert .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                            }
                        });
                        networkAlert.show();

                        // Hiding the progress dialog after all task complete.
                        pDialog.dismiss();
                        volleyError.printStackTrace();
                        // Showing error message if something goes wrong.
                        Toast.makeText(Signup.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");

                return params;
            }

        };

        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        queue.add(stringRequest);


    }



}
