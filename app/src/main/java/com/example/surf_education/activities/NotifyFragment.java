package com.example.surf_education.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.surf_education.R;

public class NotifyFragment extends Fragment {

    TextView tv_notif;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notification_layout,container,true);
        tv_notif = (TextView) view.findViewById(R.id.tv_notification);

        return view;
    }

    public String getNotificationText() {
        return tv_notif.getText().toString();
    }

    public void setNotificationText(String text) {
        tv_notif.setText(text);
    }
}
