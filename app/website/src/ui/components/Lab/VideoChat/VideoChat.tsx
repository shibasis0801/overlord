import React from "react";

import { HelloWorld } from "core-web/kotlin/core-web";

type VideoState = {
    messages: string[]
}

export default class VideoChat extends React.Component<{}, VideoState> {
    state = {
        messages: []
    }
    socket: WebSocket | null = null;

    componentDidMount() {
        this.socket = new WebSocket("ws://localhost:8080/signalling");
        this.socket.addEventListener('open', event => {
            this.socket?.send("Hello from React");
        })
        this.socket.addEventListener("message", this.messageReceiver);
    }

    componentWillUnmount() {
        this.socket?.close()
    }

    messageReceiver = (event: MessageEvent) => {
        this.setState(state => ({
            ...state,
            messages: state.messages.concat(event.data as string)
        }))
    }

    sendMessage = (message: string) => {
        this.socket?.send(message);
    }

    render() {
        return (
            <>
                <p>Message from MultiPlatform {(new HelloWorld()).getMessage()}</p>
                <button onClick={() => this.sendMessage("Shibasis")}>
                    Click me to send data
                </button>
                <ul>
                    {this.state.messages.map(message => (
                            <li>
                                {message}
                            </li>
                    ))}
                </ul>
            </>
        )
    }
}
