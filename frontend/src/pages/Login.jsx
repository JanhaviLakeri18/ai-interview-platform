import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Login() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleLogin = async () => {

        try {

            const response = await API.post(
                "/auth/login",
                formData
            );

            localStorage.setItem(
                "token",
                response.data
            );

            alert("Login Successful");

            navigate("/dashboard");

        } catch (error) {

            console.log(error);

            alert("Invalid Credentials");
        }
    };

    return (

        <div className="min-h-screen flex items-center justify-center bg-gray-900">

            <div className="bg-gray-800 p-8 rounded-2xl w-96 shadow-lg">

                <h1 className="text-3xl text-white font-bold mb-6 text-center">
                    Login
                </h1>

                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    onChange={handleChange}
                    className="w-full p-3 mb-4 rounded bg-gray-700 text-white"
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    onChange={handleChange}
                    className="w-full p-3 mb-4 rounded bg-gray-700 text-white"
                />

                <button
                    onClick={handleLogin}
                    className="w-full bg-blue-600 hover:bg-blue-700 text-white p-3 rounded"
                >
                    Login
                </button>

                <p className="text-gray-400 mt-4 text-center">
                    Don't have an account?
                </p>

                <button
                    onClick={() => navigate("/register")}
                    className="w-full mt-3 bg-gray-700 hover:bg-gray-600 text-white p-3 rounded"
                >
                    Create Account
                </button>

            </div>

        </div>
    )
}

export default Login