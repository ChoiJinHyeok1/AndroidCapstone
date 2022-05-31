package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView searchView;
        searchView = findViewById(R.id.searchForm);


      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              //검색 버튼이 눌러졌을 때 이벤트 처리
              Intent mIntent = new Intent(SearchActivity.this, Activity1Intro.class);
              startActivity(mIntent);
              return false;
          }

          @Override
          public boolean onQueryTextChange(String nextText) {
              // 검색어가 변경 되었을 때 이벤트 처리
              return false;
          }


      });

    }
}