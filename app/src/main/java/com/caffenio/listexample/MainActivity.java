package com.caffenio.listexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.caffenio.listexample.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapter.ListAdapterCallback {

    private ActivityMainBinding binding;

    private List<String> names;

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1er paso
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setTitle(R.string.app_name);
        setTitle(R.string.app_name);

        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        names = new ArrayList<>();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etNombre.getText().toString();
                names.add(name);
                binding.etNombre.setText("");

                adapter = new ListAdapter(getLayoutInflater(), names, MainActivity.this, MainActivity.this);
                binding.rvList.setAdapter(adapter);
            }
        });

        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    @Override
    public void onItemSelected(int position, String name) {
        Toast.makeText(this, position + " " + name, Toast.LENGTH_LONG).show();
    }

}