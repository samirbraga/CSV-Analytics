import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import Ionicon from 'react-ionicons';

import './calc-style.css';

class FieldsNav extends Component {
    constructor(props) {
        super(props);
    }

    render() {

        return (
            <div>
                <span class="text-muted h6" >CAMPOS:</span>
                <div class="d-inline-block ml-3">
                    {Object.keys(this.props.data).map(table => (
                        <a className='mr-3' href='javascript:void(0)' onClick={() => {
                            document.querySelector('#' + table).scrollIntoView(true);
                        }} >{table}</a>
                    ))}
                </div>
            </div>
        );
    }
}

export default FieldsNav;