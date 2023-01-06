package com.padcmyanmar.suhlaing.testgooglemap.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.padcmyanmar.suhlaing.testgooglemap.R
import com.padcmyanmar.suhlaing.testgooglemap.databinding.FragmentMapsBinding
import kotlinx.android.synthetic.main.fragment_maps.*
import java.util.Locale

class MapsFragment : Fragment(), OnMapReadyCallback {
    //,OnMapReadyCallback

    private lateinit var binding : FragmentMapsBinding
    private lateinit var mapFragment : SupportMapFragment
    private lateinit var mMap : GoogleMap
    private lateinit var fusedlocationProviderClient : FusedLocationProviderClient
    private lateinit var currentlocation : Location
    private var permissionCode = 101


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMapsBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

          fusedlocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

//        mapInitialize()
    }


//    private fun mapInitialize(){
//        val locationRequest = LocationRequest()
//        locationRequest.setInterval(5000)
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//        locationRequest.setSmallestDisplacement(16F)
//        locationRequest.setFastestInterval(3000)
//        val builder = LocationSettingsRequest.Builder()
//            .addLocationRequest(locationRequest)
//        builder.setAlwaysShow(true)
//
//        val result = LocationServices.getSettingsClient(requireContext())
//            .checkLocationSettings(builder.build())
//
//        result.addOnCompleteListener{task->
//
//        }
//
//    }

    override fun onMapReady(googlemap: GoogleMap) {

        mMap = googlemap
        Dexter.withContext(context)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener{

                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                    if(ActivityCompat.checkSelfPermission(context!!,Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            context!!,Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)

                        return
                    }
                    mMap.isMyLocationEnabled = true
                    fusedlocationProviderClient.lastLocation.addOnSuccessListener { location->
                        if (location != null) {
                            currentlocation = location
                            Toast.makeText(
                                requireContext(), currentlocation.latitude.toString() + ""
                                        + currentlocation.longitude.toString(), Toast.LENGTH_LONG
                            ).show()

                            val latlng = LatLng(currentlocation.latitude, currentlocation.longitude)
                            val makerOptions = MarkerOptions().position(latlng)
                                .title(currentlocation.latitude.toString() + "," + currentlocation.longitude.toString())

                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng))
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17F))
                            mMap.addMarker(makerOptions)
                        }
                    }
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }

            })
            .check()

    }


}




