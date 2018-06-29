package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField;
    private MyApplicationData appState;
    Business receivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            emailField.setText(receivedBusinessInfo.email);
        }
    }

    public void updateBusiness(View v){
        String BusinessID = receivedBusinessInfo.uid;
        String newName = nameField.getText().toString();
        String newEmail = emailField.getText().toString();

        Business updatedPerson = new Business(BusinessID, newName, newEmail);

        appState.firebaseReference.child(BusinessID).setValue(updatedPerson);
        finish();
    }

    public void eraseBusiness(View v)
    {
        String BusinessID = receivedBusinessInfo.uid;
        appState.firebaseReference.child(BusinessID).removeValue();
        finish();
    }
}
