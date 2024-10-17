package org.saudigitus.rei

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import org.dhis2.ui.theme.Dhis2Theme
import org.saudigitus.rei.ui.HomeScreen
import org.saudigitus.rei.ui.stages.StageViewModel

@AndroidEntryPoint
class ReiActivity : FragmentActivity() {

    private val viewModel: StageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Dhis2Theme {
                viewModel.setBundle(intent?.extras)

                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(context = this@ReiActivity) {
                        finish()
                    }
                }
            }
        }
    }
}
