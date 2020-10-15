package com.example.picturethis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{
	private lateinit var kLayout: ConstraintLayout
	private val kViews = arrayOfNulls<ItemView>(4)

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		//  Get the Super Constraint Layout in order to add customized views dynamically in the future
		kLayout = findViewById(R.id.constraintLayout)

		initData()
	}

	private fun initData()
	{
		val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val dm = DisplayMetrics()
		wm.defaultDisplay.getMetrics(dm)

		kLayout.removeAllViews()

		//  Four Views for the Four Models
		val iv0 = ItemView(this, Item(0, R.drawable.chocolate, "Chocolate", Date(), "Dessert", 4.0f))
		val iv1 = ItemView(this, Item(1, R.drawable.cake, "Cake", Date(), "Dessert", 2.0f))
		val iv2 = ItemView(this, Item(2, R.drawable.apple, "Apple", Date(), "Fruit", 3.0f))
		val iv3 = ItemView(this, Item(3, R.drawable.burger, "Burger", Date(), "Main Food", 4.0f))

		kViews[0] = iv0
		kViews[1] = iv1
		kViews[2] = iv2
		kViews[3] = iv3

		iv0.setOnClickListener(this)
		iv0.id = View.generateViewId()

		iv1.setOnClickListener(this)
		iv1.id = View.generateViewId()

		iv2.setOnClickListener(this)
		iv2.id = View.generateViewId()

		iv3.setOnClickListener(this)
		iv3.id = View.generateViewId()

		//  Add the four customized views
		kLayout.addView(iv0)
		kLayout.addView(iv1)
		kLayout.addView(iv2)
		kLayout.addView(iv3)

		val set = ConstraintSet()
		set.clone(kLayout)

		//  Set constraints for the four customized views
		set.connect(iv0.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
		set.connect(iv0.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
		set.connect(iv0.id, ConstraintSet.END, iv1.id, ConstraintSet.START)
		set.connect(iv0.id, ConstraintSet.BOTTOM, iv2.id, ConstraintSet.TOP)
		set.constrainMaxWidth(iv0.id, dm.widthPixels / 3)
		set.constrainMaxHeight(iv0.id, dm.heightPixels / 3)

		set.connect(iv1.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
		set.connect(iv1.id, ConstraintSet.START, iv0.id, ConstraintSet.END)
		set.connect(iv1.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
		set.connect(iv1.id, ConstraintSet.BOTTOM, iv3.id, ConstraintSet.TOP)
		set.constrainMaxWidth(iv1.id, dm.widthPixels / 3)
		set.constrainMaxHeight(iv1.id, dm.heightPixels / 3)

		set.connect(iv2.id, ConstraintSet.TOP, iv0.id, ConstraintSet.BOTTOM)
		set.connect(iv2.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
		set.connect(iv2.id, ConstraintSet.END, iv3.id, ConstraintSet.START)
		set.connect(iv2.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
		set.constrainMaxWidth(iv2.id, dm.widthPixels / 3)
		set.constrainMaxHeight(iv2.id, dm.heightPixels / 3)

		set.connect(iv3.id, ConstraintSet.TOP, iv1.id, ConstraintSet.BOTTOM)
		set.connect(iv3.id, ConstraintSet.START, iv2.id, ConstraintSet.END)
		set.connect(iv3.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
		set.connect(iv3.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
		set.constrainMaxWidth(iv3.id, dm.widthPixels / 3)
		set.constrainMaxHeight(iv3.id, dm.heightPixels / 3)

		set.applyTo(kLayout)
	}

	//  To be invoked when returned from the second activity
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
	{
		super.onActivityResult(requestCode, resultCode, data)

		if (data != null)
		{
			val item = data.getParcelableExtra<Item>("Item")

			val itemView = kViews[requestCode] as ItemView

			if (item != null)
			{
				itemView.update(item)
			}
		}
	}

	//  OnClickListener for the four images
	override fun onClick(p0: View?)
	{
		val iv = p0 as ItemView
		val item = iv.mItem

		val intent = Intent(this@MainActivity, ItemActivity::class.java)
		intent.putExtra("Item", item)
		startActivityForResult(intent, item.id)
	}
}