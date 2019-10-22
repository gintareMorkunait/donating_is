package ktu.edu.donatingis;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_place, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng center1 = new LatLng(54.888243, 23.928051);
        LatLng center2 = new LatLng(55.730172, 24.340705);
        LatLng center3 = new LatLng(55.940379, 23.304867);
        LatLng center4 = new LatLng(54.693618, 25.276677);
        LatLng center5 = new LatLng(55.679012, 21.157206);


        mMap.addMarker(new MarkerOptions().position(center1).title("Nacionalinio kraujo centras, Kauno filialas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center1));

        mMap.addMarker(new MarkerOptions().position(center2).title("Nacionalinis kraujo centras, Panevėžio filialas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center2));

        mMap.addMarker(new MarkerOptions().position(center3).title("Nacionalinis kraujo centras, Šiaulių filialas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center3));

        mMap.addMarker(new MarkerOptions().position(center4).title("Nacionalinis Kraujo Centras VCUP"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center4));

        mMap.addMarker(new MarkerOptions().position(center5).title("Nacionalinis kraujo centras, Klaipėdos filialas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center5));
    }
}