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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.WireframeBlack
import com.example.ui.theme.WireframeBorder
import com.example.ui.theme.WireframeBorderDark
import com.example.ui.theme.WireframeBorderLight
import com.example.ui.theme.WireframeBoxFill
import com.example.ui.theme.WireframeTextMuted
import com.example.ui.theme.WireframeTextPrimary
import com.example.ui.theme.WireframeTextSecondary
import com.example.ui.theme.WireframeWhite
import com.example.wireframe.components.WireframeButton
import com.example.wireframe.components.WireframeInputField
import com.example.wireframe.components.WireframePlaceholderBox

/**
 * SCREEN 01 — SPLASH SCREEN
 * Extremely simple: Logo placeholder, StudyFlow text, Loading indicator placeholder
 */
@Composable
fun SplashScreen(
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .padding(24.dp)
            .testTag("screen_01_splash"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // App Logo Placeholder
            WireframePlaceholderBox(
                label = "App Logo Placeholder",
                subLabel = "80 × 80 px (StudyFlow Emblem)",
                modifier = Modifier
                    .size(100.dp)
                    .testTag("splash_logo_placeholder")
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "StudyFlow",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = WireframeTextPrimary,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Student Study & Productivity",
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                color = WireframeTextSecondary
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Loading Indicator Placeholder
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(6.dp)
                        .background(WireframeBoxFill, RoundedCornerShape(3.dp))
                        .border(1.dp, WireframeBorder, RoundedCornerShape(3.dp))
                        .testTag("splash_loading_placeholder")
                ) {
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(6.dp)
                            .background(WireframeBlack, RoundedCornerShape(3.dp))
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "[ Loading Indicator Placeholder ]",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    color = WireframeTextMuted
                )
            }
        }

        // Action to advance
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "LOW-FIDELITY WIREFRAME — CODEALPHA TASK 1",
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                color = WireframeTextMuted
            )
            Spacer(modifier = Modifier.height(12.dp))
            WireframeButton(
                text = "Enter Experience →",
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
                testTag = "splash_start_button"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * REUSABLE ONBOARDING TEMPLATE (Screens 02, 03, 04)
 * Illustration placeholder, Heading placeholder, Short description, Progress indicator, Next button, Skip option
 */
@Composable
fun OnboardingScreenLayout(
    screenNumber: String,
    illustrationLabel: String,
    illustrationSubLabel: String,
    heading: String,
    description: String,
    currentStep: Int,
    totalSteps: Int = 3,
    onNext: () -> Unit,
    onSkip: () -> Unit,
    testTag: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .padding(24.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top row: Screen identifier & Skip action
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SCREEN $screenNumber",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = WireframeTextMuted
            )
            Text(
                text = "Skip",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = WireframeTextPrimary,
                modifier = Modifier
                    .testTag("onboarding_skip_button")
                    .clickable(onClick = onSkip)
                    .padding(8.dp)
            )
        }

        // Center Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Illustration Placeholder (Rectangle with diagonals & UX label)
            WireframePlaceholderBox(
                label = illustrationLabel,
                subLabel = illustrationSubLabel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .testTag("onboarding_illustration_placeholder")
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Heading Placeholder
            Text(
                text = heading,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = WireframeTextPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = description,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                color = WireframeTextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        // Bottom Actions & Progress Indicator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Step Dots / Progress Indicator
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                for (i in 1..totalSteps) {
                    val isActive = i == currentStep
                    Box(
                        modifier = Modifier
                            .size(width = if (isActive) 24.dp else 8.dp, height = 8.dp)
                            .background(
                                if (isActive) WireframeBlack else WireframeBoxFill,
                                RoundedCornerShape(4.dp)
                            )
                            .border(1.dp, if (isActive) WireframeBlack else WireframeBorder, RoundedCornerShape(4.dp))
                    )
                }
            }

            WireframeButton(
                text = if (currentStep == totalSteps) "Get Started" else "Next →",
                onClick = onNext,
                modifier = Modifier.fillMaxWidth(),
                testTag = "onboarding_next_button"
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

/**
 * SCREEN 02 — ONBOARDING: WELCOME
 */
@Composable
fun OnboardingWelcomeScreen(
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    OnboardingScreenLayout(
        screenNumber = "02",
        illustrationLabel = "Illustration Placeholder: Student Workspace",
        illustrationSubLabel = "Student sitting at organized study desk with laptop and books",
        heading = "Welcome to StudyFlow",
        description = "One centralized place for school and college students to master coursework, track milestones, and conquer daily study goals.",
        currentStep = 1,
        onNext = onNext,
        onSkip = onSkip,
        testTag = "screen_02_onboarding_welcome"
    )
}

/**
 * SCREEN 03 — ONBOARDING: ORGANIZE STUDIES
 */
@Composable
fun OnboardingOrganizeScreen(
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    OnboardingScreenLayout(
        screenNumber = "03",
        illustrationLabel = "Illustration Placeholder: Structured Subjects & Tasks",
        illustrationSubLabel = "Colorless wireframe cards representing courses, assignments, and priorities",
        heading = "Organize All Studies",
        description = "Structure your academic curriculum into subjects. Log tasks, set due dates and priorities, and never miss an assignment deadline.",
        currentStep = 2,
        onNext = onNext,
        onSkip = onSkip,
        testTag = "screen_03_onboarding_organize"
    )
}

/**
 * SCREEN 04 — ONBOARDING: TRACK PROGRESS
 */
@Composable
fun OnboardingTrackScreen(
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    OnboardingScreenLayout(
        screenNumber = "04",
        illustrationLabel = "Illustration Placeholder: Academic Metrics & Timer",
        illustrationSubLabel = "Analytics bars, focus countdown clock, and study streak badges",
        heading = "Track Progress & Habits",
        description = "Engage in timed focus sessions, build daily study streaks, and monitor subject completion with clean visual analytics.",
        currentStep = 3,
        onNext = onNext,
        onSkip = onSkip,
        testTag = "screen_04_onboarding_track"
    )
}

/**
 * SCREEN 05 — LOGIN
 * App logo placeholder, Email field, Password field, Login button, Forgot password, Sign-up link
 */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onForgotPassword: () -> Unit = {}
) {
    var email by remember { mutableStateOf("alex.chen@university.edu") }
    var password by remember { mutableStateOf("••••••••") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .testTag("screen_05_login"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "SCREEN 05",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = WireframeTextMuted
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App Logo Placeholder
            WireframePlaceholderBox(
                label = "App Logo Placeholder",
                subLabel = "StudyFlow Emblem",
                modifier = Modifier
                    .size(72.dp)
                    .testTag("login_logo_placeholder")
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome Back",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )

            Text(
                text = "Sign in to access your study schedules",
                fontSize = 13.sp,
                color = WireframeTextSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email field
            WireframeInputField(
                label = "Email Address",
                value = email,
                onValueChange = { email = it },
                placeholder = "student@university.edu",
                testTag = "login_email_input"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password field
            WireframeInputField(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                placeholder = "••••••••",
                testTag = "login_password_input"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot password?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = WireframeTextPrimary,
                    modifier = Modifier
                        .clickable(onClick = onForgotPassword)
                        .padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            WireframeButton(
                text = "Sign In",
                onClick = onLoginSuccess,
                modifier = Modifier.fillMaxWidth(),
                testTag = "login_submit_button"
            )
        }

        // Bottom Sign-up Link
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 13.sp,
                    color = WireframeTextSecondary
                )
                Text(
                    text = "Sign Up",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeBlack,
                    modifier = Modifier
                        .testTag("login_signup_link")
                        .clickable(onClick = onNavigateToSignUp)
                        .padding(4.dp)
                )
            }
        }
    }
}

/**
 * SCREEN 06 — SIGN UP
 * Name field, Email field, Password field, Confirm password field, Create account button, Login link
 */
@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("Alex Chen") }
    var email by remember { mutableStateOf("alex.chen@university.edu") }
    var password by remember { mutableStateOf("••••••••") }
    var confirmPassword by remember { mutableStateOf("••••••••") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WireframeWhite)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .testTag("screen_06_signup"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "SCREEN 06",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = WireframeTextMuted
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Create Student Account",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = WireframeTextPrimary
            )

            Text(
                text = "Set up your profile to start organizing coursework",
                fontSize = 13.sp,
                color = WireframeTextSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            WireframeInputField(
                label = "Full Name",
                value = name,
                onValueChange = { name = it },
                placeholder = "Alex Chen",
                testTag = "signup_name_input"
            )

            Spacer(modifier = Modifier.height(12.dp))

            WireframeInputField(
                label = "Student Email",
                value = email,
                onValueChange = { email = it },
                placeholder = "student@institution.edu",
                testTag = "signup_email_input"
            )

            Spacer(modifier = Modifier.height(12.dp))

            WireframeInputField(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                placeholder = "Minimum 8 characters",
                testTag = "signup_password_input"
            )

            Spacer(modifier = Modifier.height(12.dp))

            WireframeInputField(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Re-enter password",
                testTag = "signup_confirm_password_input"
            )

            Spacer(modifier = Modifier.height(24.dp))

            WireframeButton(
                text = "Create Account",
                onClick = onSignUpSuccess,
                modifier = Modifier.fillMaxWidth(),
                testTag = "signup_submit_button"
            )
        }

        // Bottom Login Link
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 13.sp,
                    color = WireframeTextSecondary
                )
                Text(
                    text = "Log In",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeBlack,
                    modifier = Modifier
                        .testTag("signup_login_link")
                        .clickable(onClick = onNavigateToLogin)
                        .padding(4.dp)
                )
            }
        }
    }
}
