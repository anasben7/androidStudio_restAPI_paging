package com.android.paginglibrary.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.android.paginglibrary.R;
import com.android.paginglibrary.databinding.ActivityUserBinding;
import com.android.paginglibrary.model.User;
import java.util.Objects;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class UserActivity extends AppCompatActivity {

  private User user;
  private ActivityUserBinding userActivityBinding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);


    userActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_user);

    Intent intent = getIntent();

    if (intent.hasExtra("user")) {
      user = getIntent().getParcelableExtra("user");
      userActivityBinding.setUserDetail(user);
    }
  }
}
