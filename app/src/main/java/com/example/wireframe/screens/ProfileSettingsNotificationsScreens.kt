package com.example.wireframe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.WireframeBlack
import com.example.ui.theme.WireframeBorder
import com.example.ui.theme.WireframeBorderDark
import com.example.ui.theme.WireframeBorderLight
import com.example.ui.theme.WireframeBoxFill
import com.example.ui.theme.WireframeDarkGray
import com.example.ui.theme.WireframeSurfaceVariant
import com.example.ui.theme.WireframeTextMuted
import com.example.ui.theme.WireframeTextPrimary
import com.example.ui.theme.WireframeTextSecondary
import com.example.ui.theme.WireframeWhite
import com.example.wireframe.components.WireframeBadge
import com.example.wireframe.components.WireframeButton
import com.example.wireframe.components.WireframeCard
import com.example.wireframe.components.WireframePlaceholderBox
import com.example.wireframe.components.WireframeSwitch
import com.example.wireframe.components.WireframeTopBar
import com.example.wireframe.model.NotificationItem
import com.example.wireframe.model.StudentProfile

/**
 * SCREEN 16 — PROFILE
 * Avatar placeholder, Student name, Email, Study statistics, Current streak, Completed tasks, Settings button
 */
@Composable
fun ProfileScreen(
    profile: StudentProfile,
    onNavigateToSettings: () -> Unit,
    onBack: (() -> Unit)? = null
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_16_profile")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WireframeTopBar(
                title = "Student Profile",
                screenNumber = "16",
                onBack = onBack,
                actions = {
                    IconButton(
                        onClick = onNavigateToSettings,
                        modifier = Modifier
                            .size(36.dp)
                            .border(1.dp, WireframeBorder, CircleShape)
                            .testTag("profile_settings_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = WireframeBlack,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            )
        }

        // Student Info & Avatar Placeholder Header
        item {
            WireframeCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = WireframeSurfaceVariant
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Avatar Placeholder
                    WireframePlaceholderBox(
                        label = "Avatar",
                        subLabel = "64×64",
                        modifier = Modifier
                            .size(72.dp)
                            .testTag("profile_avatar_placeholder")
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = profile.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = WireframeTextPrimary
                        )
                        Text(
                            text = profile.email,
                            fontSize = 12.sp,
                            color = WireframeTextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${profile.major} • ${profile.semester}",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeTextMuted
                        )
                    }
                }
            }
        }

        // Study Statistics Grid
        item {
            Text(
                text = "Academic Statistics",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Streak Card
                WireframeCard(modifier = Modifier.weight(1f)) {
                    Column {
                        Text("Current Streak", fontSize = 11.sp, color = WireframeTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${profile.currentStreakDays} Days", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                        Text("Daily goal active", fontSize = 10.sp, color = WireframeTextMuted)
                    }
                }

                // Completed Tasks Card
                WireframeCard(modifier = Modifier.weight(1f)) {
                    Column {
                        Text("Completed Tasks", fontSize = 11.sp, color = WireframeTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${profile.totalCompletedTasks}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                        Text("This semester", fontSize = 10.sp, color = WireframeTextMuted)
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Study Hours Card
                WireframeCard(modifier = Modifier.weight(1f)) {
                    Column {
                        Text("Study This Week", fontSize = 11.sp, color = WireframeTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${profile.totalStudyHoursThisWeek} hrs", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                        Text("Across 5 subjects", fontSize = 10.sp, color = WireframeTextMuted)
                    }
                }

                // Overall Mastery Card
                WireframeCard(modifier = Modifier.weight(1f)) {
                    Column {
                        Text("Overall Progress", fontSize = 11.sp, color = WireframeTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${profile.overallProgressPercent}%", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                        Text("On track for finals", fontSize = 10.sp, color = WireframeTextMuted)
                    }
                }
            }
        }

        // Account & Preferences Action
        item {
            WireframeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onNavigateToSettings)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(WireframeSurfaceVariant, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = WireframeBlack,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Application Settings", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                            Text("Notifications, study reminders, preferences", fontSize = 11.sp, color = WireframeTextSecondary)
                        }
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Navigate",
                        tint = WireframeBlack,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * SCREEN 17 — SETTINGS
 * Notifications, Study reminders, Appearance, Language, Account settings, Privacy, Help, Logout
 * (Switches and controls represented via simple wireframe components)
 */
@Composable
fun SettingsScreen(
    onLogout: () -> Unit,
    onBack: () -> Unit
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var studyRemindersEnabled by remember { mutableStateOf(true) }
    var soundEffectsEnabled by remember { mutableStateOf(false) }
    var privacyShareAnalytics by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_17_settings")
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WireframeTopBar(
            title = "Settings",
            screenNumber = "17",
            onBack = onBack
        )

        // Section: Notifications & Alerts
        Text(
            text = "Notifications & Reminders",
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = WireframeTextMuted
        )

        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                // Notifications switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Push Notifications", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                        Text("Task deadlines & upcoming assignments", fontSize = 11.sp, color = WireframeTextSecondary)
                    }
                    WireframeSwitch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        testTag = "settings_push_switch"
                    )
                }

                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(WireframeBorderLight))

                // Study reminders switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Daily Study Reminders", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                        Text("Nudges to maintain your study streak at 5:00 PM", fontSize = 11.sp, color = WireframeTextSecondary)
                    }
                    WireframeSwitch(
                        checked = studyRemindersEnabled,
                        onCheckedChange = { studyRemindersEnabled = it },
                        testTag = "settings_reminders_switch"
                    )
                }
            }
        }

        // Section: App Preferences
        Text(
            text = "Preferences",
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = WireframeTextMuted
        )

        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                // Appearance row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Appearance", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                        Text("Grayscale Low-Fidelity Wireframe Mode", fontSize = 11.sp, color = WireframeTextSecondary)
                    }
                    WireframeBadge(text = "WIREFRAME")
                }

                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(WireframeBorderLight))

                // Language row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Language", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                        Text("English (United States)", fontSize = 11.sp, color = WireframeTextSecondary)
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = WireframeTextMuted
                    )
                }
            }
        }

        // Section: Account & Security
        Text(
            text = "Account & Privacy",
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = WireframeTextMuted
        )

        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Account Settings", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = WireframeTextMuted)
                }

                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(WireframeBorderLight))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Share Anonymous Analytics", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                        Text("Help improve StudyFlow user experience", fontSize = 11.sp, color = WireframeTextSecondary)
                    }
                    WireframeSwitch(
                        checked = privacyShareAnalytics,
                        onCheckedChange = { privacyShareAnalytics = it }
                    )
                }
            }
        }

        // Section: Support
        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Help & Student Documentation", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = WireframeTextMuted)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Logout Button
        WireframeButton(
            text = "Log Out of StudyFlow",
            onClick = onLogout,
            isPrimary = false,
            modifier = Modifier.fillMaxWidth(),
            testTag = "settings_logout_button"
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * SCREEN 18 — NOTIFICATIONS
 * Notification list, Task reminders, Study reminders, Completion notifications, Empty-state structure toggle
 */
@Composable
fun NotificationsScreen(
    notifications: List<NotificationItem>,
    onBack: () -> Unit
) {
    var showEmptyState by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_18_notifications")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            WireframeTopBar(
                title = "Notifications",
                screenNumber = "18",
                onBack = onBack,
                actions = {
                    Text(
                        text = if (showEmptyState) "Show List" else "Show Empty UX",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = WireframeBlack,
                        modifier = Modifier
                            .clickable { showEmptyState = !showEmptyState }
                            .border(1.dp, WireframeBorderDark, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            )
        }

        if (showEmptyState) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WireframePlaceholderBox(
                        label = "Empty State Illustration Placeholder",
                        subLabel = "No pending notifications or alerts for student",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "No Notifications",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextPrimary
                    )

                    Text(
                        text = "You're completely up to date with tasks, deadlines, and study reminders.",
                        fontSize = 13.sp,
                        color = WireframeTextSecondary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                    )
                }
            }
        } else {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Notifications (${notifications.size})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextPrimary
                    )
                    Text(
                        text = "Mark all as read",
                        fontSize = 11.sp,
                        color = WireframeTextSecondary
                    )
                }
            }

            items(notifications) { item ->
                WireframeCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = if (item.isRead) WireframeWhite else WireframeSurfaceVariant
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(WireframeBoxFill, RoundedCornerShape(4.dp))
                                .border(1.dp, WireframeBorder, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = null,
                                tint = WireframeDarkGray,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = WireframeTextPrimary
                                )
                                Text(
                                    text = item.timeAgo,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = WireframeTextMuted
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = item.message,
                                fontSize = 12.sp,
                                color = WireframeTextSecondary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            WireframeBadge(
                                text = item.category.uppercase()
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
