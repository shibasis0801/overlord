import React, {Component} from "react";
import ContainerUI from "./ContainerUI/ContainerUI";
import "./common.css";
import { withRouter } from "react-router";

interface ContainerState {}

interface ContainerProps {}


class Container extends Component<ContainerProps, ContainerState> {
    render() {
        return (
            <ContainerUI>
                {this.props.children}
            </ContainerUI>
        )
    }
}

// Mad TS
// @ts-ignore
export default withRouter(Container);
