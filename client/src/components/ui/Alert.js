export default function Alert({ type = "info", children }) {
    const color = type === "error" ? "red" : type === "success" ? "green" : "blue";
    return (
      <div className={`p-3 rounded bg-${color}-100 text-${color}-700 mb-4`}>
        {children}
      </div>
    );
  }