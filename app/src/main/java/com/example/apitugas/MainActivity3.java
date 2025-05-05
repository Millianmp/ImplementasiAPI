package com.example.apitugas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {

    RecyclerView recyclerView2;
    TeamAdapter teamAdapter;
    List<Team> teamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView2 = findViewById(R.id.rvTeamsSpain);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        teamAdapter = new TeamAdapter(teamList);
        recyclerView2.setAdapter(teamAdapter);

        getTeamData();
    }

    private void getTeamData() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<TeamResponse> call = apiService.getPremierLeagueTeams();

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    teamList.clear();
                    teamList.addAll(response.body().getTeams());
                    teamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(MainActivity3.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}