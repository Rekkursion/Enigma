package com.rekkursion.enigma.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rekkursion.enigma.R
import com.rekkursion.enigma.templates.InitializeVocabularyActivityTemplate

class VocabularyActivity: AppCompatActivity() {
    companion object {
        internal const val REQ_GO_TO_EDIT_VOCABULARY_ACTIVITY: Int = 8620
    }

    /* ================================================================== */

    // the initializer of this activity
    private lateinit var mInitializer: InitializeVocabularyActivityTemplate

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        mInitializer = InitializeVocabularyActivityTemplate(this)
        mInitializer.initialize()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_GO_TO_EDIT_VOCABULARY_ACTIVITY)
            mInitializer.updateViewPager2()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
