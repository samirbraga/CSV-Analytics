import React, { Component } from 'react';
import Ionicon from 'react-ionicons';

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
                className={"loading-overlay justify-content-center align-items-center "
                 + (this.props.loading ? ' d-flex' : 'd-none')
                 + (this.props.darker ? ' darker' : '')
                 }>
                <Ionicon icon="md-grid"
                    color={this.props.darker ? 'white' : 'black'}
                    fontSize={this.props.darker ? '60px' : '40px'}  />
            </div>
        );
    }
}

export default LoadingOverlay;