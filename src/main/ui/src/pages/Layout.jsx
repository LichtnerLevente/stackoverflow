import {Link, Outlet} from "react-router-dom";

export const Layout = () => {
    return (
        <div className="Layout">
            <nav>
                <ul>
                    <li className="grow">
                        <Link to="/">Questions</Link>
                    </li>
                    <li>
                        <Link to="/create">
                            <button type="button">Ask a Question</button>
                        </Link>
                    </li>
                </ul>
            </nav>
            <Outlet />
        </div>
    )
}