package com.cs407.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val mDestinationLatLng = LatLng(43.0753, -89.4034) // Bascom Hall

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // 保存 GoogleMap 对象到 mMap 变量
        mMap = googleMap

        // 设置 Bascom Hall 的标记
        setLocationMarker(mDestinationLatLng, "Bascom Hall")
    }

    private fun setLocationMarker(destination: LatLng, destinationName: String) {
        // 在指定位置添加标记
        mMap.addMarker(
            MarkerOptions()
                .position(destination)
                .title(destinationName)
        )

        // 移动相机到标记位置并设置缩放级别为 15
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 15f))
    }

}