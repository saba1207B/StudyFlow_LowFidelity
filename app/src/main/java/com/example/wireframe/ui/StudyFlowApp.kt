package com.example.wireframe.ui

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
import com.example.wireframe.components.WireframeBottomNavigation
import com.example.wireframe.components.WireframeButton
import com.example.wireframe.components.WireframeCard
import com.example.wireframe.docs.UXDocSection
import com.example.wireframe.docs.UXDocumentationViewer
import com.example.wireframe.model.NotificationItem
import com.example.wireframe.model.Priority
import com.example.wireframe.model.ScreenId
import com.example.wireframe.model.StudentProfile
import com.example.wireframe.model.SubjectItem
import com.example.wireframe.model.TaskItem
import com.example.wireframe.screens.AddTaskScreen
import com.example.wireframe.screens.EditTaskScreen
import com.example.wireframe.screens.HomeDashboardScreen
import com.example.wireframe.screens.LoginScreen
import com.example.wireframe.screens.NotificationsScreen
import com.example.wireframe.screens.OnboardingOrganizeScreen
import com.example.wireframe.screens.OnboardingTrackScreen
import com.example.wireframe.screens.OnboardingWelcomeScreen
import com.example.wireframe.screens.ProfileScreen
import com.example.wireframe.screens.ProgressAnalyticsScreen
import com.example.wireframe.screens.SettingsScreen
import com.example.wireframe.screens.SignUpScreen
import com.example.wireframe.screens.SplashScreen
import com.example.wireframe.screens.StudySessionCompletedScreen
import com.example.wireframe.screens.StudySessionScreen
import com.example.wireframe.screens.SubjectDetailsScreen
import com.example.wireframe.screens.SubjectsScreen
import com.example.wireframe.screens.TasksScreen

@Composable
fun StudyFlowApp() {
    // Current Active Screen State
    var currentScreen by remember { mutableStateOf(ScreenId.SPLASH) }
    var selectedSubjectId by remember { mutableStateOf<String?>("sub_cs") }
    var editingTaskId by remember { mutableStateOf<String?>("t1") }
    var activeStudySubject by remember { mutableStateOf("Computer Science") }

    // UX Documentation Inspection Overlay
    var isViewingUXDocs by remember { mutableStateOf(false) }
    var showScreenSelectorDialog by remember { mutableStateOf(false) }

    // Realistic Mock Student Data
    val subjects = remember {
        mutableStateListOf(
            SubjectItem("sub_math", "Mathematics", "MATH-101", 68, 3, 12, 840, "Calculus problem set reviewed"),
            SubjectItem("sub_phys", "Physics", "PHYS-201", 45, 2, 8, 510, "Electromagnetism reading completed"),
            SubjectItem("sub_cs", "Computer Science", "CS-301", 82, 1, 16, 1120, "Graph algorithms implemented"),
            SubjectItem("sub_chem", "Chemistry", "CHEM-102", 55, 2, 6, 380, "Organic chemistry laboratory prep"),
            SubjectItem("sub_eng", "English Literature", "ENG-110", 90, 0, 10, 420, "Research essay draft submitted")
        )
    }

    val tasks = remember {
        mutableStateListOf(
            TaskItem(
                id = "t1",
                title = "Calculus Assignment 4: Derivatives",
                subject = "Mathematics",
                dueDate = "Today (Sep 3)",
                dueTime = "11:59 PM",
                priority = Priority.HIGH,
                isCompleted = false,
                notes = "Complete exercises 12-28. Show all working clearly."
            ),
            TaskItem(
                id = "t2",
                title = "Physics Lab Report: Wave Optics",
                subject = "Physics",
                dueDate = "Tomorrow (Sep 4)",
                dueTime = "5:00 PM",
                priority = Priority.MEDIUM,
                isCompleted = false,
                notes = "Include error propagation analysis and graphs."
            ),
            TaskItem(
                id = "t3",
                title = "Binary Search Tree Implementation",
                subject = "Computer Science",
                dueDate = "Sep 6, 2026",
                dueTime = "11:59 PM",
                priority = Priority.HIGH,
                isCompleted = false,
                notes = "Add recursive balancing and JUnit tests."
            ),
            TaskItem(
                id = "t4",
                title = "Organic Chemistry Functional Groups Review",
                subject = "Chemistry",
                dueDate = "Sep 7, 2026",
                dueTime = "8:00 PM",
                priority = Priority.LOW,
                isCompleted = true,
                notes = "Flashcard deck reviewed."
            ),
            TaskItem(
                id = "t5",
                title = "Modernist Poetry Comparative Essay",
                subject = "English Literature",
                dueDate = "Sep 10, 2026",
                dueTime = "11:59 PM",
                priority = Priority.MEDIUM,
                isCompleted = true,
                notes = "Final draft submitted to professor."
            )
        )
    }

    val notifications = remember {
        mutableStateListOf(
            NotificationItem("n1", "Assignment Due Today", "Calculus Assignment 4 is due tonight at 11:59 PM.", "15m ago", "Task Reminder"),
            NotificationItem("n2", "Study Habit Streak", "Time for your daily 25 min Computer Science focus session.", "1h ago", "Study Reminder"),
            NotificationItem("n3", "Session Logged", "Physics review session completed (30 minutes).", "Yesterday", "Session Completed")
        )
    }

    var profile by remember {
        mutableStateOf(
            StudentProfile(
                name = "Alex Chen",
                email = "alex.chen@university.edu",
                major = "Computer Science & Engineering",
                semester = "Fall 2026",
                currentStreakDays = 7,
                totalCompletedTasks = 42,
                totalStudyHoursThisWeek = 18.5,
                overallProgressPercent = 74
            )
        )
    }

    // Determine Bottom Nav Visibility
    val showBottomNav = when (currentScreen) {
        ScreenId.HOME_DASHBOARD,
        ScreenId.TASKS,
        ScreenId.SUBJECTS,
        ScreenId.PROGRESS_ANALYTICS,
        ScreenId.PROFILE -> true
        else -> false
    }

    val currentTab = when (currentScreen) {
        ScreenId.HOME_DASHBOARD -> "Home"
        ScreenId.TASKS -> "Tasks"
        ScreenId.STUDY_SESSION, ScreenId.STUDY_COMPLETED -> "Study"
        ScreenId.PROGRESS_ANALYTICS -> "Progress"
        ScreenId.PROFILE -> "Profile"
        else -> "Home"
    }

    if (isViewingUXDocs) {
        UXDocumentationViewer(
            onLaunchScreen = { screen ->
                currentScreen = screen
                isViewingUXDocs = false
            },
            onBackToPrototype = { isViewingUXDocs = false }
        )
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Inspector Toolbar: Screen indicator + Jump to any of 18 screens + UX Docs Toggle
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WireframeWhite)
                    .border(1.dp, WireframeBorderLight)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Screen Indicator Chip
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(WireframeSurfaceVariant)
                            .border(1.dp, WireframeBorder, RoundedCornerShape(4.dp))
                            .clickable { showScreenSelectorDialog = true }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Screen ${currentScreen.code} / 18: ${currentScreen.title}",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = WireframeBlack
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "▾",
                            fontSize = 12.sp,
                            color = WireframeTextSecondary
                        )
                    }

                    // UX Documentation Button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(WireframeBlack)
                            .clickable { isViewingUXDocs = true }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .testTag("app_bar_ux_docs_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "UX Docs (01-06)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeWhite
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (showBottomNav) {
                WireframeBottomNavigation(
                    selectedTab = currentTab,
                    onSelectTab = { tab ->
                        when (tab) {
                            "Home" -> currentScreen = ScreenId.HOME_DASHBOARD
                            "Tasks" -> currentScreen = ScreenId.TASKS
                            "Study" -> currentScreen = ScreenId.STUDY_SESSION
                            "Progress" -> currentScreen = ScreenId.PROGRESS_ANALYTICS
                            "Profile" -> currentScreen = ScreenId.PROFILE
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(WireframeWhite)
        ) {
            when (currentScreen) {
                ScreenId.SPLASH -> {
                    SplashScreen(
                        onContinue = { currentScreen = ScreenId.ONBOARDING_WELCOME }
                    )
                }

                ScreenId.ONBOARDING_WELCOME -> {
                    OnboardingWelcomeScreen(
                        onNext = { currentScreen = ScreenId.ONBOARDING_ORGANIZE },
                        onSkip = { currentScreen = ScreenId.LOGIN }
                    )
                }

                ScreenId.ONBOARDING_ORGANIZE -> {
                    OnboardingOrganizeScreen(
                        onNext = { currentScreen = ScreenId.ONBOARDING_TRACK },
                        onSkip = { currentScreen = ScreenId.LOGIN }
                    )
                }

                ScreenId.ONBOARDING_TRACK -> {
                    OnboardingTrackScreen(
                        onNext = { currentScreen = ScreenId.LOGIN },
                        onSkip = { currentScreen = ScreenId.LOGIN }
                    )
                }

                ScreenId.LOGIN -> {
                    LoginScreen(
                        onLoginSuccess = { currentScreen = ScreenId.HOME_DASHBOARD },
                        onNavigateToSignUp = { currentScreen = ScreenId.SIGN_UP },
                        onForgotPassword = {}
                    )
                }

                ScreenId.SIGN_UP -> {
                    SignUpScreen(
                        onSignUpSuccess = { currentScreen = ScreenId.HOME_DASHBOARD },
                        onNavigateToLogin = { currentScreen = ScreenId.LOGIN }
                    )
                }

                ScreenId.HOME_DASHBOARD -> {
                    HomeDashboardScreen(
                        tasks = tasks,
                        subjects = subjects,
                        onToggleTask = { taskId ->
                            val index = tasks.indexOfFirst { it.id == taskId }
                            if (index >= 0) {
                                val current = tasks[index]
                                tasks[index] = current.copy(isCompleted = !current.isCompleted)
                            }
                        },
                        onNavigateToStudy = { subject ->
                            activeStudySubject = subject ?: "Computer Science"
                            currentScreen = ScreenId.STUDY_SESSION
                        },
                        onNavigateToSubjectDetails = { subId ->
                            selectedSubjectId = subId
                            currentScreen = ScreenId.SUBJECT_DETAILS
                        },
                        onNavigateToAllSubjects = {
                            currentScreen = ScreenId.SUBJECTS
                        },
                        onNavigateToTasks = {
                            currentScreen = ScreenId.TASKS
                        },
                        onNavigateToNotifications = {
                            currentScreen = ScreenId.NOTIFICATIONS
                        }
                    )
                }

                ScreenId.SUBJECTS -> {
                    SubjectsScreen(
                        subjects = subjects,
                        onSelectSubject = { subId ->
                            selectedSubjectId = subId
                            currentScreen = ScreenId.SUBJECT_DETAILS
                        },
                        onAddSubject = {
                            // Wireframe demo: add subject
                            val newSub = SubjectItem("sub_${subjects.size + 1}", "Data Structures & Algorithms", "CS-302", 20, 2, 1, 120, "Course registration completed")
                            subjects.add(newSub)
                        },
                        onBack = { currentScreen = ScreenId.HOME_DASHBOARD }
                    )
                }

                ScreenId.SUBJECT_DETAILS -> {
                    val subject = subjects.find { it.id == selectedSubjectId } ?: subjects.first()
                    SubjectDetailsScreen(
                        subject = subject,
                        tasks = tasks,
                        onBack = { currentScreen = ScreenId.SUBJECTS },
                        onStartStudy = { subName ->
                            activeStudySubject = subName
                            currentScreen = ScreenId.STUDY_SESSION
                        },
                        onAddTask = { currentScreen = ScreenId.ADD_TASK }
                    )
                }

                ScreenId.TASKS -> {
                    TasksScreen(
                        tasks = tasks,
                        onToggleTask = { taskId ->
                            val index = tasks.indexOfFirst { it.id == taskId }
                            if (index >= 0) {
                                val current = tasks[index]
                                tasks[index] = current.copy(isCompleted = !current.isCompleted)
                            }
                        },
                        onNavigateToAddTask = { currentScreen = ScreenId.ADD_TASK },
                        onNavigateToEditTask = { taskId ->
                            editingTaskId = taskId
                            currentScreen = ScreenId.EDIT_TASK
                        },
                        onDeleteTask = { taskId ->
                            tasks.removeAll { it.id == taskId }
                        }
                    )
                }

                ScreenId.ADD_TASK -> {
                    AddTaskScreen(
                        subjects = subjects,
                        onSaveTask = { title, subject, dueDate, dueTime, priority, notes ->
                            tasks.add(
                                TaskItem(
                                    id = "t_${System.currentTimeMillis()}",
                                    title = title,
                                    subject = subject,
                                    dueDate = dueDate,
                                    dueTime = dueTime,
                                    priority = priority,
                                    isCompleted = false,
                                    notes = notes
                                )
                            )
                            currentScreen = ScreenId.TASKS
                        },
                        onCancel = { currentScreen = ScreenId.TASKS }
                    )
                }

                ScreenId.EDIT_TASK -> {
                    val taskToEdit = tasks.find { it.id == editingTaskId } ?: tasks.first()
                    EditTaskScreen(
                        task = taskToEdit,
                        subjects = subjects,
                        onSaveTask = { updated ->
                            val index = tasks.indexOfFirst { it.id == updated.id }
                            if (index >= 0) {
                                tasks[index] = updated
                            }
                            currentScreen = ScreenId.TASKS
                        },
                        onDeleteTask = { id ->
                            tasks.removeAll { it.id == id }
                            currentScreen = ScreenId.TASKS
                        },
                        onCancel = { currentScreen = ScreenId.TASKS }
                    )
                }

                ScreenId.STUDY_SESSION -> {
                    StudySessionScreen(
                        subjectName = activeStudySubject,
                        onEndSession = { currentScreen = ScreenId.STUDY_COMPLETED },
                        onBack = { currentScreen = ScreenId.HOME_DASHBOARD }
                    )
                }

                ScreenId.STUDY_COMPLETED -> {
                    StudySessionCompletedScreen(
                        subjectName = activeStudySubject,
                        onContinue = { currentScreen = ScreenId.PROGRESS_ANALYTICS }
                    )
                }

                ScreenId.PROGRESS_ANALYTICS -> {
                    ProgressAnalyticsScreen(
                        profile = profile,
                        subjects = subjects,
                        onSelectSubject = { subId ->
                            selectedSubjectId = subId
                            currentScreen = ScreenId.SUBJECT_DETAILS
                        }
                    )
                }

                ScreenId.PROFILE -> {
                    ProfileScreen(
                        profile = profile,
                        onNavigateToSettings = { currentScreen = ScreenId.SETTINGS },
                        onBack = { currentScreen = ScreenId.HOME_DASHBOARD }
                    )
                }

                ScreenId.SETTINGS -> {
                    SettingsScreen(
                        onLogout = { currentScreen = ScreenId.LOGIN },
                        onBack = { currentScreen = ScreenId.PROFILE }
                    )
                }

                ScreenId.NOTIFICATIONS -> {
                    NotificationsScreen(
                        notifications = notifications,
                        onBack = { currentScreen = ScreenId.HOME_DASHBOARD }
                    )
                }
            }
        }
    }

    // Direct Screen Selector Dialog (Allows jumping to any of the 18 screens immediately)
    if (showScreenSelectorDialog) {
        Dialog(onDismissRequest = { showScreenSelectorDialog = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, WireframeBlack),
                color = WireframeWhite,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(560.dp)
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "SCREEN SELECTOR",
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = WireframeTextMuted
                            )
                            Text(
                                text = "Jump to Any Screen (18 Total)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = WireframeBlack
                            )
                        }
                        IconButton(onClick = { showScreenSelectorDialog = false }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(WireframeBorderLight))
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(ScreenId.values()) { screen ->
                            val isCurrent = currentScreen == screen
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (isCurrent) WireframeBlack else WireframeSurfaceVariant)
                                    .border(1.dp, if (isCurrent) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                                    .clickable {
                                        currentScreen = screen
                                        showScreenSelectorDialog = false
                                    }
                                    .padding(horizontal = 10.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "${screen.code} — ${screen.title}",
                                        fontSize = 12.sp,
                                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isCurrent) WireframeWhite else WireframeBlack
                                    )
                                    Text(
                                        text = screen.flowCategory,
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                        color = if (isCurrent) WireframeBorderLight else WireframeTextMuted
                                    )
                                }
                                if (isCurrent) {
                                    Text(
                                        text = "ACTIVE",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        color = WireframeWhite
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
