import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {QuestionForm} from "../components/QuestionForm";
import { Link } from "react-router-dom";

const fetchQuestion = (id) => {
    return fetch(`http://localhost:8080/questions/${id}`)
        .then(res => res.json());
}
const updateQuestion = (questionDTO) => {

    return fetch(`http://localhost:8080/questions/${questionDTO.id}/update`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(questionDTO),
    }).then(res => res.json());
};
const deleteQuestion = (id) => {
    return fetch(`http://localhost:8080/questions/${id}`, {method: "DELETE"})
        .then(res => res.json());
};

const fetchAnswers = (id) => {
    return fetch(`http://localhost:8080/answers/answers-for-question/${id}`).then((res) => res.json());
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

const deleteAnswer = (answerId) => {
    return fetch(`http://localhost:8080/answers/${answerId}`, {
        method: "DELETE",
    }).then((res) => res.json());
};



export const QuestionDetails = () => {

    const { id } = useParams();
    const navigate = useNavigate();
    const [answers, setAnswers] = useState(null);
    const [question, setQuestion] = useState(null);
    const [loading, setLoading] = useState(true);
    const [editing, setEditing] = useState(false);
    const [newAnswer, setNewAnswer] = useState("");

    useEffect(() => {
        setLoading(true);

        fetchQuestion(id)
            .then((questionData) => {
                setQuestion(questionData);
                return fetchAnswers(id);
            })
            .then((answersData) => {
                setAnswers(answersData);
                setLoading(false);
            })
            .catch((error) => {
                console.error(error)
                setLoading(false);
            });
    }, [id]);

    if (loading) return <div/>;

    function handleUpdate(questionId) {
        updateQuestion(questionId).then(res => {
            setQuestion(res)
            setEditing(false)
        })
    }

    function handleDelete(idString) {
        let id = parseInt(idString);
        deleteQuestion(id).then(deleted => {
            if (deleted) {
                navigate("/");
            }
        })
    }



    const handleDeleteAnswer = (answerId) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this answer?");
        if (confirmDelete) {
            deleteAnswer(answerId).then((deleted) => {
                if (deleted) {
                    console.log(deleted)
                    fetchQuestion(id)
                        .then((questionData) => {
                            setQuestion(questionData);
                            return fetchAnswers(id);
                        })
                        .then((answersData) => {
                            setAnswers(answersData);
                        })
                        .catch((error) => {
                            console.error("Error fetching question or answers:", error);
                        });
                }
            });
        }
    };


    const handleCreateAnswer = () => {

        const newAnswerObj = {
            answer: newAnswer,
            questionId: question.id,
            userId: null,
        };

        fetch("http://localhost:8080/answers/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newAnswerObj),
        })
            .then((res) => res.json())
            .then(() => {
                fetchAnswers(id).then((answersData) => {
                    setAnswers(answersData);
                });
                setNewAnswer("");
            })
            .catch((error) => {
                console.error("Error creating answer:", error);
            });
    };


    function handleCancel() {
        setEditing(false);
    }

    return (
        <div>
            {!editing ? (
                <div>
                    <h2>Title: {question.title}</h2>
                    <h3>Description: {question.description}</h3>
                </div>
            ) : (
                <QuestionForm
                    question={question}
                    onSave={handleUpdate}
                    onCancel={handleCancel}
                />
            )}
            <div>
                <button type="button" disabled={editing} onClick={() => setEditing(true)}>
                    Edit
                </button>
                <button disabled={editing} type="button" onClick={() => handleDelete(question.id)}>
                    Delete
                </button>
            </div>
            <ul title={"Answers"}>
                <div>
                    <input
                        type="text"
                        placeholder="Enter your answer"
                        value={newAnswer}
                        onChange={(e) => setNewAnswer(e.target.value)}
                    />
                    <button onClick={handleCreateAnswer}>Add New Answer</button>
                </div>
                {answers !== null && Array.isArray(answers) ? (
                    answers.map((answer) => (
                        <li key={answer.id}>
                            {answer.answer}
                            <Link to={`/answers/${answer.id}/edit`}><button>
                                Edit Answer
                            </button>
                            </Link>
                            <button onClick={() => handleDeleteAnswer(answer.id)}>Delete Answer</button>
                        </li>
                    ))
                ) : (
                    <p>Be the first one to answer!</p>
                )}
            </ul>
        </div>
    )
}