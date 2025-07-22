import api from "./api";

export const login = (email, password) =>
  api.post("/auth/login", { email, password });

export const register = (user, password) =>
  api.post("/auth/register", user, { params: { password } }); 