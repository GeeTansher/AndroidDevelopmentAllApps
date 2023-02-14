// Generated by view binder compiler. Do not edit!
package com.example.geotag.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.geotag.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button BTNsaveImage;

  @NonNull
  public final EditText ETtext;

  @NonNull
  public final EditText ETtext1;

  @NonNull
  public final ImageButton btnRefresh;

  @NonNull
  public final Button button;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final CheckBox invertDimen;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView, @NonNull Button BTNsaveImage,
      @NonNull EditText ETtext, @NonNull EditText ETtext1, @NonNull ImageButton btnRefresh,
      @NonNull Button button, @NonNull ImageView imageView, @NonNull CheckBox invertDimen) {
    this.rootView = rootView;
    this.BTNsaveImage = BTNsaveImage;
    this.ETtext = ETtext;
    this.ETtext1 = ETtext1;
    this.btnRefresh = btnRefresh;
    this.button = button;
    this.imageView = imageView;
    this.invertDimen = invertDimen;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BTNsaveImage;
      Button BTNsaveImage = ViewBindings.findChildViewById(rootView, id);
      if (BTNsaveImage == null) {
        break missingId;
      }

      id = R.id.ETtext;
      EditText ETtext = ViewBindings.findChildViewById(rootView, id);
      if (ETtext == null) {
        break missingId;
      }

      id = R.id.ETtext1;
      EditText ETtext1 = ViewBindings.findChildViewById(rootView, id);
      if (ETtext1 == null) {
        break missingId;
      }

      id = R.id.btnRefresh;
      ImageButton btnRefresh = ViewBindings.findChildViewById(rootView, id);
      if (btnRefresh == null) {
        break missingId;
      }

      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.invertDimen;
      CheckBox invertDimen = ViewBindings.findChildViewById(rootView, id);
      if (invertDimen == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, BTNsaveImage, ETtext, ETtext1,
          btnRefresh, button, imageView, invertDimen);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}