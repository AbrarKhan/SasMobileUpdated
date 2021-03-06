package com.sasmobile.contactus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * {@link ContactUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements ContactsAdapter.OnContactItemClicked {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mHeaderTitle;
    private String[] mContactNames;
    private String[] mContactEmails;
    private ArrayList<ContactUs> mContactsList;

    private OnFragmentInteractionListener mListener;
    private String[] mContactPhones;
    private String[] mContactAddress;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.contacts_list);
        mHeaderTitle = view.findViewById(R.id.header_title);
        mHeaderTitle.setText(R.string.contacts_list);
        mContactNames = getResources().getStringArray(R.array.contact_names);
        mContactEmails = getResources().getStringArray(R.array.contact_email);
        mContactAddress = getResources().getStringArray(R.array.contact_address);
        mContactPhones = getResources().getStringArray(R.array.contact_phone);
        createContactsList();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ContactsAdapter eventsAdapter = new ContactsAdapter(getActivity(), mContactsList);
        eventsAdapter.setOnClick(this);
        recyclerView.setAdapter(eventsAdapter);
        return view;
    }

    private void createContactsList() {

        mContactsList = new ArrayList<ContactUs>();

        for (int i = 0; i < mContactNames.length; i++) {
            ContactUs contactUs = new ContactUs();
            contactUs.setContactName(mContactNames[i]);
            contactUs.setContactEmail(mContactEmails[i]);
            contactUs.setContactAddress(mContactAddress[i]);
            contactUs.setContactPhone(mContactPhones[i]);
            mContactsList.add(contactUs);

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
        Toast.makeText(getActivity(), "contact", Toast.LENGTH_SHORT).show();
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
