package com.sasmobile.services;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.sasmobile.MainActivity;
import com.sasmobile.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ExpandableListView expandableListView;
    private List<String> parentHeaderInformation;
    private TextView mHeaderTitle;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        mHeaderTitle = view.findViewById(R.id.header_title);
        mHeaderTitle.setText(R.string.service_header);
        parentHeaderInformation = new ArrayList<String>();
        parentHeaderInformation.add("Training Services");
        parentHeaderInformation.add("Consulting Services");
        parentHeaderInformation.add("Applications Development Services");

        HashMap<String, List<String>> allChildItems = returnGroupedChildItems();
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        ServicesAdapter expandableListViewAdapter = new ServicesAdapter(getActivity(), parentHeaderInformation, allChildItems);

        expandableListView.setAdapter(expandableListViewAdapter);
        return view;
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

    private HashMap<String, List<String>> returnGroupedChildItems() {

        HashMap<String, List<String>> childContent = new HashMap<String, List<String>>();

        List<String> cars = new ArrayList<String>();
        cars.add("Core Technical Training");
        cars.add("OJP Training");
        cars.add("On Demand Training");
        cars.add("Fast Track Training");

        List<String> houses = new ArrayList<String>();
        houses.add("Project Management Consulting");
        houses.add("Technical Consulting");
        houses.add("User Interface/User Experience Consulting ");
        houses.add("Two Storey");

        List<String> footballClubs = new ArrayList<String>();
        footballClubs.add("iOS");
        footballClubs.add("Android");
        footballClubs.add("Hybrid");
        //footballClubs.add("West Ham");

        childContent.put(parentHeaderInformation.get(0), cars);
        childContent.put(parentHeaderInformation.get(1), houses);
        childContent.put(parentHeaderInformation.get(2), footballClubs);

        return childContent;
    }
}
