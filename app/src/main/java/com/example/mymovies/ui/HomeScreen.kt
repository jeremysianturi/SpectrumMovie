package com.example.mymovies.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import com.example.mymovies.R
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.ui.fragment.popularmovies.PopularMoviesFragment
import com.example.mymovies.ui.fragment.favorite.FavoriteFragment
import com.example.mymovies.ui.fragment.home.HomeFragment
import com.example.mymovies.ui.fragment.LocationFragment
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var content: ConstraintLayout? = null

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    var lat : String = ""
    var long : String = ""

    // map
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()

        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = HomeFragment.newInstance()
        addFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = HomeFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_award -> {
                val fragment = PopularMoviesFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_location -> {
                val fragment = LocationFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
//            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content_layout, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun setLatLong(lat: String, long: String){
        mPreferenceEntity.lat = lat
        mPreferenceEntity.long = long
    }

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        lat = location.latitude.toString()
                        long = location.longitude.toString()
                        Timber.d("check location: \n latitude: $lat \n longitude: $long")
                        setLatLong(lat, long)
                        mPreference.setPref(mPreferenceEntity)
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            lat = mLastLocation.latitude.toString()
            long = mLastLocation.longitude.toString()
            Timber.d("check location: \n latitude: $lat \n longitude: $long")
            setLatLong(lat, long)
            mPreference.setPref(mPreferenceEntity)
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }
}