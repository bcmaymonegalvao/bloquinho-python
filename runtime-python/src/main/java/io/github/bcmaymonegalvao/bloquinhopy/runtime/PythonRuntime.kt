
package io.github.bcmaymonegalvao.bloquinhopy.runtime

data class ExecResult(
    val stdout: String,
    val stderr: String,
    val exitCode: Int,
)

interface PythonRuntime {
    suspend fun exec(code: String, workingDir: String): ExecResult
    fun interrupt()
}
