@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.jigar.materialadaptivedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.ListDetailPaneScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jigar.materialadaptivedemo.ui.theme.MaterialAdaptiveDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialAdaptiveDemoTheme {
                LanguageListDetailScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialAdaptiveDemoTheme {
        Greeting("Android")
    }
}

@Composable
fun LanguageListDetailScreen() {
    val languages = listOf(
        ProgrammingLanguage("Kotlin", "A modern statically typed language used for Android Development."),
        ProgrammingLanguage("Java", "A popular language, especially known for its use in Android development."),
        // Add more languages as needed
    )

    var selectedLanguage by remember { mutableStateOf<ProgrammingLanguage?>(null) }

    ListDetailPaneScaffold(
        listPane = { ListPane(languages, onLanguageSelected = { selectedLanguage = it }) },
        detailPane = { DetailPane(language = selectedLanguage) }
    )
}

@Composable
fun ListPane(languages: List<ProgrammingLanguage>, onLanguageSelected: (ProgrammingLanguage) -> Unit) {
    LazyColumn {
        items(languages) { language ->
            LanguageItem(language, onLanguageSelected)
        }
    }
}
@Composable
fun LanguageItem(language: ProgrammingLanguage, onLanguageSelected: (ProgrammingLanguage) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLanguageSelected(language) }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = language.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                // You can add more details here if needed
            }
        }
    }
}

@Composable
fun DetailPane(language: ProgrammingLanguage?) {
    language?.let {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = it.name, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))
            Text(text = it.description)
        }
    }
}
