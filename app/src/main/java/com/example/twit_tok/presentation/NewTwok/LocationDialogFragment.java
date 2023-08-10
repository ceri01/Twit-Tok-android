package com.example.twit_tok.presentation.NewTwok;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import com.example.twit_tok.R;
import com.example.twit_tok.presentation.NoticeDialogLocationListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class LocationDialogFragment extends DialogFragment implements OnMapReadyCallback {
    NoticeDialogLocationListener listener;
    private GoogleMap map;
    private Location currentPosition;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public LocationDialogFragment(NoticeDialogLocationListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_location_dialog, container, false);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        getLocation();

        MaterialButton confirm = view.findViewById(R.id.confirm_position);
        MaterialButton cancel = view.findViewById(R.id.cancel_position);
        MaterialButton close = view.findViewById(R.id.close_set_location);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSetLocationPositiveClick(currentPosition);
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Objects.isNull(map)) {
                    currentPosition = null;
                    map.clear();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        return view;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_LOW_POWER, null).addOnSuccessListener(this.requireActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
                    mapFragment.getMapAsync(LocationDialogFragment.this);
                    currentPosition = location;
                }
            });
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng location = new LatLng(currentPosition.getLatitude(), currentPosition.getLongitude());
        map.addMarker(new MarkerOptions()
                .position(location)
                .title(getString(R.string.current_location)));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}
