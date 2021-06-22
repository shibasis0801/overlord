import * as React from 'react';

type Props = {

};
type State = {

};

export class CameraTester extends React.Component<Props, State> {
    video: HTMLVideoElement | null = null

    componentDidMount() {
        console.log("mediaDevices" in navigator ? "Supported" : "Not Supported")
        navigator.mediaDevices.getUserMedia({ video: true }).then(stream => {
            if (this.video) {
                this.video.srcObject = stream
            }
        }).catch(console.error)
    }

    render() {
        return (
            <div>
                <p>NORMAL CAMERA</p>
                <video ref={ref => this.video = ref} autoPlay={true} />
            </div>
        );
    };
};
