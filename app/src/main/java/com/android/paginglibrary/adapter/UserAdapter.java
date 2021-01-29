package com.android.paginglibrary.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.paginglibrary.R;
import com.android.paginglibrary.databinding.UserListItemBinding;
import com.android.paginglibrary.model.User;
import com.android.paginglibrary.view.UserActivity;

/**
 * Created by MBP-de-Anas on 29,January,2021
 */

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHolder> {
  private Context context;

  public UserAdapter(Context context) {
    super(User.CALLBACK);
    this.context = context;
  }

  @NonNull
  @Override
  public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    UserListItemBinding userListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
            , R.layout.user_list_item, parent, false);

    return new UserViewHolder(userListItemBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    User user = getItem(position);
    user.setAvatar(user.getAvatar());

    holder.userListItemBinding.setUser(user);
  }

  public class UserViewHolder extends RecyclerView.ViewHolder {
    private UserListItemBinding userListItemBinding;

    public UserViewHolder(@NonNull UserListItemBinding userListItemBinding) {
      super(userListItemBinding.getRoot());
      this.userListItemBinding = userListItemBinding;

      userListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          int position = getAdapterPosition();

          if (position != RecyclerView.NO_POSITION) {

            User selectedUser = getItem(position);

            Intent intent = new Intent(context, UserActivity.class);
            intent.putExtra("user", selectedUser);
            context.startActivity(intent);
          }
        }
      });
    }
  }
}
