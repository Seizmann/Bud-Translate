package pro.spritex.budtranslate.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.data.fake.ChatMessage
import pro.spritex.budtranslate.data.fake.FakeRepositories
import pro.spritex.budtranslate.ui.components.BudCircularIconButton
import pro.spritex.budtranslate.ui.components.BudTextField
import pro.spritex.budtranslate.ui.components.BudTopBar
import pro.spritex.budtranslate.ui.components.LanguageChip
import pro.spritex.budtranslate.ui.theme.BudTheme

@Composable
fun ChatScreen(
    contactId: String,
    onBackClick: () -> Unit
) {
    val contact = FakeRepositories.contacts.firstOrNull { it.id == contactId } ?: FakeRepositories.contacts.first()
    var sourceLang by remember { mutableStateOf("English") }
    var targetLang by remember { mutableStateOf(contact.defaultLanguage) }

    val chatMessages = remember { mutableStateListOf<ChatMessage>().apply { addAll(FakeRepositories.demoChatHistory) } }
    var composeText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BudTopBar(
                title = contact.name,
                onBackClick = onBackClick,
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = BudTheme.spacing.sm)
                    ) {
                        LanguageChip(language = sourceLang)
                        Spacer(modifier = Modifier.width(BudTheme.spacing.xs))
                        Text("⇄", style = BudTheme.typography.bodySmStrong)
                        Spacer(modifier = Modifier.width(BudTheme.spacing.xs))
                        LanguageChip(language = targetLang)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.CanvasSoft)
                .padding(paddingValues)
        ) {
            // Message List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = BudTheme.spacing.lg),
                reverseLayout = false
            ) {
                items(chatMessages) { message ->
                    val alignment = if (message.isSelf) Alignment.End else Alignment.Start
                    val bubbleColor = if (message.isSelf) BudTheme.colors.Canvas else BudTheme.colors.PrimaryPale
                    val labelText = if (message.isSelf) "You" else contact.name

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = BudTheme.spacing.sm),
                        horizontalAlignment = alignment
                    ) {
                        Text(
                            text = labelText,
                            style = BudTheme.typography.caption,
                            color = BudTheme.colors.Mute,
                            modifier = Modifier.padding(bottom = BudTheme.spacing.xxs)
                        )
                        Surface(
                            shape = BudTheme.shapes.lg,
                            color = bubbleColor,
                            contentColor = BudTheme.colors.Ink
                        ) {
                            Column(
                                modifier = Modifier.padding(BudTheme.spacing.md)
                            ) {
                                Text(
                                    text = message.originalText,
                                    style = BudTheme.typography.bodySm,
                                    color = BudTheme.colors.Body
                                )
                                Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
                                Text(
                                    text = message.translatedText,
                                    style = BudTheme.typography.bodyMdStrong,
                                    color = BudTheme.colors.Ink
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Compose Row
            Surface(
                color = BudTheme.colors.Canvas,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BudTheme.spacing.md),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BudTextField(
                        value = composeText,
                        onValueChange = { composeText = it },
                        placeholder = "Type a message...",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(BudTheme.spacing.sm))
                    BudCircularIconButton(
                        onClick = {
                            if (composeText.trim().isNotEmpty()) {
                                val original = composeText.trim()
                                val trans = FakeRepositories.translate(original, sourceLang, targetLang)
                                chatMessages.add(
                                    ChatMessage(
                                        id = (chatMessages.size + 1).toString(),
                                        originalText = original,
                                        translatedText = trans,
                                        isSelf = true,
                                        timestamp = "Now"
                                    )
                                )
                                composeText = ""
                            }
                        },
                        backgroundColor = BudTheme.colors.Primary,
                        contentColor = BudTheme.colors.OnPrimary
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send"
                        )
                    }
                }
            }
        }
    }
}
