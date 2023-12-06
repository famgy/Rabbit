package com.feilinpl.rabbit.utils

sealed class GoogleSignInType {
    object Default : GoogleSignInType()
    object WithIdToken : GoogleSignInType()
    object ServerAuthCode : GoogleSignInType()
    object WithScopes : GoogleSignInType()
}