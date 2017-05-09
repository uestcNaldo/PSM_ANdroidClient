package com.uestc.naldo.psm.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.uestc.naldo.psm.Fragment.OwnerInfoFragment;
import com.uestc.naldo.psm.Fragment.OwnerPetFragment;
import com.uestc.naldo.psm.Fragment.OwnerSchoolFragment;
import com.uestc.naldo.psm.R;


public class OwnerMainActivity extends AppCompatActivity {

    private OwnerPetFragment mOwnerPetFragment;
    private OwnerSchoolFragment mOwnerSchoolFragment;
    private OwnerInfoFragment mOwnerInfoFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.owner_navigation_pet:
                    replaceFragment(mOwnerPetFragment);
                    return true;
                case R.id.owner_navigation_school:
                    replaceFragment(mOwnerSchoolFragment);
                    return true;
                case R.id.owner_navigation_information:
                    replaceFragment(mOwnerInfoFragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);

        initView();
        replaceFragment(mOwnerPetFragment);


    }

    private void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.owner_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mOwnerPetFragment = new OwnerPetFragment();
        mOwnerSchoolFragment = new OwnerSchoolFragment();
        mOwnerInfoFragment = new OwnerInfoFragment();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.owner_main_container,fragment);
        transaction.commit();

    }
//    private void showFragment(Fragment currentFragment, Fragment destFragment){
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        if (!destFragment.isAdded()){
//            transaction.hide(currentFragment);
//            transaction.add(R.id.owner_main_container, destFragment);
//            transaction.commit();
//
//        }
//        else{
//            transaction.hide(currentFragment);
//            transaction.show(destFragment);
//            transaction.commit();
//        }
//
//    }

}
