package com.ualr.recyclerviewassignment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapter.AdapterListBasic;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.SharedDataModel;

import java.util.List;
import java.util.Objects;

public class InboxListFragment extends Fragment
{
    private Context context;
    private SharedDataModel sharedDataModel;
    private static final String FORWARD_FRAGMENT_TAG = "Forward";

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        // Inflate the layout of the fragment:
        return inflater.inflate(R.layout.activity_inbox_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        // Generate the mailbox data:
        List<Inbox> mailBox = DataGenerator.getInboxData(context);

        // Create a ne shared dat model:
        sharedDataModel = new ViewModelProvider(requireActivity()).get(SharedDataModel.class);

        // Set the shared data to be the mailbox:
        sharedDataModel.setInbox(mailBox);

        // Get the reference to the recyclerView:
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        // Create an adapter to the recyclerView:
        AdapterListBasic mAdapter = new AdapterListBasic(context, mailBox);

        // Get the reference to the RecyclerView:
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Connect the Layout Manager and Adapter to the RecyclerView:
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        // Set an on-click listener for the recyclerView:
        mAdapter.setOnItemClickListener((v, email, position) -> {
            selectionChange(v, email);

            sharedDataModel.setIndex(position);
        });

        // Update the list:
        sharedDataModel.getInbox().observe(getViewLifecycleOwner(), mAdapter::updateList);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    private void selectionChange( View v, Inbox email )
    {
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
        mTrash.setOnClickListener(v1 -> deleteEmail());
    }

    public void addEmail()
    {
        // Generate a new random email:
        Inbox tempEmail = DataGenerator.getRandomInboxItem(context);

        // Get the inbox object:
        List<Inbox> inbox = sharedDataModel.getInbox().getValue();

        // Add the new email to the inbox via the SharedDataModel class:
        assert inbox != null;
        inbox.add(0, tempEmail);

        // Send the new inbox data object back replacing the old one:
        sharedDataModel.setInbox(inbox);
    }

    public void forwardEmail()
    {
        int index = sharedDataModel.getIndex().getValue();
        Inbox emailData = Objects.requireNonNull(sharedDataModel.getInbox().getValue()).get(index);

        // Create a new instance of the fragment and pass the data into it:
        ForwardEmailFragment fEmail = ForwardEmailFragment.newInstance( emailData );

        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, fEmail, FORWARD_FRAGMENT_TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void deleteEmail()
    {
        // Get the inbox object:
        List<Inbox> inbox = sharedDataModel.getInbox().getValue();

        assert inbox != null;
        int index = sharedDataModel.getIndex().getValue();
        inbox.remove(index);

        // Send the new inbox data object back replacing the old one:
        sharedDataModel.setInbox(inbox);
    }
}