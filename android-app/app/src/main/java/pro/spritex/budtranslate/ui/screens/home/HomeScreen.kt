package pro.spritex.budtranslate.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pro.spritex.budtranslate.data.fake.Contact
import pro.spritex.budtranslate.data.fake.FakeRepositories
import pro.spritex.budtranslate.ui.screens.earbud.EarbudContent
import pro.spritex.budtranslate.ui.theme.BudTheme

private val tabs = listOf("Earbud", "Chats", "Calls")

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedBottomItem by remember { mutableIntStateOf(0) }
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            HomeTopBar(
                showMenu = showMenu,
                onMenuToggle = { showMenu = it },
                onProfileClick = { onNavigate("profile") }
            )
        },
        bottomBar = {
            HomeBottomNav(
                selected = selectedBottomItem,
                onSelect = { idx ->
                    when (idx) {
                        0 -> selectedBottomItem = 0
                        1 -> onNavigate("camera")
                        2 -> onNavigate("text")
                        3 -> onNavigate("settings")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BudTheme.colors.CanvasSoft)
                .padding(padding)
        ) {
            // Tab row — WhatsApp style (flush, no elevation)
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = BudTheme.colors.Canvas,
                contentColor = BudTheme.colors.Ink,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = BudTheme.colors.Primary
                    )
                },
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                style = BudTheme.typography.bodySmStrong,
                                color = if (selectedTab == index) BudTheme.colors.Ink else BudTheme.colors.Mute
                            )
                        }
                    )
                }
            }

            when (selectedTab) {
                0 -> EarbudContent()
                1 -> ChatsTab(onNavigate = onNavigate)
                2 -> CallsTab(onNavigate = onNavigate)
            }
        }
    }
}

@Composable
private fun HomeTopBar(
    showMenu: Boolean,
    onMenuToggle: (Boolean) -> Unit,
    onProfileClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = BudTheme.colors.Canvas,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = BudTheme.spacing.lg),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bud Translate",
                style = BudTheme.typography.bodyMdStrong,
                color = BudTheme.colors.Ink,
                modifier = Modifier.weight(1f)
            )
            Box {
                IconButton(onClick = { onMenuToggle(true) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = BudTheme.colors.Ink
                    )
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { onMenuToggle(false) }
                ) {
                    DropdownMenuItem(
                        text = { Text("Profile", style = BudTheme.typography.bodySm) },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = BudTheme.colors.Ink)
                        },
                        onClick = {
                            onMenuToggle(false)
                            onProfileClick()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatsTab(onNavigate: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(FakeRepositories.contacts) { contact ->
            ContactRow(
                contact = contact,
                lastMessage = FakeRepositories.demoChatHistory
                    .lastOrNull { !it.isSelf }?.originalText ?: "",
                onClick = { onNavigate("chat/${contact.id}") }
            )
        }
    }
}

@Composable
private fun ContactRow(
    contact: Contact,
    lastMessage: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(BudTheme.colors.Canvas)
            .padding(horizontal = BudTheme.spacing.lg, vertical = BudTheme.spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar circle
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(BudTheme.shapes.full)
                .background(BudTheme.colors.PrimaryPale),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contact.name.first().uppercaseChar().toString(),
                style = BudTheme.typography.bodyMdStrong,
                color = BudTheme.colors.Primary
            )
        }
        Spacer(modifier = Modifier.width(BudTheme.spacing.md))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = contact.name,
                    style = BudTheme.typography.bodyMdStrong,
                    color = BudTheme.colors.Ink
                )
                Text(
                    text = "10:03 AM",
                    style = BudTheme.typography.caption,
                    color = BudTheme.colors.Mute
                )
            }
            Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
            Text(
                text = lastMessage,
                style = BudTheme.typography.bodySm,
                color = BudTheme.colors.Mute,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private data class CallEntry(
    val contactName: String,
    val type: String,
    val time: String,
    val isVideo: Boolean,
    val contactId: String
)

@Composable
private fun CallsTab(onNavigate: (String) -> Unit) {
    val calls = listOf(
        CallEntry("Mohammad Sijan", "Incoming", "Yesterday", false, "1"),
        CallEntry("Rexio AI", "Outgoing", "Yesterday", true, "2"),
        CallEntry("Spritex Operator", "Missed", "Mon", false, "3"),
        CallEntry("Mohammad Sijan", "Outgoing", "Sun", true, "1"),
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(calls) { call ->
            CallRow(call = call, onNavigate = onNavigate)
        }
    }
}

@Composable
private fun CallRow(call: CallEntry, onNavigate: (String) -> Unit) {
    val route = if (call.isVideo) "videocall/${call.contactId}" else "audiocall/${call.contactId}"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigate(route) }
            .background(BudTheme.colors.Canvas)
            .padding(horizontal = BudTheme.spacing.lg, vertical = BudTheme.spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(BudTheme.shapes.full)
                .background(BudTheme.colors.PrimaryPale),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = call.contactName.first().uppercaseChar().toString(),
                style = BudTheme.typography.bodyMdStrong,
                color = BudTheme.colors.Primary
            )
        }
        Spacer(modifier = Modifier.width(BudTheme.spacing.md))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = call.contactName,
                style = BudTheme.typography.bodyMdStrong,
                color = if (call.type == "Missed") BudTheme.colors.Negative else BudTheme.colors.Ink
            )
            Spacer(modifier = Modifier.height(BudTheme.spacing.xxs))
            Text(
                text = "${call.type} · ${call.time}",
                style = BudTheme.typography.bodySm,
                color = BudTheme.colors.Mute
            )
        }
        Icon(
            imageVector = if (call.isVideo) Icons.Default.Videocam else Icons.Default.Phone,
            contentDescription = null,
            tint = BudTheme.colors.Primary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun HomeBottomNav(selected: Int, onSelect: (Int) -> Unit) {
    NavigationBar(
        containerColor = BudTheme.colors.Canvas,
        tonalElevation = 0.dp
    ) {
        val items = listOf(
            Icons.Default.Hearing to "Home",
            Icons.Default.CameraAlt to "Scan",
            Icons.Default.Translate to "Text",
            Icons.Default.Settings to "Settings"
        )
        items.forEachIndexed { index, (icon, label) ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onSelect(index) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label, style = BudTheme.typography.caption) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BudTheme.colors.OnPrimary,
                    selectedTextColor = BudTheme.colors.Ink,
                    indicatorColor = BudTheme.colors.Primary,
                    unselectedIconColor = BudTheme.colors.Mute,
                    unselectedTextColor = BudTheme.colors.Mute
                )
            )
        }
    }
}
