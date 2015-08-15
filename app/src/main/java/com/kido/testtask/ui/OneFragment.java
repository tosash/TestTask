package com.kido.testtask.ui;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.kido.testtask.R;
import com.kido.testtask.adapters.ListViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements SearchView.OnQueryTextListener {
    Context fc;
    ListView lv;
    SearchView search_view;

    String[] strings = {"Cheese", "XWQ", "Pepperoni", "Black Olives", "Ankit", "Bohra", "Xyz", "Test1", "Test2"};
    ArrayList<String> mstrings;
    ListViewAdapter adapter;

    View rootView;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fc = getActivity().getApplicationContext();
        lv = (ListView) rootView.findViewById(R.id.listView);
        search_view = (SearchView) rootView.findViewById(R.id.search_view);

        mstrings = new ArrayList<String>();
        for (int i = 0; i < strings.length; i++) {
            mstrings.add(strings[i]);
        }
        //Sorting Array
        Collections.sort(mstrings, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        adapter = new ListViewAdapter(fc, mstrings);
        lv.setAdapter(adapter);
        search_view.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }
}
