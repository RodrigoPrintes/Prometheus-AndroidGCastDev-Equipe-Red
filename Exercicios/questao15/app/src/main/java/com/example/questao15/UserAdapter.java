package com.example.questao15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserData> {
    public UserAdapter(Context context, List<UserData> userList) {
        super(context, 0, userList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserData currentUser = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_user, parent, false);
        }

        TextView idTextView = convertView.findViewById(R.id.idTextView);
        TextView nomeTextView = convertView.findViewById(R.id.nomeTextView);
        TextView numeroTextView = convertView.findViewById(R.id.numeroTextView);

        idTextView.setText("ID: " + currentUser.getId());
        nomeTextView.setText("Nome: " + currentUser.getNome());
        numeroTextView.setText("Número: " + currentUser.getNumero());

        return convertView;
    }
}
