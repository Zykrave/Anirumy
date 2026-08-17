package com.zykrave.anirumy.core.model.staff

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.network.StaffDetailsQuery
import com.zykrave.anirumy.core.resources.R

@Composable
fun StaffDetailsQuery.Staff.yearsActiveFormatted(): String? {
    yearsActive?.getOrNull(0)?.let { startYear ->
        val possibleEndYear = yearsActive!!.getOrNull(1)
        return if (yearsActive!!.size > 1 && possibleEndYear != null) {
            "$startYear-$possibleEndYear"
        } else {
            "$startYear-${stringResource(R.string.present)}"
        }
    }
    return null
}