// @flow
import * as React from 'react';

type Props = {

};
type State = {

};

const config = {
    script: "https://d38knilzwtuys1.cloudfront.net/revieve-plugin-v4/revieve-plugin-loader.js",
    partner_id: "9KpsLizwYK",
    locale: "en",
    env: "test"
}

export class RevieveView extends React.Component<Props, State> {
    componentDidMount() {
        let htmlScriptElement = document.createElement("script");
        htmlScriptElement.src = config.script;
        htmlScriptElement.type = "text/javascript";
        htmlScriptElement.async = true

        // @ts-ignore
        htmlScriptElement.onload = htmlScriptElement.onreadystatechange = function () {
            // @ts-ignore
            const readyState = this.readyState;

            if (readyState && readyState !== "complete" && readyState !== "loaded") {
                return null;
            }

            window.Revieve.Init(config, function() {
                window.Revieve.API.show();
            })

        }

        document.body.appendChild(htmlScriptElement);
    }

    render() {
        return (
            <video id="player" controls autoPlay />
        );
    };
};
