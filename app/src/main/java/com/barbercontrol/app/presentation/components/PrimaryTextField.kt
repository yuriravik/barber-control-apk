package com.barbercontrol.app.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Campo de texto padrão do app (baseado no OutlinedTextField do Material 3).
 *
 * @param value Valor atual do campo.
 * @param onValueChange Callback chamado quando o valor muda.
 * @param label Rótulo exibido dentro do campo.
 * @param modifier Modificador Compose opcional.
 * @param isError Indica se o campo está em estado de erro (borda vermelha).
 * @param errorMessage Mensagem de erro exibida abaixo do campo.
 * @param keyboardOptions Opções de teclado (tipo, ação).
 * @param keyboardActions Ações do teclado (ex: ir para próximo campo, enviar).
 * @param visualTransformation Transformação visual (ex: ocultar senha).
 * @param trailingIcon Ícone à direita do campo (ex: olho para mostrar/ocultar senha).
 * @param singleLine Define se o campo aceita apenas uma linha.
 */
@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(),
        isError = isError,
        supportingText = if (isError && !errorMessage.isNullOrBlank()) {
            { Text(text = errorMessage, color = MaterialTheme.colorScheme.error) }
        } else null,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        singleLine = singleLine
    )
}
