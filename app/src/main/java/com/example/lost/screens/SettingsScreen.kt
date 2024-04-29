package com.example.lost.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.lost.components.settings.SettingsBar
import com.example.lost.navigation.BottomRoutes
import com.example.lost.navigation.Routes
import com.example.lost.navigation.SettingsRoutes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navHostController: NavController
) {
    Scaffold(
        topBar = {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "ZARZĄDZANIE",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
        ) {
            ElevatedCard {
                SettingsBar(
                    name = "Konto",
                    icon = Icons.Default.Person
                ) {
                    navHostController.navigate("${SettingsRoutes.Account.route}/NAZWA KONTA")
                }

                Divider()

                SettingsBar(
                    name = "Preferencje",
                    icon = Icons.Default.Build
                ) {
                    /* TODO */
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard {
                SettingsBar(
                    name = "Prywatność",
                    icon = Icons.Default.Lock
                ) {
                    /* TODO */
                }

                Divider()

                SettingsBar(
                    name = "Powiadomienia",
                    icon = Icons.Default.Audiotrack
                ) {
                    /* TODO */
                }

                Divider()

                SettingsBar(
                    name = "Ustawienia",
                    icon = Icons.Default.Settings
                ) {
                    /* TODO */
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard {
                SettingsBar(
                    name = "Pomoc",
                    icon = Icons.Default.Help
                ) {
                    /* TODO */
                }

                SettingsBar(
                    modifier = Modifier
                        .background(
                            Color(0xFFFF5252)
                        ),
                    name = "Wyloguj",
                    icon = Icons.Default.Logout
                ) {
                    /* TODO */
                }
            }
        }
    }
}