import { useEffect, useState } from "react";
import api from "../../services/api";

export default function AdminDashboardPage() {
  const [stats, setStats] = useState({ userCount: 0, projectCount: 0 });

  useEffect(() => {
    api.get("/admin/dashboard").then(res => setStats(res.data));
  }, []);

  return (
    <div className="max-w-2xl mx-auto py-8">
      <h2 className="text-2xl font-bold mb-6">Admin Dashboard</h2>
      <div className="bg-white p-6 rounded shadow">
        <div className="mb-4">Total Users: <span className="font-bold">{stats.userCount}</span></div>
        <div className="mb-4">Total Projects: <span className="font-bold">{stats.projectCount}</span></div>
      </div>
    </div>
  );
}