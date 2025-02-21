package com.uestc.naldo.psm.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.NotificationItemAdapter;
import com.uestc.naldo.psm.model.Notification;
import com.uestc.naldo.psm.model.NotificationItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TrainerNotificationFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshNotification;
    private List<Notification> notificationItemList = new ArrayList<>();
    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/trainergetnotifilist";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private NotificationItemAdapter notificationItemAdapter;

    public TrainerNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getNotifiListAll();

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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "获取通知列表失败,请重试", Toast.LENGTH_SHORT).show();
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

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                notificationItemAdapter.notifyDataSetChanged();
                                swipeRefreshNotification.setRefreshing(false);
                                if (notificationListAll.size()!=0){
                                    Toast.makeText(getActivity(), "获取通知列表成功", Toast.LENGTH_SHORT).show();
                                }
                                if (notificationListAll.size()==0){
                                    Toast.makeText(getActivity(), "通知列表为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });

            }
        }).start();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trainer_notification, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar_owner_pet = (Toolbar) getActivity().findViewById(R.id.toolbar_trainer_notification);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_owner_pet);

        RecyclerView recyclerView_NotifiItemList = (RecyclerView) getActivity().findViewById(R.id.recycler_view_notification);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView_NotifiItemList.setLayoutManager(layoutManager);
        notificationItemAdapter = new NotificationItemAdapter(notificationItemList);
        recyclerView_NotifiItemList.setAdapter(notificationItemAdapter);

        swipeRefreshNotification = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_notification);
        swipeRefreshNotification.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshNotification.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getNotifiListAll();
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_trainer_notification, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:{
                //Refresh Notification
                getNotifiListAll();

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
