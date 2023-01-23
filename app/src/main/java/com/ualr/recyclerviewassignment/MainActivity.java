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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.ualr.recyclerviewassignment.databinding.ActivityListMultiSelectionBinding;

public class MainActivity extends AppCompatActivity
{
    // Add the binder:
    private ActivityListMultiSelectionBinding binding;

    InboxListFragment inboxFragment;

    private static final String INBOX_FRAGMENT_TAG = "Inbox";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityListMultiSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home Devices");
        setSupportActionBar(toolbar);

        initComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.delete_action:
                inboxFragment.deleteEmail();

                // Create the Snackbar to be displayed to the user:
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "Email deleted...", Snackbar.LENGTH_SHORT);
                snackbar.show();
                return true;
            case R.id.forward_action:
                inboxFragment.forwardEmail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initComponent()
    {
        // Create a new instance of the inbox fragment:
        inboxFragment = new InboxListFragment();

        // Define the fragment manager so we can dynamically add the inbox fragment:
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Replace the placeholder with the actual inbox fragment and assigning it a tag to refer to later:
        ft.replace(R.id.fragment_placeholder, inboxFragment, INBOX_FRAGMENT_TAG);

        // Apply the changes:
        ft.commit();

        // Set the on-click listener:
        binding.fab.setOnClickListener(v -> inboxFragment.addEmail());
    }
}