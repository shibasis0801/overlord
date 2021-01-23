import React from "react";
import "./header.css"
import WrappedLink from "../../subcomponents/WrappedLink";
import {RouteUIConfig} from "../../Routes";


export type HeaderProps = {
    title: string
}
export default function Header(props: HeaderProps) {
    return (
        <header>
            <h1>{props.title}</h1>
            <nav>
                <ul id="nav-list">
                    {RouteUIConfig.map(data => (
                        <li className="nav-item" key={data.link}>
                            <WrappedLink {...data} />
                        </li>
                    ))}
                </ul>
            </nav>
        </header>
    );
}

