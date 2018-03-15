package com.example.example.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.example.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar               mToolbar;
    private TextView              tvToolTitle;
    private DrawerLayout          mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView        mNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolTitle = (TextView) findViewById(R.id.toolbar_title);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        tvToolTitle.setText("测试");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_clsoe);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigation = (NavigationView) findViewById(R.id.navigation);
        setupDrawerContent(mNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new RecycleFragment()).commit();

    }

    private void setupDrawerContent(NavigationView mNavigation) {
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_item1:
                        Snackbar.make(mToolbar,"item1",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item2:
                        Snackbar.make(mToolbar,"item2",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item3:
                        Snackbar.make(mToolbar,"item3",Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item4:
                        Snackbar.make(mToolbar,"item4",Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.welcome_menu_item,menu);
            return true;
        }

}
