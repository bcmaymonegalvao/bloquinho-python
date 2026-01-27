package io.github.bcmaymonegalvao.bloquinhopy.engine

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Python execution engine for running Python code in notebooks.
 * Uses Chaquopy (Python for Android) for Python execution.
 */
class PythonEngine {
    companion object {
        private const val TAG = "PythonEngine"
    }

    /**
     * Executes Python code and returns the output.
     * @param code Python code to execute
     * @return Execution result containing output and any errors
     */
    suspend fun execute(code: String): ExecutionResult = withContext(Dispatchers.Default) {
        return@withContext try {
            if (code.isBlank()) {
                ExecutionResult(
                    output = "",
                    error = null,
                    executionTime = 0L,
                    success = true
                )
            } else {
                // TODO: Integrate with Chaquopy for actual Python execution
                // For now, return a mock response
                val mockOutput = evaluateMockCode(code)
                ExecutionResult(
                    output = mockOutput,
                    error = null,
                    executionTime = System.currentTimeMillis(),
                    success = true
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error executing Python code", e)
            ExecutionResult(
                output = "",
                error = e.message ?: "Unknown error",
                executionTime = System.currentTimeMillis(),
                success = false
            )
        }
    }

    /**
     * Mock evaluation for basic Python expressions.
     * TODO: Replace with actual Chaquopy integration
     */
    private fun evaluateMockCode(code: String): String {
        return when {
            code.contains("print") -> code.substringAfter("print(").substringBefore(")")
                .replace("\"", "").replace("'", "")
            code.contains("=") && !code.contains("==") -> "Variable assigned"
            code.contains("+") || code.contains("-") || code.contains("*") -> "Calculation performed"
            else -> "Code executed"
        }
    }
}

/**
 * Represents the result of Python code execution.
 */
data class ExecutionResult(
    val output: String,
    val error: String?,
    val executionTime: Long,
    val success: Boolean
) {
    val displayOutput: String
        get() = if (success) output else "Error: $error"
}
