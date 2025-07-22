import { useState } from "react";
import api from "../../services/api";
import { useNavigate } from "react-router-dom";

export default function RegisterPage() {
  const [form, setForm] = useState({ name: "", email: "", password: "", role: "STUDENT" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    setError("");
    try {
      await api.post("/auth/register", {
        name: form.name,
        email: form.email,
        role: form.role,
        bio: "",
        skills: ""
      }, { params: { password: form.password } });
      navigate("/login");
    } catch (err) {
      setError("Registration failed. Try a different email.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-50">
      <form onSubmit={handleSubmit} className="bg-white p-8 rounded shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center">Register</h2>
        <input className="w-full mb-4 p-2 border rounded" name="name" placeholder="Full Name" value={form.name} onChange={handleChange} required />
        <input className="w-full mb-4 p-2 border rounded" name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
        <input className="w-full mb-4 p-2 border rounded" name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
        <select className="w-full mb-4 p-2 border rounded" name="role" value={form.role} onChange={handleChange}>
          <option value="STUDENT">Student</option>
          <option value="INSTRUCTOR">Instructor</option>
        </select>
        {error && <div className="text-red-500 mb-4">{error}</div>}
        <button className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700 transition" type="submit">
          Register
        </button>
        <div className="mt-4 text-center">
          <a href="/login" className="text-blue-600 hover:underline">Already have an account? Login</a>
        </div>
      </form>
    </div>
  );
}