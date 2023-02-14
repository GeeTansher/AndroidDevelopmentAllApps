// Generated by view binder compiler. Do not edit!
package com.example.geotag.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.geotag.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentListBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final FloatingActionButton btnAddLoc;

  @NonNull
  public final RelativeLayout container;

  @NonNull
  public final RecyclerView rvList;

  @NonNull
  public final TextView tvEmpty;

  private FragmentListBinding(@NonNull RelativeLayout rootView,
      @NonNull FloatingActionButton btnAddLoc, @NonNull RelativeLayout container,
      @NonNull RecyclerView rvList, @NonNull TextView tvEmpty) {
    this.rootView = rootView;
    this.btnAddLoc = btnAddLoc;
    this.container = container;
    this.rvList = rvList;
    this.tvEmpty = tvEmpty;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddLoc;
      FloatingActionButton btnAddLoc = ViewBindings.findChildViewById(rootView, id);
      if (btnAddLoc == null) {
        break missingId;
      }

      RelativeLayout container = (RelativeLayout) rootView;

      id = R.id.rvList;
      RecyclerView rvList = ViewBindings.findChildViewById(rootView, id);
      if (rvList == null) {
        break missingId;
      }

      id = R.id.tvEmpty;
      TextView tvEmpty = ViewBindings.findChildViewById(rootView, id);
      if (tvEmpty == null) {
        break missingId;
      }

      return new FragmentListBinding((RelativeLayout) rootView, btnAddLoc, container, rvList,
          tvEmpty);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
