import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saudigitus.support_module.ui.MenuScreen
import com.saudigitus.support_module.ui.Screen
import com.saudigitus.support_module.ui.manualScreen.ManualScreen

@Composable
fun AppNavHost(navController: NavHostController, route: String) {
    NavHost(
        navController = navController,
        startDestination = route
    ) {
        print("route_going: $route")
        composable(Screen.Menu.route) {
            MenuScreen(context = LocalContext.current)
        }
        composable(Screen.Manuals.route) {
            ManualScreen(navController = navController, onBack = { })
        }
        composable(Screen.Support.route) {
            SupportScreen(navController = navController)
        }
    }
}

