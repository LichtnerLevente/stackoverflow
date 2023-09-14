import {useState} from "react";

export const QuestionForm = ({question, onSave, onCancel, disabled}) => {

    const [title, setTitle] = useState(question?.title ?? "");
    const [description, setDescription] = useState(question?.description ?? "");

    const onSubmit = (e) => {
        e.preventDefault();

        if (question) {
            return onSave({
                ...question,
                title,
                description,
            })
        }
        return onSave({
            title,
            description,
        })
    }
    return (
        <form onSubmit={onSubmit}>

            <div className={"control"}>
                <label>Title:</label>
                <input
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                    name={"title"}
                    id={"title"}
                />
            </div>

            <div className={"control"}>
                <label>Description:</label>
                <input
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                    name={"description"}
                    id={"description"}
                />
            </div>

            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {question ? "Update Question" : "Create Question"}
                </button>
                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    )
}