import TaskForm from "./features/tasks/components/TaskForm";
import { getTasks } from "./api/taskApi";

export default function App() {
  async function handleTest() {
    const tasks = await getTasks();
    console.log(tasks);
  }

  return (
    <div>
      <h1>TO DO</h1>
      <TaskForm onSave={(task) => console.log(task)} />
      <button onClick={handleTest}>Probar API</button>
    </div>
  );
}