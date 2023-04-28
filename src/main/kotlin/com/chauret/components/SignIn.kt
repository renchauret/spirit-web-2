package com.chauret.components

import com.chauret.api.request.SignInRequest
import com.chauret.mainScope
import com.chauret.service.AuthService
import csstype.px
import emotion.react.css
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLFormElement
import react.FC
import react.Props
import react.dom.events.FormEvent
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.useState

val SignIn = FC<Props> {
    val (user, setUser) = useState(SignInRequest("", ""))
    suspend fun signIn() {
        AuthService.signIn(user)
    }

    fun handleSubmission(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        mainScope.launch { signIn() }
    }

    ReactHTML.div {
        css {
            margin = 10.px
        }
        ReactHTML.form {
            onSubmit = { handleSubmission(it) }
            ReactHTML.input {
                type = InputType.text
                name = "username"
                value = user.username
                placeholder = "username"
                onChange = { event ->
                    setUser {
                        it.copy(username = event.target.value)
                    }
                }
            }
            ReactHTML.input {
                type = InputType.password
                name = "password"
                value = user.password
                placeholder = "password"
                onChange = { event ->
                    setUser {
                        user.copy(password = event.target.value)
                    }
                }
            }
            ReactHTML.button {
                css {
                    margin = 10.px
                }
                +"Sign In"
                type = ButtonType.submit
            }
        }
    }
}
