package com.sasmobile.tutorial;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sasmobile.R;
import com.sasmobile.productservices.Product;

import me.relex.circleindicator.CircleIndicator;

/**
 * CircleIndicator library is
 * Copyright (C) 2014 relex
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * source GIT URL : https://github.com/ongakuer/CircleIndicator
 */
public class TutorialFragment extends Fragment {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String ARG_PARAM1 = "product";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Button leftText;
    private Button rightText;
    private Button skipTutorial;


    static int[] tutorialImages = {
            R.drawable.tutorial1,
            R.drawable.tutorial2,
            R.drawable.tutorial3
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_tutorial, container, false);
        // To hide skip button
        view.findViewById(R.id.skip_tutorial).setVisibility(View.GONE);

        leftText = (Button) view.findViewById(R.id.left_text);
        rightText = (Button) view.findViewById(R.id.right_text);
        skipTutorial = (Button) view.findViewById(R.id.skip_tutorial);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        indicator.setViewPager(mViewPager);

        mViewPager.setOnPageChangeListener(pageListener());
        skipTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish();
                //startActivity(new Intent(TutorialFragment.this, MainActivity.class));
            }
        });

        rightText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (rightText.getText().toString().equals(getString(R.string.tutorial_finish))) {
                    //SharedPreferences myPrefs = getActivity().getSharedPreferences("sasPrefs", MODE_PRIVATE);
                    // SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    // prefsEditor.putBoolean(SasConstants.IS_TUTORIAL_VISITED, true);
                    // prefsEditor.commit();
                    // finish();
                    // startActivity(new Intent(TutorialFragment.this, MainActivity.class));
                } else {
                    if (mViewPager.getCurrentItem() != mViewPager.getChildCount()) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                    } else {
                        mViewPager.setCurrentItem(0, true);
                    }
                }
            }
        });


        leftText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (mViewPager.getCurrentItem() != 0) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
                } else {
                    mViewPager.setCurrentItem(mViewPager.getChildCount(), true);
                }

            }
        });


        // code for adding page indicator in viewpager.
        //ViewPager viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        //CircleIndicator indicator = (CircleIndicator)  findViewById(R.id.indicator);
        //viewpager.setAdapter(mPageAdapter);
        //indicator.setViewPager(viewpager);

        return view;
    }


    // TODO: Rename and change types and number of parameters
    public static TutorialFragment newInstance(Product product) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, product);

        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    private ViewPager.OnPageChangeListener pageListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                switch (position) {
                    case 0:
                        leftText.setVisibility(View.GONE);
                        break;
                    case 1:
                        leftText.setVisibility(View.VISIBLE);
                        rightText.setText(getString(R.string.tutorial_next));
                        break;
                    case 2:
                        rightText.setText(getString(R.string.tutorial_finish));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            int pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            //textView.setText(getString(R.string.section_format, pageNumber));
            ImageView tutorialImage = (ImageView) rootView.findViewById(R.id.tutorial_image);
            tutorialImage.setImageResource(tutorialImages[pageNumber - 1]);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return tutorialImages.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
