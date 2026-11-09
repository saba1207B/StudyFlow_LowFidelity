// Copyright (c) 2026 Sabareesh M (saba1207B). All Rights Reserved.
// All rights reserved. No public license is granted.

package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.wireframe.model.ScreenId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("StudyFlow", appName)
  }

  @Test
  fun `verify all 18 wireframe screens are defined for CodeAlpha Task 1`() {
    val allScreens = ScreenId.values()
    assertEquals(18, allScreens.size)

    val expectedCodes = (1..18).map { String.format("%02d", it) }
    val actualCodes = allScreens.map { it.code }
    assertEquals(expectedCodes, actualCodes)

    // Ensure key screens exist
    assertTrue(allScreens.any { it.code == "01" && it.title == "Splash Screen" })
    assertTrue(allScreens.any { it.code == "07" && it.title == "Home Dashboard" })
    assertTrue(allScreens.any { it.code == "10" && it.title == "Tasks" })
    assertTrue(allScreens.any { it.code == "13" && it.title == "Study Session / Focus Mode" })
    assertTrue(allScreens.any { it.code == "15" && it.title == "Progress & Analytics" })
    assertTrue(allScreens.any { it.code == "18" && it.title == "Notifications" })
  }
}

