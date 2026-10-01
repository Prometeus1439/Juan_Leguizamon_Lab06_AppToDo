import TaskForm from "./features/tasks/components/TaskForm";

export default function App() {
  return (
    <div>
      <h1>TO DO</h1>
      <TaskForm onSave={(task) => console.log(task)}/>
    </div>
  );
}