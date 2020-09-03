package com.spaceapps.tasks.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.spaceapps.tasks.core.extensions.async
import com.spaceapps.tasks.core.extensions.safeAsync
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.Status.*
import com.spaceapps.tasks.core.model.UserProfileModel
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.core.repository.UserProfileRepository
import kotlinx.coroutines.flow.map
import java.io.File

class ProfileScreenViewModel
@ViewModelInject constructor(
    private val tasksRepository: TasksRepository,
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    private val _profileImageUrl = MutableLiveData<Status<String?>>()
    val profileImageUrl: LiveData<Status<String?>>
        get() = _profileImageUrl

    private val _userProfile = MutableLiveData<Status<UserProfileModel?>>()
    val userProfile: LiveData<Status<UserProfileModel?>>
        get() = _userProfile

    val subTasks = liveData { emitSource(tasksRepository.getSubTasks().asLiveData()) }

    fun getUserProfile() = async {
        _userProfile.postValue(Loading)
        val result = safeAsync {
            userProfileRepository.getUserProfile()
        }
        _userProfile.postValue(result)
    }

    fun setProfileImage(file: File) {
        _userProfile.postValue(Loading)
        async {
            _profileImageUrl.postValue(
                safeAsync { userProfileRepository.setProfileImage(file) }
            )
        }
    }
}