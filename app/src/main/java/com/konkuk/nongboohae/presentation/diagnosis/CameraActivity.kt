package com.konkuk.nongboohae.presentation.diagnosis

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityCameraBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import java.util.concurrent.ExecutorService

class CameraActivity : BaseActivity<ActivityCameraBinding>() {
    override val TAG: String = "CameraActivity"
    override val layoutRes: Int = R.layout.activity_camera

    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun initViewModel() {

    }

    override fun afterViewCreated() {
        requestPermission()
        binding.captureBtn.setOnClickListener {
            takePhoto()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun requestPermission() {
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "권한이 거부되었습니다",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    private fun startCamera() {
        //ProcessCameraProvider 인스턴스 생성. 카메라의 수명 주기를 액티비티와 바인딩하므로 카메라를 열고 닫는 작업이 필요없어짐
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        //future에 리스너 추가
        cameraProviderFuture.addListener(
            //인자1: Runnable
            {
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                //preview객체 초기화, build 호출, binding의 previewView에서 노출 영역 제공자를 받아 미리보기에 설정
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(binding.previewView.surfaceProvider)
                    }

                // 후면 카메라를 디폴트로 설정
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // rebinding 전에 바인딩된 객체 초기화
                    cameraProvider.unbindAll()

                    // selector와 미리보기 객체를 provider에 바인딩함
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview
                    )

                } catch (exc: Exception) {
                    //앱에 포커스가 없는 경우와 같은 케이스스                    Log.e(TAG, "Use case binding failed", exc)
                }

            }
            //인자2: ContextCompat.getMainExecutor >> 기본 스레드에서 실행되는 Executor 반환됨
            , ContextCompat.getMainExecutor(this)
        )
    }

    private fun takePhoto() {

    }
}