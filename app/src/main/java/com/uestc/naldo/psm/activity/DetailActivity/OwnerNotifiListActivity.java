package com.uestc.naldo.psm.activity.DetailActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.NotificationItemAdapter;
import com.uestc.naldo.psm.model.Notification;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OwnerNotifiListActivity extends AppCompatActivity {

    private List<Notification> notificationItemList = new ArrayList<>();
    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/trainergetnotifilist";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private NotificationItemAdapter notificationItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_notifi_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView_NotifiItemList = (RecyclerView) findViewById(R.id.recycler_view_notification);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OwnerNotifiListActivity.this);
        recyclerView_NotifiItemList.setLayoutManager(layoutManager);
        notificationItemAdapter = new NotificationItemAdapter(notificationItemList);
        recyclerView_NotifiItemList.setAdapter(notificationItemAdapter);

        getNotifiListAll();



        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getNotifiListAll() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url(URL).build();
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MyApplication.getContext(), "获取通知列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                        final List<Notification> notificationListAll = gson.fromJson(responseData, new TypeToken<List<Notification>>(){}.getType());

                        notificationItemList.clear();
                        for (Notification notification : notificationListAll){
                            notificationItemList.add(notification);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                notificationItemAdapter.notifyDataSetChanged();

                                if (notificationListAll.size()!=0){
                                    Toast.makeText(MyApplication.getContext(), "获取通知列表成功", Toast.LENGTH_SHORT).show();
                                }
                                if (notificationListAll.size()==0){
                                    Toast.makeText(MyApplication.getContext(), "通知列表为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });

            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_trainer_notification, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
            case R.id.action_refresh:{
                getNotifiListAll();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
