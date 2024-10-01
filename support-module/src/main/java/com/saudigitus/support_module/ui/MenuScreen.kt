package com.saudigitus.support_module.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewtest.ui.components.ListCard
import com.saudigitus.support_module.R
import com.saudigitus.support_module.ui.components.CustomCard


@Composable
fun MenuScreen(
    //viewModel: MenuViewModel,
    //onBack: () -> Unit
) {

    Scaffold(
        topBar = {
            /*Toolbar(
                headers = toolbarHeaders,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2C98F0),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationAction = { onBack.invoke() },
                disableNavigation = false,
                actionState = ToolbarActionState(syncVisibility = false),
                syncAction = {  },
                filterAction = { displayFilters = !displayFilters }
            )*/
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // First Card
                CustomCard(imageResId = R.drawable.manual_icon, title = "Manuais")
                // Second Card
                CustomCard(imageResId = R.drawable.support, title = "Supporte")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MenuScreen()
}