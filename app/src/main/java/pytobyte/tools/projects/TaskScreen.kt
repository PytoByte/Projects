package pytobyte.tools.projects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.util.Date

@Composable
fun TaskScreen(taskModels: List<TaskModel>) {
    LazyColumn() {
        itemsIndexed(taskModels.sortedBy { it.priority }) { index, item ->
            Task(item)
        }
    }
}

@Composable
fun Task(taskModel: TaskModel) {
    val checked = remember { mutableStateOf(false) }
    Row() {
        Column {
            Text(taskModel.name)
            Text(taskModel.deadLine.date.toLocaleString())
        }
        Checkbox(checked = checked.value, onCheckedChange = {checked.value = checked.value.not()})
    }

}

data class TaskModel(
    val priority: Int,
    val name: String,
    val deadLine: DeadLine,
    var complete: Boolean = false,
)

data class DeadLine(
    val date: Date,
    val hard: Boolean
)
