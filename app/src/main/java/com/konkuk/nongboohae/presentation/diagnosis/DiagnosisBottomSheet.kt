package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.DialogDiagnosisBottomSheetBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

/*
액티비티에서 다음과 같이 호출하여 사용가능
val modal = DiagnosisBottomSheet()
modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.TransParentBottomSheetDialogTheme)
modal.show(supportFragmentManager, DiagnosisBottomSheet.TAG)
*/

class DiagnosisBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDiagnosisBottomSheetBinding
    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    val REQUEST_TAKE_PHOTO = 1
    lateinit var currentPhotoPath: String
    private var getPhotoURI: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogDiagnosisBottomSheetBinding.inflate(inflater, container, false)
        afterBinding()
        return binding.root
    }

    private fun afterBinding() {
        Glide.with(requireActivity()).load("https://www.farmnmarket.com/data/photos/20220831/art_16598729535107_00d7c4.jpg").into(binding.diagnosisBottomSheetCrop1Iv)
        Glide.with(requireActivity()).load("https://image.yes24.com/images/chyes24/d/1/c/1/d1c1ec15e22d9ed5b88a054464886774.jpg").into(binding.diagnosisBottomSheetCrop2Iv)
        Glide.with(requireActivity()).load("https://www.amnews.co.kr/news/photo/202011/44247_31347_2423.jpg").into(binding.diagnosisBottomSheetCrop3Iv)
        binding.diagnosisBottomSheetCrop1Cv.setOnClickListener {
            requestCameraPermission()
        }
        binding.diagnosisBottomSheetCrop2Cv.setOnClickListener {
            requestCameraPermission()
        }
        binding.diagnosisBottomSheetCrop3Cv.setOnClickListener {
            requestCameraPermission()
        }
    }

    companion object {
        const val TAG = "DiagnosisBottomSheet"
    }

    private fun requestCameraPermission() {
        Log.d("bottomSheet", "카메라 권한 요청")
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            callCamera() // 이미 권한이 허용된 경우
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera()
            } else {
                val toast = Toast.makeText(requireActivity(), "카메라 권한을 승인해 주세요", Toast.LENGTH_SHORT)
                toast?.show()
            }
        }
    }

    private fun callCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireActivity(),
                        "com.konkuk.nongboohae.fileprovider",
                        it
                    )
                    getPhotoURI = photoURI
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK) {
            Log.d("photoUri", getPhotoURI.toString()) // 이미지 URI 획득!!
            // 서버통신 구현 필요
            // 모델로부터 값을 받아오면 진단 결과화면으로 전환
            val intent = Intent(requireActivity(), DiagnosisResultActivity::class.java)
            intent.putExtra("photoUri", getPhotoURI.toString())
            intent.putExtra("currentPhotoPath", currentPhotoPath.toString())
            startActivity(intent)
            dismiss()
        }
    }

}