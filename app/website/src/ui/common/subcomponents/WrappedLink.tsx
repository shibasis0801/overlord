import React from "react";
import { Link } from "react-router-dom";
import {isRelativeLink} from "../../../utils/links";

export type LinkProps = {
    link: string,
    text: string,
    target?: "_blank"
}

const LinkStyle = {
    "color": "inherit",
    "textDecoration": "inherit",
    "cursor": "inherit"
}

export default class WrappedLink extends React.PureComponent<LinkProps, {}> {
    render() {
        const { link, text, target } = this.props;
        return isRelativeLink(link) ? (
            <Link to={link} style={LinkStyle}>
                {text}
            </Link>
        ) : (
            <a href={link} style={LinkStyle} target={target}>
                {text}
            </a>
        )
    }
}
