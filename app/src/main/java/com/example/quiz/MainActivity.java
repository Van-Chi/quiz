package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.QuestionAnswer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView allcauhoiTextView;
    TextView cauhoiTextView;
    Button dapana, dapanb, dapanc, dapand;
    Button submitBtn;

    int diem=0;
    int allcauhoi = QuestionAnswer.cauhoi.length;
    int cauhoiindex = 0;
    String dapanchon = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allcauhoiTextView = findViewById(R.id.allcauhoi);
        cauhoiTextView = findViewById(R.id.cauhoi);
        dapana = findViewById(R.id.dapana);
        dapanb = findViewById(R.id.dapanb);
        dapanc = findViewById(R.id.dapanc);
        dapand = findViewById(R.id.dapand);
        submitBtn = findViewById(R.id.submit_btn);

        dapana.setOnClickListener(this);
        dapanb.setOnClickListener(this);
        dapanc.setOnClickListener(this);
        dapand.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        allcauhoiTextView.setText("Tất cả câu hỏi : "+allcauhoi);

        taicauhoi();




    }

    @Override
    public void onClick(View view) {

        dapana.setBackgroundColor(Color.WHITE);
        dapanb.setBackgroundColor(Color.WHITE);
        dapanc.setBackgroundColor(Color.WHITE);
        dapand.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(dapanchon.equals(QuestionAnswer.dapan[cauhoiindex])){
                diem++;
            }
            cauhoiindex++;
            taicauhoi();


        }else{
            //choices button clicked
            dapanchon  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void taicauhoi(){

        if(cauhoiindex == allcauhoi ){
            hoanthanh();
            return;
        }

        cauhoiTextView.setText(QuestionAnswer.cauhoi[cauhoiindex]);
        dapana.setText(QuestionAnswer.danhsachdapan[cauhoiindex][0]);
        dapanb.setText(QuestionAnswer.danhsachdapan[cauhoiindex][1]);
        dapanc.setText(QuestionAnswer.danhsachdapan[cauhoiindex][2]);
        dapand.setText(QuestionAnswer.danhsachdapan[cauhoiindex][3]);

    }

    void hoanthanh(){
        String passStatus = "";
        if(diem > allcauhoi*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ diem+" out of "+ allcauhoi)
                .setPositiveButton("Restart",(dialogInterface, i) -> choilai() )
                .setCancelable(false)
                .show();


    }

    void choilai(){
        diem = 0;
        cauhoiindex =0;
        taicauhoi();
    }

}