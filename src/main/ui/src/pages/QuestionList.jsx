import {useEffect, useState} from "react";
import {QuestionTable} from "../components/QuestionTable";

const fetchQuestions = () => {
    return fetch("http://localhost:8080/questions/all").then((res) => res.json());
}
const deleteQuestion = (id) => {
    return fetch(`http://localhost:8080/questions/${id}`, {method: "DELETE"})
        .then(res => res.json());
};

export const QuestionList = () => {

    const [questions, setQuestions] = useState(null);


    const [loading, setLoading] = useState(true);

    function handleDelete(idString) {
        let id = parseInt(idString);
        deleteQuestion(id).then(res =>{
            console.log(res);
        })
        setQuestions(questions.filter(q => q.id !== id));
    }


    useEffect(() => {
        fetchQuestions().then(questions => {
            console.log(questions)
            setQuestions(questions);
            setLoading(false)
        })
    }, []);

    if (loading) {
        return <div/>;
    } else {
        console.log(questions);
        return (
            <QuestionTable questions={questions} onDelete={handleDelete}/>
        )
    }
}