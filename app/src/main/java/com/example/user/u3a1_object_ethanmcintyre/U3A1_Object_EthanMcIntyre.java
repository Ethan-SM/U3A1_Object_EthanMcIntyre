package com.example.user.u3a1_object_ethanmcintyre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class U3A1_Object_EthanMcIntyre extends AppCompatActivity {
    public final ArrayList<Bank_Accounts> storedAccounts = new ArrayList<Bank_Accounts>();
    //creating the bank accounts class
    public class Bank_Accounts{
        double balance;
        String id;
        final TextView output = (TextView) findViewById(R.id.displayField);
        public void deposit(double addBalance){
            balance = balance + addBalance;
        }

        public void withdraw(double subBalance){
            balance = balance - subBalance;
        }
    }

    public static double calculate_funds(ArrayList<Bank_Accounts> l){
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
        final EditText amtInput = (EditText) findViewById(R.id.amtInput);
        final EditText accId = (EditText) findViewById(R.id.idInput);
        final TextView output = (TextView) findViewById(R.id.displayField);

        //code to be executed when the create button is pressed
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank_Accounts newAcc = new Bank_Accounts();
                newAcc.balance = 0;
                String newId = new String();
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
                //adding the new account to the list of saved accounts
                storedAccounts.add(newAcc);
                int max = storedAccounts.size() - 1;
                //displaying the accounts balance unique id
                output.setText("Current balance - " + String.valueOf(storedAccounts.get(max).balance) + "\n"
                        + "Account id - " + storedAccounts.get(max).id);
            }
        });

        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(accId.getText());
                double amtDeposit = Double.valueOf(String.valueOf(amtInput.getText()));
                //TEST CODE ONLY, PLEASE REVISE BEOFRE SUBMITSION
                int max = storedAccounts.size() - 1;
                int i = 0;
                Boolean idFound = false;
                for(i = 0; i < storedAccounts.size();){
                    if(id.equals(storedAccounts.get(i).id)){
                        Bank_Accounts acc = storedAccounts.get(i);
                        acc.deposit(amtDeposit);
                        output.setText("Current balance - " + String.valueOf(storedAccounts.get(i).balance) + "\n"
                                + "Account id - " + storedAccounts.get(i).id);
                        idFound = true;
                    }
                    i++;
                }
                if(!idFound){
                    output.setText("Invalid Id");
                }
            }
        });

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(accId.getText());
                double amtWithdraw = Double.valueOf(String.valueOf(amtInput.getText()));
                //TEST CODE ONLY, PLEASE REVISE BEOFRE SUBMITSION
                int max = storedAccounts.size() - 1;
                int i = 0;
                Boolean idFound = false;
                for(i = 0; i < storedAccounts.size();){
                    if(id.equals(storedAccounts.get(i).id)){
                        if(storedAccounts.get(i).balance >= amtWithdraw){
                            Bank_Accounts acc = storedAccounts.get(i);
                            acc.withdraw(amtWithdraw);
                            output.setText("Current balance - " + String.valueOf(storedAccounts.get(i).balance) + "\n"
                                    + "Account id - " + storedAccounts.get(i).id);
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
    }




}
