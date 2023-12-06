package com.feilinpl.rabbit.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feilinpl.rabbit.R
import com.feilinpl.rabbit.data.GetContactsTask
import com.feilinpl.rabbit.utils.GoogleSignInType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.services.people.v1.model.Person
import kotlinx.coroutines.launch


class SignInViewModel : ViewModel() {
    private val _signInAccount = MutableLiveData<GoogleSignInAccount?>()
    val signInAccount: LiveData<GoogleSignInAccount?> get() = _signInAccount
    private val _contact = MutableLiveData<String?>()
    val contact: LiveData<String?> get() = _contact
    private val _googleSignInClient = MutableLiveData<GoogleSignInClient?>()
    val googleSignInClient: LiveData<GoogleSignInClient?> get() = _googleSignInClient
    private var signInType: GoogleSignInType? = null

    fun updateSignInAccount(context: Context, account: GoogleSignInAccount?) {
        _signInAccount.value = account
        if (account == null) {
            _contact.value = null
        }

        if (null != account && signInType is GoogleSignInType.WithScopes) {
            viewModelScope.launch {
                val contacts: List<Person> = GetContactsTask(context).execute(account.account)
                if (contacts.size <= 1) {
                    _contact.value = "null"
                }
                _contact.value = contacts.getOrNull(1)?.names?.getOrNull(0)?.displayName.orEmpty()
            }
        }
    }

    fun initializeGoogleSignInClient(context: Context, type: GoogleSignInType) {
        signInType = type

        val gsoBuilder = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()

        when (type) {
            is GoogleSignInType.Default -> {

            }
            is GoogleSignInType.WithIdToken -> {
                gsoBuilder.requestIdToken(context.getString(R.string.server_client_id))
            }

            is GoogleSignInType.ServerAuthCode -> {
                gsoBuilder.requestServerAuthCode(context.getString(R.string.server_client_id))
            }

            is GoogleSignInType.WithScopes -> {
                gsoBuilder.requestScopes(Scope("https://www.googleapis.com/auth/contacts.readonly"))
            }
        }

        _googleSignInClient.value = GoogleSignIn.getClient(context, gsoBuilder.build())
    }
}