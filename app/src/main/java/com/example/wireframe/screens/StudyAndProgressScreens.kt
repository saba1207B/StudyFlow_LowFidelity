// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.wireframe.components.WireframeProgressBar
import com.example.wireframe.components.WireframeTopBar
import com.example.wireframe.model.StudentProfile
import com.example.wireframe.model.SubjectItem

/**
 * SCREEN 13 — STUDY SESSION / FOCUS MODE
 * Subject, Study topic, Large timer placeholder, Session progress, Pause button, End session button
 * (Timer is represented structurally, not visually polished)
 */
@Composable
fun StudySessionScreen(
    subjectName: String,
    topicName: String = "Algorithms & Data Structures",
    onEndSession: () -> Unit,
    onBack: () -> Unit
) {
    var isPaused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_13_study_session")
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SCREEN 13 — FOCUS MODE",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = WireframeTextMuted
                )
                Text(
                    text = "Cancel",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = WireframeTextSecondary,
                    modifier = Modifier
                        .clickable(onClick = onBack)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Subject & Topic Badge
            WireframeBadge(
                text = subjectName.uppercase(),
                isFilled = false
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = topicName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Target: 25 minutes Pomodoro Session",
                fontSize = 12.sp,
                color = WireframeTextSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Structural Timer Placeholder Container (Strictly Wireframe)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .background(WireframeBoxFill, CircleShape)
                    .border(2.dp, WireframeBorderDark, CircleShape)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "[ Timer Placeholder ]",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeTextMuted
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isPaused) "18:42" else "18:42",
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeBlack
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isPaused) "SESSION PAUSED" else "FOCUSING...",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = if (isPaused) WireframeDarkGray else WireframeBlack
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Session Progress Bar (elapsed / total)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Progress: 6.3 min elapsed", fontSize = 11.sp, color = WireframeTextSecondary)
                    Text("75% remaining", fontSize = 11.sp, fontFamily = FontFamily.Monospace, color = WireframeTextPrimary)
                }
                Spacer(modifier = Modifier.height(6.dp))
                WireframeProgressBar(
                    progressPercent = 25,
                    height = 8.dp
                )
            }
        }

        // Controls: Pause and End Session Buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WireframeButton(
                    text = if (isPaused) "Resume Session" else "Pause Session",
                    onClick = { isPaused = !isPaused },
                    modifier = Modifier.weight(1f),
                    isPrimary = false,
                    icon = {
                        Icon(
                            imageVector = if (isPaused) Icons.Default.PlayArrow else Icons.Default.Pause,
                            contentDescription = null,
                            tint = WireframeBlack,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    testTag = "study_session_pause_button"
                )

                WireframeButton(
                    text = "End Session",
                    onClick = onEndSession,
                    modifier = Modifier.weight(1f),
                    isPrimary = true,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Stop,
                            contentDescription = null,
                            tint = WireframeWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    testTag = "study_session_end_button"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * SCREEN 14 — STUDY SESSION COMPLETED
 * Completion indicator, Session duration, Subject, Study summary, Updated progress placeholder, Continue button
 */
@Composable
fun StudySessionCompletedScreen(
    subjectName: String,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_14_study_completed")
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "SCREEN 14 — SESSION COMPLETED",
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = WireframeTextMuted
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Completion Indicator Box
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(WireframeSurfaceVariant, CircleShape)
                    .border(2.dp, WireframeBlack, CircleShape)
                    .testTag("session_completed_badge"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = WireframeBlack,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Session Completed!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )

            Text(
                text = "Great focus streak! Your study log has been updated.",
                fontSize = 13.sp,
                color = WireframeTextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Summary Card
            WireframeCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = WireframeSurfaceVariant
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subject", fontSize = 12.sp, color = WireframeTextSecondary)
                        Text(subjectName, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Session Duration", fontSize = 12.sp, color = WireframeTextSecondary)
                        Text("25 Minutes (Full Cycle)", fontSize = 13.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Study Streak", fontSize = 12.sp, color = WireframeTextSecondary)
                        Text("7 Days (+1 Streak)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Updated Progress Placeholder
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Updated Daily Target", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        Text("2.9 / 3.0 hrs", fontSize = 12.sp, fontFamily = FontFamily.Monospace, color = WireframeBlack)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    WireframeProgressBar(
                        progressPercent = 95,
                        height = 8.dp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "[ Updated Progress Placeholder: 95% Daily Goal Achieved ]",
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeTextMuted
                    )
                }
            }
        }

        // Action Button
        Column(modifier = Modifier.fillMaxWidth()) {
            WireframeButton(
                text = "View Analytics & Progress →",
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
                testTag = "study_completed_continue_button"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * SCREEN 15 — PROGRESS / ANALYTICS
 * Overall study progress, Weekly study time, Completed tasks, Study streak, Subject progress, Weekly activity chart placeholder, Study history
 */
@Composable
fun ProgressAnalyticsScreen(
    profile: StudentProfile,
    subjects: List<SubjectItem>,
    onSelectSubject: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_15_progress_analytics")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WireframeTopBar(
                title = "Progress & Analytics",
                screenNumber = "15"
            )
        }

        // Overall Study Progress Banner
        item {
            WireframeCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = WireframeSurfaceVariant
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Semester Academic Progress",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = WireframeTextPrimary
                        )
                        Text(
                            text = "${profile.overallProgressPercent}%",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeBlack
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    WireframeProgressBar(
                        progressPercent = profile.overallProgressPercent,
                        height = 10.dp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 3 KPI Boxes
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Weekly Time", fontSize = 11.sp, color = WireframeTextSecondary)
                            Text("${profile.totalStudyHoursThisWeek} hrs", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        }
                        Column {
                            Text("Completed Tasks", fontSize = 11.sp, color = WireframeTextSecondary)
                            Text("${profile.totalCompletedTasks} items", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        }
                        Column {
                            Text("Study Streak", fontSize = 11.sp, color = WireframeTextSecondary)
                            Text("${profile.currentStreakDays} Days", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        }
                    }
                }
            }
        }

        // Weekly Activity Chart Placeholder
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Weekly Study Activity",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextPrimary
                    )
                    Text(
                        text = "Target: 20 hrs/wk",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeTextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Chart Placeholder Box
                WireframeCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "[ Weekly Activity Chart Placeholder ]",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeTextMuted
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Low-fidelity Bar Representation (Mon to Sun)
                        val days = listOf(
                            Pair("Mon", 0.7f),
                            Pair("Tue", 0.5f),
                            Pair("Wed", 0.9f),
                            Pair("Thu", 0.8f),
                            Pair("Fri", 0.4f),
                            Pair("Sat", 0.6f),
                            Pair("Sun", 0.3f)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            days.forEach { (day, fraction) ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.width(32.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(18.dp)
                                            .height((90 * fraction).dp)
                                            .background(
                                                if (day == "Thu") WireframeBlack else WireframeBoxFill,
                                                RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)
                                            )
                                            .border(1.dp, WireframeBorderDark, RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = day,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = if (day == "Thu") FontWeight.Bold else FontWeight.Normal,
                                        color = if (day == "Thu") WireframeBlack else WireframeTextSecondary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Subject Progress Section
        item {
            Text(
                text = "Subject Breakdown",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )
        }

        items(subjects) { subject ->
            WireframeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectSubject(subject.id) }
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(subject.name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        Text("${subject.progressPercent}%", fontSize = 12.sp, fontFamily = FontFamily.Monospace, color = WireframeBlack)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    WireframeProgressBar(
                        progressPercent = subject.progressPercent,
                        height = 6.dp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${subject.totalStudyMinutes / 60} hrs study time • ${subject.pendingTasksCount} tasks remaining",
                        fontSize = 11.sp,
                        color = WireframeTextSecondary
                    )
                }
            }
        }

        // Study History Section
        item {
            Text(
                text = "Recent Study History",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )
        }

        item {
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    val logs = listOf(
                        Triple("Today, 10:30 AM", "Computer Science", "25 min focus"),
                        Triple("Yesterday, 4:15 PM", "Mathematics", "45 min focus"),
                        Triple("Sep 1, 2:00 PM", "Physics", "30 min focus"),
                        Triple("Aug 31, 11:00 AM", "Chemistry", "50 min focus")
                    )
                    logs.forEach { (time, sub, duration) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(sub, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                                Text(time, fontSize = 11.sp, color = WireframeTextMuted)
                            }
                            WireframeBadge(text = duration)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
