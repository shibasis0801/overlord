import * as React from 'react';
import Webcam from 'react-webcam';

type Props = {

};
type State = {

};

export class WebcamTester extends React.Component<Props, State> {
    render() {
        return (
            <div>
                <Webcam />
            </div>
        );
    };
};
