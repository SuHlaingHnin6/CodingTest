package com.padcmyanmar.suhlaing.testgooglemap.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.padcmyanmar.suhlaing.testgooglemap.R
import com.padcmyanmar.suhlaing.testgooglemap.receiver.WifiReceiver
import kotlinx.android.synthetic.main.fragment_wifi.*

class WifiFragment : Fragment(){

    private var wifilist : ListView ?= null
    private var wifiManager : WifiManager ?= null
    private val MY_PERMISSION_ACESS_CROSE_LOCATION = 1
     var receiverwifi : WifiReceiver ?= null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wifi,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wifilist = getView()?.findViewById(R.id.listview)
        val btnScan = getView()?.findViewById<Button>(R.id.btnscanwifi)

         wifiManager = requireContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager

        if(!wifiManager!!.isWifiEnabled){
            Toast.makeText(requireContext(),"Turning wifi on",Toast.LENGTH_LONG).show()
            wifiManager!!.setWifiEnabled(true)
        }


        btnScan?.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
       ActivityCompat.requestPermissions(requireContext() as Activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),MY_PERMISSION_ACESS_CROSE_LOCATION)
            }else{
                wifiManager!!.startScan()
            }


        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onResume() {
        super.onResume()
  receiverwifi = WifiReceiver(wifiManager!!,wifilist!!)
        val intentInflater = IntentFilter()
            intentInflater.addAction(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION
            )
        requireActivity().registerReceiver(receiverwifi,intentInflater)

    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(receiverwifi)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSION_ACESS_CROSE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
    }

}