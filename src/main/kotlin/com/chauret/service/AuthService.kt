package com.chauret.service

import com.chauret.api.request.SignInRequest
import com.chauret.api.response.SessionResponse
import com.chauret.model.Permissions
import kotlinx.browser.localStorage
import react.dom.html.FormMethod

object AuthService : Service {
    override val path: String
        get() = "/auth"

    fun getSession(): SessionResponse? {
        val token = localStorage.getItem("session") ?: return null
        val permissions = localStorage.getItem("permissions") ?: return null
        val expirationTimeSeconds = localStorage.getItem("expirationTimeSeconds")?.toLong() ?: return null
        val username = localStorage.getItem("username") ?: return null
        return SessionResponse(token, username, expirationTimeSeconds, Permissions.valueOf(permissions))
    }

    suspend fun signIn(signInRequest: SignInRequest): SessionResponse {
        val session: SessionResponse = request(signInRequest, "put")
        // TODO:  This isn't secure.
        localStorage.setItem("session", session.token)
        localStorage.setItem("permissions", session.permissions.toString())
        localStorage.setItem("expirationTimeSeconds", session.expirationTimeSeconds.toString())
        localStorage.setItem("username", session.username ?: "")
        return session
    }

    suspend fun signUp(signInRequest: SignInRequest): SessionResponse =
        request(signInRequest, FormMethod.post)
}
