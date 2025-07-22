import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../services/api";

export default function ProjectDetailPage() {
  const { id } = useParams();
  const [project, setProject] = useState(null);

  useEffect(() => {
    api.get(`/projects/${id}`).then(res => setProject(res.data));
  }, [id]);

  if (!project) return <div className="p-8">Loading...</div>;

  return (
    <div className="max-w-2xl mx-auto py-8">
      <h2 className="text-3xl font-bold mb-2">{project.title}</h2>
      <div className="mb-2 text-gray-500">By {project.createdBy?.name}</div>
      <p className="mb-4">{project.description}</p>
      <div className="mb-2">Status: <span className="font-semibold">{project.status}</span></div>
      <div className="mb-2">Tags: <span className="text-blue-600">{project.tags}</span></div>
      {project.fileUrl && (
        <a href={project.fileUrl} className="text-blue-600 underline" target="_blank" rel="noopener noreferrer">View Attached File</a>
      )}
    </div>
  );
}