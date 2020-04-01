package com.example.yori.domain.repositories.models.rest

import com.google.gson.annotations.SerializedName

data class UserUpdate(
    @SerializedName("avatar_success")
    var avatarSuccess: Boolean?,

    @SerializedName("new_avatar_url")
    var newAvatarUrl: String,

    @SerializedName("new_password")
    var newPassword: String,

    @SerializedName("old_password")
    var oldPassword: String,

    @SerializedName("password_success")
    var passwordSuccess: Boolean?
)
