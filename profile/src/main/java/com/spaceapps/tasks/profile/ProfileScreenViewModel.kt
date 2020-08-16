package com.spaceapps.tasks.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.async
import com.spaceapps.tasks.core.extensions.safeAsync
import com.spaceapps.tasks.core.extensions.safeAsyncNullable
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.UserProfileModel
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.core.repository.UserProfileRepository
import java.io.File
import javax.inject.Inject

class ProfileScreenViewModel
@Inject constructor(
    private val tasksRepository: TasksRepository,
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    private val _profileImageUrl = MutableLiveData<Status>()
    val profileImageUrl:LiveData<Status>
        get() = _profileImageUrl

    private val _userProfile = MutableLiveData<Status>()
    val userProfile: LiveData<Status>
        get() = _userProfile

    private val _subTasks = MutableLiveData<List<SubTask>>()
    val subTasks: LiveData<List<SubTask>>
        get() = _subTasks

    fun getUserProfile() = async {
        _userProfile.postValue(Status.Loading)
        val result = safeAsyncNullable {
            userProfileRepository.getUserProfile()
        }
        _userProfile.postValue(result)
    }

    fun getSubTasks() = async {
        _subTasks.postValue(tasksRepository.getSubTasks())
    }

    fun setProfileImage(file: File) {
        _userProfile.postValue(Status.Loading)
        async {
            _profileImageUrl.postValue(
                safeAsyncNullable { userProfileRepository.setProfileImage(file) }
            )
        }
    }

    class Factory @Inject constructor(
        private val tasksRepository: TasksRepository,
        private val userProfileRepository: UserProfileRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProfileScreenViewModel(
                tasksRepository,
                userProfileRepository
            ) as T
        }
    }
}