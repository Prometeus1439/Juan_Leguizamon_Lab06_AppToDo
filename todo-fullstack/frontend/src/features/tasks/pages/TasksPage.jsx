import TaskForm from "../components/TaskForm";
import TaskList from "../components/TaskList";
import { useTasks } from "../hooks/useTasks";
import { useState } from "react";

export default function TasksPage() {
  const { tasks, loading, error, addTask, editTask, removeTask } = useTasks();
  const [editingTask, setEditingTask] = useState(null);

  function handleSave(data) {
  if (editingTask !== null) {
    editTask(editingTask.id, { ...data, status: editingTask.status });
    setEditingTask(null);
  } else {
    addTask(data);
  }
 }

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
      <TaskForm onSave={handleSave} task={editingTask} />

      {error !== "" ? <p role="alert">{error}</p> : null}

      <h2>TAREAS</h2>
      {loading ? (
        <p>Cargando...</p>
      ) : (
        <TaskList
          tasks={tasks}
          onDelete={removeTask}
          onChangeStatus={handleChangeStatus}
          onEdit={setEditingTask}
        />
      )}
    </div>
  );
}