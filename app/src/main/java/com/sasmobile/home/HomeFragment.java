package com.sasmobile.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sasmobile.R;
import com.sasmobile.achievements.AchievementsFragment;
import com.sasmobile.announcements.AnnouncementsFragment;
import com.sasmobile.appszone.AppsZoneFragment;
import com.sasmobile.services.ServicesFragment;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <p>
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

public class HomeFragment extends Fragment implements HomeAdapter.OnItemClicked {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String[] mHomeItems;// = getResources().getStringArray(R.array.home_elements);

    private OnFragmentInteractionListener mListener;
    private Handler mPageHandler;
    private int mDelayPageTimer = 3000; //milliseconds

    private int mPageNumber;

    private static final int NO_OF_COLUMNS = 2;

    static int[] tutorialImages = {
            R.drawable.home_page_image,
            R.drawable.home_page_image,
            R.drawable.home_page_image
    };
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Runnable runnable;
    private int[] mMip;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.home_view_pager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);

        // mViewPager.setOffscreenPageLimit(3);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        indicator.setViewPager(mViewPager);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_items_list);
        mHomeItems = getResources().getStringArray(R.array.home_elements);
        //make it global and start with m,m for member variable
        mMip = new int[]{R.drawable.services_ic,
                R.drawable.apps_zone_ic,
                R.drawable.achievements_ic,
                R.drawable.announcements_ic
        };

        //If you use these below line of code then recycler view will be displayed like GridView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), NO_OF_COLUMNS); //no of columns = 3

        recyclerView.setLayoutManager(gridLayoutManager);

        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), mHomeItems, mMip);
        homeAdapter.setOnClick(this);
        recyclerView.setAdapter(homeAdapter);

        mPageHandler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (mSectionsPagerAdapter.getCount() == mPageNumber) {
                    mPageNumber = 0;
                } else {
                    mPageNumber++;
                }
                mViewPager.setCurrentItem(mPageNumber, true);
                mPageHandler.postDelayed(this, mDelayPageTimer);
            }
        };


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPageHandler.postDelayed(runnable, mDelayPageTimer);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPageHandler.removeCallbacks(runnable);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("HomeFragment", "Homedapter.setOnclick");

        Toast.makeText(getActivity(), "Recycle Click  " + mHomeItems[position], Toast.LENGTH_SHORT).show();
        Fragment fragment = null;
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                fragment = new ServicesFragment();
                break;
            case 1:
                fragment = new AppsZoneFragment();
                break;
            case 2:
                fragment = new AchievementsFragment();
                break;
            case 3:
                fragment = new AnnouncementsFragment();
                break;
        }

        if (fragment != null) {
            ft.replace(R.id.main_container, fragment);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
            View rootView = inflater.inflate(R.layout.home_image_pager, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            int pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            //textView.setText(getString(R.string.section_format, pageNumber));
            ImageView tutorialImage = (ImageView) rootView.findViewById(R.id.tutorial_image);
            tutorialImage.setImageResource(tutorialImages[pageNumber - 1]);
            return rootView;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //mViewPager.removeView();
    }
}
