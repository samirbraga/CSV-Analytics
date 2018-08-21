import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Ionicon from 'react-ionicons';
import cx from 'classnames';

import './round-button.css';

class RoundButton extends Component {
    render() {
        return (
            <div
                {...{ ...this.props, actived: null }}
                className={cx(
                    "round-button",
                    this.props.className,{
                        "active": this.props.actived
                    }
                )}
                >
                <Ionicon icon={this.props.icon} color="white" />
            </div>
        );
    }
}

RoundButton.propTypes = {
    actived: PropTypes.bool
}

export default RoundButton;