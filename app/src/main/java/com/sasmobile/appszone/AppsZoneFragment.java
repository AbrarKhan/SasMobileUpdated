package com.sasmobile.appszone;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sasmobile.MainActivity;
import com.sasmobile.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductAndServicesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductAndServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppsZoneFragment extends Fragment implements AppsZoneAdapter.OnEventItemClicked {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private String[] mEventNames;
    private String[] mEventDesc;
    private ArrayList<Event> mEventsList;
    private TextView mHeaderTitle;

    public AppsZoneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductAndServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppsZoneFragment newInstance(String param1, String param2) {
        AppsZoneFragment fragment = new AppsZoneFragment();
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
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.events_list);
        mHeaderTitle = view.findViewById(R.id.header_title);
        mHeaderTitle.setText(R.string.events_list);
        mEventNames = getResources().getStringArray(R.array.event_names);
        mEventDesc = getResources().getStringArray(R.array.events_desc);
        createEventsList();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        AppsZoneAdapter productServicesAdapter = new AppsZoneAdapter(getActivity(), mEventsList);
        productServicesAdapter.setOnClick(this);
        recyclerView.setAdapter(productServicesAdapter);
        return view;
    }

    private void createEventsList() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.random_imgs);
        mEventsList = new ArrayList<Event>();

        for (int i = 0; i < mEventNames.length; i++) {
            Event event = new Event();
            event.setEventName(mEventNames[i]);
            event.setEventDesc(mEventDesc[i]);
            event.setEventImage(imgs.getResourceId(i, -1));
            mEventsList.add(event);

        }
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
        Toast.makeText(getActivity(), "event Click  " + mEventNames[position], Toast.LENGTH_SHORT).show();
        String productName = mEventNames[position];
        Fragment fragment = null;
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment = EventDescriptionFragment.newInstance(mEventsList.get(position));
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
}
