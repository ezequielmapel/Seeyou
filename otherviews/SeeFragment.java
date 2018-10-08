package com.emapel.seeyou.seeyou.otherviews;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emapel.seeyou.seeyou.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String SHARED_PREF = "SHARED_PREFS";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String btnPowerStateString = "BTN_POWER_STATE";
    private boolean btnPowerState;
    private ImageButton btnPower;
    private TextView tvState;
    private ProgressBar progressBarPower;

    public SeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeeFragment newInstance(String param1, String param2) {
        SeeFragment fragment = new SeeFragment();
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

        loadData();


        Log.d("SHARED123", "SHARED " + btnPowerStateString);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_see, container, false);

        btnPower = rootView.findViewById(R.id.btnPower);
        btnPower.setOnClickListener(this);

        tvState = rootView.findViewById(R.id.tvState);
        progressBarPower = rootView.findViewById(R.id.progressBar);

        btnPowerChangeState();

        return rootView;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPower:
                btnPowerChangeState();
                break;
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


    private void btnPowerChangeState() {

        Log.d("STTS-BTNPOWER", "STTS-BTNPOWER "+btnPowerState);
        // BUTTON POWER VERIFICATION
            if (!btnPowerState) {
                btnPower.setImageResource(R.drawable.button_power_actived);

                btnPowerActived();

            } else {
                btnPower.setImageResource(R.drawable.button_power_not_actived);

                btnPowerDesatived();
            }

            btnPowerState = !btnPowerState;

    }

    // WHEN BUTTON POWER IS ACTIVE
    private void btnPowerActived(){
        // INITIATE
        tvState.setText(R.string.state_tracking_loading);
        progressBarPower.setProgress(10);

        // CONNECTION WITH BD
        tvState.setText("Conectando ao banco de dados");
        progressBarPower.setProgress(40);

        // SAVE POSITION
        tvState.setText(R.string.state_tracking_atived);
        progressBarPower.setProgress(100);

    }

    private void btnPowerDesatived(){
        // Turn off tracking
        tvState.setText(R.string.state_tracking_desatived);
        progressBarPower.setProgress(0);
    }

    private void saveData(){
        // Save data

        Toast.makeText(getContext(), "BTN SAVE "+btnPowerState, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(btnPowerStateString, btnPowerState);
        editor.apply();

        Toast.makeText(getContext(), "SAVE", Toast.LENGTH_SHORT).show();

    }

    private void loadData(){
        //Toast.makeText(getContext(), "BTN LOAD "+btnPowerState, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        btnPowerState = sharedPreferences.getBoolean(btnPowerStateString, false);
        btnPowerState = !btnPowerState;

        //Toast.makeText(getContext(), "LOAD D "+btnPowerState, Toast.LENGTH_SHORT).show();
    }

    private void updateViews(){
        btnPowerChangeState();
    }

    @Override
    public void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveData();
    }
}
