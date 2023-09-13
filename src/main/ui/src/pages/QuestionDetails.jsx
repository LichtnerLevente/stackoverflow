import {useEffect, useId, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

const fetchQuestion = (id) => {
    return fetch(`http://localhost:8080/questions/${id}`)
        .then(res => res.json());
}

export const QuestionDetails = () => {

    const {id} = useParams();
    const navigate = useNavigate();

    const [question, setQuestion] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        fetchQuestion(id).then(question => {
            setQuestion(question);
            setLoading(false);
        })
    }, []);


    if (loading) return <div/>;

    function onEdit(id) {}
    function onDelete(id) {}

    return (
        <div>
            <div>
                <h1>{question.title}</h1>
                <h2>{question.description}</h2>
            </div>
            <div>
                <button type="button" onClick={() => onEdit(question.id)}>
                    Edit
                </button>
                <button disabled={false} type="button" onClick={() => onDelete(question.id)}>
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