import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './index.css';
import reportWebVitals from './reportWebVitals';

import {Layout} from "./pages/Layout";
import {QuestionList} from "./pages/QuestionList";
import {QuestionDetails} from "./pages/QuestionDetails";
import {QuestionCreator} from "./pages/QuestionCreator";
import AnswerEditor from "./pages/AnswerEditor";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        children: [
            {
                path: "/",
                element: <QuestionList/>,
            },
            {
                path: "/questions/:id",
                element: <QuestionDetails/>,
            },
            {
                path:"/create",
                element: <QuestionCreator/>
            },
            {
                path: "/answers/:id/edit",
                element: <AnswerEditor />,
            },

        ]
    }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

reportWebVitals();
