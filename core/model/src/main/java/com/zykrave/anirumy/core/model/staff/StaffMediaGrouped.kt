package com.zykrave.anirumy.core.model.staff

import com.zykrave.anirumy.core.network.StaffMediaQuery

data class StaffMediaGrouped(
    val value: StaffMediaQuery.Edge,
    val staffRoles: List<String>,
)
