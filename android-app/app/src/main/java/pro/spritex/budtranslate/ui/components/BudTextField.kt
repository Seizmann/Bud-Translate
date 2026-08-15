package pro.spritex.budtranslate.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun BudTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = label?.let { { Text(it, style = BudTheme.typography.bodySm) } },
        placeholder = placeholder?.let { { Text(it, style = BudTheme.typography.bodyMd, color = BudTheme.colors.Mute) } },
        singleLine = singleLine,
        shape = BudTheme.shapes.md,
        textStyle = BudTheme.typography.bodyMd.copy(color = BudTheme.colors.Ink),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BudTheme.colors.Ink,
            unfocusedBorderColor = BudTheme.colors.Mute,
            focusedContainerColor = BudTheme.colors.Canvas,
            unfocusedContainerColor = BudTheme.colors.Canvas,
            focusedLabelColor = BudTheme.colors.Ink,
            unfocusedLabelColor = BudTheme.colors.Mute
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource
    )
}
