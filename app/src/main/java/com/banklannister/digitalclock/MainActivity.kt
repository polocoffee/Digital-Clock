package com.banklannister.digitalclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.banklannister.digitalclock.databinding.ActivityMainBinding
import com.banklannister.digitalclock.databinding.LayoutDigitDisplayBinding
import com.google.android.material.card.MaterialCardView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.apply {

            hourLeftDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setUpLayoutWithNewDigit(binding.layoutHourLeft, map)
            }

            hourRightDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setUpLayoutWithNewDigit(binding.layoutHourRight, map)
            }

            secondLeftDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setUpLayoutWithNewDigit(binding.layoutSecondsLeft, map)
            }

            secondRightDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setUpLayoutWithNewDigit(binding.layoutSecondsRight, map)
            }
            startCounting()
        }
    }

    private fun setUpLayoutWithNewDigit(
        binding: LayoutDigitDisplayBinding,
        map: Map<Int, Int>
    ) {
        styleCardView(binding.segmentTop.root, map[R.id.segmentTop]!!)
        styleCardView(binding.segmentTopLeft.root, map[R.id.segmentTopLeft]!!)
        styleCardView(binding.segmentTopRight.root, map[R.id.segmentTopRight]!!)
        styleCardView(binding.segmentMiddle.root, map[R.id.segmentMiddle]!!)
        styleCardView(binding.segmentBottomLeft.root, map[R.id.segmentBottomLeft]!!)
        styleCardView(binding.segmentBottomRight.root, map[R.id.segmentBottomRight]!!)
        styleCardView(binding.segmentBottom.root, map[R.id.segmentBottom]!!)
    }

    private fun styleCardView(materialCardView: MaterialCardView, @ColorRes colorRes: Int) {
        materialCardView.apply {
            val resolvedColor = ContextCompat.getColor(context, colorRes)
            setCardBackgroundColor(resolvedColor)
        }
    }


}