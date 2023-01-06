//package com.padcmyanmar.suhlaing.testgooglemap.receiver
//
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothDevice
//import android.bluetooth.BluetoothManager
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.net.wifi.ScanResult
//import android.widget.ListView
//
//class BluetoothReciver(var bluetoothAdapter : BluetoothAdapter, bluetoothDeviceList : ListView) : BroadcastReceiver() {
//
//    var sb : StringBuilder ?= null
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        if (bluetoothAdapter.isEnabled){
//            sb = StringBuilder()
//
//            val devices = bluetoothAdapter.bondedDevices
//            for (device in devices) {
//                var deviceName = device.name
//                val deviceAddress = device
//                sb!!.append("\n").append(deviceName).append("-").append(deviceAddress)
//            }
//        }
//    }
//}