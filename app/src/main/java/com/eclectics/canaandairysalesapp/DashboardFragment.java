package com.eclectics.canaandairysalesapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DashboardFragment extends Fragment {

    private ImageView drawer_icon, notification_icon, search_icon;
    CardView make_order_card, orders_card, register_buyer;

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
        register_buyer = view.findViewById(R.id.register_buyer);

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
                openMakeOrderActivity();
            }
        });

        register_buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register buyer activity
                openRegisterBuyerActivity();
            }
        });

        return view;
    }

    private void openRegisterBuyerActivity() {
        //open Register Buyer Activity
        Intent intent = new Intent(getActivity(), SignUpOneActivity.class);
        startActivity(intent);
    }

    private void openMakeOrderActivity() {
        //open purchase orders activity
        Intent intent = new Intent(getActivity(), PurchaseOrderActivity.class);
        startActivity(intent);
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
