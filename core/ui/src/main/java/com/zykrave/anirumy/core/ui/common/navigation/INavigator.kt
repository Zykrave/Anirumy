package com.zykrave.anirumy.core.ui.common.navigation

interface INavigator {
    fun navigate(route: Route)
    fun goBack()
}