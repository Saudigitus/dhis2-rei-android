package com.saudigitus.support_module.ui

sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object Manuals : Screen("manuals")
    object Support : Screen("support")
}


