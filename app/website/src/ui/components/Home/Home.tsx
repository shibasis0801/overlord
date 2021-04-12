import React from "react";

export interface HomeProps {
    prompt: string,
    defaultName: string
}

export interface HomeState {
    response: object | null
}

export default class Home extends React.Component<HomeProps, HomeState> {
    constructor(props: HomeProps) {
        super(props);
        this.state = {
            response: null
        }
    }

    componentDidMount() {
        fetch("https://pravegapredictor.herokuapp.com/personalDialog")
            .then(response => response.json())
            .then(response => this.setState(() => ({
                response
            })))
    }



    render() {
        return (
            <div>
                <h2>Home</h2>
                <div>
                    {this.state.response &&
                    <p>{JSON.stringify(this.state.response)}</p>}
                </div>
            </div>
        )
    }
}
