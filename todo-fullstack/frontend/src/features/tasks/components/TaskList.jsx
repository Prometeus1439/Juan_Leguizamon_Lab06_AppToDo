export default function TaskList({ tasks, onDelete, onChangeStatus, onEdit }) {

if (tasks.length === 0) {
    return <p>No hay tareas registradas.</p>;
  }
  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id}>
            <strong>[{task.priority}] {task.title}</strong>
            <p>Estado: {task.status}</p>
            <p>Fecha: {task.dueDate}</p>
            <button onClick={() => onEdit(task)}>Editar</button>
            {task.status === "PENDING" ? (
                <button onClick={() => onChangeStatus(task, "IN_PROGRESS")}>Iniciar</button>
            ) : null}
            {task.status === "IN_PROGRESS" ? (
                <button onClick={() => onChangeStatus(task, "COMPLETED")}>Completar</button>
            ) : null}
            <button onClick={() => onDelete(task.id)}>Eliminar</button>
        </li>
      ))}
    </ul>
  );
}