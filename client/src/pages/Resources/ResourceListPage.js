import { useEffect, useState } from "react";
import api from "../../services/api";

export default function ResourceListPage() {
  const [resources, setResources] = useState([]);

  useEffect(() => {
    api.get("/resources").then(res => setResources(res.data));
  }, []);

  return (
    <div className="max-w-4xl mx-auto py-8">
      <h2 className="text-2xl font-bold mb-6">Resources</h2>
      <div className="grid gap-4">
        {resources.map(r => (
          <div key={r.id} className="p-4 bg-white rounded shadow">
            <h3 className="text-xl font-semibold">{r.title}</h3>
            <p className="text-gray-600">{r.description}</p>
            <div className="text-sm text-gray-400">Category: {r.category}</div>
            {r.fileUrl && (
              <a href={r.fileUrl} className="text-blue-600 underline" target="_blank" rel="noopener noreferrer">Download</a>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}