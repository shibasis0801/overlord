import React from "react";
import {Button} from "@material-ui/core";

export interface HomeProps {
    prompt: string,
    defaultName: string
}

export interface HomeState {
    response: object | null,
    supported: string
}

export default class Home extends React.Component<HomeProps, HomeState> {
    constructor(props: HomeProps) {
        super(props);
        this.state = {
            supported: "mediaDevices" in navigator ? "Supported" : "Not Supported",
            response: null
        }
    }

    componentDidMount() {
        fetch("https://pravegapredictor.herokuapp.com/personalDialog")
            .then(response => response.json())
            .then(response => this.setState(() => ({
                response
            })))

        const player = document.getElementById('player');

        const constraints = {
            video: true,
        };

        navigator.mediaDevices.getUserMedia(constraints)
            .then((stream) => {
            player.srcObject = stream;
        });

    }



    render() {
        return (
            <div>
                <h2>Home</h2>
                <div>
                    {this.state.response &&
                    <p>{JSON.stringify(this.state.response)}</p>}
                </div>
                <div>
                    {this.state.supported}
                </div>
                <video id="player" controls autoPlay />
            </div>
        )
    }
}
