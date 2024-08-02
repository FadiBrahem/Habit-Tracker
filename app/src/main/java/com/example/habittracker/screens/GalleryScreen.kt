package com.example.habittracker.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import android.Manifest
import android.net.Uri
import com.example.habittracker.viewmodel.GalleryViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalPermissionsApi::class)
@Composable
fun GalleryScreen(viewModel: GalleryViewModel, onBackClick: () -> Unit) {
    val images by viewModel.images.collectAsState()
    var showDownloadDialog by remember { mutableStateOf(false) }
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        viewModel.addImages(uris)
    }

    val writePermissionState = rememberPermissionState(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gallery") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { launcher.launch("image/*") }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Images")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(images) { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(4.dp)
                        .combinedClickable(
                            onClick = { },
                            onLongClick = {
                                selectedImage = uri
                                showDownloadDialog = true
                            }
                        ),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    if (showDownloadDialog) {
        AlertDialog(
            onDismissRequest = { showDownloadDialog = false },
            title = { Text("Download Image") },
            text = { Text("Do you want to download this image?") },
            confirmButton = {
                Button(
                    onClick = {
                        if (writePermissionState.status.isGranted) {
                            selectedImage?.let { viewModel.downloadImage(it) }
                        } else {
                            writePermissionState.launchPermissionRequest()
                        }
                        showDownloadDialog = false
                    }
                ) {
                    Text("Download")
                }
            },
            dismissButton = {
                Button(onClick = { showDownloadDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
