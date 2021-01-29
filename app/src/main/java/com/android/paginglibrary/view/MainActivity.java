package com.android.paginglibrary.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.paginglibrary.R;
import com.android.paginglibrary.adapter.UserAdapter;
import com.android.paginglibrary.databinding.ActivityMainBinding;
import com.android.paginglibrary.model.User;
import com.android.paginglibrary.viewmodel.MainActivityViewModel;
/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class MainActivity extends AppCompatActivity {
  private PagedList<User> users;
  private RecyclerView recyclerView;
  private UserAdapter userAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private MainActivityViewModel mainActivityViewModel;
  private ActivityMainBinding activityMainBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

    getUsers();

    swipeRefreshLayout = activityMainBinding.swipeLayout;
    swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        getUsers();
      }
    });
  }

  public void getUsers() {
    mainActivityViewModel.getMoviesPagedList().observe(this, new Observer<PagedList<User>>() {
      @Override
      public void onChanged(@Nullable PagedList<User> usersFromLiveData) {
        users = usersFromLiveData;
        showOnRecyclerView();
      }
    });
  }

  private void showOnRecyclerView() {
    recyclerView = activityMainBinding.rvMovies;
    userAdapter = new UserAdapter(this);
    userAdapter.submitList(users);

    if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    } else {

      recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(userAdapter);
    userAdapter.notifyDataSetChanged();
    if (swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }
  }
}
