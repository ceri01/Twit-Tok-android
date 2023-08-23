package com.example.twit_tok.presentation.wall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.twit_tok.R;
import com.example.twit_tok.common.TwoksUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class DisplayLocationDialogFragment extends DialogFragment implements OnMapReadyCallback {
    private final Double latitude;
    private final Double longiture;

    public DisplayLocationDialogFragment(Double latitude, Double longiture) {
        if (!TwoksUtils.isValidCoord(latitude, longiture)) {
            this.latitude = null;
            this.longiture = null;
        } else {
            this.latitude = latitude;
            this.longiture = longiture;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_location_dialog, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map_display_location);
        mapFragment.getMapAsync(DisplayLocationDialogFragment.this);

        MaterialButton close = view.findViewById(R.id.close_display_location);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location = new LatLng(latitude, longiture);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(getString(R.string.current_location)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}
