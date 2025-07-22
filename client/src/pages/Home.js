export default function Home() {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen bg-gradient-to-br from-blue-50 to-green-50">
        <h1 className="text-4xl font-bold mb-4">Welcome to Mini UniConnect</h1>
        <p className="text-lg text-gray-700 mb-8 text-center max-w-xl">
          Collaborate, share resources, post academic projects, and communicate with your university community.
        </p>
        <a href="/projects" className="bg-blue-600 text-white px-6 py-3 rounded shadow hover:bg-blue-700 transition">
          Explore Projects
        </a>
      </div>
    );
  }