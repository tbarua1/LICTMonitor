package licttrainer.ttinnovations.lictmonitor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
public class TrainerInformation extends AppCompatActivity implements View.OnClickListener
{
    private Button buttonclick;
    private RadioGroup trainernamegroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private GetSetTrainerInfo trainerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_information);

        buttonclick= (Button) findViewById(R.id.id_buttonclick);
        trainernamegroup= (RadioGroup) findViewById(R.id.id_trainernamegroup);
        rb1= (RadioButton) findViewById(R.id.rb1);
        rb2= (RadioButton) findViewById(R.id.rb2);
        rb3= (RadioButton) findViewById(R.id.rb3);
        rb4= (RadioButton) findViewById(R.id.rb4);

        buttonclick.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String trainerName="";String s="";

    if(rb1.isChecked()){
        trainerInfo=new GetSetTrainerInfo();
        trainerInfo.setTrainername(rb1.getText().toString());
        trainerInfo.setContactnumber("+8801726242143");
        trainerInfo.setTrack("ANDROID");
        trainerInfo.setEmail("tarunrathi001@gmail.com");
        trainerInfo.setWhatsappno("+919971889554");
        trainerInfo.setSkypeid("tarunrathi122");
        trainerInfo.setUniversity("Stamford University");
    }
        if(rb2.isChecked()){
            trainerInfo.setTrainername(rb2.getText().toString());
            trainerInfo.setContactnumber("01995745367");
            trainerInfo.setTrack("ANDROID");
            trainerInfo.setEmail("tbarua1@gmail.com");
            trainerInfo.setWhatsappno("+917503227151");
            trainerInfo.setSkypeid("tarkeshwar.barua");
            trainerInfo.setUniversity("Stamford University");
        }
        if(rb3.isChecked()){
            trainerInfo.setTrainername(rb3.getText().toString());
            trainerInfo.setContactnumber("+8801765258559");
            trainerInfo.setTrack("WEB DEVELOPMENT");
            trainerInfo.setEmail("prabhatjon@gmail.com");
            trainerInfo.setWhatsappno("+918447192955");
            trainerInfo.setSkypeid("prabhatjon");
            trainerInfo.setUniversity("Stamford University");
        }
        if(rb4.isChecked()){
            trainerInfo.setTrainername(rb4.getText().toString());
        }

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            s = objectMapper.writeValueAsString(trainerInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        intent.putExtra("json", s);
        startActivity(intent);
    }
}










