import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.saudigitus.support_module.R
import com.saudigitus.support_module.ui.components.SimpleCard
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(
    navController: NavHostController,
    onBack: () -> Unit = {} // Placeholder for back action
) {
    Scaffold(
        Modifier.background(Color(0xFF2196F3)),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.support_view_title),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3) // Blue background color
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2196F3)) // Set your background color here
                .padding(paddingValues) // Ensures the padding is applied to the content
        ) {
        // Wrapping the content with a Surface to give it rounded corners
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), // Rounded top corners

            shadowElevation = 4.dp // Elevation for shadow
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(Modifier.height(20.dp))
                SimpleCard(title = "Relatar erros de sincronização", icon = Icons.Default.Close)
                Spacer(Modifier.height(20.dp))
                SimpleCard(title = "Relatar outros erros", icon = Icons.Default.Warning)
            }
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupportPreview() {
    SupportScreen(navController = NavHostController(LocalContext.current), onBack = {})
}