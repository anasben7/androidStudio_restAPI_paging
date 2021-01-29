package com.android.paginglibrary.model;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import com.android.paginglibrary.service.RetrofitInstance;
import com.android.paginglibrary.service.UserDataService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class UserDataSource extends PageKeyedDataSource<Long, User> {
  private UserDataService userDataService;

  public UserDataSource(UserDataService userDataService) {
    this.userDataService = userDataService;
  }

  // It loads the first page of your data that use to initialize RecyclerView
  @Override
  public void loadInitial(@NonNull LoadInitialParams<Long> params,
      @NonNull final LoadInitialCallback<Long, User> callback) {

    userDataService = RetrofitInstance.getService();
    Call<UserDBResponse> call = userDataService.getUsersWithPaging(1);

    call.enqueue(new Callback<UserDBResponse>() {
      @Override
      public void onResponse(Call<UserDBResponse> call, Response<UserDBResponse> response) {

        UserDBResponse userDBResponse = response.body();
        ArrayList<User> users = new ArrayList<>();

        if (userDBResponse != null && userDBResponse.getUsers() != null) {
          users = (ArrayList<User>) userDBResponse.getUsers();

          callback.onResult(users, null, (long) 2);
        }
      }

      @Override
      public void onFailure(Call<UserDBResponse> call, Throwable t) {

      }
    });
  }

  // it used when to scroll up
  @Override
  public void loadBefore(@NonNull LoadParams<Long> params,
      @NonNull LoadCallback<Long, User> callback) {

  }

  //is called when natural scroll down
  @Override
  public void loadAfter(@NonNull final LoadParams<Long> params,
      @NonNull final LoadCallback<Long, User> callback) {

    userDataService = RetrofitInstance.getService();
    Call<UserDBResponse> call = userDataService.getUsersWithPaging(params.key);
    call.enqueue(new Callback<UserDBResponse>() {
      @Override
      public void onResponse(Call<UserDBResponse> call, Response<UserDBResponse> response) {

        UserDBResponse userDBResponse = response.body();
        ArrayList<User> users = new ArrayList<>();

        if (userDBResponse != null && userDBResponse.getUsers() != null) {
          users = (ArrayList<User>) userDBResponse.getUsers();

          callback.onResult(users, params.key + 1);
        }
      }

      @Override
      public void onFailure(Call<UserDBResponse> call, Throwable t) {

      }
    });
  }
}
