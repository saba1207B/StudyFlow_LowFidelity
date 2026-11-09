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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
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
import com.example.ui.theme.WireframeSurfaceVariant
import com.example.ui.theme.WireframeTextMuted
import com.example.ui.theme.WireframeTextPrimary
import com.example.ui.theme.WireframeTextSecondary
import com.example.ui.theme.WireframeWhite
import com.example.wireframe.components.WireframeBadge
import com.example.wireframe.components.WireframeButton
import com.example.wireframe.components.WireframeCard
import com.example.wireframe.components.WireframeCheckbox
import com.example.wireframe.components.WireframeInputField
import com.example.wireframe.components.WireframeTopBar
import com.example.wireframe.model.Priority
import com.example.wireframe.model.SubjectItem
import com.example.wireframe.model.TaskItem

/**
 * SCREEN 10 — TASKS
 * Page title, Task list, Task cards, Subject, Due date, Priority, Completion checkbox, Filter controls, Add task button
 */
@Composable
fun TasksScreen(
    tasks: List<TaskItem>,
    onToggleTask: (String) -> Unit,
    onNavigateToAddTask: () -> Unit,
    onNavigateToEditTask: (String) -> Unit,
    onDeleteTask: (String) -> Unit
) {
    var selectedFilter by remember { mutableStateOf("All") }
    val filterOptions = listOf("All", "Pending", "Due Today", "High Priority", "Completed")

    val filteredTasks = tasks.filter { task ->
        when (selectedFilter) {
            "Pending" -> !task.isCompleted
            "Due Today" -> task.dueDate.contains("Today", ignoreCase = true) || task.dueDate.contains("Sep 3", ignoreCase = true)
            "High Priority" -> task.priority == Priority.HIGH
            "Completed" -> task.isCompleted
            else -> true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(WireframeWhite)
                .testTag("screen_10_tasks")
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                WireframeTopBar(
                    title = "Tasks & Assignments",
                    screenNumber = "10",
                    actions = {
                        Text(
                            text = "+ Add Task",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = WireframeBlack,
                            modifier = Modifier
                                .clickable(onClick = onNavigateToAddTask)
                                .border(1.dp, WireframeBlack, RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                )
            }

            // Filter Controls (Horizontal wireframe tab chips)
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(filterOptions) { filter ->
                        val isSelected = selectedFilter == filter
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) WireframeBlack else WireframeSurfaceVariant)
                                .border(1.dp, if (isSelected) WireframeBlack else WireframeBorder, RoundedCornerShape(16.dp))
                                .clickable { selectedFilter = filter }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = filter,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) WireframeWhite else WireframeTextPrimary
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Task List (${filteredTasks.size})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextPrimary
                    )
                    Text(
                        text = "Filter: $selectedFilter",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeTextSecondary
                    )
                }
            }

            if (filteredTasks.isEmpty()) {
                item {
                    WireframeCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "[ No Tasks Found in Filter: $selectedFilter ]",
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace,
                                color = WireframeTextMuted
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            WireframeButton(
                                text = "+ Create Task",
                                onClick = onNavigateToAddTask,
                                isPrimary = false
                            )
                        }
                    }
                }
            } else {
                items(filteredTasks) { task ->
                    WireframeCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
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
                                        color = if (task.isCompleted) WireframeTextMuted else WireframeTextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(3.dp))
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
                                            text = "Due: ${task.dueDate} at ${task.dueTime}",
                                            fontSize = 11.sp,
                                            color = WireframeTextSecondary
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(6.dp))

                                WireframeBadge(
                                    text = task.priority.name,
                                    isFilled = task.priority == Priority.HIGH
                                )
                            }

                            if (task.notes.isNotBlank()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Notes: ${task.notes}",
                                    fontSize = 11.sp,
                                    color = WireframeTextSecondary,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(WireframeSurfaceVariant, RoundedCornerShape(2.dp))
                                        .padding(6.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Edit Task",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = WireframeBlack,
                                    modifier = Modifier
                                        .clickable { onNavigateToEditTask(task.id) }
                                        .padding(4.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Delete",
                                    fontSize = 11.sp,
                                    color = WireframeTextMuted,
                                    modifier = Modifier
                                        .clickable { onDeleteTask(task.id) }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

/**
 * SCREEN 11 — ADD TASK
 * Task title, Subject selector, Due date, Time, Priority, Notes, Save button, Cancel/back action
 */
@Composable
fun AddTaskScreen(
    subjects: List<SubjectItem>,
    onSaveTask: (title: String, subject: String, dueDate: String, dueTime: String, priority: Priority, notes: String) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("Calculus Assignment 4: Derivatives") }
    var selectedSubject by remember { mutableStateOf(subjects.firstOrNull()?.name ?: "Mathematics") }
    var dueDate by remember { mutableStateOf("Tomorrow (Sep 4, 2026)") }
    var dueTime by remember { mutableStateOf("11:59 PM") }
    var selectedPriority by remember { mutableStateOf(Priority.HIGH) }
    var notes by remember { mutableStateOf("Complete problem set exercises 12 through 28. Show intermediate steps.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_11_add_task")
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WireframeTopBar(
            title = "Add Task",
            screenNumber = "11",
            onBack = onCancel
        )

        // Task Title
        WireframeInputField(
            label = "Task Title *",
            value = title,
            onValueChange = { title = it },
            placeholder = "e.g. Read Physics Chapter 5",
            testTag = "add_task_title_input"
        )

        // Subject Selector
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Subject *",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = WireframeTextPrimary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(subjects) { subject ->
                    val isSelected = selectedSubject == subject.name
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) WireframeBlack else WireframeWhite)
                            .border(1.dp, if (isSelected) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                            .clickable { selectedSubject = subject.name }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = subject.name,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) WireframeWhite else WireframeTextPrimary
                        )
                    }
                }
            }
        }

        // Due Date & Time Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                WireframeInputField(
                    label = "Due Date",
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    placeholder = "Select date",
                    testTag = "add_task_date_input"
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                WireframeInputField(
                    label = "Time",
                    value = dueTime,
                    onValueChange = { dueTime = it },
                    placeholder = "Select time",
                    testTag = "add_task_time_input"
                )
            }
        }

        // Priority Selector
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Priority Level",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = WireframeTextPrimary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Priority.values().forEach { priority ->
                    val isSelected = selectedPriority == priority
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) WireframeBlack else WireframeSurfaceVariant)
                            .border(1.dp, if (isSelected) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                            .clickable { selectedPriority = priority },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = priority.name,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) WireframeWhite else WireframeTextPrimary
                        )
                    }
                }
            }
        }

        // Notes field
        WireframeInputField(
            label = "Task Notes & Instructions",
            value = notes,
            onValueChange = { notes = it },
            placeholder = "Optional guidelines, book chapters, submission link...",
            helperText = "Add any assignment details or submission criteria",
            testTag = "add_task_notes_input"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save and Cancel Actions
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WireframeButton(
                text = "Save Task",
                onClick = {
                    onSaveTask(title, selectedSubject, dueDate, dueTime, selectedPriority, notes)
                },
                modifier = Modifier.fillMaxWidth(),
                testTag = "add_task_save_button"
            )
            WireframeButton(
                text = "Cancel",
                onClick = onCancel,
                isPrimary = false,
                modifier = Modifier.fillMaxWidth(),
                testTag = "add_task_cancel_button"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * SCREEN 12 — EDIT TASK
 * Pre-filled task editor with existing task information, status toggle, save changes, delete, cancel/back
 */
@Composable
fun EditTaskScreen(
    task: TaskItem,
    subjects: List<SubjectItem>,
    onSaveTask: (TaskItem) -> Unit,
    onDeleteTask: (String) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var selectedSubject by remember { mutableStateOf(task.subject) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var dueTime by remember { mutableStateOf(task.dueTime) }
    var selectedPriority by remember { mutableStateOf(task.priority) }
    var isCompleted by remember { mutableStateOf(task.isCompleted) }
    var notes by remember { mutableStateOf(task.notes) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("screen_12_edit_task")
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WireframeTopBar(
            title = "Edit Task",
            screenNumber = "12",
            onBack = onCancel,
            actions = {
                IconButton(onClick = { onDeleteTask(task.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Task",
                        tint = WireframeBlack
                    )
                }
            }
        )

        // Status Card
        WireframeCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = WireframeSurfaceVariant
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Completion Status", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeTextPrimary)
                    Text(if (isCompleted) "Completed" else "Pending action", fontSize = 11.sp, color = WireframeTextSecondary)
                }
                WireframeCheckbox(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it }
                )
            }
        }

        // Task Title
        WireframeInputField(
            label = "Task Title",
            value = title,
            onValueChange = { title = it },
            testTag = "edit_task_title_input"
        )

        // Subject Selector
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Subject",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = WireframeTextPrimary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(subjects) { subject ->
                    val isSelected = selectedSubject == subject.name
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) WireframeBlack else WireframeWhite)
                            .border(1.dp, if (isSelected) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                            .clickable { selectedSubject = subject.name }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = subject.name,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) WireframeWhite else WireframeTextPrimary
                        )
                    }
                }
            }
        }

        // Due Date & Time
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                WireframeInputField(
                    label = "Due Date",
                    value = dueDate,
                    onValueChange = { dueDate = it }
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                WireframeInputField(
                    label = "Time",
                    value = dueTime,
                    onValueChange = { dueTime = it }
                )
            }
        }

        // Priority Level
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Priority Level",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = WireframeTextPrimary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Priority.values().forEach { priority ->
                    val isSelected = selectedPriority == priority
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) WireframeBlack else WireframeSurfaceVariant)
                            .border(1.dp, if (isSelected) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                            .clickable { selectedPriority = priority },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = priority.name,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) WireframeWhite else WireframeTextPrimary
                        )
                    }
                }
            }
        }

        // Notes
        WireframeInputField(
            label = "Task Notes & Instructions",
            value = notes,
            onValueChange = { notes = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WireframeButton(
                text = "Save Changes",
                onClick = {
                    onSaveTask(
                        task.copy(
                            title = title,
                            subject = selectedSubject,
                            dueDate = dueDate,
                            dueTime = dueTime,
                            priority = selectedPriority,
                            isCompleted = isCompleted,
                            notes = notes
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                testTag = "edit_task_save_button"
            )

            WireframeButton(
                text = "Cancel",
                onClick = onCancel,
                isPrimary = false,
                modifier = Modifier.fillMaxWidth(),
                testTag = "edit_task_cancel_button"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
