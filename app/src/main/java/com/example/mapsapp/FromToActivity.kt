package com.example.mapsapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_from_to.*
import java.io.IOException
import java.util.*


class FromToActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var mMap: GoogleMap
    var fromIsValid: Boolean = false
    var toIsValid: Boolean = false
    private var locationManager: LocationManager? = null
    private val MIN_TIME: Long = 400
    private val MIN_DISTANCE = 1000f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_from_to)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        search.setOnClickListener {
            openLocationInMap(mAddress = from.text.toString(),offset = 0)
            openLocationInMap(mAddress = to.text.toString(),offset = 1)
            addMarkerToPetrolPump()
        }

        bus.setOnClickListener {
            val intent = Intent(applicationContext, RecommendationActivity::class.java)
            intent.putExtra("type", "Bus")
            startActivity(intent)
        }

        taxi.setOnClickListener {
            val intent = Intent(applicationContext, RecommendationActivity::class.java)
            intent.putExtra("type", "Taxi")
            startActivity(intent)
        }
        autoRickshaw.setOnClickListener {
            val intent = Intent(applicationContext, RecommendationActivity::class.java)
            intent.putExtra("type", "AutoRickshaw")
            startActivity(intent)
        }

        person.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }

        bluetooth.setOnClickListener {
            Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_LONG).show()
        }

        metro.setOnClickListener {
            Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_LONG).show()
        }
    }

    private fun addMarkerToPetrolPump() {
        val petrol = listOf(LatLng(28.6204699,77.0732799), LatLng(28.6104928,77.0415965) ,
            LatLng(28.6636581,77.0665111), LatLng(28.6775397,77.0320948), LatLng(28.6775397,77.0320948),
            LatLng(28.6775397,77.0320948), LatLng(28.612417,76.9445361), LatLng(28.612417,76.9445361),
            LatLng(28.612417,76.9445361), LatLng(28.612417,76.9445361)
        )
        petrol.forEach {
            val location = it
            mMap.addMarker(MarkerOptions().position(location).title("Sanitization Center").icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ))
        }
    }
    fun openLocationInMap(mAddress: String, offset: Int){
        val geocoder = Geocoder(this, Locale.getDefault())
        var addresses: List<Address?>? = null
        try {
            //To put the address in list
            addresses = geocoder.getFromLocationName(mAddress, 1)
            val address: Address? = addresses?.get(0)
            val mLongitude = address?.getLongitude()?:0.0
            val mlatitude = address?.getLatitude()?:0.0

            val location = LatLng(mlatitude, mLongitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0f))
            mMap.addMarker(MarkerOptions().position(location).title(mAddress))
            if (offset == 0)
                fromIsValid = true
            else
                toIsValid = true

            if (fromIsValid and toIsValid){
                horizontalView.isVisible = true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);

    }

    override fun onLocationChanged(location: Location?) {
        val latLng = LatLng(location!!.getLatitude(), location.getLongitude())
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10f)
        mMap.animateCamera(cameraUpdate)
        locationManager!!.removeUpdates(this)
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }
}