package com.example.mymovies.ui.fragment

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import com.example.mymovies.databinding.FragmentLocationBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


class LocationFragment : Fragment() {

    private var map: MapView? = null
    private var mapController: IMapController? = null
    private val TAG = "OsmActivity"
    private val PERMISSION_REQUEST_CODE = 1
    var myLocationOverlay: MyLocationNewOverlay? = null

    private var lat : Double? = 0.0
    private var long : Double? = 0.0

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    private lateinit var binding: FragmentLocationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPreference = UserPreference(requireContext())
        mPreferenceEntity = mPreference.getPref()

        lat = mPreferenceEntity.lat?.toDouble()
        long = mPreferenceEntity.long?.toDouble()

//        //handle permissions first, before map is created. not depicted here
//
//
//        //load/initialize the osmdroid configuration, this can be done
//        //handle permissions first, before map is created. not depicted here
//
//
//        //load/initialize the osmdroid configuration, this can be done
//        val ctx: Context = requireActivity().applicationContext
//        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))
//        //setting this before the layout is inflated is a good idea
//        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
//        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
//        //see also StorageUtils
//        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string
//
//        //inflate and create the map
//
//        //setting this before the layout is inflated is a good idea
//        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
//        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
//        //see also StorageUtils
//        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string
//
//
//        if (Build.VERSION.SDK_INT >= 23) {
////            if (isStoragePermissionGranted()) {
////            }
//        }
//
//
//        map = binding.mapView
//        map!!.setTileSource(TileSourceFactory.MAPNIK)
//        map!!.setBuiltInZoomControls(true)
//        map!!.setMultiTouchControls(true)
//        mapController = map?.controller
//        mapController?.setZoom(15)
//        val startPoint = GeoPoint(51496994, -134733)
//        mapController?.setCenter(startPoint)


        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        val ctx: Context = requireActivity().applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils

        //Get Layout
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils


        map = binding.mapView
        map!!.setTileSource(TileSourceFactory.MAPNIK)

        map!!.zoomController.setVisibility(CustomZoomButtonsController.Visibility.ALWAYS)
        map!!.setMultiTouchControls(true)

        // Position from LocationListener or by predefinied position like below

        // Position from LocationListener or by predefinied position like below
//        map!!.controller.setCenter(GeoPoint(48.8583, 2.2944)) // example
        map!!.controller.setCenter(GeoPoint(lat!!, long!!))
        // Kann erst gesetzt werden wenn eine Position bekannt ist.
        // Kann erst gesetzt werden wenn eine Position bekannt ist.
        map!!.controller.setZoom(18.0)

        /**
         *Now, I tried to set the symbol (without GNSS), but it does not work for a predefined position.
         *
         * Is it possibe to replace the function GpsMyLocationProvider() by new position (predefined position) like GeoPoint
         */
        /**
         * Now, I tried to set the symbol (without GNSS), but it does not work for a predefined position.
         *
         * Is it possibe to replace the function GpsMyLocationProvider() by new position (predefined position) like GeoPoint
         */
        this.myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(activity), map)
        this.myLocationOverlay!!.enableMyLocation()
        map!!.overlays.add(this.myLocationOverlay)

    }

    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        if (map != null)
            map!!.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        if (map != null)
            map!!.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }





}