package food2you.hp.shoitout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import food2you.hp.shoitout.model.event;
import food2you.hp.shoitout.model.eventresponse;
import food2you.hp.shoitout.service.APIService;
import food2you.hp.shoitout.service.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreateEvent extends AppCompatActivity  {

    public static ImageView imageview;
    private ImageView btnSelectImage;
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    ImageView camera;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS= 7;
    String token;
    JSONObject obj = null;


    SpotsDialog dailog ;
    EditText full_name,email,people,price,description;
    RequestQueue queue;
    DatePicker picker;
    Button create_event;
    LinearLayout layout;
    String Flag,flag2;
    JSONObject json=null;
    private ProgressDialog progressDialog;
    private String baseUrl;
    private APIService mAPIService;
    CognitoCachingCredentialsProvider credentialsProvider;

       Retrofit.Builder   builder = new Retrofit.Builder()
                                        .baseUrl("http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/")
                                        .addConverterFactory(GsonConverterFactory.create());
       Retrofit retrofit = builder.build();
       APIService mAPIServices = retrofit.create(APIService.class);






    String String_email,String_name,String_people,String_price,String_description,image_AWSurl,String_date;






















    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        dailog = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .build();


       // mAPIService = ApiUtils.getAPIService();
        AWSinit();
        camera=findViewById(R.id.camera);
        imageview=findViewById(R.id.image_selected);
        baseUrl = "http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/";

        queue = Volley.newRequestQueue(CreateEvent.this);
        full_name = (EditText) findViewById(R.id.snpFull_name_create_event);
        layout = (LinearLayout) findViewById(R.id.layout_create_event);
        email = (EditText) findViewById(R.id.snpEmail_create_event);
        people = (EditText) findViewById(R.id.people_create_event);
        price = (EditText) findViewById(R.id.price_create_event);
        description = (EditText) findViewById(R.id.desciption_creat_event);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        create_event=(Button) findViewById(R.id.btnCreateEvent);

        SharedPreferences sharedPreferences = getSharedPreferences("Authtoken", MODE_PRIVATE);

       token = sharedPreferences.getString("token", null);

       Log.d("tokenPassed:",token);
        create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String_name = String.valueOf(full_name.getText());
                String_description = String.valueOf(description.getText());
                String_price = String.valueOf(price.getText());
                String_people = String.valueOf(people.getText());
                String_email = String.valueOf(email.getText());



                String_date = picker.getDayOfMonth() + "-"+ picker.getMonth() + "-" + picker.getYear() ;

                if (String_name.length()  == 0 || String_description.length()  == 0 ||String_price.length()  == 0 || email.length()  == 0){
                    Snackbar snackbar = Snackbar
                            .make(layout, "name or description or price can not be empty!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                else
                {


                }



               upload();






            }
        });

        checkAndroidVersion();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });
    }


    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEvent.this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                imageview.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                imageview.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermissions();

        } else {
            // code for lollipop and pre-lollipop devices
        }

    }


    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(CreateEvent.this,
                Manifest.permission.CAMERA);
        int wtite = ContextCompat.checkSelfPermission(CreateEvent.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(CreateEvent.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(CreateEvent.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d("in fragment on request", "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("in fragment on request", "CAMERA & WRITE_EXTERNAL_STORAGE READ_EXTERNAL_STORAGE permission granted");
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d("in fragment on request", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(CreateEvent.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(CreateEvent.this, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(CreateEvent.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            showDialogOK("Camera and Storage Permission required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            Toast.makeText(CreateEvent.this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                                    .show();
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(CreateEvent.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }








    void  callfuction(String Image_AWS_URL){

        Log.d("Date",String_date);
        event ebody=new event(String_name, String_email, String_date,String_description,Image_AWS_URL,Integer.valueOf(String_people),Integer.valueOf(String_price));
      Call<eventresponse> call  =      mAPIServices.CreateEvent("application/json","application/json",token,ebody);
        call.enqueue(new Callback<eventresponse>() {
            @Override
            public void onResponse(Call<eventresponse> call, Response<eventresponse> response) {
if(response.isSuccessful()){
    Log.d("Response",response.body().getData().getFullname());
    Log.d("Response",response.body().getData().getEventdate() );
    Log.d("response:Des",response.body().getData().getDescription() + response.body().getData().getUserId() + response.body().getData().getEventbanner() );
    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();

    dailog.dismiss();

}else {
   Log.d("Response",response.body().getMsg());
    Log.d("Response",response.body().getData().getEventdate() );
    Log.d("response:Des",response.body().getData().getEventdate());
    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
    dailog.dismiss();


}

            }

            @Override
            public void onFailure(Call<eventresponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                dailog.dismiss();
            }
        });





    }










 private void AWSinit(){
     credentialsProvider = new CognitoCachingCredentialsProvider(
             getApplicationContext(),
             "ap-south-1:28937b7b-fce2-4df2-a041-4c6a81afd420", // Identity pool ID
             Regions.AP_SOUTH_1 // Region
     );
//

 }

    public void upload() {

        dailog.show();
      final  File file = new File(imgPath);
        Log.d("filepath",imgPath);
        Log.d("Filename",file.getName());

        AmazonS3 s3 = new AmazonS3Client(credentialsProvider);
        s3.setEndpoint("s3.ap-south-1.amazonaws.com");
        TransferUtility transferUtility = new TransferUtility(s3, getApplicationContext());
        final TransferObserver observer = transferUtility.upload(
                "shoitout",  //this is the bucket name on S3
                file.getName(), //this is the path and name
                file, //path to the file locally
                CannedAccessControlList.PublicRead //to make the file public
        );
        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (state.equals(TransferState.COMPLETED)) {
                    Log.d("done","Finished");
                    image_AWSurl ="https://shoitout.s3.ap-south-1.amazonaws.com/"+file.getName();
                    callfuction(image_AWSurl);




                } else if (state.equals(TransferState.FAILED)) {
                    Log.d("done","Failed");
                }

            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

            }

            @Override
            public void onError(int id, Exception ex) {
                Log.d("done",ex.toString());
            }
        });
    }











}
