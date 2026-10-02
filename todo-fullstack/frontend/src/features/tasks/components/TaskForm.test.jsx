import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import TaskForm from "./TaskForm";

describe("TaskForm", () => {
  test("renders the form", () => {
    // Arrange & Act
    render(<TaskForm onSave={vi.fn()} />);

    // Assert
    expect(screen.getByLabelText("Título:")).toBeInTheDocument();
    expect(screen.getByLabelText("Descripción:")).toBeInTheDocument();
    expect(screen.getByLabelText("Prioridad:")).toBeInTheDocument();
    expect(screen.getByLabelText("Fecha límite:")).toBeInTheDocument();
    expect(screen.getByRole("button", { name: "Guardar" })).toBeInTheDocument();
  });

  test("validates required title", async () => {
    // Arrange
    const onSave = vi.fn();
    render(<TaskForm onSave={onSave} />);
    const user = userEvent.setup();
    
    // Act
    await user.click(screen.getByRole("button", { name: "Guardar" }));

    // Assert
    expect(screen.getByRole("alert")).toHaveTextContent("El título es obligatorio");
    expect(onSave).not.toHaveBeenCalled();
  });

  test("allows typing a title", async () => {
    // Arrange
    render(<TaskForm onSave={vi.fn()} />);
    const user = userEvent.setup();
    const titleInput = screen.getByLabelText("Título:");

    // Act
    await user.type(titleInput, "Estudiar");

    // Assert
    expect(titleInput).toHaveValue("Estudiar");

  });

  test("excecutes save action", async () => {
    //Arrange
    const onSave = vi.fn();
    render(<TaskForm onSave={onSave} />);
    const user = userEvent.setup();

    // Act
    await user.type(screen.getByLabelText("Título:"), "Estudiar");
    await user.type(screen.getByLabelText("Descripción:"), "Capítulo 3");
    await user.selectOptions(screen.getByLabelText("Prioridad:"), "HIGH");
    await user.click(screen.getByRole("button", { name: "Guardar"}));

    // Assert
    expect(onSave).toHaveBeenCalledWith({
        title: "Estudiar",
        description: "Capítulo 3",
        priority: "HIGH",
        dueDate: "",
    }); 
  });
});