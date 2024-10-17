package org.saudigitus.rei

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.saudigitus.support_module.ui.MenuScreen
import dagger.hilt.android.AndroidEntryPoint
import org.dhis2.commons.Constants
import org.dhis2.ui.theme.Dhis2Theme
import org.saudigitus.rei.ui.stages.StageScreen
import org.saudigitus.rei.ui.stages.StageViewModel

@AndroidEntryPoint
class ReiActivity : FragmentActivity() {

    private val viewModel: StageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Dhis2Theme {
                viewModel.setProgram(intent?.extras?.getString(Constants.PROGRAM_UID) ?: "")

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        StageScreen(viewModel)

                        Text(
                            text = "Suporte ao utilizador",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black.copy(.5f),
                        )

                        MenuScreen(context = this@ReiActivity)
                    }
                }
            }
        }
    }
}
