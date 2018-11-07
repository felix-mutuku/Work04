package com.eclectics.canaandairysalesapp;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class DashboardFragment extends Fragment {

    private ImageView drawer_icon, notification_icon, search_icon;
    CardView make_order_card, orders_card;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        drawer_icon = view.findViewById(R.id.drawer_icon);
        notification_icon = view.findViewById(R.id.notification_icon);
        search_icon = view.findViewById(R.id.search_icon);
        make_order_card = view.findViewById(R.id.make_order_card);
        orders_card = view.findViewById(R.id.orders_card);

        drawer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                goToNotifications();
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                goToSearch();
            }
        });

        orders_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open orders activity
                openOrderActivity();
            }
        });

        make_order_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show order dialog
                showDialog();
            }
        });

        return view;
    }

    private void showDialog() {
        //show make new order dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_make_order);

        Button place_order_btn = dialog.findViewById(R.id.place_order_btn);

        place_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openOrderActivity() {
        //open orders activity

    }

    private void goToSearch() {
        SearchFragment fragmentSearch = new SearchFragment();
        android.support.v4.app.FragmentTransaction fragmentTransactionSearch = getActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransactionSearch.replace(R.id.app_content, fragmentSearch);
        fragmentTransactionSearch.addToBackStack(null);
        fragmentTransactionSearch.commit();
    }

    private void goToNotifications() {
        NotificationsFragment fragmentShop = new NotificationsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransactionShop = getActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransactionShop.replace(R.id.app_content, fragmentShop);
        fragmentTransactionShop.addToBackStack(null);
        fragmentTransactionShop.commit();
    }

}
