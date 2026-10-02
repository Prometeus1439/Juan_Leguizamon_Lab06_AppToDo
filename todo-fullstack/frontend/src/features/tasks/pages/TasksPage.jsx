import TaskForm from "../components/TaskForm";
import TaskList from "../components/TaskList";
import { useTasks } from "../hooks/useTasks";

export default function TasksPage() {
  const { tasks, loading, error, addTask, editTask, removeTask } = useTasks();

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

      {error !== "" ? <p role="alert">{error}</p> : null}

      <h2>TAREAS</h2>
      {loading ? (
        <p>Cargando...</p>
      ) : (
        <TaskList tasks={tasks} onDelete={removeTask} onChangeStatus={handleChangeStatus} />
      )}
    </div>
  );
}