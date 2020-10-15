package com.example.picturethis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemActivity : AppCompatActivity()
{
	private lateinit var mItem: Item

	private lateinit var mIV: ImageView
	private lateinit var mNameTV: TextView
	private lateinit var mDateTV: TextView
	private lateinit var mTypeET: EditText
	private lateinit var mRatingRB: RatingBar

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item)

		initUI()
	}

	//  To be invoked when the system back button is clicked
	override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean
	{
		if (event != null)
		{
			if (keyCode==KeyEvent.KEYCODE_BACK && mTypeET.text.toString().isNotEmpty())
			{
				mItem.type = mTypeET.text.toString()
				mItem.rating = mRatingRB.rating

				val intent = Intent()
				intent.putExtra("Item", mItem)
				setResult(Activity.RESULT_OK, intent)
				finish()
			}
		}

		return true
	}

	private fun initUI()
	{
		//  Get the parceled item instance from the intent object
		mItem = intent.getParcelableExtra<Item>("Item")!!

		//  The initialize control properties. Especially to correctly display rating bar
		mIV = findViewById(R.id.imageIV)
		mIV.setImageResource(mItem.imageID)

		mNameTV = findViewById(R.id.nameTV)
		mNameTV.text = mItem.name

		mDateTV = findViewById(R.id.dateTV)
		mDateTV.text = mItem.date.toString()

		mTypeET = findViewById(R.id.typeET)
		mTypeET.setText(mItem.type, TextView.BufferType.EDITABLE)

		mRatingRB = findViewById(R.id.ratingRB)
		mRatingRB.rating = mItem.rating
	}
}