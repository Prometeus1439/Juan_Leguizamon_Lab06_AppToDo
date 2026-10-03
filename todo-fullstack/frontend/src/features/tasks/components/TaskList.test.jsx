import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import TaskList from "./TaskList";

const tasks = [
  { id: 1, title: "Estudiar", status: "PENDING", priority: "HIGH", dueDate: "2026-10-05" },
  { id: 2, title: "Leer", status: "COMPLETED", priority: "LOW", dueDate: null },
];

describe("TaskList", () => {
  test("renders tasks", () => {
    // Arrange & Act
    render(
      <TaskList tasks={tasks} onDelete={vi.fn()} onChangeStatus={vi.fn()} onEdit={vi.fn()} />
    );

    // Assert
    expect(screen.getByText(/Estudiar/)).toBeInTheDocument();
    expect(screen.getByText(/Leer/)).toBeInTheDocument();
  });

  test("shows status", () => {
    // Arrange & Act
    render(
        <TaskList tasks={tasks} onDelete={vi.fn()} onChangeStatus={vi.fn()} onEdit={vi.fn()} />
    );

    // Assert
    expect(screen.getByText(/PENDING/)).toBeInTheDocument();
  });

  test("shows priority", () => {
    // Arrange & Act
    render(
        <TaskList tasks={tasks} onDelete={vi.fn()} onChangeStatus={vi.fn()} onEdit={vi.fn()} />
    );

    // Assert
    expect(screen.getByText(/HIGH/)).toBeInTheDocument();
  });

  test("executes delete", async () => {
    // Arrange
    const onDelete = vi.fn();
    render(
        <TaskList tasks={tasks} onDelete={onDelete} onChangeStatus={vi.fn()} onEdit={vi.fn()} />
    );
     const user = userEvent.setup();

    // Act
    await user.click(screen.getAllByRole("button", { name: "Eliminar"})[0]);

    // Assert
    expect(onDelete).toHaveBeenCalledWith(1);
  });

});
