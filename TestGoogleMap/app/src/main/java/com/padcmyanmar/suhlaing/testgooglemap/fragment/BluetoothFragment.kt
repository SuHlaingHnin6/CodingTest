package com.padcmyanmar.suhlaing.testgooglemap.fragment

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.padcmyanmar.suhlaing.testgooglemap.R
import kotlinx.android.synthetic.main.fragment_bluetooth.*

class BluetoothFragment : Fragment() {

    private lateinit var bAdapter : BluetoothAdapter
    private var bluetoothManager : BluetoothManager?= null
    private var REQUEST_DISCOVERABLE_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_bluetooth,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBluetoothScan.setOnClickListener {
            
        }

//        bAdapter = BluetoothAdapter.getDefaultAdapter()
//        if(bAdapter == null){
//            Toast.makeText(context,"Bluetooth is not available",Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(context,"Bluetooth is available",Toast.LENGTH_LONG).show()
//        }

//        btnBluetoothScan.setOnClickListener {
//            if (ActivityCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.BLUETOOTH_SCAN
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                if(bAdapter.isEnabled){
//                    val device = bAdapter.bondedDevices
//                    for (device in device){
//                        val deviceName = device.name
//                        val deviceAddress = device
//                        BluetoothListView.append()
//                    }
//                }
//            }
//        }
    }

//    if (!bAdapter.isDiscovering){
//        Toast.makeText(context,"Making your device is discoverable",Toast.LENGTH_LONG).show()
//        var intent = Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
//        startActivityForResult(intent,REQUEST_DISCOVERABLE_CODE)
//    }
}