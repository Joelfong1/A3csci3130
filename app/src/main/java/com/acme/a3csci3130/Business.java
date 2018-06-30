package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel Fong on 2018-06-29.
 *
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public String uid;
    public int businessNum;
    public String name;
    public String primaryBusiness;
    public String address;
    public String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Creates a new business.
     * @param uid String UID. to identify the business on firebase.
     * @param businessNum Integer Business number. Must be an int of 9 digits.
     * @param name String Name. The name of the business up to 48 chars
     * @param primaryBusiness String primaryBusiness. The business type
     * @param address String address. The address up to 50 chars
     * @param province String province. The province
     */
    public Business(String uid, int businessNum, String name, String primaryBusiness, String address, String province){
        this.uid = uid;
        this.businessNum = businessNum;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    /**
     * Maps the business to JSON format.
     * @return this Business's resulting JSON.
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("businessNum", businessNum);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
