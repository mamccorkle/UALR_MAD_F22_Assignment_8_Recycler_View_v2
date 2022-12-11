# UALR - Mobile Application Development - F22 - Assignment 8 - Android Recycler View

## CPSC 3367 Assignment 8 Topics:

<img title="" src="file:///Users/decyple/Downloads/RecyclerIcon.png" alt="" data-align="center">

> Android Application Development - Using the Android Recycler View Continued

#### Instructions:

> :x: [10%] Modify MainActivity's layout and the corresponding Java class to add a Toolbar and the targeted options menu.<br>
> :x: [20%] Decouple the RecyclerView we use to render the inbox list from the MainActivity. Create a new Fragment (InboxListFragment) and the corresponding layout. Move the recyclerView element to the new layout, and the code for the list setup and management to the Fragment class. Create and display the new fragment from MainActivity.<br>
> :x: [10%] Create a new ViewModel class to be used for the exchange of the inbox data between the activity and the fragments.<br>
> :x: [10%] Define a basic management of the inbox list:<br>
>     * Once selected, an item can no longer be deleted by tapping the thumbnail (watch the video)<br>
>     * The Floating Action Button is still used to add a new random message to the top of the list. Make the changes required to make that work with the new project structure.<br>
> :x: [10%] Implement the new item deletion feature triggered when the corresponding option on the toolbar menu is tapped.<br>
> :x: [20%] Define a Fragment class (ForwardDialogFragment) and the corresponding layout for the new Forward dialog.<br>
> :x: [5%] Modify the Inbox class so that an Inbox object can be sent attached to an intent, and thus we can initialize an instance of the new ForwardDialogFragment class with the given Inbox data.<br>
> :x: [5%] Implement the new Forward feature triggered when the corresponding option of the toolbar menu is tapped. A new instance of the ForwardDialogFragment is created and initialized with the data of the currently selected item.<br>
> :x: [10%] Implement a new method called onSendBtnClicked to define the actions triggered when he user taps the "Send" button in the Forward dialog.<br>

#### Submission Due Date:

>  Source Due: Monday, December 12, 2022 11:59 PM
