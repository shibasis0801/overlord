import React from "react";
import "./lab.css";

export default class Lab extends React.Component<{}, {}> {
    render() {
        return (
            <div className="lab">
                <aside>

                </aside>
                <main >
                    {[1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map(i => (<div>SPAN {i}</div>))}
                </main>
            </div>
        )
    }
}
