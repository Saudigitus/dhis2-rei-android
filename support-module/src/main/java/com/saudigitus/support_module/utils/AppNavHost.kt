import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saudigitus.support_module.ui.MenuScreen
import com.saudigitus.support_module.ui.Screen
import com.saudigitus.support_module.ui.manualScreen.ManualScreen
import timber.log.Timber

@Composable
fun AppNavHost(navController: NavHostController, route: String, activity: Activity) {
    NavHost(
        navController = navController,
        startDestination = route,
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(context = LocalContext.current)
        }
        composable(Screen.Manuals.route) {
            ManualScreen(navController = navController, onBack = {
                activity.finish()
            })
        }
        composable(Screen.Support.route) {
            SupportScreen(navController = navController, onBack = {
                activity.finish()
            })
        }
    }
}

