package com.android.paginglibrary.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import com.android.paginglibrary.service.UserDataService;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class UserDataSourceFactory extends DataSource.Factory {

  private UserDataSource userDataSource;
  private UserDataService userDataService;
  private MutableLiveData<UserDataSource> mutableLiveData;

  public UserDataSourceFactory(UserDataService userDataService) {
    this.userDataService = userDataService;
    mutableLiveData = new MutableLiveData<>();
  }

  @Override
  public DataSource create() {

    userDataSource = new UserDataSource(userDataService);
    mutableLiveData.postValue(userDataSource);
    return userDataSource;
  }

  public MutableLiveData<UserDataSource> getMutableLiveData() {
    return mutableLiveData;
  }
}

