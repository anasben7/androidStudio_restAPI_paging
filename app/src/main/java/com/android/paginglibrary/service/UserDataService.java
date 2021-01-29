package com.android.paginglibrary.service;

import com.android.paginglibrary.model.UserDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public interface UserDataService {
  @GET("users")
  Call<UserDBResponse> getUsers();

  @GET("users")
  Call<UserDBResponse> getUsersWithPaging(@Query("page") long page);
}
