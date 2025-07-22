import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../services/api";

export default function DiscussionDetailPage() {
  const { id } = useParams();
  const [discussion, setDiscussion] = useState(null);

  useEffect(() => {
    api.get(`/discussions/${id}`).then(res => setDiscussion(res.data));
  }, [id]);

  if (!discussion) return <div className="p-8">Loading...</div>;

  return (
    <div className="max-w-2xl mx-auto py-8">
      <h2 className="text-3xl font-bold mb-2">{discussion.title}</h2>
      <div className="mb-2 text-gray-500">By {discussion.user?.name}</div>
      <p className="mb-4">{discussion.content}</p>
      <div className="mb-2 text-gray-400">Posted at: {discussion.timestamp}</div>
      {/* Comments and add comment form can go here */}
    </div>
  );
}