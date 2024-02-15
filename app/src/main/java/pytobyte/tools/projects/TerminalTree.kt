package pytobyte.tools.projects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class NodeModel(
    val name: String = "Node",
    val color: Color = Color.Unspecified,
    val nodeModels: List<NodeModel> = emptyList(),
    val buttons: List<ButtonModel> = emptyList(),
    val state: MutableState<Boolean> = mutableStateOf(false)
)

data class ButtonModel(
    val name: String = "Button",
    val color: Color = Color.White,
    val onClick: () -> Unit = {}
)

@Composable
fun terminalTreeDrawer(nodeModel: NodeModel, level: Int = 0, edge: String = "") {
    val summaryIndex = nodeModel.buttons.size + nodeModel.nodeModels.size
    var currentIndex = 0
    Column() {
        nodeModel.buttons.forEach {
            currentIndex++
            Row() {
                Text((edge) + (if (currentIndex == summaryIndex) "└──" else "├──"))
                ChildNodeButton(it)
            }

        }
        nodeModel.nodeModels.forEach {
            currentIndex++
            Row() {
                Text((edge) + (if (currentIndex == summaryIndex) "└──" else "├──"))
                SimpleNode(it)
            }
            if (it.state.value) {
                terminalTreeDrawer(
                    it,
                    level + 1,
                    if (currentIndex == summaryIndex) edge + "        " else edge + "│      "
                )
            }
        }
    }
}

@Composable
fun TerminalTree(nodeModel: NodeModel) {
    Column {
        SimpleNode(nodeModel)
        if (nodeModel.state.value) {
            terminalTreeDrawer(nodeModel)
        }
    }
}

@Composable
fun SimpleNode(nodeModel: NodeModel) {
    Column {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .border(2.dp, if (nodeModel.state.value) nodeModel.color else Color.Transparent, RoundedCornerShape(10.dp))
            .clickable { nodeModel.state.value = nodeModel.state.value.not() }
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = nodeModel.name,
                color = if (nodeModel.state.value) Color.Unspecified else nodeModel.color
            )
        }
    }
}

@Composable
fun ChildNodeButton(buttonModel: ButtonModel) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { buttonModel.onClick() }) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = buttonModel.name,
            color = buttonModel.color
        )
    }
}