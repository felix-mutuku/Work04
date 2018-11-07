package com.eclectics.canaandairysalesapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class NotificationsFragment extends Fragment {

    private ImageView back, search_icon, notifications_icon;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        back = view.findViewById(R.id.back);
        search_icon = view.findViewById(R.id.search_icon);
        notifications_icon = view.findViewById(R.id.notification_icon);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearch();
            }
        });

        notifications_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
            }
        });

        return view;
    }

    private void goToSearch() {
        SearchFragment fragmentSearch = new SearchFragment();
        android.support.v4.app.FragmentTransaction fragmentTransactionSearch = getActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransactionSearch.replace(R.id.app_content, fragmentSearch);
        fragmentTransactionSearch.addToBackStack(null);
        fragmentTransactionSearch.commit();
    }

}
