package com.rekkursion.enigma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rekkursion.enigma.R
import com.rekkursion.enigma.templates.InitializeEditVocabularyActivityTemplate

class EditVocabularyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vocabulary)

        InitializeEditVocabularyActivityTemplate(this).initialize()
    }
}
