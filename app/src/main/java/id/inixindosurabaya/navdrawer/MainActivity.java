package id.inixindosurabaya.navdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    // inisialisasi komponen
    DrawerLayout drawer;
    NavigationView navigationView;
    FrameLayout frameLayout;
    Toolbar toolbar;
    ImageView imageView;
    View header;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mengenali komponen
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.frame);
        navigationView = findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        imageView = header.findViewById(R.id.imageView);

        // event handling
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        // membuat tampilan yg pertama kali muncul
        loadFragment(new GalleryFragment());

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // mendeteksi semua menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav_gallery) {
                    loadFragment(new GalleryFragment());
                } else if (id == R.id.nav_share) {
                    loadFragment(new ShareFragment());
                } else if (id == R.id.nav_send) {
                    loadFragment(new SendFragment());
                } else if (id == R.id.nav_setting) {
                    loadFragment(new SettingFragment());
                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
