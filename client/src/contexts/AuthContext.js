import { createContext, useState, useEffect } from "react";
import api from "../services/api";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Optionally, fetch user profile if token exists
    // TODO: Implement persistent login
  }, []);

  const login = async (email, password) => {
    const { data: token } = await api.post("/auth/login", { email, password });
    localStorage.setItem("token", token);
    setUser({ email }); // Optionally fetch user details
  };

  const logout = () => {
    localStorage.removeItem("token");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
} 