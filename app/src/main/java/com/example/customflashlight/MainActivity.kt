package com.example.customflashlight

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    var hasCameraFlash = false
    var flashEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleFlashlightButton: ImageButton = findViewById(R.id.toggleFlashLightButton)

        hasCameraFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        toggleFlashlightButton.setOnClickListener {
            if (hasCameraFlash) {
                if (flashEnabled) {
                    flashEnabled = false
                    toggleFlashlightButton.setImageResource(R.drawable.flashlight_off)

                    turnOffFlashLight()
                } else {
                    flashEnabled = true
                    toggleFlashlightButton.setImageResource(R.drawable.flashlight_on)

                    turnOnFlashLight()
                }
            }
        }
    }

    private fun turnOffFlashLight() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId: String
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, false)
        } catch (e: java.lang.Exception){}
    }

    private fun turnOnFlashLight() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId: String
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, true)
        } catch (e: java.lang.Exception){}
    }
}