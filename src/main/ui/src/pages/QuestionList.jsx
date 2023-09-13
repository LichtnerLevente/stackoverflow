import {useEffect, useState} from "react";

const fetchQuestions = () => {
    return fetch("http://localhost:8080/questions/all").then((res) => res.json());
}
const deleteQuestion = (id) => {
    return fetch(String.format("/api/questions/ %d", id), {method: "DELETE"})
        .then(res => res.json());
};

export const QuestionList = () => {

    const [questions, setQuestions] = useState();

    function handleDelete(id) {

    }


    useEffect(() => {
        fetchQuestions().then(questions => {
            setQuestions(questions);
            console.log(questions)
        })
    }, []);
    return (
        <div>asd</div>
        // <QuestionList questions ={questions} onDelete = {handleDelete()}/>
    )
}