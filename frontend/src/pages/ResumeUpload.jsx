import { useState } from "react";
import API from "../services/api";

function ResumeUpload() {

    const [file, setFile] = useState(null);

    const handleUpload = async () => {

        if (!file) {
            alert("Select Resume");
            return;
        }

        const formData = new FormData();

        formData.append("file", file);

        formData.append(
            "email",
            "test@gmail.com"
        );

        try {

            const response = await API.post(
                "/resume/upload",
                formData,
                {
                    headers: {
                        "Content-Type":
                            "multipart/form-data"
                    }
                }
            );

            alert(response.data);

        } catch (error) {

            console.log(error);

            alert("Upload Failed");
        }
    };

    return (

        <div className="min-h-screen bg-gray-900 text-white flex items-center justify-center">

            <div className="bg-gray-800 p-8 rounded-2xl w-[500px]">

                <h1 className="text-3xl font-bold mb-6">
                    Upload Resume
                </h1>

                <input
                    type="file"
                    accept=".pdf"
                    onChange={(e) =>
                        setFile(e.target.files[0])
                    }
                    className="mb-6"
                />

                <button
                    onClick={handleUpload}
                    className="bg-blue-600 px-6 py-3 rounded hover:bg-blue-700"
                >
                    Upload Resume
                </button>

            </div>

        </div>
    )
}

export default ResumeUpload