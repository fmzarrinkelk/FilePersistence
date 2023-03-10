package com.example.filepersistence03

import android.content.Context
import java.io.File

class UserTextInputModel(_context: Context) {
    private var context = _context
    private val textFileName = "userInput.txt"

    private fun makeFile(): File {
        return File(this.context.filesDir, this.textFileName)
    }

    fun saveText(s: String) {
        val file = this.makeFile()
        file.delete()
        file.writeText(s)
    }

    fun loadText(): String {
        val file = this.makeFile()
        return if (file.exists()) {
            file.readText()
        } else {
            ""
        }
    }
}