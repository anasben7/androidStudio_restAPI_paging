package com.android.paginglibrary.model;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import com.android.paginglibrary.service.RetrofitInstance;
import com.android.paginglibrary.service.UserDataService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class UserRepository {
  private ArrayList<User> users = new ArrayList<>();
  private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
  private Application application;

  public UserRepository(Application application) {
    this.application = application;
  }

  public MutableLiveData<List<User>> getMutableLiveData() {

    final UserDataService userDataService = RetrofitInstance.getService();

    Call<UserDBResponse> call = userDataService.getUsers();

    call.enqueue(new Callback<UserDBResponse>() {
      @Override
      public void onResponse(Call<UserDBResponse> call, Response<UserDBResponse> response) {
        UserDBResponse userDBResponse = response.body();

        if (userDataService != null && userDBResponse.getUsers() != null) {

          users = (ArrayList<User>) userDBResponse.getUsers();
          mutableLiveData.setValue(users);
        }
      }

      @Override
      public void onFailure(Call<UserDBResponse> call, Throwable t) {

      }
    });

    return mutableLiveData;
  }
}
