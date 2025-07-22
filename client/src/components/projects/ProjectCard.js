import { Link } from "react-router-dom";

export default function ProjectCard({ project }) {
  return (
    <div className="bg-white rounded shadow p-4 hover:bg-blue-50 transition">
      <h3 className="text-xl font-semibold mb-1">{project.title}</h3>
      <p className="text-gray-600 mb-2">{project.description}</p>
      <div className="text-sm text-gray-400 mb-2">By {project.createdBy?.name}</div>
      <Link to={`/projects/${project.id}`} className="text-blue-600 hover:underline">View Details</Link>
    </div>
  );
}