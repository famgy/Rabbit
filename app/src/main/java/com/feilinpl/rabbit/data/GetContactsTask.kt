package com.feilinpl.rabbit.data

import android.accounts.Account
import android.content.Context
import android.util.Log
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.people.v1.PeopleService
import com.google.api.services.people.v1.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class GetContactsTask(private val context: Context) {
    private val HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport()
    private val JSON_FACTORY: JacksonFactory? = JacksonFactory.getDefaultInstance()

    suspend fun execute(vararg accounts: Account?): List<Person> {
        val account = accounts.firstOrNull() ?: return emptyList()

        return withContext(Dispatchers.IO) {
            try {
                val credential = GoogleAccountCredential.usingOAuth2(
                    context,
                    setOf("https://www.googleapis.com/auth/contacts.readonly")
                )
                credential.selectedAccount = account

                val service = PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("Rabbit")
                    .build()

                val connectionsResponse = service.people().connections()
                    .list("people/me")
                    .setRequestMaskIncludeField("person.names")
                    .execute()

                connectionsResponse.connections ?: emptyList()
            } catch (recoverableException: UserRecoverableAuthIOException) {
                // 处理可恢复的身份验证异常
                // 你可能需要将 recoverableException 传递给调用者以执行授权操作
                emptyList()
            } catch (e: IOException) {
                Log.e("GetContactsTask", "Exception", e)
                emptyList()
            }
        }
    }
}