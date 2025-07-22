import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./contexts/AuthContext";
import Navbar from "./components/common/Navbar";
import Footer from "./components/common/Footer";
import Home from "./pages/Home";
import LoginPage from "./pages/Auth/LoginPage";
import RegisterPage from "./pages/Auth/RegisterPage";
import ProjectListPage from "./pages/Projects/ProjectListPage";
import ProjectDetailPage from "./pages/Projects/ProjectDetailPage";
import CreateProjectPage from "./pages/Projects/CreateProjectPage";
import ResourceListPage from "./pages/Resources/ResourceListPage";
import UploadResourcePage from "./pages/Resources/UploadResourcePage";
import DiscussionListPage from "./pages/Discussions/DiscussionListPage";
import DiscussionDetailPage from "./pages/Discussions/DiscussionDetailPage";
import UserProfilePage from "./pages/Profile/UserProfilePage";
import AdminDashboardPage from "./pages/Admin/AdminDashboardPage";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <main className="min-h-screen bg-gray-50 pt-4 pb-8">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/projects" element={<ProjectListPage />} />
            <Route path="/projects/create" element={<CreateProjectPage />} />
            <Route path="/projects/:id" element={<ProjectDetailPage />} />
            <Route path="/resources" element={<ResourceListPage />} />
            <Route path="/resources/upload" element={<UploadResourcePage />} />
            <Route path="/discussions" element={<DiscussionListPage />} />
            <Route path="/discussions/:id" element={<DiscussionDetailPage />} />
            <Route path="/profile/:id" element={<UserProfilePage />} />
            <Route path="/admin/dashboard" element={<AdminDashboardPage />} />
          </Routes>
        </main>
        <Footer />
      </Router>
    </AuthProvider>
  );
}

export default App;
