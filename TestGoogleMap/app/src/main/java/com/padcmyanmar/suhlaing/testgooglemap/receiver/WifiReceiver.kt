package com.padcmyanmar.suhlaing.testgooglemap.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class WifiReceiver (var wifiManager: WifiManager,  wifiDeviceList : ListView) : BroadcastReceiver() {

     var sb : StringBuilder ?= null
     var wifiDeviceList : ListView ?= null

        override fun onReceive(content: Context?, intent: Intent?) {
        val action = intent?.action
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION == action )
            sb = StringBuilder()
        var wifilist : List<ScanResult> = wifiManager.scanResults
        var deviceList : ArrayList<String> = ArrayList()
        for (scanResult in wifilist){
            sb!!.append("\n").append(scanResult.SSID)
//                .append(" - ")
//                .append(scanResult.capabilities)
            deviceList.add(
                scanResult.SSID.toString()
//                        + " - " + scanResult.capabilities
           )

        }
//            Toast.makeText(content, sb, Toast.LENGTH_SHORT).show();

        val arrayAdapter : ArrayAdapter<*> = ArrayAdapter(content!!.applicationContext,
            android.R.layout.simple_list_item_1, deviceList.toArray())

        wifiDeviceList?.adapter = arrayAdapter
    }

    init {
        this.wifiDeviceList = wifiDeviceList
    }
}