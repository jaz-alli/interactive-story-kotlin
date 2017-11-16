package com.example.android.interactivestory.ui

import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.android.interactivestory.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mNameEditText: EditText? = null
    var mStartButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNameEditText = et_name
        mStartButton = bu_start

        mStartButton!!.setOnClickListener {
            var name: String = mNameEditText!!.text.toString()
            if (name == null || name.isBlank()) {
                Toast.makeText(this, getString(R.string.toast_enter_name), Toast.LENGTH_LONG).show()
            } else {
                startStory(name)
            }

        }

    }

    override fun onResume() {
        super.onResume()
        mNameEditText!!.setText("")
    }

    fun startStory(name: String) {
        var intent = Intent(this, StoryActivity::class.java)
        var resources: Resources = resources
        var key: String = resources.getString(R.string.key_name)
        intent.putExtra(key, name)
                startActivity(intent)
    }

}
