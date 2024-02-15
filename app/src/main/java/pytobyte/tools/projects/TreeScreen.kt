package pytobyte.tools.projects

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TreeScreen(navTask: () -> Unit) {
    TerminalTree(
        nodeModel = NodeModel(
            "Root",
            Color.Red,
            nodeModels = listOf(
                NodeModel("Child", Color.Blue,
                    nodeModels = listOf(
                        NodeModel(nodeModels = listOf(NodeModel())),
                        NodeModel(nodeModels = listOf(NodeModel(), NodeModel()))
                    )
                ),
                NodeModel("Child", Color.Green, nodeModels = listOf(NodeModel(), NodeModel())),
                NodeModel("Child", Color.Gray,
                    nodeModels = listOf(
                        NodeModel(buttons = listOf(ButtonModel{navTask()})),
                        NodeModel()
                    )
                )
            )
        )
    )
}