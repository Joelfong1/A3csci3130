package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Joel Fong on 2018-06-29.
 */

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessNumberField, addressField;
    private Spinner businessSpinner, provinceSpinner;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        addressField = (EditText) findViewById(R.id.address);

        //From android studio spinner doc https://developer.android.com/guide/topics/ui/controls/spinner
        businessSpinner = (Spinner) findViewById(R.id.businessSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.business_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        businessSpinner.setAdapter(adapter);

        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);
        ArrayAdapter<CharSequence> provinceAdapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        int businessNumber = Integer.parseInt(businessNumberField.getText().toString());
        String businessType = businessSpinner.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceSpinner.getSelectedItem().toString();
        Business business = new Business(businessID, businessNumber, name, businessType, address, province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();

    }
}
