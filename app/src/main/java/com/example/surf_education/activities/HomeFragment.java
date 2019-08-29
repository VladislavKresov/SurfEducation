package com.example.surf_education.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.surf_education.R;
import com.example.surf_education.adapter.StaggeredRecyclerViewAdapter;
import com.example.surf_education.network.response.MemesInfo;
import com.example.surf_education.network.util.ConnectionDetector;
import com.example.surf_education.network.util.NetworkService;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private static final int NUM_COLUMNS = 2;
    private ArrayList<String> memeTitles = new ArrayList<>();
    private ArrayList<String> memeImageUtls = new ArrayList<>();
    private ArrayList<Boolean> isMemeFavorite = new ArrayList<>();
    private TextView tv_error;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container == null)
            return null;

        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.fragment_home, container,false);

        mRecyclerView = rl.findViewById(R.id.recyclerViewHome);
        tv_error = rl.findViewById(R.id.tv_home_error);

        requestMemes();
        initRecyclerView();

        return rl;
    }

    private void requestMemes() {
        NetworkService.getInstance()
                .getJSONApi()
                .getPost()
                .enqueue(new Callback<List<MemesInfo>>() {
                    @Override
                    public void onResponse(Call<List<MemesInfo>> call, Response<List<MemesInfo>> response) {
                        List<MemesInfo> memes = response.body();
                        MemesInfo meme;
                        for (int i = 0; i < memes.size(); i++) {
                            meme = memes.get(i);
                            memeImageUtls.add(meme.getPhotoUrl());
                            memeTitles.add(meme.getTitle());
                            isMemeFavorite.add(meme.isFavorite());

                            initRecyclerView();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<MemesInfo>> call, Throwable t) {
                        checkError();
                    }
                });
    }

    private void checkError() {

        boolean isConnected =  new ConnectionDetector(getContext()).isConnected();

        String message;

        if(!isConnected) {
            message = getString(R.string.connection_lost);

            Snackbar mSnackbar = Snackbar.make( mRecyclerView ,message,Snackbar.LENGTH_LONG)
                    .setAction("Action",null);

            View snackbarView = mSnackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.colorError));
            mSnackbar.show();
            tv_error.setText(getString(R.string.load_error));
            tv_error.setVisibility(View.VISIBLE);
        } else {
            tv_error.setText(getString(R.string.load_error));
            tv_error.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: initializing recyclerView");

        StaggeredRecyclerViewAdapter adapter =
                new StaggeredRecyclerViewAdapter(getContext(), memeTitles, memeImageUtls, isMemeFavorite);
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);


    }


}
