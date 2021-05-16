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
                <Webcam
                    audio={false}
                    screenshotFormat={"image/jpeg"}
                    videoConstraints={{
                        width: 1280,
                        height: 720,
                        facingMode: "user"
                    }}
                    minScreenshotWidth={800}
                    minScreenshotHeight={800}
                    screenshotQuality={1}
                    forceScreenshotSourceSize={false}
                    mirrored={true}

                />
            </div>
        );
    };
};
