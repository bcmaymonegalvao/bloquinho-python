package io.github.bcmaymonegalvao.bloquinhopy.runtime

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NotebookEngineTest {

    private lateinit var engine: PythonNotebookEngine

    @Before
    fun setup() {
        engine = PythonNotebookEngine()
    }

    @Test
    fun `test runCell with print code returns text output`() = runTest {
        val id = NotebookCellId(0)
        val code = "print('Hello World')"
        
        val outputs = engine.runCell(id, code)
        
        assertEquals(1, outputs.size)
        assertTrue(outputs[0] is NotebookOutput.Text)
        assertEquals("Hello World", (outputs[0] as NotebookOutput.Text).value)
    }

    @Test
    fun `test runCell with error returns error output`() = runTest {
        val id = NotebookCellId(1)
        val code = "raise Exception('error')"
        
        val outputs = engine.runCell(id, code)
        
        assertEquals(1, outputs.size)
        assertTrue(outputs[0] is NotebookOutput.Error)
        assertEquals("Erro simulado na execução Python", (outputs[0] as NotebookOutput.Error).message)
    }

    @Test
    fun `test reset clears state and emits reset message`() = runTest {
        engine.reset()
        val lastOutput = engine.outputFlow().first()
        assertEquals("Engine resetado.", lastOutput)
    }
}
