import { useEffect, useState } from "react";
import api from "../../services/api";
import { useParams } from "react-router-dom";

export default function UserProfilePage() {
  const { id } = useParams();
  const [user, setUser] = useState(null);

  useEffect(() => {
    api.get(`/users/${id}`).then(res => setUser(res.data));
  }, [id]);

  if (!user) return <div className="p-8">Loading...</div>;

  return (
    <div className="max-w-xl mx-auto py-8">
      <h2 className="text-3xl font-bold mb-2">{user.name}</h2>
      <div className="mb-2 text-gray-500">{user.email}</div>
      <div className="mb-2">Role: <span className="font-semibold">{user.role}</span></div>
      <div className="mb-2">Bio: {user.bio}</div>
      <div className="mb-2">Skills: {user.skills}</div>
    </div>
  );
}