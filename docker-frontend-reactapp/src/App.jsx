import { useEffect, useState } from "react";
import "./App.css";

const API_URL = "http://localhost:8081/electricalrepair";

function App() {
  const [repairs, setRepairs] = useState([]);
  const [form, setForm] = useState({
    technicianName: "",
    applianceType: "",
    repairCost: "",
    completed: true,
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;

    setForm({
      ...form,
      [name]: name === "completed" ? value === "true" : value,
    });
  };

  const loadRepairs = async () => {
    try {
      const response = await fetch(`${API_URL}/displayall`);

      if (!response.ok) {
        throw new Error("Failed to load repairs");
      }

      const data = await response.json();
      setRepairs(data);
      setMessage("");
    } catch (error) {
      console.error(error);
      setMessage("Backend is not connected.");
    }
  };

  const addRepair = async (e) => {
    e.preventDefault();

    try {
      const data = {
        technicianName: form.technicianName,
        applianceType: form.applianceType,
        repairCost: Number(form.repairCost),
        completed: form.completed,
      };

      const response = await fetch(`${API_URL}/add`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error("Add failed");
      }

      await response.json();
      setMessage("Repair added successfully!");

      setForm({
        technicianName: "",
        applianceType: "",
        repairCost: "",
        completed: true,
      });

      loadRepairs();
    } catch (error) {
      console.error(error);
      setMessage("Failed to add repair.");
    }
  };

  const deleteRepair = async (id) => {
    try {
      const response = await fetch(`${API_URL}/delete/${id}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("Delete failed");
      }

      const result = await response.text();
      setMessage(result);

      loadRepairs();
    } catch (error) {
      console.error(error);
      setMessage("Failed to delete repair.");
    }
  };

  useEffect(() => {
    loadRepairs();
  }, []);

  return (
    <div className="container">
      <header className="hero">
        <div className="tool-icon">🔧</div>
        <div>
          <h1>Electrical Repair Management</h1>
          <p>Manage electrical appliance repairs easily</p>
        </div>
      </header>

      <section className="card">
        <h2>📝 Add Repair</h2>

        <form onSubmit={addRepair}>
          <div className="form-grid">
            <div className="field">
              <label>Technician Name</label>
              <input
                type="text"
                name="technicianName"
                value={form.technicianName}
                onChange={handleChange}
                placeholder="Enter Technician Name"
                required
              />
            </div>

            <div className="field">
              <label>Appliance Type</label>
              <input
                type="text"
                name="applianceType"
                value={form.applianceType}
                onChange={handleChange}
                placeholder="Enter Appliance Type"
                required
              />
            </div>

            <div className="field">
              <label>Repair Cost</label>
              <input
                type="number"
                name="repairCost"
                value={form.repairCost}
                onChange={handleChange}
                placeholder="Enter Repair Cost"
                min="0"
                step="0.01"
                required
              />
            </div>

            <div className="field">
              <label>Completed</label>
              <select
                name="completed"
                value={String(form.completed)}
                onChange={handleChange}
              >
                <option value="true">Yes</option>
                <option value="false">No</option>
              </select>
            </div>
          </div>

          <div className="buttons">
            <button className="btn add" type="submit">
              ＋ Add
            </button>

            <button
              className="btn view"
              type="button"
              onClick={loadRepairs}
            >
              ◉ View All
            </button>
          </div>
        </form>

        <p className="message">{message}</p>
      </section>

      <section className="card">
        <h2>▣ Repairs List</h2>

        <div className="table-wrapper">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Technician Name</th>
                <th>Appliance Type</th>
                <th>Repair Cost</th>
                <th>Completed</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {repairs.length === 0 ? (
                <tr>
                  <td colSpan="6" className="empty">
                    No repairs found.
                  </td>
                </tr>
              ) : (
                repairs.map((repair) => (
                  <tr key={repair.id}>
                    <td>{repair.id}</td>
                    <td>{repair.technicianName}</td>
                    <td>{repair.applianceType}</td>
                    <td>₹{repair.repairCost}</td>
                    <td>{repair.completed ? "Yes" : "No"}</td>
                    <td>
                      <button
                        className="btn delete"
                        onClick={() => deleteRepair(repair.id)}
                      >
                        🗑 Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </section>
    </div>
  );
}

export default App;
