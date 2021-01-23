import React from "react";
import {Button} from "@material-ui/core";

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
            <>
                <h2>Home</h2>
                {/*<Button variant = "contained" color="primary">*/}
                {/*    {this.props.prompt} {this.props.defaultName}*/}
                {/*</Button>*/}
                {/*{this.state.response &&*/}
                {/*<p>{JSON.stringify(this.state.response)}</p>*/}
                {/*}*/}
            </>
        )
    }
}
