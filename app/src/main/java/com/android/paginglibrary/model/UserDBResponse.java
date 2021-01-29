package com.android.paginglibrary.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class UserDBResponse implements Parcelable {

  @SerializedName("page")
  @Expose
  private Integer page;
  @SerializedName("per_page")
  @Expose
  private Integer perPage;
  @SerializedName("total")
  @Expose
  private Integer total;
  @SerializedName("total_pages")
  @Expose
  private Integer totalPages;
  @SerializedName("data")
  @Expose
  private List<User> users = null;

  public final static Parcelable.Creator<UserDBResponse> CREATOR = new Creator<UserDBResponse>() {

    public UserDBResponse createFromParcel(Parcel in) {
      return new UserDBResponse(in);
    }

    public UserDBResponse[] newArray(int size) {
      return (new UserDBResponse[size]);
    }
  };

  protected UserDBResponse(Parcel in) {
    this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.perPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
    this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    in.readList(this.users, (java.lang.Object.class.getClassLoader()));
  }

  public UserDBResponse() {
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> data) {
    this.users = data;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(page);
    dest.writeValue(perPage);
    dest.writeValue(total);
    dest.writeValue(totalPages);
    dest.writeList(users);
  }

  public int describeContents() {
    return 0;
  }
}