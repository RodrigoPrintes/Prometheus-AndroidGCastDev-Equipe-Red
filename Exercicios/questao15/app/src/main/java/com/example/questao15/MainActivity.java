package com.example.questao15;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = listView.findViewById(R.id.listView);
        userAdapter = new UserAdapter(getApplicationContext(), new ArrayList<>());
        listView.setAdapter(userAdapter);

        new FetchDataAsyncTask().execute("https://658b520eba789a962238a813.mockapi.io/users");
    }

    private Context getApplicationContext() {
        Context o = null;
        return o;
    }

    private void setContentView(int activityMain) {

    }

    private class FetchDataAsyncTask extends AsyncTask<String, Void, List<UserData>> {

        @Override
        protected List<UserData> doInBackground(String... strings) {
            return null;
        }
    }
}