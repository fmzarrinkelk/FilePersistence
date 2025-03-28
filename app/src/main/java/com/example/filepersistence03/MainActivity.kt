package com.example.filepersistence03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import android.Manifest
import android.graphics.BitmapFactory
import android.os.Environment
import android.widget.ImageView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var textField: TextView
    private lateinit var imageView: ImageView

    private var textInputModel = UserTextInputModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textField = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val t = textField.text
            if (t != null) {
                textInputModel.saveText(t.toString())
            }
        }

        textField.text = textInputModel.loadText()

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            0
        )

        loadAnImage()
    }

    private fun loadAnImage() {
        val storageRoot = Environment.getExternalStorageDirectory()
        val downloadsDir = File(storageRoot, "Download")
        val fileList = downloadsDir.listFiles()
        val myFile = fileList?.get(1)
        if (myFile != null && myFile.isFile) {
            val bytes = myFile.readBytes()
            val myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            this.imageView.setImageBitmap(myBitmap)
        }
    }
}