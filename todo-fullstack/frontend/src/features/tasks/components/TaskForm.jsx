import { useState, useEffect } from "react";

export default function TaskForm({ onSave, task }) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [priority, setPriority] = useState("MEDIUM");
  const [dueDate, setDueDate] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
  if (task) {
    setTitle(task.title);
    setDescription(task.description ?? "");
    setPriority(task.priority);
    setDueDate(task.dueDate ?? "");
    }
  }, [task]);

  function handleTitleChange(e) {
    setTitle(e.target.value);
  }

  function handleDescriptionChange(e) {
    setDescription(e.target.value);
  }

  function handlePriorityChange(e) {
    setPriority(e.target.value);
  }

  function handleDueDateChange(e) {
    setDueDate(e.target.value);
  }

  function handleSubmit(e) {
    e.preventDefault();

    if (title.trim() === "") {
      setError("El título es obligatorio");
      return;
    }

    onSave({ title, description, priority, dueDate });

    setTitle("");
    setDescription("");
    setPriority("MEDIUM");
    setDueDate("");
    setError("");
  }

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="title">Título:</label>
      <input
        id="title"
        type="text"
        value={title}
        onChange={handleTitleChange}
      />

      <label htmlFor="description">Descripción:</label>
      <input
        id="description"
        type="text"
        value={description}
        onChange={handleDescriptionChange}
      />

      <label htmlFor="priority">Prioridad:</label>
      <select
        id="priority"
        value={priority}
        onChange={handlePriorityChange}
      >
        <option value="LOW">LOW</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="HIGH">HIGH</option>
      </select>

      <label htmlFor="dueDate">Fecha límite:</label>
      <input
        id="dueDate"
        type="date"
        value={dueDate}
        onChange={handleDueDateChange}
      />

      {error !== "" ? <p role="alert">{error}</p> : null}

      <button type="submit">{task ? "Actualizar" : "Guardar"}</button>
    </form>
  );
}