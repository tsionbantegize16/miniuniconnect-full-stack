import { useEffect, useState } from "react";
import api from "../../services/api";
import { Link } from "react-router-dom";

export default function ProjectListPage() {
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    api.get("/projects").then(res => setProjects(res.data));
  }, []);

  return (
    <div className="max-w-4xl mx-auto py-8">
      <h2 className="text-2xl font-bold mb-6">Projects</h2>
      <Link to="/projects/create" className="mb-4 inline-block bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">Create Project</Link>
      <div className="grid gap-4">
        {projects.map(p => (
          <Link to={`/projects/${p.id}`} key={p.id} className="block p-4 bg-white rounded shadow hover:bg-blue-50">
            <h3 className="text-xl font-semibold">{p.title}</h3>
            <p className="text-gray-600">{p.description}</p>
            <div className="text-sm text-gray-400">By {p.createdBy?.name}</div>
          </Link>
        ))}
      </div>
    </div>
  );
}