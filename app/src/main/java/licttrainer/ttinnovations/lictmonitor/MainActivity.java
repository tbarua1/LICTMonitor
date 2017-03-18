package licttrainer.ttinnovations.lictmonitor;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    private TextView textviewtrainername,universityname,batchcode;
    private Intent intent;
    private GetSetTrainerInfo trainerInfo;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private String url="http://27.147.210.136:8080/bht/UniversityBatchTrainerSchedule?university=";
    // Data From Server - [{"id":4,"date":1489765140000,"till":1489765140000,"university":"Dhaka University","trainer":"Tarkeshwar Barua","batchcode":"FS-EDEN-01","comment":null,"status":"scheduled"}]_[{"id":80,"name":"Tarkeshwar Barua","qualification":"MCA","skillset":"Android","experience":"4+ Years","natianality":"Indian","mobile":"1995745367","skype":"tarkeshwar.barua","username":null,"password":null,"emailid":null,"usertype":null,"date":null}]
    private String batch_scheduleString, trainerInfoString;
    private Trainer trainer[];
    private   BatchSchedule[] batchSchedules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
intent=getIntent();
        String data = intent.getStringExtra("data");
        String replace = data.replace(" ", "_");
        System.out.println("Data Received from Map "+ data);
        try {
            String s = new GetXMLTask().execute(url + replace).get();
            System.out.println("Data From Server - "+s);
            String[] split = s.split("_");

           batchSchedules = new ObjectMapper().readValue(split[0], BatchSchedule[].class);
            trainer = new ObjectMapper().readValue(split[1], Trainer[].class);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textviewtrainername= (TextView) findViewById(R.id.trainer_name);
        universityname= (TextView) findViewById(R.id.university_name);
        batchcode= (TextView) findViewById(R.id.batch_code);

       /* Intent intent = getIntent();
        String trainername = intent.getStringExtra("json");
       */// ObjectMapper mapper=new ObjectMapper();
     /*   try {
            trainerInfo = mapper.readValue(trainername, GetSetTrainerInfo.class);


        } catch (IOException e) {
            e.printStackTrace();
        }*/

       textviewtrainername.setText(trainer[0].getName());
        universityname.setText(batchSchedules[0].getUniversity());
        batchcode.setText(trainer[0].getSkillset());
    }

    public void makePhoneCall(View view) {
        intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+trainer[0].getMobile()));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
           Toast.makeText(getApplicationContext(),"You can Make Phone call "+checkLocationPermission(),Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"You can Make Phone call " +checkLocationPermission(),Toast.LENGTH_LONG).show();
        }
        startActivity(intent);
    }

    public void makewhatsAppCall(View view) {
        makewhatsAppMsg(trainer[0].getWhatsapp());
       /* PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }*/
    }

    public void makewhatsAppMsg(String number) {

        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }

    private boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Context compat Pass");
            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.CALL_PHONE)) {
                System.out.println("Activity compat Pass and requesting permission");
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                System.out.println("Context compat Failed");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public void makeSkype(View view) {
        Intent skypeVideo = new Intent("android.intent.action.VIEW");
        //skypeVideo.setData(Uri.parse("skype:" + "<username>" + "?call&video=true"));
        skypeVideo.setData(Uri.parse("skype:"+trainer[0].getSkype()+"?call&video=true"));
        startActivity(skypeVideo);
    }


    public void makeEmail(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{trainer[0].getEmailid()});
        i.putExtra(Intent.EXTRA_SUBJECT, "email from Monitor Team");
        i.putExtra(Intent.EXTRA_TEXT   , "Quality Test email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}








