import {useNavigate} from "react-router-dom";
import {QuestionForm} from "../components/QuestionForm";
import {useState} from "react";

const createQuestion = (questionDTO) => {
    return fetch("http://localhost:8080/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(questionDTO),
    }).then(res => res.json());
};

export const QuestionCreator = () => {


    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const handleCreate = (question) => {
        setLoading(true);

        createQuestion(question)
            .then(() => {
                setLoading(false)
                navigate("/");
            })
    };
    return (
        <QuestionForm
            onSave={handleCreate}
            onCancel={() => navigate("/")}
        />
    )
}