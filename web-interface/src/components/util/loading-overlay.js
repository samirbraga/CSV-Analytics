import React, { Component } from 'react';
import Ionicon from 'react-ionicons';
import cx from 'classnames';

import './loading-overlay.css';

class LoadingOverlay extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: props.loading
        }
    }

    render() {
        return (
            <div 
                className={cx(
                    "loading-overlay justify-content-center align-items-center",
                    {
                        "darker": this.props.darker,
                        "d-flex": this.props.loading,
                        "d-none": !this.props.loading
                    }
                )}
            >
                <Ionicon
                    icon="md-grid"
                    color={this.props.darker ? 'white' : 'black'}
                    fontSize={this.props.darker ? '60px' : '40px'}
                    />
            </div>
        );
    }
}

export default LoadingOverlay;