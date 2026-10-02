import TaskForm from "./features/tasks/components/TaskForm";
import TaskList from "./features/tasks/components/TaskList";
import { useTasks } from "./features/tasks/hooks/useTasks";

export default function App() {
  const { tasks, addTask, editTask, removeTask } = useTasks();

  function handleChangeStatus(task, newStatus) {
    editTask(task.id, {
      title: task.title,
      description: task.description,
      status: newStatus,
      priority: task.priority,
      dueDate: task.dueDate,
    });
  }

  return (
    <div>
      <h1>TO DO</h1>
      <TaskForm onSave={addTask} />
      <TaskList tasks={tasks} onDelete={removeTask} onChangeStatus={handleChangeStatus} />
    </div>
  );
}