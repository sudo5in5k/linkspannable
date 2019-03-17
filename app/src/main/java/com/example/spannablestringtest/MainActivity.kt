package com.example.spannablestringtest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        link_text.apply {
            text = createLinkSpannable()
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun createLinkSpannable(): SpannableStringBuilder {
        val highLightText = getString(R.string.high_light_text)
        val introductionText = getString(R.string.introduction_text)

        val spannableTextBuilder = SpannableStringBuilder(introductionText)
        val startPos = introductionText.indexOf(highLightText)
        return spannableTextBuilder.apply {
            setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
                    view.context.startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
                }
            }, startPos, startPos + highLightText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}
