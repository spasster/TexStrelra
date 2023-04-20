package com.example.texstrelra.Activities;

import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.view.View;

import com.example.texstrelra.databinding.ActivityQuizBinding;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityQuizBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}