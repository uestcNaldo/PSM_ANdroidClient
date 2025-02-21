package com.uestc.naldo.psm.activity.ItemActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uestc.naldo.psm.BaseActivity;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.model.CourseItem;
import com.uestc.naldo.psm.model.Trainer;
import com.uestc.naldo.psm.model.TrainerItem;

import org.w3c.dom.Text;

public class TrainerActivity extends BaseActivity {

    public static String TRAINER_ITEM_DATA = "trainer_item_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取点击事件的训练师数据
        Trainer trainerItem = (Trainer) getIntent().getSerializableExtra(TRAINER_ITEM_DATA);
        Long trainerId = trainerItem.getId();
        String trainerName = trainerItem.getName();
        String trainerSex = trainerItem.getSex();
        String trainerPosition = trainerItem.getPosition();
        Integer trainerAge = trainerItem.getAge();
        String trainerEmail = trainerItem.getEmail();
        String trainerMaxim = trainerItem.getMaxim();
        String trainerIntro = trainerItem.getIntro();


        //绑定View
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView textView_trainerName = (TextView) findViewById(R.id.text_trainer_name);
        TextView textView_trainerSex = (TextView) findViewById(R.id.text_trainer_sex);
        TextView textView_trainerPosition = (TextView) findViewById(R.id.text_trainer_position);
        TextView textView_trainerEmail = (TextView) findViewById(R.id.text_trainer_email);
        TextView textView_trainerAge = (TextView) findViewById(R.id.text_trainer_age);
        TextView textView_trainerMaxim = (TextView) findViewById(R.id.text_trainer_maxim);
        TextView textView_trainerIntro = (TextView) findViewById(R.id.text_trainer_intro);


        //设置数据
        collapsingToolbarLayout.setTitle(trainerName);
        textView_trainerName.setText(trainerName);
        textView_trainerSex.setText(trainerSex);
        textView_trainerPosition.setText(trainerPosition);
        textView_trainerEmail.setText(trainerEmail);
        textView_trainerAge.setText(String.valueOf(trainerAge));
        textView_trainerMaxim.setText(trainerMaxim);
        textView_trainerIntro.setText(trainerIntro);


        //查询数据库并绑定



        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
