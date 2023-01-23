package com.ualr.recyclerviewassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.model.SharedDataModel;

import java.util.List;

public class ForwardEmailFragment extends DialogFragment
{
    // Variable to hold the data being passed into the fragment:
    private static final String TO_VALUE_KEY = "ToValue";
    private static final String NAME_VALUE_KEY = "NameValue";
    private static final String MSG_VALUE_KEY = "MSGValue";
    private static final String DATE_VALUE_KEY = "DateValue";

    private SharedDataModel mViewModel;

    private static final String INBOX_FRAGMENT_TAG = "Inbox";

    // This function creates the new fragment, assembles the arguments, and adds the arguments
    // needed to create the fragment object:
    public static ForwardEmailFragment newInstance( Inbox email )
    {
        // Create a new instance of the fragment that cant have anything but a default constructor:
        ForwardEmailFragment fragment = new ForwardEmailFragment();

        // Create a new bundle container of arguments:
        Bundle args = new Bundle();
        args.putString(TO_VALUE_KEY, email.getEmail());
        args.putString(NAME_VALUE_KEY, email.getFrom());
        args.putString(MSG_VALUE_KEY, email.getMessage());
        args.putString(DATE_VALUE_KEY, email.getDate());

        // Since the we cant create a fragment constructor with arguments, we now pass the bundle of
        // arguments to the fragment after its creation:
        fragment.setArguments(args);

        // Return the created fragment:
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        // Inflate the layout of the fragment:
        return inflater.inflate(R.layout.forward_email_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        // All of the components of the fragment exist now. So, we can start adding the code...

        EditText etTo = view.findViewById(R.id.etTo);
        assert getArguments() != null;
        etTo.setText(getArguments().getString(TO_VALUE_KEY));

        EditText etName = view.findViewById(R.id.etName);
        etName.setText(getArguments().getString(NAME_VALUE_KEY));

        EditText etMSG = view.findViewById(R.id.etText);
        etMSG.setText(getArguments().getString(MSG_VALUE_KEY));

        Button btnSend = view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {

            String to = etTo.getText().toString();
            String name = etName.getText().toString();
            String msg = etMSG.getText().toString();

            Inbox newInboxEmail = new Inbox();
            newInboxEmail.setEmail(to);
            newInboxEmail.setFrom(name);
            newInboxEmail.setMessage(msg);

            onSendBtnClicked(v, newInboxEmail);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(SharedDataModel.class);
    }

    //public void onActivityCreated(){};
    //public void onStart(){};
    //public void onResume(){};
    //public void onPause(){};
    //public void onDestroyView(){};
    //public void onDestroy(){};
    //public void onDetach(){};

    private void onSendBtnClicked(View v, Inbox newInboxEmail)
    {
        List<Inbox> inboxEmails = mViewModel.getInbox().getValue();
        assert inboxEmails != null;
        inboxEmails.add(0, newInboxEmail);
        mViewModel.setInbox(inboxEmails);

        Snackbar snackbar = Snackbar.make(v, "Email sent...", Snackbar.LENGTH_SHORT);
        snackbar.show();

        // Define the fragment manager so we can dynamically add the inbox fragment:
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();

        // Replace the placeholder with the actual inbox fragment:
        InboxListFragment inboxFragment2 = (InboxListFragment) requireActivity().getSupportFragmentManager().findFragmentByTag(INBOX_FRAGMENT_TAG);
        assert inboxFragment2 != null;
        ft.replace(R.id.fragment_placeholder, inboxFragment2, INBOX_FRAGMENT_TAG);

        // Apply the changes:
        ft.commit();
    }

}