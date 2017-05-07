package com.hamilton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hamilton.view.ntb.NavigationTabBar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private NavigationTabBar navigationTabBar;
    private ArrayList<NavigationTabBar.Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_home),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_search))
                        .selectedIcon(getResources().getDrawable(R.drawable.icon_home_selected))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_home),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_properties))
                        .selectedIcon(getResources().getDrawable(R.drawable.icon_home_selected))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_home),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_shortlist))
                        .selectedIcon(getResources().getDrawable(R.drawable.icon_home_selected))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_home),
                        getResources().getColor(R.color.transparent))
                        .title(getResources().getString(R.string.str_me))
                        .selectedIcon(getResources().getDrawable(R.drawable.icon_home_selected))
                        .build()
        );

        navigationTabBar.setModels(models);

    }

}
