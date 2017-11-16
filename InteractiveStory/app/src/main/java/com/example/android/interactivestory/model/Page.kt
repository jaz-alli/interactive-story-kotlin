package com.example.android.interactivestory.model

class Page {
    var imageId:Int? = null
    var textId:Int? = null
    var choice1:Choice? = null
    var choice2:Choice? = null
    var isFinalPage: Boolean = false

    constructor(imageId:Int, textId:Int) {
        this.imageId = imageId
        this.textId = textId
        this.isFinalPage = true
    }

    constructor(imageId:Int, textId:Int, choice1:Choice, choice2:Choice) {
        this.imageId = imageId
        this.textId = textId
        this.choice1 = choice1
        this.choice2 = choice2
    }
}