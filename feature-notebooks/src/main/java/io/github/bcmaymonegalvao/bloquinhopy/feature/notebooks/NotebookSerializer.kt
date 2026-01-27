package io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks

import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookCellId
import io.github.bcmaymonegalvao.bloquinhopy.runtime.NotebookOutput
import org.json.JSONArray
import org.json.JSONObject

/**
 * Serializer for Jupyter .ipynb notebook format.
 */
object NotebookSerializer {
    
    /**
     * Serialize notebook cells to .ipynb JSON format.
     */
    fun toIpynb(cells: List<CellState>): String {
        val notebook = JSONObject()
        
        // Metadata
        val metadata = JSONObject()
        metadata.put("kernelspec", JSONObject().apply {
            put("display_name", "Python 3")
            put("language", "python")
            put("name", "python3")
        })
        metadata.put("language_info", JSONObject().apply {
            put("name", "python")
            put("version", "3.11")
        })
        
        // Cells
        val cellsArray = JSONArray()
        cells.forEach { cell ->
            val cellJson = JSONObject()
            cellJson.put("cell_type", "code")
            cellJson.put("execution_count", null)
            cellJson.put("metadata", JSONObject())
            
            // Source code (split by lines)
            val sourceArray = JSONArray()
            cell.code.lines().forEach { line ->
                sourceArray.put("$line\n")
            }
            cellJson.put("source", sourceArray)
            
            // Outputs
            val outputsArray = JSONArray()
            cell.outputs.forEach { output ->
                when (output) {
                    is NotebookOutput.Text -> {
                        val outputJson = JSONObject()
                        outputJson.put("output_type", "stream")
                        outputJson.put("name", "stdout")
                        outputJson.put("text", JSONArray().apply {
                            put(output.content)
                        })
                        outputsArray.put(outputJson)
                    }
                    is NotebookOutput.Error -> {
                        val outputJson = JSONObject()
                        outputJson.put("output_type", "error")
                        outputJson.put("ename", "Exception")
                        outputJson.put("evalue", output.message)
                        outputJson.put("traceback", JSONArray().apply {
                            put(output.message)
                            if (output.traceback != null) {
                                put(output.traceback)
                            }
                        })
                        outputsArray.put(outputJson)
                    }
                }
            }
            cellJson.put("outputs", outputsArray)
            
            cellsArray.put(cellJson)
        }
        
        // Build final notebook
        notebook.put("cells", cellsArray)
        notebook.put("metadata", metadata)
        notebook.put("nbformat", 4)
        notebook.put("nbformat_minor", 5)
        
        return notebook.toString(2) // Pretty print with indent
    }
    
    /**
     * Deserialize .ipynb JSON format to notebook cells.
     */
    fun fromIpynb(jsonString: String): List<CellState> {
        val notebook = JSONObject(jsonString)
        val cellsArray = notebook.getJSONArray("cells")
        val cells = mutableListOf<CellState>()
        
        for (i in 0 until cellsArray.length()) {
            val cellJson = cellsArray.getJSONObject(i)
            val cellType = cellJson.getString("cell_type")
            
            if (cellType == "code") {
                // Parse source code
                val sourceArray = cellJson.getJSONArray("source")
                val code = StringBuilder()
                for (j in 0 until sourceArray.length()) {
                    code.append(sourceArray.getString(j))
                }
                
                // Parse outputs
                val outputs = mutableListOf<NotebookOutput>()
                if (cellJson.has("outputs")) {
                    val outputsArray = cellJson.getJSONArray("outputs")
                    for (k in 0 until outputsArray.length()) {
                        val outputJson = outputsArray.getJSONObject(k)
                        val outputType = outputJson.getString("output_type")
                        
                        when (outputType) {
                            "stream" -> {
                                val textArray = outputJson.getJSONArray("text")
                                val text = StringBuilder()
                                for (l in 0 until textArray.length()) {
                                    text.append(textArray.getString(l))
                                }
                                outputs.add(NotebookOutput.Text(text.toString()))
                            }
                            "error" -> {
                                val evalue = outputJson.getString("evalue")
                                val traceback = if (outputJson.has("traceback")) {
                                    val tbArray = outputJson.getJSONArray("traceback")
                                    if (tbArray.length() > 1) {
                                        tbArray.getString(1)
                                    } else null
                                } else null
                                outputs.add(NotebookOutput.Error(evalue, traceback))
                            }
                        }
                    }
                }
                
                cells.add(CellState(
                    id = NotebookCellId(i),
                    code = code.toString().trimEnd('\n'),
                    outputs = outputs
                ))
            }
        }
        
        return cells
    }
}
