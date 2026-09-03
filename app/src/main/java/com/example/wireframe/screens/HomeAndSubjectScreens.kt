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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.wireframe.components.WireframeCheckbox
import com.example.wireframe.components.WireframeProgressBar
import com.example.wireframe.components.WireframeTopBar
import com.example.wireframe.model.SubjectItem
import com.example.wireframe.model.TaskItem

/**
 * SCREEN 07 — HOME DASHBOARD
 * Greeting, Date, Today's progress, Quick study action, Upcoming tasks, Today's subjects, Study streak, Progress summary
 */
@Composable
fun HomeDashboardScreen(
    tasks: List<TaskItem>,
    subjects: List<SubjectItem>,
    onToggleTask: (String) -> Unit,
    onNavigateToStudy: (String?) -> Unit,
    onNavigateToSubjectDetails: (String) -> Unit,
    onNavigateToAllSubjects: () -> Unit,
    onNavigateToTasks: () -> Unit,
    onNavigateToNotifications: () -> Unit
) {
    val completedCount = tasks.count { it.isCompleted }
    val totalCount = tasks.size
    val progressPercent = if (totalCount > 0) (completedCount * 100) / totalCount else 0

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_07_home_dashboard")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header: Greeting, Date, Notifications, Streak
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "SCREEN 07 — HOME DASHBOARD",
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextMuted
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Hello, Alex",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextPrimary
                    )
                    Text(
                        text = "Thursday, Sep 3, 2026",
                        fontSize = 12.sp,
                        color = WireframeTextSecondary
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Study Streak Badge
                    Row(
                        modifier = Modifier
                            .background(WireframeSurfaceVariant, RoundedCornerShape(16.dp))
                            .border(1.dp, WireframeBorderDark, RoundedCornerShape(16.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Streak",
                            tint = WireframeBlack,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "7 Days",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = WireframeBlack
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Notifications Button
                    IconButton(
                        onClick = onNavigateToNotifications,
                        modifier = Modifier
                            .size(36.dp)
                            .border(1.dp, WireframeBorder, CircleShape)
                            .testTag("home_notifications_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = WireframeBlack,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Today's Progress Card & Summary
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
                            text = "Today's Study Progress",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = WireframeTextPrimary
                        )
                        WireframeBadge(text = "$completedCount / $totalCount Tasks", isFilled = true)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    WireframeProgressBar(
                        progressPercent = progressPercent,
                        height = 10.dp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Completion: $progressPercent%",
                            fontSize = 12.sp,
                            color = WireframeTextSecondary
                        )
                        Text(
                            text = "Focus Target: 2.5 hrs / 3.0 hrs",
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeTextPrimary
                        )
                    }
                }
            }
        }

        // Quick Study Action (Primary Call to Action)
        item {
            WireframeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToStudy("Computer Science") },
                borderWidth = 1.5.dp,
                borderColor = WireframeBlack
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(WireframeBlack, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Start Study",
                                tint = WireframeWhite,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Quick Study Session",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = WireframeTextPrimary
                            )
                            Text(
                                text = "Next: Computer Science (25 min)",
                                fontSize = 12.sp,
                                color = WireframeTextSecondary
                            )
                        }
                    }
                    Text(
                        text = "Start →",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = WireframeBlack
                    )
                }
            }
        }

        // Upcoming Tasks Section
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Upcoming Tasks",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeTextPrimary
                )
                Text(
                    text = "View All (${tasks.size})",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = WireframeTextSecondary,
                    modifier = Modifier
                        .clickable(onClick = onNavigateToTasks)
                        .padding(4.dp)
                )
            }
        }

        items(tasks.take(3)) { task ->
            WireframeCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WireframeCheckbox(
                        checked = task.isCompleted,
                        onCheckedChange = { onToggleTask(task.id) }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = task.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (task.isCompleted) WireframeTextMuted else WireframeTextPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = task.subject,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                color = WireframeTextSecondary
                            )
                            Text("•", fontSize = 10.sp, color = WireframeTextMuted)
                            Text(
                                text = "Due ${task.dueDate}",
                                fontSize = 11.sp,
                                color = WireframeTextMuted
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    WireframeBadge(
                        text = task.priority.name,
                        isFilled = task.priority.name == "HIGH"
                    )
                }
            }
        }

        // Today's Subjects Section
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today's Subjects",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeTextPrimary
                )
                Text(
                    text = "Manage Subjects",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = WireframeTextSecondary,
                    modifier = Modifier
                        .clickable(onClick = onNavigateToAllSubjects)
                        .padding(4.dp)
                )
            }
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(subjects) { subject ->
                    WireframeCard(
                        modifier = Modifier
                            .width(160.dp)
                            .clickable { onNavigateToSubjectDetails(subject.id) }
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = subject.code,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    color = WireframeTextMuted
                                )
                                Text(
                                    text = "${subject.pendingTasksCount} tasks",
                                    fontSize = 10.sp,
                                    color = WireframeTextSecondary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = subject.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = WireframeTextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            WireframeProgressBar(
                                progressPercent = subject.progressPercent,
                                height = 6.dp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "${subject.progressPercent}% syllabus completed",
                                fontSize = 10.sp,
                                color = WireframeTextSecondary
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

/**
 * SCREEN 08 — SUBJECTS
 * Page title, Search/filter placeholder, Subject cards, Progress indicator, Pending task count, Add subject action
 */
@Composable
fun SubjectsScreen(
    subjects: List<SubjectItem>,
    onSelectSubject: (String) -> Unit,
    onAddSubject: () -> Unit,
    onBack: (() -> Unit)? = null
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_08_subjects")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            WireframeTopBar(
                title = "Subjects",
                screenNumber = "08",
                onBack = onBack,
                actions = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .border(1.dp, WireframeBlack, RoundedCornerShape(4.dp))
                            .clickable(onClick = onAddSubject),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Subject",
                            tint = WireframeBlack,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        }

        // Search / Filter Placeholder
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(WireframeBoxFill, RoundedCornerShape(4.dp))
                    .border(1.dp, WireframeBorder, RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = WireframeTextMuted,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "[ Search & filter subjects placeholder ]",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = WireframeTextMuted
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enrolled Subjects (${subjects.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeTextPrimary
                )
                Text(
                    text = "Fall Semester 2026",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = WireframeTextSecondary
                )
            }
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(WireframeSurfaceVariant, RoundedCornerShape(4.dp))
                                    .border(1.dp, WireframeBorder, RoundedCornerShape(4.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Book,
                                    contentDescription = null,
                                    tint = WireframeDarkGray,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = subject.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = WireframeTextPrimary
                                )
                                Text(
                                    text = "Code: ${subject.code}",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = WireframeTextSecondary
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Details",
                            tint = WireframeTextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Progress indicator
                    WireframeProgressBar(
                        progressPercent = subject.progressPercent,
                        showLabel = true,
                        height = 8.dp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${subject.pendingTasksCount} pending tasks",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = WireframeTextPrimary
                        )
                        Text(
                            text = "${subject.totalStudyMinutes / 60}h logged",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeTextSecondary
                        )
                    }
                }
            }
        }

        item {
            WireframeButton(
                text = "+ Add New Subject",
                onClick = onAddSubject,
                isPrimary = false,
                modifier = Modifier.fillMaxWidth(),
                testTag = "subjects_add_button"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * SCREEN 09 — SUBJECT DETAILS
 * Subject title, Overall progress, Progress indicator, Study time, Upcoming tasks, Completed tasks, Recent activity
 */
@Composable
fun SubjectDetailsScreen(
    subject: SubjectItem,
    tasks: List<TaskItem>,
    onBack: () -> Unit,
    onStartStudy: (String) -> Unit,
    onAddTask: () -> Unit
) {
    val subjectTasks = tasks.filter { it.subject.contains(subject.name, ignoreCase = true) || subject.name.contains(it.subject, ignoreCase = true) }
    val pending = subjectTasks.filter { !it.isCompleted }
    val completed = subjectTasks.filter { it.isCompleted }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_09_subject_details")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WireframeTopBar(
                title = subject.name,
                screenNumber = "09",
                onBack = onBack
            )
        }

        // Overall progress & metrics card
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
                            text = "Course Progress",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = WireframeTextPrimary
                        )
                        Text(
                            text = "${subject.progressPercent}%",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeBlack
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    WireframeProgressBar(
                        progressPercent = subject.progressPercent,
                        height = 10.dp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Stats row: Study Time & Task Stats
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Total Study Time", fontSize = 11.sp, color = WireframeTextSecondary)
                            Text("${subject.totalStudyMinutes / 60}h ${subject.totalStudyMinutes % 60}m", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        }
                        Column {
                            Text("Pending Tasks", fontSize = 11.sp, color = WireframeTextSecondary)
                            Text("${pending.size} Tasks", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        }
                        Column {
                            Text("Completed", fontSize = 11.sp, color = WireframeTextSecondary)
                            Text("${completed.size} Tasks", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                        }
                    }
                }
            }
        }

        // Quick Actions Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                WireframeButton(
                    text = "Start Focus Session",
                    onClick = { onStartStudy(subject.name) },
                    modifier = Modifier.weight(1f),
                    isPrimary = true,
                    testTag = "subject_details_start_study_button"
                )
                WireframeButton(
                    text = "+ Task",
                    onClick = onAddTask,
                    modifier = Modifier.width(100.dp),
                    isPrimary = false,
                    testTag = "subject_details_add_task_button"
                )
            }
        }

        // Upcoming Tasks
        item {
            Text(
                text = "Upcoming Subject Tasks (${pending.size})",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )
        }

        if (pending.isEmpty()) {
            item {
                WireframeCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "No pending tasks for this subject. Great job!",
                        fontSize = 13.sp,
                        color = WireframeTextSecondary
                    )
                }
            }
        } else {
            items(pending) { task ->
                WireframeCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(task.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
                            Text("Due: ${task.dueDate} • ${task.priority.name}", fontSize = 11.sp, color = WireframeTextSecondary)
                        }
                        WireframeBadge(text = "PENDING")
                    }
                }
            }
        }

        // Completed Tasks
        item {
            Text(
                text = "Completed Coursework (${completed.size})",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )
        }

        if (completed.isEmpty()) {
            item {
                WireframeCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "No completed tasks recorded yet.",
                        fontSize = 13.sp,
                        color = WireframeTextMuted
                    )
                }
            }
        } else {
            items(completed) { task ->
                WireframeCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(task.title, fontSize = 13.sp, color = WireframeTextMuted)
                        WireframeBadge(text = "DONE", isFilled = true)
                    }
                }
            }
        }

        // Recent Activity
        item {
            Text(
                text = "Recent Subject Activity",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        text = subject.recentActivity,
                        fontSize = 12.sp,
                        color = WireframeTextSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Last focused: Yesterday for 45 min",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeTextMuted
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
