package com.compose.testing.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

interface InputFieldState {
    var text: String
}

class InputFieldStateImpl(fieldText: String = "") : InputFieldState {

    private var _text by mutableStateOf(fieldText)
    override var text: String
        get() {
            return _text
        }
        set(value) {
            _text = value
        }

    companion object {
        val Saver = Saver<InputFieldStateImpl, List<Any>>(
            save = { listOf(it._text) },
            restore = {
                InputFieldStateImpl(
                    fieldText = it[0] as String,
                )
            }
        )
    }
}

@Composable
fun rememberTextInputFieldState(): InputFieldState =
    rememberSaveable(saver = InputFieldStateImpl.Saver) {
        InputFieldStateImpl("")
    }