// https://github.com/UALR-MobileAppsDevelopmentCourse/assignment-06-mamccorkle
//
//  UALR - MAD - F22 - Assignment 8 - Android Recycler View Continued
//
//  Project: UALR - Mobile Applications Development - Fall 2022 - Assignment 8 - Android Recycler View Continued
//  Created by: Mark McCorkle on 20221211
//  Based on: Code Provided by Dr Ivan Rodriguez-Conde
//
//  IDE:
//      Android Studio Dolphin | 2021.3.1 Patch 1
//              Build #AI-213.7172.25.2113.9123335, built on September 29, 2022
//              Runtime version: 11.0.13+0-b1751.21-8125866 x86_64
//              VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
//              macOS 11.5.2
//              GC: G1 Young Generation, G1 Old Generation
//              Memory: 2048M
//              Cores: 8
//              Registry:
//              external.system.auto.import.disabled=true
//              ide.text.editor.with.preview.show.floating.toolbar=false
//
package com.ualr.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapter.AdapterListBasic;
import com.ualr.recyclerviewassignment.databinding.ActivityListMultiSelectionBinding;

import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityListMultiSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMultiSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    // TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator
    //  class to create the new item to be added.
    private void addItem(AdapterListBasic mAdapter) {
        // Generate a new item and add to the top of the list:
        Inbox tempEmail = DataGenerator.getRandomInboxItem(mAdapter.getContext());
        mAdapter.addItem(0, tempEmail);

        // Scroll to the new item in the list:
        binding.recyclerView.scrollToPosition(0);
    }

    // TODO 07. Detect click events on the thumbnail located on the left of every list row when the
    //  corresponding item is selected. Implement a new method to delete the corresponding item in
    //  the list
    private void selectionChange( View v, Inbox email, int position, AdapterListBasic mAdapter ) {
        // Toggle the email selection:
        email.toggleSelection();

        if(email.isSelected()) {
            v.findViewById(R.id.ivAvatar).setVisibility(View.INVISIBLE);
            v.findViewById(R.id.ivTrash).setVisibility(View.VISIBLE);
            v.setBackgroundColor(Color.LTGRAY);
        } else {
            v.findViewById(R.id.ivAvatar).setVisibility(View.VISIBLE);
            v.findViewById(R.id.ivTrash).setVisibility(View.INVISIBLE);
            v.setBackgroundColor(Color.WHITE);
        }

        ImageView mTrash = v.findViewById(R.id.ivTrash);
        mTrash.setOnClickListener(v1 -> removeItem(position, mAdapter));
    }
    private void removeItem(int position, AdapterListBasic mAdapter) {
        // Remove Item:
        mAdapter.removeItem(position);
    }

    private void initComponent() {
        // TODO 01. Generate the item list to be displayed using the DataGenerator class
        List<Inbox> mailBox = DataGenerator.getInboxData(this);
        mailBox.addAll(DataGenerator.getInboxData(this));

        // TODO 03. Do the setup of a new RecyclerView instance to display the item list properly
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        // TODO 04. Define the layout of each item in the list
        // TODO 05. Create a new Adapter class and the corresponding ViewHolder class in a different
        //          file. The adapter will be used to populate the recyclerView and manage the
        //          interaction with the items in the list
        AdapterListBasic mAdapter = new AdapterListBasic(this, mailBox);

        // Get the reference to the RecyclerView:
        RecyclerView recyclerView = binding.recyclerView;

        // TODO 09. Create a new instance of the created Adapter class and bind it to the
        //          RecyclerView instance created in step 03
        // Connect the Layout Manager and Adapter to the RecyclerView:
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        // TODO 06. Detect click events on the list items. Implement a new method to toggle items'
        //  selection in response to click events
        mAdapter.setOnItemClickListener((v, email, position) -> selectionChange(v, email, position, mAdapter));

        binding.fab.setOnClickListener(v -> {
            // TODO 10. Invoke the method created to a new item to the top of the list so it's
            //  triggered when the user taps the Floating Action Button
            addItem(mAdapter);
        });
    }
}