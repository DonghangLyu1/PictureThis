package com.example.picturethis

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

//  This is the Class for customized view of image data

@SuppressLint("ViewConstructor")
class ItemView constructor(context: Context, item: Item) : ConstraintLayout(context)
{
	var mItem: Item = item

	private val mView: View = LayoutInflater.from(context).inflate(R.layout.item_view, findViewById(R.id.rootLayout), true)

	private var mIV: ImageView
	private var mNameTV: TextView
	private var mRating: TextView

	init
	{
		mIV = mView.findViewById(R.id.imageIV)
		mNameTV = mView.findViewById(R.id.nameTV)
		mRating = mView.findViewById(R.id.ratingTV)

		mIV.setImageResource(item.imageID)
		mNameTV.text = item.name
		mRating.text = item.rating.toString()

		this.addView(mView)
	}

	//  Update data when returned from the second activity
	fun update(item: Item)
	{
		mItem = item
		mRating.text = item.rating.toString()
	}
}