// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

package com.example.wireframe.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.WireframeBlack
import com.example.ui.theme.WireframeBorder
import com.example.ui.theme.WireframeBorderDark
import com.example.ui.theme.WireframeBorderLight
import com.example.ui.theme.WireframeBoxFill
import com.example.ui.theme.WireframeDarkGray
import com.example.ui.theme.WireframeSurface
import com.example.ui.theme.WireframeSurfaceVariant
import com.example.ui.theme.WireframeTextMuted
import com.example.ui.theme.WireframeTextPrimary
import com.example.ui.theme.WireframeTextSecondary
import com.example.ui.theme.WireframeWhite

/**
 * Standard UX Wireframe Placeholder Box with crossed diagonal guide lines
 * Used for illustrations, chart placeholders, logo placeholders, etc.
 */
@Composable
fun WireframePlaceholderBox(
    modifier: Modifier = Modifier,
    label: String,
    subLabel: String? = null,
    showDiagonals: Boolean = true,
    aspectRatioFill: Boolean = false
) {
    Box(
        modifier = modifier
            .background(WireframeBoxFill, RoundedCornerShape(4.dp))
            .border(1.dp, WireframeBorder, RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (showDiagonals) {
            Canvas(modifier = Modifier.matchParentSize()) {
                drawLine(
                    color = Color(0xFFDCDCDC),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1f
                )
                drawLine(
                    color = Color(0xFFDCDCDC),
                    start = Offset(size.width, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 1f
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .background(WireframeWhite.copy(alpha = 0.9f), RoundedCornerShape(2.dp))
                .border(1.dp, WireframeBorderLight, RoundedCornerShape(2.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text(
                text = "[ $label ]",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace,
                color = WireframeTextPrimary,
                textAlign = TextAlign.Center
            )
            if (subLabel != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subLabel,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = WireframeTextSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Wireframe Button (Primary solid or Secondary outlined)
 */
@Composable
fun WireframeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = true,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    testTag: String = "wireframe_button"
) {
    val bg = if (isPrimary) WireframeBlack else WireframeWhite
    val textCol = if (isPrimary) WireframeWhite else WireframeBlack
    val borderColor = if (isPrimary) WireframeBlack else WireframeBorderDark

    Box(
        modifier = modifier
            .testTag(testTag)
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(if (enabled) bg else WireframeBoxFill)
            .border(1.5.dp, if (enabled) borderColor else WireframeBorderLight, RoundedCornerShape(4.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = if (enabled) textCol else WireframeTextMuted,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}

/**
 * Wireframe Text Input field
 */
@Composable
fun WireframeInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    helperText: String? = null,
    testTag: String = "wireframe_input"
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = WireframeTextPrimary,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
                .background(WireframeWhite, RoundedCornerShape(4.dp))
                .border(1.dp, WireframeBorderDark, RoundedCornerShape(4.dp))
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .testTag(testTag),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = WireframeTextMuted,
                    fontSize = 14.sp
                )
            } else {
                Text(
                    text = value,
                    color = WireframeTextPrimary,
                    fontSize = 14.sp
                )
            }
        }
        if (helperText != null) {
            Text(
                text = helperText,
                fontSize = 11.sp,
                color = WireframeTextMuted,
                modifier = Modifier.padding(top = 2.dp, start = 2.dp)
            )
        }
    }
}

/**
 * Wireframe Card Container
 */
@Composable
fun WireframeCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    borderWidth: Dp = 1.dp,
    borderColor: Color = WireframeBorder,
    backgroundColor: Color = WireframeWhite,
    content: @Composable () -> Unit
) {
    val clickableModifier = if (onClick != null) {
        modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
    } else {
        modifier
    }

    Box(
        modifier = clickableModifier
            .background(backgroundColor, RoundedCornerShape(6.dp))
            .border(borderWidth, borderColor, RoundedCornerShape(6.dp))
            .padding(14.dp)
    ) {
        content()
    }
}

/**
 * Wireframe Progress Bar
 */
@Composable
fun WireframeProgressBar(
    progressPercent: Int,
    modifier: Modifier = Modifier,
    height: Dp = 8.dp,
    showLabel: Boolean = false
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (showLabel) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Progress", fontSize = 11.sp, color = WireframeTextSecondary)
                Text("$progressPercent%", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = WireframeTextPrimary)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(WireframeBoxFill, RoundedCornerShape(2.dp))
                .border(0.75.dp, WireframeBorder, RoundedCornerShape(2.dp))
        ) {
            val fraction = (progressPercent.coerceIn(0, 100)) / 100f
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(height)
                    .background(WireframeBlack, RoundedCornerShape(2.dp))
            )
        }
    }
}

/**
 * Wireframe Checkbox
 */
@Composable
fun WireframeCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "wireframe_checkbox"
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .testTag(testTag)
            .background(if (checked) WireframeBlack else WireframeWhite, RoundedCornerShape(3.dp))
            .border(1.5.dp, WireframeBlack, RoundedCornerShape(3.dp))
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = WireframeWhite,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * Wireframe Priority Badge (Grayscale only)
 */
@Composable
fun WireframeBadge(
    text: String,
    isFilled: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                if (isFilled) WireframeBlack else WireframeSurfaceVariant,
                RoundedCornerShape(3.dp)
            )
            .border(1.dp, WireframeBorderDark, RoundedCornerShape(3.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = if (isFilled) WireframeWhite else WireframeTextPrimary
        )
    }
}

/**
 * Wireframe Switch (grayscale structural control)
 */
@Composable
fun WireframeSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "wireframe_switch"
) {
    Box(
        modifier = modifier
            .size(width = 46.dp, height = 26.dp)
            .testTag(testTag)
            .clip(RoundedCornerShape(13.dp))
            .background(if (checked) WireframeBlack else WireframeBoxFill)
            .border(1.5.dp, WireframeBorderDark, RoundedCornerShape(13.dp))
            .clickable { onCheckedChange(!checked) }
            .padding(2.dp),
        contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(if (checked) WireframeWhite else WireframeBorderDark, CircleShape)
                .border(0.5.dp, WireframeBorder, CircleShape)
        )
    }
}

/**
 * Wireframe Top App Bar
 */
@Composable
fun WireframeTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null,
    screenNumber: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(WireframeWhite)
            .border(width = 0.dp, color = Color.Transparent)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (onBack != null) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("nav_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate Back",
                        tint = WireframeBlack
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
            Column {
                if (screenNumber != null) {
                    Text(
                        text = "SCREEN $screenNumber",
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = WireframeTextMuted
                    )
                }
                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = WireframeTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (actions != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                actions()
            }
        }
    }
}

/**
 * Mandatory Bottom Navigation for StudyFlow
 * Items: Home, Tasks, Study, Progress, Profile
 */
@Composable
fun WireframeBottomNavigation(
    selectedTab: String,
    onSelectTab: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(WireframeWhite)
            .border(width = 1.dp, color = WireframeBorderLight)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val navItems = listOf(
            Triple("Home", Icons.Default.Home, "nav_tab_home"),
            Triple("Tasks", Icons.Default.CheckCircle, "nav_tab_tasks"),
            Triple("Study", Icons.Default.Timer, "nav_tab_study"),
            Triple("Progress", Icons.Default.BarChart, "nav_tab_progress"),
            Triple("Profile", Icons.Default.Person, "nav_tab_profile")
        )

        navItems.forEach { (label, icon, tag) ->
            val isSelected = selectedTab == label
            Column(
                modifier = Modifier
                    .testTag(tag)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onSelectTab(label) }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = if (isSelected) WireframeBlack else WireframeTextMuted,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = label,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) WireframeBlack else WireframeTextMuted
                )
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .width(16.dp)
                            .height(2.dp)
                            .background(WireframeBlack)
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
