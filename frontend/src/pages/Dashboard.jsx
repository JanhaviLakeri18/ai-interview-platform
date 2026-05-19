function Dashboard() {

    const token = localStorage.getItem("token");

    return (

        <div className="min-h-screen bg-gray-900 text-white p-10">

            <h1 className="text-4xl font-bold mb-6">
                AI Interview Platform
            </h1>

            <div className="bg-gray-800 p-6 rounded-xl">

                <h2 className="text-2xl mb-4">
                    Dashboard
                </h2>

                <p className="break-all text-green-400">
                    JWT Token:
                </p>

                <p className="mt-2 text-sm text-gray-300">
                    {token}
                </p>

            </div>

        </div>
    )
}

export default Dashboard