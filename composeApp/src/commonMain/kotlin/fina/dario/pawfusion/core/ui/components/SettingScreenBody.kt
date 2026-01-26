package fina.dario.pawfusion.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import fina.dario.pawfusion.core.ThemeViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun SettingsScreenBody(modifier: Modifier){
    val prefs: DataStore<Preferences> = koinInject()
    val themeViewModel: ThemeViewModel = koinInject()
    val isDarkTheme by themeViewModel.useDynamicColors.collectAsState()
    val scope = rememberCoroutineScope()


    val theme by prefs
        .data.map { dataStore ->
            val themeKey = booleanPreferencesKey("theme")
            dataStore[themeKey]  ?: false
        }.collectAsState( false )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(0.8f),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            shape = RoundedCornerShape(12.dp),
            border = CardDefaults.outlinedCardBorder(),
            elevation = CardDefaults.cardElevation(1.dp)
        ){
            Box(
                modifier = Modifier.fillMaxSize().background(color = Color.Transparent),
                contentAlignment = Alignment.Center
            ){
                Row(
                    modifier = Modifier.fillMaxSize().background(color = Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ){
                    IconButton(
                        modifier = Modifier.padding(5.dp).height(55.dp).width(100.dp),
                        onClick = {
                            scope.launch {
                                prefs.edit { datastore ->
                                    val themeKey = booleanPreferencesKey("theme")
                                    datastore[themeKey]?.let { datastore[themeKey] = !it }
                                }
                            }
                            themeViewModel.setTheme(theme)
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent
                        )
                    )
                    {
                        Icon(
                            imageVector = Icons.Filled.WbSunny,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = modifier.width(15.dp))
                    IconButton(
                        modifier = Modifier.padding(5.dp).height(55.dp).width(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            scope.launch {
                                prefs.edit { datastore ->
                                    val themeKey = booleanPreferencesKey("theme")
                                    datastore[themeKey] = !theme
                                }
                            }.invokeOnCompletion {
                                themeViewModel.setTheme(theme)
                            }

                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent

                        ))
                    {
                        Icon(
                            imageVector = Icons.Default.DarkMode,
                            contentDescription = null
                        )
                    }
                }
            }

        }
    }
}

