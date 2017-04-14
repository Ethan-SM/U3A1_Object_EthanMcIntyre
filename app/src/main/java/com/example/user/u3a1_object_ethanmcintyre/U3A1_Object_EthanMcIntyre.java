package com.example.user.u3a1_object_ethanmcintyre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class U3A1_Object_EthanMcIntyre extends AppCompatActivity {
    //creating the bank accounts class
    public class Bank_Accounts{
        double balance;
        String id;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3_a1__object__ethan_mc_intyre);
        final ArrayList<Bank_Accounts> storedAccounts = new ArrayList<Bank_Accounts>();

    }
}
