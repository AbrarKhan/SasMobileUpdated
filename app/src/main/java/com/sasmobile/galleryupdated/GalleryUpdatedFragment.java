package com.sasmobile.galleryupdated;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
 * {@link GalleryUpdatedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryUpdatedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryUpdatedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Fragment fragment;
    private FragmentTransaction ft;
    private OnFragmentInteractionListener mListener;
    private TextView galleryText;

    public GalleryUpdatedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryUpdatedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryUpdatedFragment newInstance(String param1, String param2) {
        GalleryUpdatedFragment fragment = new GalleryUpdatedFragment();
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
        fragment = null;

        View view = inflater.inflate(R.layout.fragment_gallery_updated, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        galleryText =(TextView) view.findViewById(R.id.header_title);
        galleryText.setText("Gallery");
        final ImageView gallery_videos = (ImageView) view.findViewById(R.id.gallery_videos);
        final ImageView gallery_images = (ImageView) view.findViewById(R.id.gallery_images);
        gallery_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery_images.setImageResource(R.drawable.gallery_tab_ic_active);
                gallery_videos.setImageResource(R.drawable.video_tab_ic_inactive);
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                fragment = new ImageFragment();
                ft.replace(R.id.image_video, fragment);
                ft.commit();
            }
        });

        gallery_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery_images.setImageResource(R.drawable.gallery_tab_ic_inactive);
                gallery_videos.setImageResource(R.drawable.video_tab_ic_active);
                ft = getActivity().getSupportFragmentManager().beginTransaction();
                fragment = new VideoFragment();
                ft.replace(R.id.image_video, fragment);
                ft.commit();
            }
        });
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment = new ImageFragment();
        ft.replace(R.id.image_video, fragment);
        ft.commit();
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
