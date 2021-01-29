package com.android.paginglibrary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import com.android.paginglibrary.model.User;
import com.android.paginglibrary.model.UserDataSource;
import com.android.paginglibrary.model.UserDataSourceFactory;
import com.android.paginglibrary.model.UserRepository;
import com.android.paginglibrary.service.RetrofitInstance;
import com.android.paginglibrary.service.UserDataService;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class MainActivityViewModel extends AndroidViewModel {
  private UserRepository userRepository;

  LiveData<UserDataSource> userDataSourceLiveData;
  private Executor executor;
  private LiveData<PagedList<User>> usersPagedList;

  public MainActivityViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);

    UserDataService userDataService = RetrofitInstance.getService();
    UserDataSourceFactory factory = new UserDataSourceFactory(userDataService);
    userDataSourceLiveData = factory.getMutableLiveData();

    PagedList.Config config = (new PagedList.Config.Builder())
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .setPrefetchDistance(4)
        .build();

    executor = Executors.newFixedThreadPool(5);

    usersPagedList = (new LivePagedListBuilder<Long, User>(factory, config))
        .setFetchExecutor(executor)
        .build();
  }

  public LiveData<List<User>> getAllUsers() {

    return userRepository.getMutableLiveData();
  }

  public LiveData<PagedList<User>> getMoviesPagedList() {
    return usersPagedList;
  }
}
