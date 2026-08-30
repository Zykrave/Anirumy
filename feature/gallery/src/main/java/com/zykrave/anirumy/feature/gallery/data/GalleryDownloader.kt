package com.zykrave.anirumy.feature.gallery.data

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class GalleryDownloader(
    private val client: OkHttpClient,
    private val context: Context,
) {
    suspend fun downloadImage(
        url: String,
        fileName: String,
        onProgress: (Float) -> Unit
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return@withContext Result.failure(Exception("Failed to download image: ${response.code}"))
            }

            val body = response.body ?: return@withContext Result.failure(Exception("Empty response body"))
            val contentLength = body.contentLength()
            
            val bytes = if (contentLength > 0) {
                val outputStream = ByteArrayOutputStream()
                val inputStream = body.byteStream()
                val buffer = ByteArray(8192)
                var bytesRead: Int
                var totalBytesRead = 0L
                
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                    totalBytesRead += bytesRead
                    onProgress(totalBytesRead.toFloat() / contentLength)
                }
                outputStream.toByteArray()
            } else {
                body.bytes()
            }

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Anirumy")
                    put(MediaStore.MediaColumns.IS_PENDING, 1)
                }
            }

            val contentResolver = context.contentResolver
            val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            val uri = contentResolver.insert(collection, contentValues)
                ?: return@withContext Result.failure(Exception("Failed to create MediaStore entry"))

            try {
                contentResolver.openOutputStream(uri)?.use { outputStream: OutputStream ->
                    outputStream.write(bytes)
                } ?: return@withContext Result.failure(Exception("Failed to open output stream"))

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    contentValues.clear()
                    contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    contentResolver.update(uri, contentValues, null, null)
                }
                
                onProgress(1f)
                Result.success(Unit)
            } catch (e: Exception) {
                contentResolver.delete(uri, null, null)
                throw e
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
