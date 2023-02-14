// Generated by view binder compiler. Do not edit!
package com.example.geotag.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.geotag.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LocRowLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout locRowLayout;

  @NonNull
  public final TextView tvAdd;

  @NonNull
  public final TextView tvTitle;

  private LocRowLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout locRowLayout, @NonNull TextView tvAdd, @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.locRowLayout = locRowLayout;
    this.tvAdd = tvAdd;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LocRowLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LocRowLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.loc_row_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LocRowLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout locRowLayout = (ConstraintLayout) rootView;

      id = R.id.tvAdd;
      TextView tvAdd = ViewBindings.findChildViewById(rootView, id);
      if (tvAdd == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new LocRowLayoutBinding((ConstraintLayout) rootView, locRowLayout, tvAdd, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}