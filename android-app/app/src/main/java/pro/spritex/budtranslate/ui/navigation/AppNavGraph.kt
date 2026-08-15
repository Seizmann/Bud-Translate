package pro.spritex.budtranslate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pro.spritex.budtranslate.ThemeMode
import pro.spritex.budtranslate.ui.screens.audiocall.AudioCallScreen
import pro.spritex.budtranslate.ui.screens.camera.CameraScreen
import pro.spritex.budtranslate.ui.screens.chat.ChatScreen
import pro.spritex.budtranslate.ui.screens.earbud.EarbudScreen
import pro.spritex.budtranslate.ui.screens.home.HomeScreen
import pro.spritex.budtranslate.ui.screens.settings.SettingsScreen
import pro.spritex.budtranslate.ui.screens.text.TextScreen
import pro.spritex.budtranslate.ui.screens.videocall.VideoCallScreen

@Composable
fun AppNavGraph(
    themeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable("earbud") {
            EarbudScreen(onBackClick = { navController.popBackStack() })
        }

        composable(
            route = "chat/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: "1"
            ChatScreen(contactId = contactId, onBackClick = { navController.popBackStack() })
        }

        composable(
            route = "audiocall/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: "1"
            AudioCallScreen(contactId = contactId, onBackClick = { navController.popBackStack() })
        }

        composable(
            route = "videocall/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId") ?: "1"
            VideoCallScreen(contactId = contactId, onBackClick = { navController.popBackStack() })
        }

        composable("camera") {
            CameraScreen(onBackClick = { navController.popBackStack() })
        }

        composable("text") {
            TextScreen(onBackClick = { navController.popBackStack() })
        }

        composable("settings") {
            SettingsScreen(
                themeMode = themeMode,
                onThemeModeChange = onThemeModeChange,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
