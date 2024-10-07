package com.kuby.kubot.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kuby.kubot.R
import com.kuby.kubot.presentation.ui.theme.KubotTheme
import com.kuby.kubot.util.UiText

@Composable
fun NameField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    label: String? = UiText.Res(R.string.nameField_label).get, // if this is null, label is not shown
    @Suppress("UNUSED_PARAMETER") // left for future use
    labelComponent: @Composable (() -> Unit)? =
        { Text(text = label ?: UiText.Res(R.string.nameField_label).get) },
    placeholder: String = UiText.Res(R.string.nameField_placeholder).get,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    keyboardActions: KeyboardActions? = null,
) {
    val focusManager = LocalFocusManager.current
    val keyboardActionsLocal: KeyboardActions = keyboardActions
        ?: KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
    )

    TextEntryField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        label = label,
        placeholder = placeholder,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActionsLocal,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Person,
                UiText.Res(R.string.nameField_description_name).get)
        },
        validInputDescription = UiText.Res(R.string.nameField_description_isValid).get,
        invalidInputDescription = UiText.Res(R.string.nameField_description_isInvalid).get,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NameFieldPreview() {
    KubotTheme  {
        NameField(
            value = "John Doe",
            isError = false,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NameFieldPreviewError() {
    KubotTheme {
        NameField(
            value = "Chris Athanas",
            isError = true,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NameFieldPreviewNoLabel() {
    KubotTheme  {
        NameField(
            value = "",
            label = null,
            isError = false,
            onValueChange = {}
        )
    }
}
