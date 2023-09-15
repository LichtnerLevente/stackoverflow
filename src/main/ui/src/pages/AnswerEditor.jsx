import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const fetchAnswer = (id) => {
    return fetch(`http://localhost:8080/answers/${id}`)
        .then((res) => res.json());
};

const updateAnswer = (updatedAnswerDTO, answerId) => {
    return fetch(`http://localhost:8080/answers/${answerId}/update`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedAnswerDTO),
    }).then((res) => res.json());
};

const AnswerEditor = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [answer, setAnswer] = useState("");
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (id) {
            setLoading(true);
            console.log(id)
            fetchAnswer(id)
                .then((data) => {
                    setAnswer(data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error("Error fetching answer:", error);
                    setLoading(false);
                });
        }
    }, [id]);

    const handleSaveAnswer = () => {
        setLoading(true);

        if (id) {
            const editedAnswer = answer;
            const updatedAnswerDTO = {
                id: id,
                answer: editedAnswer,
                questionId: editedAnswer.questionId
            };
            updateAnswer(updatedAnswerDTO, id)
                .then(() => {
                    setLoading(false);
                    navigate(`/`);
                })
                .catch((error) => {
                    console.error("Error updating answer:", error);
                    setLoading(false);
                });
        }
    };

    return (
        <div>
            <h2>Edit Answer</h2>
            <textarea
                value={answer.answer}
                onChange={(e) => setAnswer(e.target.value)}
            />
            <button onClick={handleSaveAnswer} disabled={loading}>
                {loading ? "Loading" : "Save"}
            </button>
        </div>
    );
};

export default AnswerEditor;