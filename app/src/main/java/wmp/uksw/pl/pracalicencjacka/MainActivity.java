package wmp.uksw.pl.pracalicencjacka;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import wmp.uksw.pl.pracalicencjacka.fragments.FirstFragment;
import wmp.uksw.pl.pracalicencjacka.fragments.SecondFragment;
import wmp.uksw.pl.pracalicencjacka.fragments.SmartFragmentStatePagerAdapter;
import wmp.uksw.pl.pracalicencjacka.templates.MyActivity;

public class MainActivity extends MyActivity {

    SmartFragmentStatePagerAdapter fragmentPagerAdapter;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                int pageWidth = page.getWidth();
                int pageHeight = page.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    viewPager.setAlpha(0);
                } else if(position <= 1){ // Page to the left, page centered, page to the right
                    // modify page view animations here for pages in view
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    viewPager.setAlpha(0);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Snackbar snackbar = Snackbar.make(relativeLayout, "Selected page position: " + position, Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //TODO uncomment if you want action bar menu
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static class MyPagerAdapter extends SmartFragmentStatePagerAdapter  {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FirstFragment.newInstance(0, "Page #1");
                case 1:
                    return FirstFragment.newInstance(1, "Page #2");
                case 2:
                    return SecondFragment.newInstance(2, "Page #3");
            }
            return null;
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public float getPageWidth(int position) {
            return 0.93f;
        }
    }

}
