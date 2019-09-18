package com.SWEProject.bringwithyou.Activites;

import android.os.Bundle;

import com.SWEProject.bringwithyou.R;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

//abstract****
public abstract class HomeProf extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_prof);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mAuth =FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this
                ,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

    }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /* mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }*/



    @Override
       public void onBackPressed(){
           DrawerLayout drawer =(DrawerLayout)findViewById(R.id.drawer_layout);
           if(drawer.isDrawerOpen(GravityCompat.START)){
               drawer.closeDrawer(GravityCompat.START);
           }else{
               super.onBackPressed();
           }

       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_prof, menu);
        return true;
    }
@Override
    public boolean onOptionsItemSelected(MenuItem item){
           int id=item.getItemId();
           if(id==R.id.action_settings){
               return true;
           }
           return super.onOptionsItemSelected(item);
     }


     @SuppressWarnings("statmentWithEmptyBody")
     @Override
     public boolean onNavigationItemSelected(MenuItem item){
           int id=item.getItemId();
           if(id==R.id.nav_home) {
           }else if (id==R.id.nav_gallery){ }
           else if (id==R.id.nav_slideshow){
           }//else if(id==R.id.nav_manage){}
           else if(id==R.id.nav_share){}
           else if(id==R.id.nav_logout){}
           DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
           drawer.closeDrawer(GravityCompat.START);
           return true;
     }
   /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
   public void updateNavHeader(){

       NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
       View headerView =navigationView.getHeaderView(0);
       TextView navUsername = headerView.findViewById(R.id.nav_username);
       TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
       ImageView navUserPhot = headerView.findViewById(R.id.nav_user_photo);

       navUserMail.setText(currentUser.getEmail());
       navUsername.setText(currentUser.getDisplayName());

       Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);


   }
}
