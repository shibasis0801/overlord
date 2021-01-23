import React from "react";
import "./lab.css";

// Convert this into HoC for other components.
// Pass in Links and Switch accordingly
export default class Lab extends React.Component<{}, {}> {
    render() {
        return (
            <section className="lab">
                <aside>

                </aside>
                <main >
                    {[1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map(i => (<div>SPAN {i}</div>))}
                </main>
            </section>
        )
    }
}
