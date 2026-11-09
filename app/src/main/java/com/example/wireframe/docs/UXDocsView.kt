// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

package com.example.wireframe.docs

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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
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
import com.example.wireframe.model.ScreenId

enum class UXDocSection(val code: String, val title: String) {
    OVERVIEW("01", "UX Overview"),
    INFORMATION_ARCHITECTURE("02", "Information Architecture"),
    USER_FLOWS("03", "User Flows"),
    WIREFRAMES_CATALOG("04", "Low-Fidelity Wireframes"),
    NAVIGATION_MATRIX("05", "Screen-to-Screen Navigation"),
    UX_NOTES("06", "UX Notes")
}

@Composable
fun UXDocumentationViewer(
    initialSection: UXDocSection = UXDocSection.OVERVIEW,
    onLaunchScreen: (ScreenId) -> Unit,
    onBackToPrototype: () -> Unit
) {
    var activeSection by remember { mutableStateOf(initialSection) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .testTag("ux_docs_viewer")
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WireframeWhite)
                .border(1.dp, WireframeBorderLight)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "CODEALPHA UI/UX INTERNSHIP — TASK 1",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = WireframeTextMuted
                )
                Text(
                    text = "StudyFlow UX Architecture & Wireframe Spec",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeBlack
                )
            }

            WireframeButton(
                text = "Back to Prototype",
                onClick = onBackToPrototype,
                isPrimary = false,
                testTag = "docs_back_to_prototype_button"
            )
        }

        // Section Tabs
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(WireframeSurfaceVariant)
                .border(1.dp, WireframeBorder)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(UXDocSection.values()) { section ->
                val isSelected = activeSection == section
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isSelected) WireframeBlack else WireframeWhite)
                        .border(1.dp, if (isSelected) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                        .clickable { activeSection = section }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "${section.code} — ${section.title}",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) WireframeWhite else WireframeTextPrimary
                    )
                }
            }
        }

        // Section Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (activeSection) {
                UXDocSection.OVERVIEW -> {
                    item { UXOverviewSection(onLaunchScreen) }
                }
                UXDocSection.INFORMATION_ARCHITECTURE -> {
                    item { InformationArchitectureSection() }
                }
                UXDocSection.USER_FLOWS -> {
                    item { UserFlowsSection(onLaunchScreen) }
                }
                UXDocSection.WIREFRAMES_CATALOG -> {
                    item { WireframesCatalogSection(onLaunchScreen) }
                }
                UXDocSection.NAVIGATION_MATRIX -> {
                    item { NavigationMatrixSection() }
                }
                UXDocSection.UX_NOTES -> {
                    item { UXNotesSection() }
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

// 01 — UX OVERVIEW
@Composable
private fun UXOverviewSection(onLaunchScreen: (ScreenId) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "01 — UX Overview",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WireframeBlack
        )

        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Project Overview", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                Text(
                    text = "Project Name: StudyFlow — Student Study & Productivity App\n" +
                            "Platform: Native Mobile Application\n" +
                            "Target Frame: 390 × 844 px (Standard Handheld Canvas)\n" +
                            "Stage: Low-Fidelity Wireframing & Information Architecture",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    lineHeight = 18.sp,
                    color = WireframeTextPrimary
                )
            }
        }

        WireframeCard(modifier = Modifier.fillMaxWidth(), backgroundColor = WireframeSurfaceVariant) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Core Student Problem", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                Text(
                    text = "Students regularly juggle multiple academic disciplines, overlapping assignments, rigid test deadlines, and fragmented study habits across scattered notes, physical planners, and messaging apps. StudyFlow provides a single, distraction-free environment to consolidate subjects, prioritize tasks, maintain study momentum through timers, and track academic growth.",
                    fontSize = 13.sp,
                    color = WireframeTextSecondary,
                    lineHeight = 19.sp
                )
            }
        }

        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Target Audiences", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                val audiences = listOf(
                    "Secondary School Students (High school coursework, exam revision)",
                    "Undergraduate College Students (Multi-subject course loads, lab reports)",
                    "University & Graduate Students (Self-directed research, thesis milestones)"
                )
                audiences.forEach { audience ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).background(WireframeBlack, CircleShape))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(audience, fontSize = 12.sp, color = WireframeTextPrimary)
                    }
                }
            }
        }

        WireframeCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Strict Low-Fidelity Visual Mandates", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                Text(
                    text = "1. Strict 100% Grayscale Palette: Pure white, light gray, medium gray, dark gray, black.\n" +
                            "2. Zero Decorative Styling: No colors, no brand gradients, no glassmorphism, no 3D effects.\n" +
                            "3. Structural Representation: Timer dials, illustration placeholders, and chart graphs are rendered as wireframe blueprints.\n" +
                            "4. Minimum Touch Targets: Strict adherence to >= 48dp touch accessibility standards.",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = WireframeTextSecondary,
                    lineHeight = 18.sp
                )
            }
        }

        WireframeButton(
            text = "Start Flow 1: New User Journey →",
            onClick = { onLaunchScreen(ScreenId.SPLASH) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// 02 — INFORMATION ARCHITECTURE
@Composable
private fun InformationArchitectureSection() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "02 — Information Architecture",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WireframeBlack
        )

        Text(
            text = "StudyFlow's structural hierarchy is engineered into 5 primary pillars, keeping the cognitive load low while supporting rich academic workflows.",
            fontSize = 13.sp,
            color = WireframeTextSecondary
        )

        val iaPillars = listOf(
            Pair("Level 1: Entry & Onboarding", listOf("01. Splash Screen", "02. Onboarding — Welcome", "03. Onboarding — Organize Studies", "04. Onboarding — Track Progress", "05. Login", "06. Sign Up")),
            Pair("Level 2: Core Hub (Home)", listOf("07. Home Dashboard (Daily Progress, Quick Study, Today's Tasks, Subjects, Streak)")),
            Pair("Level 3: Academic Management", listOf("08. Subjects List (Search/Filter, Course Cards, Add Subject)", "09. Subject Details (Progress, Coursework, Recent Logs)")),
            Pair("Level 4: Task Execution & Focus", listOf("10. Tasks List (Filters: All/Due Today/High Priority)", "11. Add Task (Subject selector, Due Date, Priority, Notes)", "12. Edit Task (Pre-filled editor, Status toggle)", "13. Focus Mode / Study Session (Pomodoro Timer, Progress, Controls)", "14. Study Session Completed (Duration, Streak Update, KPI Impact)")),
            Pair("Level 5: Insights & Account", listOf("15. Progress / Analytics (Weekly Study Hours, Chart, Subject Progress, History)", "16. Student Profile (Avatar, KPIs, Semester Stats)", "17. Settings (Notifications, Reminders, Privacy, Logout)", "18. Notifications (Reminders, Alert List, Empty-State Structure)"))
        )

        iaPillars.forEach { (pillarTitle, screens) ->
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(pillarTitle, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(WireframeBorderLight))
                    screens.forEach { s ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("•", fontSize = 14.sp, color = WireframeBlack)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(s, fontSize = 12.sp, color = WireframeTextSecondary)
                        }
                    }
                }
            }
        }
    }
}

// 03 — USER FLOWS
@Composable
private fun UserFlowsSection(onLaunchScreen: (ScreenId) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "03 — User Flows",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WireframeBlack
        )

        val flows = listOf(
            Triple(
                "FLOW 1 — NEW USER JOURNEY",
                "Splash (01)  →  Onboarding (02–04)  →  Login / Sign Up (05/06)  →  Home Dashboard (07)",
                ScreenId.SPLASH
            ),
            Triple(
                "FLOW 2 — CREATE & MANAGE TASK",
                "Home (07)  →  Tasks (10)  →  Add Task (11)  →  Save Task  →  Tasks List (10)  →  Home (07)",
                ScreenId.ADD_TASK
            ),
            Triple(
                "FLOW 3 — STUDY SESSION & FOCUS",
                "Home (07)  →  Study Focus Mode (13)  →  Run Timer  →  End Session  →  Session Completed (14)  →  Progress (15)",
                ScreenId.STUDY_SESSION
            ),
            Triple(
                "FLOW 4 — VIEW PROGRESS & ANALYTICS",
                "Home (07)  →  Progress (15)  →  Weekly Activity Chart  →  Subject Breakdown  →  Study History",
                ScreenId.PROGRESS_ANALYTICS
            ),
            Triple(
                "FLOW 5 — SUBJECT MANAGEMENT",
                "Home (07)  →  Subjects (08)  →  Subject Details (09)  →  Coursework Tasks / Focus Session",
                ScreenId.SUBJECTS
            ),
            Triple(
                "FLOW 6 — ACCOUNT & SETTINGS",
                "Home (07)  →  Profile (16)  →  Settings (17)  →  Notification & Reminder Preferences",
                ScreenId.PROFILE
            )
        )

        flows.forEach { (title, path, startScreen) ->
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                        WireframeButton(
                            text = "Launch Flow",
                            onClick = { onLaunchScreen(startScreen) },
                            isPrimary = false
                        )
                    }
                    Text(
                        text = path,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = WireframeTextSecondary,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

// 04 — LOW-FIDELITY WIREFRAMES CATALOG
@Composable
private fun WireframesCatalogSection(onLaunchScreen: (ScreenId) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "04 — Low-Fidelity Wireframes (All 18 Screens)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WireframeBlack
        )

        Text(
            text = "Click any screen below to instantly inspect and interact with that wireframe in the mobile frame.",
            fontSize = 12.sp,
            color = WireframeTextSecondary
        )

        ScreenId.values().forEach { screen ->
            WireframeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLaunchScreen(screen) }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            WireframeBadge(text = "SCREEN ${screen.code}", isFilled = true)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = screen.title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = WireframeTextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = screen.description,
                            fontSize = 11.sp,
                            color = WireframeTextSecondary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Flow: ${screen.flowCategory}",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            color = WireframeTextMuted
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Open",
                        tint = WireframeBlack,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

// 05 — SCREEN-TO-SCREEN NAVIGATION
@Composable
private fun NavigationMatrixSection() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "05 — Screen-to-Screen Navigation Matrix",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WireframeBlack
        )

        val routes = listOf(
            Pair("01 Splash", "Tap Continue → 02 Onboarding Welcome"),
            Pair("02 Onboarding Welcome", "Next → 03 Onboarding Organize | Skip → 05 Login"),
            Pair("03 Onboarding Organize", "Next → 04 Onboarding Track | Skip → 05 Login"),
            Pair("04 Onboarding Track", "Get Started → 05 Login | Skip → 05 Login"),
            Pair("05 Login", "Sign In → 07 Home Dashboard | Sign Up link → 06 Sign Up"),
            Pair("06 Sign Up", "Create Account → 07 Home Dashboard | Login link → 05 Login"),
            Pair("07 Home Dashboard", "Bottom Bar: Tasks(10), Study(13), Progress(15), Profile(16) | Top Bell → 18 Notifications | Subjects card → 08 Subjects"),
            Pair("08 Subjects", "Subject Card → 09 Subject Details | + Add Subject → Dialog / Action"),
            Pair("09 Subject Details", "Start Study → 13 Focus Mode | + Task → 11 Add Task | Back → 08 Subjects"),
            Pair("10 Tasks", "+ Add Task → 11 Add Task | Edit Task row → 12 Edit Task | Filters → In-place"),
            Pair("11 Add Task", "Save / Cancel → Returns to 10 Tasks"),
            Pair("12 Edit Task", "Save Changes / Cancel / Delete → Returns to 10 Tasks"),
            Pair("13 Study Focus Mode", "End Session → 14 Session Completed | Cancel/Back → 07 Home"),
            Pair("14 Session Completed", "Continue → 15 Progress / Analytics"),
            Pair("15 Progress / Analytics", "Subject click → 09 Subject Details | Bottom Bar Navigation"),
            Pair("16 Profile", "Settings Gear → 17 Settings | Bottom Bar Navigation"),
            Pair("17 Settings", "Logout → 05 Login | Back → 16 Profile"),
            Pair("18 Notifications", "Back → Previous screen (07 Home)")
        )

        routes.forEach { (src, dest) ->
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(src, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(dest, fontSize = 11.sp, fontFamily = FontFamily.Monospace, color = WireframeTextSecondary)
                }
            }
        }
    }
}

// 06 — UX NOTES
@Composable
private fun UXNotesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "06 — UX Notes & Heuristics",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WireframeBlack
        )

        val notes = listOf(
            Pair(
                "1. Touch Targets & Fitts's Law",
                "All primary interactive buttons, input fields, checkboxes, and bottom navigation items maintain a minimum tap target bounding box of 48dp × 48dp, preventing mis-taps during active study sessions."
            ),
            Pair(
                "2. Information Hierarchy & Cognitive Load",
                "Students experiencing academic stress require quick scannability. On the Home Dashboard (Screen 07), today's immediate progress and quick study action take first visual prominence, followed by urgent tasks, with secondary subjects accessible horizontally."
            ),
            Pair(
                "3. Structural Wireframing Rules",
                "Components strictly avoid color reliance for priority or completion. High, Medium, and Low priorities are communicated via clear text labels, borders, and solid vs outlined bounding boxes rather than colored badges."
            ),
            Pair(
                "4. Habit Formation & Motivation Loops",
                "The study streak counter and Pomodoro-style structural timer encourage consistent daily study habits without inducing anxiety through gamified flashiness."
            ),
            Pair(
                "5. Transition Roadmap to High-Fidelity (Next Phase)",
                "Once stakeholder and CodeAlpha review approves this wireframe architecture, Phase 2 will introduce typographic scale tokens, accessible semantic palettes (e.g. calm slate and focused indigo), micro-animations, and fluid component transitions."
            )
        )

        notes.forEach { (title, desc) ->
            WireframeCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = WireframeBlack)
                    Text(desc, fontSize = 12.sp, color = WireframeTextSecondary, lineHeight = 18.sp)
                }
            }
        }
    }
}
