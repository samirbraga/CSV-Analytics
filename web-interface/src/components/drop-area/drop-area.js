import React, { Component } from 'react';
import { Row, Col } from 'reactstrap';

import (__dirname + '/drop-area.css');

class DropArea extends Component {
    render() {
        return (
            <div className="drop-area w-100 h-100">
                <div className="overlay-darker"></div>
                <div className="overlay-line">
                </div>
            </div>
        );
    }
}

export default DropArea;