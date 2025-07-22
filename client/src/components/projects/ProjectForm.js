import { useState } from "react";

export default function ProjectForm({ onSubmit, initial = {} }) {
  const [form, setForm] = useState({
    title: initial.title || "",
    description: initial.description || "",
    tags: initial.tags || "",
    fileUrl: initial.fileUrl || "",
    status: initial.status || "OPEN"
  });

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = e => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow">
      <input className="w-full border p-2 rounded" name="title" placeholder="Title" value={form.title} onChange={handleChange} required />
      <textarea className="w-full border p-2 rounded" name="description" placeholder="Description" value={form.description} onChange={handleChange} required />
      <input className="w-full border p-2 rounded" name="tags" placeholder="Tags (comma separated)" value={form.tags} onChange={handleChange} />
      <input className="w-full border p-2 rounded" name="fileUrl" placeholder="File URL" value={form.fileUrl} onChange={handleChange} />
      <select className="w-full border p-2 rounded" name="status" value={form.status} onChange={handleChange}>
        <option value="OPEN">Open</option>
        <option value="CLOSED">Closed</option>
      </select>
      <button className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition" type="submit">Save</button>
    </form>
  );
}