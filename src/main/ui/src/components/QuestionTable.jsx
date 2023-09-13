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
                            <Link to={`/details/${question.id}`}>
                                <button disabled={true} type="button">Details</button>
                            </Link>
                        </td>
                        <td>
                            <Link to={`/edit/${question.id}`}>
                                <button disabled={true} type="button">Edit</button>
                            </Link>
                        </td>
                        <td>
                            <button disabled={true} type="button" onClick={() => onDelete(question.id)}>
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