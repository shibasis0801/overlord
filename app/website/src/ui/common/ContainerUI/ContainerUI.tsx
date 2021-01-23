import React from "react";
import {Link} from "react-router-dom";

const ContainerUI = () => (
    <nav>
        <ul>
            <li>
                <Link to="/">Home</Link>
            </li>
            <li>
                <Link to="/about">About</Link>
            </li>
            <li>
                <Link to="/users">Users</Link>
            </li>
            <li>
                <Link to="/rnw">RNW</Link>
            </li>
        </ul>
    </nav>
);

export default ContainerUI;
