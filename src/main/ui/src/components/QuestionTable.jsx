import {Link} from "react-router-dom";

export const QuestionTable = ({questions, onDelete}) => {
    console.log(questions)
    return (
        <div>
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th/>
                </tr>
                </thead>
                <tbody>
                {questions.map((question) => (
                    <tr key={question.id}>
                        <td>{question.title}</td>
                        <td>
                            <Link to={`/questions/${question.id}`}>
                                <button disabled={false} type="button">Details</button>
                            </Link>
                        </td>
                        <td>
                            <button disabled={false} type="button" onClick={() => onDelete(question.id)}>
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}