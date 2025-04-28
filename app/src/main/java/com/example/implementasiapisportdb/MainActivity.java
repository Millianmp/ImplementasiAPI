package com.example.implementasiapisportdb;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.rvTeams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 2. Panggil loadTeams() untuk fetch data dan tampilkan logo + nama
        loadTeams();
    }

    /**
     * 4. Di sinilah poin #4 berlaku:
     *    - Mengambil data via Retrofit
     *    - Mengisi adapter dengan nama tim + logo (strTeamBadge)
     */
    private void loadTeams() {
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<TeamResponse> call = api.getTeams("English Premier League");
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> res) {
                if (res.isSuccessful() && res.body() != null) {
                    List<Team> list = res.body().getTeams();
                    Log.d("MainActivity", "Got " + (list==null?0:list.size()) + " teams");

                    // Inisialisasi adapter dengan data lengkap (logo+nama)
                    adapter = new TeamAdapter(list);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Response gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", t.getMessage(), t);
            }
        });
    }
}
