package com.technocracy.app.aavartan.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.Contacts.View.ContactsActivity;
import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.Sponsors.View.SponsActivity;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.View.GalleryActivity;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM = 0;
    FloatingActionButton fab;
    private Boolean isFabOpen = true;
    private SessionManager sessionManager;
    private String currentDateString;
    private SimpleDateFormat dateFormat;
    private SQLiteHandler sqLiteHandler;
    private Intent intent;
    private String intent_name[] = {"Gallery", "Sponsors", "Contacts", "Team Android", "About Us", "Vigyaan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setTitleTextColor(Color.WHITE);
        // toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateString = dateFormat.format(Calendar.getInstance().getTime());
        sessionManager = new SessionManager(getApplicationContext());

        //new DatabaseHandler(getApplicationContext()).dropDB();

        if (sessionManager.isLoggedIn()) {
            sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
            if (user.getVerified() == 0) {
                if (getTime(user.getMember_since())) {
                    sessionManager.setLogin(false);
                    sqLiteHandler.deleteUsers();
                    new AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert).setTitle("Verification")
                            .setMessage("Please verify your email from the verification link sent to your email to continue using this account. ")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sessionManager.setLogin(false);
                                    sqLiteHandler.deleteUsers();
                                    Snackbar.make(findViewById(R.id.main_activity_layout), "You have been logged out.", Snackbar.LENGTH_LONG).show();
                                }
                            }).show();
                }
            }

        }
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        //toggle.syncState();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
        }
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        // bottomNavigationView.setItemBackgroundResource(R.color.white);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                    case R.id.btn2:
                        intent = new Intent(MainActivity.this, EventListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                    case R.id.btn3:
                        intent = new Intent(MainActivity.this, AttractionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                    case R.id.btn4:
                        intent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                    case R.id.btn5:
                        intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                }
                //   updateNavigationBarState(bottomNavigationView,item.getItemId());
                return true;
            }
        });
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Intent intent2;
                            if (index == 0) {
                                intent2 = new Intent(MainActivity.this, GalleryActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 1) {
                                intent2 = new Intent(MainActivity.this, SponsActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 2) {
                                intent2 = new Intent(MainActivity.this, ContactsActivity.class);
                                intent2.putExtra("contact_type", "1");
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 3) {
                                intent2 = new Intent(MainActivity.this, ContactsActivity.class);
                                intent2.putExtra("contact_type", "2");
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 4) {
                                intent2 = new Intent(MainActivity.this, AboutUsActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 5) {
                                intent2 = new Intent(MainActivity.this, VigyaanActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            }
                        }
                    })
                    .normalImageRes(R.drawable.ic_action_search)
                    .normalText(intent_name[i])
                    .rotateImage(true)
                    .shadowEffect(true)
                    .imagePadding(new Rect(0, 0, 0, 0))
                    .textGravity(Gravity.CENTER)
                    .rippleEffect(true);
            bmb.addBuilder(builder);
        }

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Login) {
            sessionManager = new SessionManager(getApplicationContext());
            boolean userLoggedIn = sessionManager.isLoggedIn();
            if (userLoggedIn) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
            MainActivity.this.startActivity(intent);
        }
        return false;
    }

    private boolean getTime(String notifTime) {
        try {
            Date currentDate = dateFormat.parse(currentDateString);
            Date notifDate = dateFormat.parse(notifTime);
            long differenceInMS = currentDate.getTime() - notifDate.getTime();
            long differenceInSecs = TimeUnit.MILLISECONDS.toSeconds(differenceInMS);
            long differenceInDays = TimeUnit.SECONDS.toDays(differenceInSecs);
            String time = new SimpleDateFormat("h:mm aa").format(notifDate);
            if (differenceInDays >= 1)
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}