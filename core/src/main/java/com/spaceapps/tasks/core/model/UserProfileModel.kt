package com.spaceapps.tasks.core.model

import java.util.*

data class UserProfileModel(
    val id: UUID,
    val userName: String,
    val profileImage: String
) : DomainEntity