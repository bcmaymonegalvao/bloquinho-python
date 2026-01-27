package io.github.bcmaymonegalvao.bloquinhopy.runtime

import com.chaquo.python.Python
import javax.inject.Inject
import javax.inject.Singleton

@JvmInline
value class NotebookCellId(val value: Int)

sealed class NotebookOutput {
    data class Success(val result: String) : NotebookOutput()
    data class Error(val message: String) : NotebookOutput()
}

interface NotebookEngine {
    fun execute(code: String): NotebookOutput
}

@Singleton
class PythonNotebookEngine @Inject constructor() : NotebookEngine {
    private val py by lazy { Python.getInstance() }
    private val sys by lazy { py.getModule("sys") }
    private val io by lazy { py.getModule("io") }

    override fun execute(code: String): NotebookOutput {
        return try {
            val console = io.get("StringIO")?.call()
            sys.set("stdout", console)
            sys.set("stderr", console)
            
            py.getBuiltins().get("exec")?.call(code)
            
            val result = console?.callAttr("getvalue")?.toString() ?: ""
            NotebookOutput.Success(result.ifBlank { "Executed successfully (no output)" })
        } catch (e: Exception) {
            NotebookOutput.Error(e.message ?: "Unknown Python error")
        }
    }
}
