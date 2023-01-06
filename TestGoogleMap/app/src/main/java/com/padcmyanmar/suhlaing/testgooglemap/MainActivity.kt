package com.padcmyanmar.suhlaing.testgooglemap

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.padcmyanmar.suhlaing.testgooglemap.fragment.BluetoothFragment
import com.padcmyanmar.suhlaing.testgooglemap.fragment.MapsFragment
import com.padcmyanmar.suhlaing.testgooglemap.fragment.WifiFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchFragment(MapsFragment())

        bottom_nav.setOnNavigationItemSelectedListener {  menuItem: MenuItem ->

            when(menuItem.itemId){
                R.id.icon_map->{
                    switchFragment(MapsFragment())
                }
                R.id.icon_wifi ->{
                    switchFragment(WifiFragment())
                }
                R.id.icon_bluetooth ->{
                    switchFragment(BluetoothFragment())
                }
            }
            true
        }
    }

    private fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_container,fragment).commit()

    }
}