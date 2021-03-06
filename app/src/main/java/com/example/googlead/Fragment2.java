package com.example.googlead;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment2 extends Fragment {
    private TextView tvCountry;
    private TextView tvName;
    private TextView tvScore;

    ListView listView;
    private RecyclerView rv;
    private List<Category>imageLists;
    private TikomaApi apiInterface;
    private ScoreAdapter adapter;
    List<String> stringList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment2_layout, container, false);

        tvCountry = root.findViewById(R.id.countryTV);
        tvName = root.findViewById(R.id.nameTV);
        tvScore = root.findViewById(R.id.scoreTV);
        displayUsers();
        rv = root.findViewById(R.id.recyclerview2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(root.getContext()));
        return root;
    }
    private void displayUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TikomaApi tikomaApi = retrofit.create(TikomaApi.class);
        Call<List<Category>> call = tikomaApi.getSkills();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    tvName.setText("Code: " + response.code());
                    return;
                }
                List<Category> categories = response.body();
                imageLists=response.body();
                adapter=new ScoreAdapter(imageLists,getContext());
                rv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                tvName.setText(t.getMessage())
                ;
            }
        });
    }
}
