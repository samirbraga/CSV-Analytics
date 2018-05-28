import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Container, Row, Col } from 'reactstrap';

import './slin-button.css';

class SlinButton extends Component {
    render() {
        return (
            <Link {...this.props} className={"slin-button " + this.props.className}  >
                {this.props.children}
            </Link>
        );
    }
}

export default SlinButton;