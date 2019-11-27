package com.edson.mapsgooglesdk;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mudar a exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker  and move the camera
        final LatLng ibirapuera = new LatLng(-23.587097, -46.657635);

      /*  //desenhando dentro do mapa
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(ibirapuera);
        circleOptions.radius(500); //medida em metros
        circleOptions.strokeWidth(15);//borda
        circleOptions.strokeColor(Color.MAGENTA);//cor da borda
        circleOptions.fillColor(Color.argb(130,0,102,255));//0 até 255 o alpha, 0 não aparece e 255 totalmente opaco

        mMap.addCircle(circleOptions);
*/

   /*   //desenhando poligonos //as configurações de borda e cor tbm se aplicam ao polygonOptions
        final PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754));
        polygonOptions.add(new LatLng(-23.585615, -46.656662));
        polygonOptions.add(new LatLng(-23.587158, -46.657037));
        polygonOptions.add(new LatLng(-23.587247, -46.658797));

        mMap.addPolygon(polygonOptions);*/

        //eventos de clique
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(ibirapuera);
                polylineOptions.add(latLng);
                polylineOptions.color(Color.BLUE);
                polylineOptions.width(20);

                mMap.addPolyline(polylineOptions);

                //  Toast.makeText(MapsActivity.this, "Latitude: " + latitude +"\n"+ "Londitude: " +longitude, Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Descricao") //add uma descrição
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_loja))


                );

            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this, "ON LONG CLICK" + "Latitude: " + latitude + "\n" + "Londitude: " + longitude, Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Descricao") //add uma descrição
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_carro_roxo_96px))


                );

            }
        });
        mMap.addMarker(
                new MarkerOptions()
                        .position(ibirapuera)
                        .title("Ibirapuera")
                        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)) //muda a cor do marcador
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_carro_roxo_48px))


        );

        mMap.moveCamera( //o valor do zoom/float varia de 2.0 até 21.0
                CameraUpdateFactory.newLatLngZoom(ibirapuera, 15)

        );



    }
}
