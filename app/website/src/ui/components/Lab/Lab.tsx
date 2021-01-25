import React from "react";
import "./lab.css";
import VideoChat from "./VideoChat/VideoChat";

// Convert this into HoC for other components.
// Pass in Links and Switch accordingly
export default class Lab extends React.Component<{}, {}> {
    render() {
        return (
            <section className="lab">
                <aside>

                </aside>
                <main >
                    <VideoChat />
                </main>
            </section>
        )
    }
}
