package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumberField, addressField;
    private Spinner businessSpinner, provinceSpinner;
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
        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        addressField = (EditText) findViewById(R.id.address);

        businessSpinner = (Spinner) findViewById(R.id.businessSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.business_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessSpinner.setAdapter(adapter);

        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);
        ArrayAdapter<CharSequence> provinceAdapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(provinceAdapter);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            businessNumberField.setText(receivedBusinessInfo.businessNum);
            addressField.setText(receivedBusinessInfo.address);
        }
    }

    public void updateBusiness(View v){
        String businessID = receivedBusinessInfo.uid;
        String newName = nameField.getText().toString();
        int newBusinessNumber = Integer.parseInt(businessNumberField.getText().toString());
        String newBusinessType = businessSpinner.getSelectedItem().toString();
        String newAddress = addressField.getText().toString();
        String newProvince = provinceSpinner.getSelectedItem().toString();
        Business updatedBusiness = new Business(businessID, newBusinessNumber, newName, newBusinessType, newAddress, newProvince);

        appState.firebaseReference.child(businessID).setValue(updatedBusiness);
        finish();
    }

    public void eraseBusiness(View v)
    {
        String businessID = receivedBusinessInfo.uid;
        appState.firebaseReference.child(businessID).removeValue();
        finish();
    }
}
