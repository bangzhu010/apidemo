package com.example.liangwenchao.appdemo;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.liangwenchao.appdemo.ui.animation.AnimationFragment;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;
import com.example.liangwenchao.appdemo.ui.camera.CameraFragment;
import com.example.liangwenchao.appdemo.ui.location.LocationFragment;
import com.example.liangwenchao.appdemo.ui.sensor.SensorFragment;

import java.util.List;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentInteractionListener {

    private Fragment mContent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        initView();


        switchFragment(R.id.nav_view, null, (String) navigationView.getMenu().getItem(0).getTitle());

        navigationView.setCheckedItem(R.id.nav_view);
        navigationView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });

        cheakPermission();

    }

    private void cheakPermission() {
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION
        },10);
    }


    @Override
    public void setContentView() {

    }

    public void initView() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switchFragment(id, null, (String) item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 切换 Fragment
     *
     * @param index
     * @param args
     * @param tag
     */

    public void switchFragment(int index, Bundle args, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        //隐藏显示的Fragment，防止出现重叠现象
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            for (Fragment fragment : fragmentList) {
                if (fragment.isVisible()) {
                    transaction.hide(fragment);
                }
            }
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            switch (index) {
                case R.id.nav_view:
                    fragment = new ViewFragment();
                    break;
                case R.id.nav_animation:
                    fragment = new AnimationFragment();
                    break;
                case R.id.nav_slideshow:
                    fragment = new LocationFragment();
                    break;
                case R.id.nav_manage:
                    fragment = new SensorFragment();
                    break;
                case R.id.nav_share:
                    fragment = new CameraFragment();
                    break;
                case R.id.nav_send:
                    break;
                default:
                    fragment = new ViewFragment();
            }
            transaction.replace(R.id.content, fragment, tag);
        } else {
            transaction.show(fragment);
        }

        ((BaseFragment) fragment).setArgs(args);

        transaction.commitAllowingStateLoss();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class MyAdapter extends ArrayAdapter {
        private int resourceId;

        public MyAdapter(Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
            this.resourceId = resourceId;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return super.getView(position, convertView, parent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
