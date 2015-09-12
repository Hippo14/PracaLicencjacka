package wmp.uksw.pl.pracalicencjacka;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import wmp.uksw.pl.pracalicencjacka.fragments.GoProFragment;
import wmp.uksw.pl.pracalicencjacka.fragments.HomeFragment;
import wmp.uksw.pl.pracalicencjacka.fragments.SettingsFragment;
import wmp.uksw.pl.pracalicencjacka.helpers.adapters.NavDrawerListAdapter;
import wmp.uksw.pl.pracalicencjacka.helpers.items.NavDrawerItem;
import wmp.uksw.pl.pracalicencjacka.templates.MyActivity;

public class MainActivity extends MyActivity {

    // Slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private DrawerLayout drawerLayout;
    private ListView listView;

    private ArrayList<NavDrawerItem> arrayList;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // Nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_items);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.list_slidermenu);

        arrayList = new ArrayList<>();

        // Adding nav drawer items to array
        arrayList.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        arrayList.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        arrayList.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(5, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        listView.setOnItemClickListener(new SlideMenuClickListener());

        // Setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getContext(), arrayList);
        listView.setAdapter(adapter);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }


    @Override
    protected Context getContext() {
        return getApplicationContext();
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new SettingsFragment();
                break;
            case 2:
                fragment = new GoProFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            listView.setItemChecked(position, true);
            listView.setSelection(position);
            setTitle(navMenuTitles[position]);
            drawerLayout.closeDrawer(listView);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

}
