package com.example.user.binarybeast.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.binarybeast.R;
import com.example.user.binarybeast.model.UserData;

import java.util.NoSuchElementException;
/**
 * @author Yan Chen
 * @version 1.0
 */
public class FriendAdderActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_adder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_adder, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * add new friend
     * @param view current view
     */
    public void makeNewFriend(View view) {
        EditText friendName = (EditText) findViewById(R.id.r_friendName);
        EditText friendEmail = (EditText) findViewById(R.id.r_friendEmail);
        String name = friendName.getText().toString();
        String email = friendEmail.getText().toString();
        UserData addFriendName = MainActivity.helper.findUser(name, "name");
        UserData addFriendEmail = MainActivity.helper.findUser(email, "email");
        //add new friend
        if (MainActivity.helper.currUser.getEmail().equals(email) || MainActivity.helper.currUser.getName().equals(name)) {
            //can not add yourself as friend
            Toast.makeText(FriendAdderActivity.this, "Don't add yourself!", Toast.LENGTH_LONG).show();
        } else if (addFriendName != null) {
            //add friend by name
            if (!MainActivity.helper.addFriend(addFriendName)) {
                Toast.makeText(FriendAdderActivity.this, "the user is already your friend.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FriendAdderActivity.this, "Friend added", Toast.LENGTH_LONG).show();
            }
        } else if (addFriendEmail != null) {
            //add friend by email
            if (!MainActivity.helper.addFriend(addFriendEmail)) {
                Toast.makeText(FriendAdderActivity.this, "the user is already your friend.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FriendAdderActivity.this, "Friend added", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(FriendAdderActivity.this, "Can not find the user", Toast.LENGTH_LONG).show();
        }
    }
}
