import TaskForm from "./features/tasks/components/TaskForm";
import TaskList from "./features/tasks/components/TaskList";
import { useTasks } from "./features/tasks/hooks/useTasks";

export default function App() {
  const { tasks, addTask, removeTask } = useTasks();

  return (
    <div>
      <h1>TO DO</h1>
      <TaskForm onSave={addTask} />
      <TaskList tasks={tasks} onDelete={removeTask} />
    </div>
  );
}