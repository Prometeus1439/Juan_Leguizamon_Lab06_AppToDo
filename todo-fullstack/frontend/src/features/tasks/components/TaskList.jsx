export default function TaskList({ tasks, onDelete }) {
  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id}>
            <strong>[{task.priority}] {task.title}</strong>
            <p>Estado: {task.status}</p>
            <p>Fecha: {task.dueDate}</p>
            <button onClick={() => onDelete(task.id)}>Eliminar</button>
        </li>
      ))}
    </ul>
  );
}