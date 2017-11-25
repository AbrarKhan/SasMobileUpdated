package com.sasmobile;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sasmobile.about.AboutUsFragment;
import com.sasmobile.aboutapp.AboutAppFragment;
import com.sasmobile.achievements.AchievementsFragment;
import com.sasmobile.announcements.AnnouncementsFragment;
import com.sasmobile.appszone.AppsZoneFragment;
import com.sasmobile.appszone.EventDescriptionFragment;
import com.sasmobile.buy.BuyFragment;
import com.sasmobile.contactus.ContactUsFragment;
import com.sasmobile.gallery.GalleryFragment;
import com.sasmobile.galleryupdated.GalleryUpdatedFragment;
import com.sasmobile.galleryupdated.ImageFragment;
import com.sasmobile.galleryupdated.VideoFragment;
import com.sasmobile.home.HomeFragment;
import com.sasmobile.productservices.ProductAndServicesFragment;
import com.sasmobile.productservices.ProductDescriptionFragment;
import com.sasmobile.services.ServicesFragment;

public class MainActivity extends SASBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener,
        AchievementsFragment.OnFragmentInteractionListener, ProductAndServicesFragment.OnFragmentInteractionListener,
        AboutUsFragment.OnFragmentInteractionListener, BuyFragment.OnFragmentInteractionListener,
        AnnouncementsFragment.OnFragmentInteractionListener, ContactUsFragment.OnFragmentInteractionListener,
        AppsZoneFragment.OnFragmentInteractionListener, GalleryFragment.OnFragmentInteractionListener,
        ProductDescriptionFragment.OnFragmentInteractionListener, EventDescriptionFragment.OnFragmentInteractionListener,
        AboutAppFragment.OnFragmentInteractionListener, GalleryUpdatedFragment.OnFragmentInteractionListener,
        ImageFragment.OnFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener,
        ServicesFragment.OnFragmentInteractionListener {

    public Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, new HomeFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        //((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();

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
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Toast.makeText(this, "back", Toast.LENGTH_LONG).show();
            onBackPressed();
            return true;
        }
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

        Fragment fragment = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


        switch (id) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_gallery:
                fragment = new GalleryUpdatedFragment();
                break;
            case R.id.nav_contact:
                fragment = new ContactUsFragment();
                break;
            case R.id.nav_about_us:
                fragment = new AboutUsFragment();
                break;
            case R.id.nav_about_app:
                fragment = new AboutAppFragment();
                break;
            default:
                fragment = new HomeFragment();

        }

        if (fragment != null) {
            ft.replace(R.id.main_container, fragment);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void onFragmentBackPressed(View view) {
        Toast.makeText(this, "back", Toast.LENGTH_LONG).show();
        // getSupportFragmentManager().popBackStack();
        onBackPressed();
    }
}
