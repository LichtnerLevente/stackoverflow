import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {QuestionForm} from "../components/QuestionForm";

const fetchQuestion = (id) => {
    return fetch(`http://localhost:8080/questions/${id}`)
        .then(res => res.json());
}
const updateQuestion = (newQuestionDTO, id) => {

    return fetch(`http://localhost:8080/questions/${id}/update`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newQuestionDTO),
    }).then(res => res.json());
};

export const QuestionDetails = () => {

    const {id} = useParams();
    const navigate = useNavigate();

    const [question, setQuestion] = useState(null);
    const [loading, setLoading] = useState(true);
    const [editing, setEditing] = useState(false);

    useEffect(() => {
        setLoading(true);
        fetchQuestion(id).then(question => {
            setQuestion(question);
            setLoading(false);
        })
    }, []);


    if (loading) return <div/>;


    function handleUpdate(question, id) {
        updateQuestion(question, id).then(res => {
            setEditing(false)
            if (res === true) {
                setQuestion(question)
                // console.log(question)
            }
        })
    }

    function onDelete(id) {
    }

    function handleCancel() {
        setEditing(false);
    }

    return (
        <div>
            {!editing ?
                <div>
                    <h2>Title: {question.title}</h2>
                    <h3>Description: {question.description}</h3>
                </div>
                :
                <QuestionForm
                    question={question}
                    onSave={handleUpdate}
                    onCancel={handleCancel}
                />
            }
            <div>
                <button type="button" disabled={editing} onClick={() => setEditing(true)}>
                    Edit
                </button>
                <button disabled={editing} type="button" onClick={() => onDelete(question.id)}>
                    Delete
                </button>
            </div>
            <ul title={"Answers"}>
                <li>answer1</li>
                <li>answer2</li>
                <li>answer3</li>
                <li>answer4</li>
            </ul>
        </div>
    )
}