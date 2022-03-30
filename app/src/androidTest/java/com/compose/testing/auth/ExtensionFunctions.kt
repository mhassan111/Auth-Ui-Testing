package com.compose.testing.auth

import androidx.compose.ui.test.SemanticsNodeInteraction

fun SemanticsNodeInteraction.imeActionValue(): String? {
    for ((key, value) in fetchSemanticsNode().config) {
        if (key.name == "ImeAction") {
            return value?.toString()
        }
    }
    return null
}

fun SemanticsNodeInteraction.currentText(): String? {
    for ((key, value) in fetchSemanticsNode().config) {
        if (key.name == "EditableText") {
            return value?.toString()
        }
    }
    return null
}