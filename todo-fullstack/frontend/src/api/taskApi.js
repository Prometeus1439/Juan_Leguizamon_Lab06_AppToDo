const API_URL = "http://localhost:8080/api/v1/tasks";

export async function getTasks() {
    const response = await fetch(API_URL);

    if (!response.ok){
        throw new Error("Error loading tasks");
    }

    return response.json();
}

export async function getTask(id) {
    // TODO
}

export async function createTask(task) {
    // TODO
}

export async function updateTask(id, task) {
    // TODO
}

export async function deleteTask(id) {
    // TODO
}