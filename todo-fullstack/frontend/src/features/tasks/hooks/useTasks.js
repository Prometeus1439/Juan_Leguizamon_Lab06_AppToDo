import { useState, useEffect } from "react";
import { getTasks, createTask, updateTask, deleteTask } from "../../../api/taskApi";

export function useTasks() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  async function loadTasks() {
    setLoading(true);
    setError("");
    try {
      const data = await getTasks();
      setTasks(data);
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadTasks();
  }, []);

  async function addTask(task) {
    try {
      await createTask(task);
      await loadTasks();
    } catch (e) {
      setError(e.message);
    }
  }

  async function editTask(id, task) {
    try {
      await updateTask(id, task);
      await loadTasks();
    } catch (e) {
      setError(e.message);
    }
  }

  async function removeTask(id) {
    try {
      await deleteTask(id);
      await loadTasks();
    } catch (e) {
      setError(e.message);
    }
  }

  return { tasks, loading, error, loadTasks, addTask, editTask, removeTask };
}