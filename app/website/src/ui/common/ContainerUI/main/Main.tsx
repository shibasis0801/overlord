import React from "react";
import "./main.css";

export default class Main extends React.Component<{}, {}> {
    render() {
        return (
            <main>
                {this.props.children}
            </main>
        )
    }
}
