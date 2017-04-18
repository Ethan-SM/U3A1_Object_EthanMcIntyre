package com.example.user.u3a1_object_ethanmcintyre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class U3A1_Object_EthanMcIntyre extends AppCompatActivity {
    public final ArrayList<Bank_Account> storedAccounts = new ArrayList<Bank_Account>();
    //creating the bank accounts class
    public class Bank_Account {
        double balance;
        String id;
        String name;
        DecimalFormat df = new DecimalFormat("#.##");
        public void deposit(double addBalance){
            balance = balance + Double.parseDouble(df.format(addBalance));
        }

        public void withdraw(double subBalance){
            balance = balance - Double.parseDouble(df.format(subBalance));
        }

        public Bank_Account create_child(Bank_Account b) {
            double tenPerc;
            Bank_Account childAcc = new Bank_Account();
            tenPerc = Math.round(b.balance * 0.10);
            childAcc.balance = tenPerc;
            b.balance = b.balance - tenPerc;
            childAcc.name = b.name;
            String newId = new String();
            for (int i = 0; i <= 5; ) {
                Random random = new Random();
                newId = String.valueOf(random.nextInt(9) + 1) + newId;
                if (i == 5) {
                    //ensuring the randomly created id is unique
                    if (storedAccounts.size() != 0) {
                        for (int a = 0; a < storedAccounts.size(); ) {
                            if (newId.equals(String.valueOf(storedAccounts.get(a).id))) {
                                newId = new String();
                                i = 0;
                            }
                            a++;
                        }
                    }

                    if (i != 0) {
                        childAcc.id = newId;
                    }
                }
                i++;

            }
            return childAcc;
        }
    }

    public static double calculate_funds(ArrayList<Bank_Account> l){
        double total = 0;
        for(int i = 0; i < l.size(); ){
            total = total + l.get(i).balance;
            i++;
        }
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u3_a1__object__ethan_mc_intyre);
        Button createBtn = (Button) findViewById(R.id.createBtn);
        Button depositBtn = (Button) findViewById(R.id.depositBtn);
        Button withdrawBtn = (Button) findViewById(R.id.withdrawBtn);
        Button totalBtn = (Button) findViewById(R.id.totalBtn);
        Button createChildBtn = (Button) findViewById(R.id.createChildBtn);
        final EditText amtInput = (EditText) findViewById(R.id.amtInput);
        final EditText idInput = (EditText) findViewById(R.id.idInput);
        final TextView output = (TextView) findViewById(R.id.displayField);
        final EditText nameInput = (EditText) findViewById(R.id.nameInput);
        final DecimalFormat df = new DecimalFormat("#.##");

        //code to be executed when the create button is pressed
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank_Account newAcc = new Bank_Account();
                newAcc.balance = 0;
                String newId = new String();
                String newName = String.valueOf(nameInput.getText());
                if(!TextUtils.isEmpty(nameInput.getText())){
                    for(int i = 0; i <= 5;){
                        Random random = new Random();
                        newId = String.valueOf(random.nextInt(9) + 1) + newId;
                        if(i == 5){
                            //ensuring the randomly created id is unique
                            if(storedAccounts.size() != 0){
                                for(int a = 0; a < storedAccounts.size();){
                                    if(newId.equals(String.valueOf(storedAccounts.get(a).id))){
                                        newId = new String();
                                        i = 0;
                                    }
                                    a++;
                                }
                            }

                            if(i != 0){
                                newAcc.id = newId;
                            }
                        }
                        i++;
                    }

                }

                if(newName.contains(" ") && !String.valueOf(newName.charAt(0)).equals(" ")){
                    newName = (newName.toLowerCase()); //add capitalization function before submitting
                    newAcc.name = newName;
                    //adding the new account to the list of saved accounts
                    storedAccounts.add(newAcc);
                    int max = storedAccounts.size() - 1;
                    //displaying the accounts balance unique id
                    output.setText("Current balance - $" + String.valueOf(storedAccounts.get(max).balance) + "\n"
                            + "Account id - " + storedAccounts.get(max).id + "\n Name - " +
                            storedAccounts.get(max).name);
                }else{
                    output.setText("Invalid name \n " +
                            "Make sure you have entered your first and last name.");
                }


            }
        });

        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(idInput.getText());
                if(!TextUtils.isEmpty(nameInput.getText()) &&
                        !TextUtils.isEmpty(idInput.getText()) &&
                        !TextUtils.isEmpty(amtInput.getText())){
                    double amtDeposit = Double.valueOf(String.valueOf(amtInput.getText()));
                    int i = 0;
                    String name = String.valueOf(nameInput.getText()).toLowerCase();
                    Boolean idFound = false;
                    for(i = 0; i < storedAccounts.size();){
                        if(id.equals(storedAccounts.get(i).id)
                                && name.equals(storedAccounts.get(i).name)){
                            Bank_Account acc = storedAccounts.get(i);
                            amtDeposit = Double.valueOf(df.format(amtDeposit));
                            acc.deposit(amtDeposit);
                            output.setText("Current balance - $" + df.format((storedAccounts.get(i).balance)) + "\n"
                                    + "Account id - " + storedAccounts.get(i).id + "\n Name - " +
                                    storedAccounts.get(i).name);
                            idFound = true;
                        }
                        i++;
                    }
                    if(!idFound){
                        output.setText("Id or Name not found");
                    }
                }else{
                    output.setText("Make sure each field has been filled");
                }

            }
        });

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(nameInput.getText()) &&
                        !TextUtils.isEmpty(idInput.getText()) &&
                        !TextUtils.isEmpty(amtInput.getText())){
                    String id = String.valueOf(idInput.getText());
                    double amtWithdraw = Double.valueOf(String.valueOf(amtInput.getText()));
                    int i;
                    String name = String.valueOf(nameInput.getText()).toLowerCase();
                    Boolean idFound = false;
                    for(i = 0; i < storedAccounts.size();){
                        if(id.equals(storedAccounts.get(i).id)
                                && name.equals(storedAccounts.get(i).name)){
                            if(storedAccounts.get(i).balance >= amtWithdraw){
                                Bank_Account acc = storedAccounts.get(i);
                                acc.withdraw(amtWithdraw);
                                output.setText("Current balance - $" + String.valueOf(storedAccounts.get(i).balance) + "\n"
                                        + "Account id - " + storedAccounts.get(i).id + "\n Name - " +
                                        storedAccounts.get(i).name);
                                idFound = true;
                            }else{
                                output.setText("Not enough funds in selected account.");
                            }

                        }
                        i++;
                    }
                    if(!idFound){
                        output.setText("Invalid Id");
                    }
                }else{
                    output.setText("Make sure each field has been filled");
                }
            }
        });

        totalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storedAccounts.size() != 0){
                    output.setText("$ " + String.valueOf(calculate_funds(storedAccounts)));
                }else{
                    output.setText("There are no accounts in storage");
                }
            }
        });

        createChildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //COMPLETE THIS BEFORE SUBMITTING!!!!
            }
        });{

        }
    }
}