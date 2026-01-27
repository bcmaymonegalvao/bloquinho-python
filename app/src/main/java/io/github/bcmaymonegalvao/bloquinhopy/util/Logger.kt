package io.github.bcmaymonegalvao.bloquinhopy.util

import android.util.Log

/**
 * Unified logging system for the application.
 * Provides different log levels and easy debugging.
 */
object Logger {
    private const val DEFAULT_TAG = "BloquinhoPy"

    fun d(tag: String = DEFAULT_TAG, message: String, throwable: Throwable? = null) {
        Log.d(tag, message, throwable)
    }

    fun i(tag: String = DEFAULT_TAG, message: String, throwable: Throwable? = null) {
        Log.i(tag, message, throwable)
    }

    fun w(tag: String = DEFAULT_TAG, message: String, throwable: Throwable? = null) {
        Log.w(tag, message, throwable)
    }

    fun e(tag: String = DEFAULT_TAG, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }
}

/**
 * Custom exception types for better error handling.
 */
sealed class BloquinhoException : Exception() {
    abstract override val message: String
}

data class DatabaseException(override val message: String, val cause: Throwable? = null) :
    BloquinhoException()

data class ExecutionException(override val message: String, val cause: Throwable? = null) :
    BloquinhoException()

data class ValidationException(override val message: String) : BloquinhoException()

data class NotebookNotFoundException(val notebookId: String) :
    BloquinhoException() {
    override val message: String = "Notebook with id $notebookId not found"
}

data class ProjectNotFoundException(val projectId: String) :
    BloquinhoException() {
    override val message: String = "Project with id $projectId not found"
}
