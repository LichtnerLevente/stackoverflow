import {useEffect, useState} from "react";
import {QuestionTable} from "../components/QuestionTable";

const fetchQuestions = () => {
    return fetch("http://localhost:8080/questions/all").then((res) => res.json());
}
const deleteQuestion = (id) => {
    return fetch(String.format("http://localhost:8080/questions/%d", id), {method: "DELETE"})
        .then(res => res.json());
};

export const QuestionList = () => {

    const [questions, setQuestions] = useState(null);


    const [loading, setLoading] = useState(true);

    function handleDelete(id) {

    }


    useEffect(() => {
        fetchQuestions().then(questions => {
            console.log(questions)
            // let questionsArray = questions.keys();
            setQuestions(questions);
            setLoading(false)
        })
    }, []);

    if (loading) {
        return <div/>;
    } else {
        console.log(questions);
        return (
            // <div>asd</div>
            <QuestionTable questions={questions} onDelete={handleDelete()}/>
        )
    }
}