import { Link } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

export default function Navbar() {
  const { user, logout } = useAuth();

  return (
    <nav className="bg-white shadow mb-6">
      <div className="container mx-auto flex justify-between items-center p-4">
        <div>
          <Link to="/" className="font-bold text-blue-700 text-lg">UniConnect</Link>
        </div>
        <div className="flex gap-4 items-center">
          <Link to="/projects" className="hover:text-blue-700">Projects</Link>
          <Link to="/resources" className="hover:text-blue-700">Resources</Link>
          <Link to="/discussions" className="hover:text-blue-700">Discussions</Link>
          {user ? (
            <>
              <Link to="/profile" className="hover:text-blue-700">Profile</Link>
              <button onClick={logout} className="ml-2 text-red-500 hover:underline">Logout</button>
            </>
          ) : (
            <>
              <Link to="/login" className="hover:text-blue-700">Login</Link>
              <Link to="/register" className="hover:text-blue-700">Register</Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}