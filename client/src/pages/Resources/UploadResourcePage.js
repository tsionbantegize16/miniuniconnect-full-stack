import { useState } from "react";
import api from "../../services/api";
import { useNavigate } from "react-router-dom";

export default function UploadResourcePage() {
  const [form, setForm] = useState({ title: "", description: "", fileUrl: "", category: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    setError("");
    try {
      // Replace 1 with the actual logged-in user ID
      await api.post("/resources", { ...form }, { params: { uploaderId: 1 } });
      navigate("/resources");
    } catch {
      setError("Failed to upload resource.");
    }
  };

  return (
    <div className="max-w-xl mx-auto py-8">
      <h2 className="text-2xl font-bold mb-6">Upload Resource</h2>
      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow">
        <input className="w-full border p-2 rounded" name="title" placeholder="Title" value={form.title} onChange={handleChange} required />
        <textarea className="w-full border p-2 rounded" name="description" placeholder="Description" value={form.description} onChange={handleChange} required />
        <input className="w-full border p-2 rounded" name="fileUrl" placeholder="File URL" value={form.fileUrl} onChange={handleChange} />
        <input className="w-full border p-2 rounded" name="category" placeholder="Category" value={form.category} onChange={handleChange} />
        {error && <div className="text-red-500">{error}</div>}
        <button className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700 transition" type="submit">Upload</button>
      </form>
    </div>
  );
}