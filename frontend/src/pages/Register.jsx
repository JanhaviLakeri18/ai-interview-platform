import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Register() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleRegister = async () => {

        try {

            const response = await API.post(
                "/auth/register",
                formData
            );

            alert(response.data);

            navigate("/");

        } catch (error) {

            console.log(error);

            alert("Registration Failed");
        }
    };

    return (

        <div className="min-h-screen flex items-center justify-center bg-gray-900">

            <div className="bg-gray-800 p-8 rounded-2xl w-96 shadow-lg">

                <h1 className="text-3xl text-white font-bold mb-6 text-center">
                    Register
                </h1>

                <input
                    type="text"
                    name="name"
                    placeholder="Name"
                    onChange={handleChange}
                    className="w-full p-3 mb-4 rounded bg-gray-700 text-white"
                />

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
                    onClick={handleRegister}
                    className="w-full bg-green-600 hover:bg-green-700 text-white p-3 rounded"
                >
                    Register
                </button>

            </div>

        </div>
    )
}

export default Register