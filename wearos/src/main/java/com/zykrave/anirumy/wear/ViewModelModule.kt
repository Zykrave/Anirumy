package com.zykrave.anirumy.wear

import com.zykrave.anirumy.wear.ui.screens.login.LoginViewModel
import com.zykrave.anirumy.wear.ui.screens.main.MainViewModel
import com.zykrave.anirumy.wear.ui.screens.usermedialist.UserMediaListViewModel
import com.zykrave.anirumy.wear.ui.screens.usermedialist.edit.EditMediaViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val viewModelModule = module {
    viewModel<MainViewModel>()
    viewModel<LoginViewModel>()
    viewModel<UserMediaListViewModel>()
    viewModel<EditMediaViewModel>()
}