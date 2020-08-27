package testchaf.apptestest.screens.choose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_choose_people.*
import testchaf.apptestest.R

class ChoosePeopleFragment : Fragment(R.layout.fragment_choose_people) {

    private val arrayImage = arrayOf(
        R.drawable.f,
        R.drawable.fi,
        R.drawable.fo,
        R.drawable.t,
        R.drawable.th
    )
    var i = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView_fav.setOnClickListener {
            i++
            if (i == 5) i = 0
            choose_image_view.setImageResource(arrayImage[i])

        }
        imageView_close.setOnClickListener {
            i++
            if (i == 5) i = 0
            choose_image_view.setImageResource(arrayImage[i])
        }

    }

}