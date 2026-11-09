// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ui.theme.StudyFlowTheme
import com.example.ui.theme.WireframeWhite
import com.example.wireframe.ui.StudyFlowApp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      StudyFlowTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = WireframeWhite
        ) {
          StudyFlowApp()
        }
      }
    }
  }
}

