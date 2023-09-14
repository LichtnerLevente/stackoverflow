import {useEffect, useState} from "react";
import {UserForm} from "../../components/UserForm";
const createUser = () => {
  //fetch
}
export const UserRegister = () => {

    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(false);

    return (
        <UserForm/>
    )
}