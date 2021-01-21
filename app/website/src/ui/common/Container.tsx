import React, {Component} from "react";
import ContainerUI from "./ContainerUI/ContainerUI";

interface ContainerState {}

interface ContainerProps {}


export default class Container extends Component<ContainerProps, ContainerState> {
    render(): React.ReactNode {
        return (
            <>
                <ContainerUI />
                {this.props.children}
            </>
        )
    }
}