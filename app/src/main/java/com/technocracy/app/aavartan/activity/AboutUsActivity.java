package com.technocracy.app.aavartan.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.Event.View.EventActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.View.GalleryActivity;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  //      toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("About Us");
//        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
  //      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
    //            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      //  drawer.setDrawerListener(toggle);
        //toggle.syncState();
       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Login) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            boolean userLoggedIn = sessionManager.isLoggedIn();
            if (userLoggedIn) {
                Intent intent = new Intent(AboutUsActivity.this, UserActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
            } else {
                Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);



                 }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(this, NotificationsActivity.class);
            this.startActivity(intent);
            overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
        }
        return false;
    }

    /*@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_home:
                finish();
                break;
            case R.id.nav_attractions:
                intent = new Intent(this, AttractionActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_gallery:
                intent = new Intent(this, GalleryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_fun_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected_set", "fun");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_managerial_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "manager");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_robotics:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "robo");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_technical_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "tech");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_vigyaan:
                intent = new Intent(this, VigyaanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_sponsors:
                intent = new Intent(this, SponsorsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_initiatives:
                intent = new Intent(this, InitiativesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_schedule:
                intent = new Intent(this, ScheduleActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_about_us:
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public void onBackPressed() {
       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // if (drawer.isDrawerOpen(GravityCompat.START)) {
         //   drawer.closeDrawer(GravityCompat.START);
        //} else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
    }
