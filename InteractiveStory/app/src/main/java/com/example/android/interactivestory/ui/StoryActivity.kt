package com.example.android.interactivestory.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.android.interactivestory.R
import com.example.android.interactivestory.model.Page
import com.example.android.interactivestory.model.Story
import kotlinx.android.synthetic.main.activity_story.*
import java.util.*

class StoryActivity : AppCompatActivity() {

    var story: Story? = null
    var name: String? = null

    var mStoryImageView: ImageView? = null
    var mStoryTextView: TextView? = null
    var mChoice1Button: Button? = null
    var mChoice2Button: Button? = null

    var pageStack: Stack<Int> = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        mStoryImageView = iv_story
        mStoryTextView = tv_story
        mChoice1Button = bu_choice1
        mChoice2Button = bu_choice2

        var intent: Intent = getIntent()
        name = intent.getStringExtra(getString(R.string.key_name))

        story = Story()
        loadPage(0)

    }

    fun loadPage(pageNumber: Int) {

        pageStack.push(pageNumber)

        var page: Page? = story!!.getPage(pageNumber)

        var image: Drawable = ContextCompat.getDrawable(this, page!!.imageId!!)
        mStoryImageView!!.setImageDrawable(image)

        var pageText: String = getString(page.textId!!)

        // Add name if placeholder is included, won't add if not
        pageText = String.format(pageText, name)
        mStoryTextView!!.text = pageText


        if (page.isFinalPage) {
            mChoice1Button!!.visibility = View.INVISIBLE
            mChoice2Button!!.text = getString(R.string.play_again_button)
            mChoice2Button!!.setOnClickListener {
                loadPage(0)
            }
        } else {
            loadButtons(page)
        }




    }

    private fun loadButtons(page: Page) {

        mChoice1Button!!.visibility = View.VISIBLE
        mChoice2Button!!.visibility = View.VISIBLE

        // using .text = shows an int so had to use .setText instead
        mChoice1Button!!.setText(page.choice1!!.textId)

        mChoice1Button!!.setOnClickListener {
            var nextPage: Int = page.choice1!!.nextPage
            loadPage(nextPage)
        }

        mChoice2Button!!.setText(page.choice2!!.textId)

        mChoice2Button!!.setOnClickListener {
            var nextPage: Int = page.choice2!!.nextPage
            loadPage(nextPage)
        }
    }

    override fun onBackPressed() {
        pageStack.pop()
        if (pageStack.isEmpty()) {
            super.onBackPressed()
        } else {
            loadPage(pageStack.pop())
        }
    }

}



















