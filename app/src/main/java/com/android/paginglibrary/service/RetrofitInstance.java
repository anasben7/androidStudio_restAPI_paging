package com.android.paginglibrary.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class RetrofitInstance {
  private static Retrofit retrofit = null;
  private static String BASE_URL = "https://reqres.in/api/";

  public static UserDataService getService() {

    if (retrofit == null) {
      retrofit = new Retrofit
          .Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return retrofit.create(UserDataService.class);
  }
}
