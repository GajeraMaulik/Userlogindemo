package com.example.userlogindemo.Profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.StatFs
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import com.example.userlogindemo.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object FileUtil {
    /**
     * Get Image File
     *
     * Default it will take Camera folder as it's directory
     *
     * @param dir File Folder in which file needs tobe created.
     * @param extension String Image file extension.
     * @return Return Empty file to store camera image.
     * @throws IOException if permission denied of failed to create new file.
     */
    fun getImageFile(context: Context, dir: File? = null, extension: String? = null): File? {
        try {
            // Create an image file name
            val ext = extension ?: ".jpg"
            val imageFileName = "IMG_${getTimestamp()}$ext"

            // Create File Directory Object
            val storageDir = dir ?: getCameraDirectory(context)

            // Create Directory If not exist
            if (!storageDir.exists()) storageDir.mkdirs()

            // Create File Object
            val file = File(storageDir, imageFileName)

            // Create empty file
            file.createNewFile()

            return file
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }
    fun getFileInfo(context: Context, uri: Uri?): String {
        if (uri == null) {
            return "Image not found"
        }

        // Get Resolution
        val resolution = getImageResolution(context, uri)

        // File Path
        val filePath = FileUriUtils.getRealPath(context, uri)
        val document = getDocumentFile(context, uri) ?: return "Image not found"

        // Get Last Modified
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault())
        val modified = sdf.format(document.lastModified())

        // File Size
        val fileSize = getFileSize(document.length())

        return StringBuilder()

            .append("Resolution: ")
            .append("${resolution.first}x${resolution.second}")
            .append("\n\n")

            .append("Modified: ")
            .append(modified)
            .append("\n\n")

            .append("File Size: ")
            .append(fileSize)
            .append("\n\n")

            /*.append("File Name: ")
            .append(getFileName(context.contentResolver, uri))
            .append("\n\n")*/

            .append("File Path: ")
            .append(filePath)
            .append("\n\n")

            .append("Uri Path: ")
            .append(uri.toString())
            .toString()
    }
    private fun getFileSize(file: File): String {
        val fileSize = file.length().toFloat()
        val mb = fileSize / (1024 * 1024)
        val kb = fileSize / (1024)

        return if (mb > 1) {
            "$mb MB"
        } else {
            "$kb KB"
        }
    }

    private fun getFileSize(fileSize: Long): String {
        val mb = fileSize / (1024 * 1024)
        val kb = fileSize / (1024)

        return if (mb > 1) {
            "$mb MB"
        } else {
            "$kb KB"
        }
    }

    fun getImageUri(context: Context, dir: File? = null, extension: String? = null): Uri? {
        try {
            // Create an image file name
            val ext = extension ?: ".jpg"
            val imageFileName = "IMG_${getTimestamp()}$ext"

            // Create File Directory Object
            val storageDir = dir ?: getCameraDirectory(context)


            // Create Directory If not exist
            if (!storageDir.exists()) storageDir.mkdirs()

            // Create File Object
            val file = File(storageDir, imageFileName)

            // Create empty file
            file.createNewFile()

            val authority =
                context.packageName + context.getString(R.string.image_picker_provider_authority_suffix)
            val uriForFile = FileProvider.getUriForFile(
                context,
                authority,
                file
            )
            return uriForFile
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    /**
     * Get Camera Image Directory
     *
     * @return File Camera Image Directory
     */
    private fun getCameraDirectory(context: Context): File {
        val dir =
            context.getExternalFilesDir(Environment.DIRECTORY_DCIM) // Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        return File(dir, "Camera")
    }

    /**
     * Get Current Time in yyyyMMdd HHmmssSSS format
     *
     * 2019/01/30 10:30:20 000
     * E.g. 20190130_103020000
     */
    private fun getTimestamp(): String {
        val timeFormat = "yyyyMMdd_HHmmssSSS"
        return SimpleDateFormat(timeFormat, Locale.getDefault()).format(Date())
    }

    /**
     * Get Free Space size
     * @param file directory object to check free space.
     */
    fun getFreeSpace(file: Uri?): Long {
        val stat = StatFs(file?.path)
        val availBlocks = stat.availableBlocksLong
        val blockSize = stat.blockSizeLong
        return availBlocks * blockSize
    }

    /**
     * Get Image Width & Height from Uri
     *
     * @param uri Uri to get Image Size
     * @return Int Array, Index 0 has width and Index 1 has height
     */
    fun getImageResolution(context: Context, uri: Uri): Pair<Int, Int> {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        val stream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(stream, null, options)
        return Pair(options.outWidth, options.outHeight)
    }

    /**
     * Get Image Width & Height from File
     *
     * @param file File to get Image Size
     * @return Int Array, Index 0 has width and Index 1 has height
     */
    fun getImageResolution(file: File): Pair<Int, Int> {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file.absolutePath, options)
        return Pair(options.outWidth, options.outHeight)
    }

    /**
     * Get Image File Size
     *
     * @param uri Uri to get Image Size
     * @return Int Image File Size
     */
    fun getImageSize(context: Context, uri: Uri): Long {
        return getDocumentFile(context, uri)?.length() ?: 0
    }

    /**
     * Create copy of Uri into application specific local path
     *
     * @param context Application Context
     * @param uri Source Uri
     * @return File return copy of Uri object
     */
    fun getTempFile(context: Context, uri: Uri): File? {
        try {
            val destination = File(context.cacheDir, "image_picker.png")

            val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
            val fileDescriptor = parcelFileDescriptor?.fileDescriptor ?: return null

            val src = FileInputStream(fileDescriptor).channel
            val dst = FileOutputStream(destination).channel
            dst.transferFrom(src, 0, src.size())
            src.close()
            dst.close()

            return destination
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * Get DocumentFile from Uri
     *
     * @param context Application Context
     * @param uri Source Uri
     * @return DocumentFile return DocumentFile from Uri
     */
    fun getDocumentFile(context: Context, uri: Uri): DocumentFile? {
        var file: DocumentFile? = null
        if (isFileUri(uri)) {
            val path = FileUriUtils.getRealPath(context, uri)
            if (path != null) {
                file = DocumentFile.fromFile(File(path))
            }
        } else {
            file = DocumentFile.fromSingleUri(context, uri)
        }
        return file
    }

    /**
     * Get Bitmap Compress Format
     *
     * @param extension Image File Extension
     * @return Bitmap CompressFormat
     */
    fun getCompressFormat(extension: String): Bitmap.CompressFormat {
        return when {
            extension.contains("png", ignoreCase = true) -> Bitmap.CompressFormat.PNG
            extension.contains("webp", ignoreCase = true) -> Bitmap.CompressFormat.WEBP
            else -> Bitmap.CompressFormat.JPEG
        }
    }

    /**
     * Check if provided URI is backed by File
     *
     * @return Boolean, True if Uri is local file object else return false
     */
    private fun isFileUri(uri: Uri): Boolean {
        return "file".equals(uri.scheme, ignoreCase = true)
    }
}
