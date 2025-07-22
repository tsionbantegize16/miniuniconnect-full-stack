import { useEffect, useState } from "react";
import api from "../../services/api";
import { Link } from "react-router-dom";

export default function DiscussionListPage() {
  const [discussions, setDiscussions] = useState([]);

  useEffect(() => {
    api.get("/discussions").then(res => setDiscussions(res.data));
  }, []);

  return (
    <div className="max-w-4xl mx-auto py-8">
      <h2 className="text-2xl font-bold mb-6">Discussions</h2>
      <div className="grid gap-4">
        {discussions.map(d => (
          <Link to={`/discussions/${d.id}`} key={d.id} className="block p-4 bg-white rounded shadow hover:bg-blue-50">
            <h3 className="text-xl font-semibold">{d.title}</h3>
            <p className="text-gray-600">{d.content.slice(0, 100)}...</p>
            <div className="text-sm text-gray-400">By {d.user?.name}</div>
          </Link>
        ))}
      </div>
    </div>
  );
}