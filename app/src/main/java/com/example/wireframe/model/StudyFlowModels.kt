// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

package com.example.wireframe.model

enum class ScreenId(
    val code: String,
    val title: String,
    val description: String,
    val flowCategory: String
) {
    SPLASH("01", "Splash Screen", "App logo placeholder, StudyFlow brand text, loading indicator", "Flow 1 — New User"),
    ONBOARDING_WELCOME("02", "Onboarding — Welcome", "Illustration placeholder, welcome title, value proposition, progress indicator", "Flow 1 — New User"),
    ONBOARDING_ORGANIZE("03", "Onboarding — Organize Studies", "Organize subjects, tasks, and deadlines in one central location", "Flow 1 — New User"),
    ONBOARDING_TRACK("04", "Onboarding — Track Progress", "Monitor study time, streaks, and academic milestones", "Flow 1 — New User"),
    LOGIN("05", "Login", "Email, password, login action, forgot password, sign-up link", "Flow 1 — New User"),
    SIGN_UP("06", "Sign Up", "Name, email, password, confirm password, create account", "Flow 1 — New User"),
    HOME_DASHBOARD("07", "Home Dashboard", "Greeting, date, today's progress, quick study, upcoming tasks, subjects, streak", "Primary Hub"),
    SUBJECTS("08", "Subjects", "Search/filter, subject cards, progress indicators, pending task count, add subject", "Flow 5 — Subject Mgmt"),
    SUBJECT_DETAILS("09", "Subject Details", "Overall progress, study time, upcoming tasks, completed tasks, recent activity", "Flow 5 — Subject Mgmt"),
    TASKS("10", "Tasks", "Task list, subject tags, due date, priority, completion checkboxes, filter tabs", "Flow 2 — Create Task"),
    ADD_TASK("11", "Add Task", "Title, subject selector, due date, time, priority selector, notes, save/cancel", "Flow 2 — Create Task"),
    EDIT_TASK("12", "Edit Task", "Pre-filled task editor with existing data, status toggle, save/cancel", "Flow 2 — Create Task"),
    STUDY_SESSION("13", "Study Session / Focus Mode", "Subject, study topic, large structural timer, progress, pause & end buttons", "Flow 3 — Study Session"),
    STUDY_COMPLETED("14", "Study Session Completed", "Completion indicator, session duration, subject, summary, updated progress", "Flow 3 — Study Session"),
    PROGRESS_ANALYTICS("15", "Progress / Analytics", "Overall progress, weekly time, completed tasks, streak, weekly chart, history", "Flow 4 — View Progress"),
    PROFILE("16", "Profile", "Avatar placeholder, student name, email, study statistics, streak, completed count", "Flow 6 — Account"),
    SETTINGS("17", "Settings", "Notifications, study reminders, appearance, language, privacy, logout", "Flow 6 — Account"),
    NOTIFICATIONS("18", "Notifications", "Task reminders, study reminders, session completions, empty state structure", "Utility Hub")
}

enum class Priority(val label: String) {
    HIGH("High Priority"),
    MEDIUM("Medium Priority"),
    LOW("Low Priority")
}

data class TaskItem(
    val id: String,
    val title: String,
    val subject: String,
    val dueDate: String,
    val dueTime: String = "11:59 PM",
    val priority: Priority,
    val isCompleted: Boolean = false,
    val notes: String = ""
)

data class SubjectItem(
    val id: String,
    val name: String,
    val code: String,
    val progressPercent: Int,
    val pendingTasksCount: Int,
    val completedTasksCount: Int,
    val totalStudyMinutes: Int,
    val recentActivity: String
)

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val timeAgo: String,
    val category: String, // "Task Reminder", "Study Reminder", "Session Completed"
    val isRead: Boolean = false
)

data class StudentProfile(
    val name: String = "Alex Chen",
    val email: String = "alex.chen@university.edu",
    val major: String = "Computer Science & Engineering",
    val semester: String = "Fall 2026",
    val currentStreakDays: Int = 7,
    val totalCompletedTasks: Int = 42,
    val totalStudyHoursThisWeek: Double = 18.5,
    val overallProgressPercent: Int = 74
)

data class WeeklyStudyDay(
    val day: String,
    val hours: Float,
    val isToday: Boolean = false
)
