// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val WireframeColorScheme = lightColorScheme(
  primary = WireframeBlack,
  onPrimary = WireframeWhite,
  primaryContainer = WireframeSurfaceVariant,
  onPrimaryContainer = WireframeBlack,
  secondary = WireframeDarkGray,
  onSecondary = WireframeWhite,
  secondaryContainer = WireframeBoxFill,
  onSecondaryContainer = WireframeTextPrimary,
  tertiary = WireframeBorderDark,
  onTertiary = WireframeWhite,
  background = WireframeWhite,
  onBackground = WireframeTextPrimary,
  surface = WireframeWhite,
  onSurface = WireframeTextPrimary,
  surfaceVariant = WireframeSurfaceVariant,
  onSurfaceVariant = WireframeTextSecondary,
  outline = WireframeBorder,
  outlineVariant = WireframeBorderLight,
)

@Composable
fun StudyFlowTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = WireframeColorScheme,
    typography = Typography,
    content = content
  )
}

