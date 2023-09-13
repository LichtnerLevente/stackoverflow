import {Link} from "react-router-dom";

export const QuestionTable = (questions, onDelete) => {
    return (
        <div className="EmployeeTable">
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th/>
                </tr>
                </thead>
                <tbody>
              {/*  {questions.map((question) => (
                    <tr key={question.id}>
                        <td>{question}</td>
                        <td>
                            <Link to={`/edit/${question.id}`}>
                                <button type="button">Update</button>
                            </Link>
                            <button type="button" onClick={() => onDelete(question.id)}>
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}*/}
                </tbody>
            </table>
        </div>
    )
}