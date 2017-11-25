package com.sasmobile.appszone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sasmobile.MainActivity;
import com.sasmobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventDescriptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventDescriptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "event";

    // TODO: Rename and change types of parameters
    private Event event;


    private OnFragmentInteractionListener mListener;
    private TextView mHeaderTitle;

    public EventDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductDescription.
     */
    // TODO: Rename and change types and number of parameters
    public static EventDescriptionFragment newInstance(Event event) {
        EventDescriptionFragment fragment = new EventDescriptionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, event);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            event = (Event) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_description, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        TextView eventName = (TextView) view.findViewById(R.id.event_name);
        TextView eventDes = (TextView) view.findViewById(R.id.event_desc);
        ImageView imageProduct = (ImageView) view.findViewById(R.id.imageEvent);
        mHeaderTitle = view.findViewById(R.id.header_title);
        mHeaderTitle.setText(R.string.events_desc);

        eventName.setText(event.getEventName());
        eventDes.setText(event.getEventDesc());
        imageProduct.setImageResource(event.getEventImage());
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
}
