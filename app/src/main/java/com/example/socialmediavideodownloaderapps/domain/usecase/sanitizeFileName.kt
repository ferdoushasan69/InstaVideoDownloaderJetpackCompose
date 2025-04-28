package com.example.socialmediavideodownloaderapps.domain.usecase

fun sanitizeFileName(fileName: String): String {
    return fileName
        .replace(Regex("[^A-Za-z0-9 _.-]"), "") // Keep only letters, numbers, spaces, underscores, dots, and dashes
        .trim()
}
