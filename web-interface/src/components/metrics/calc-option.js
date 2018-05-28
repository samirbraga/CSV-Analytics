import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import CalcPlainTable from './calc-plain-table';

import calcOptions from '../../data';

import './calc-style.css';
import LoadingOverlay from '../util/loading-overlay';

let calcs = [];

for (let calc in calcOptions) {
    calcs.push(...calcOptions[calc]);
}


class CalcOption extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            data: {},
            calc: calcs.filter(calc => calc.key === props.calcKey)[0]
        }

        this.updateComponent = this.updateComponent.bind(this);
    }

    updateComponent(props) {
        var myInit = {
            method: 'GET',
            mode: 'cors',
            cache: 'default'
        };

        this.setState({
            loading: true
        });

        fetch('https://next.json-generator.com/api/json/get/NyiwEdB1r?key=' + props.calcKey, myInit)
        .then(response => {
            response.json().then(json => {
                this.setState({
                    data: json,
                    calc: calcs.filter(calc => calc.key === props.calcKey)[0]
                }, () => {
                    setTimeout(() => {
                        this.setState({
                            loading: false,
                        });
                    }, 300);
                });
            });
        });
    }

    componentDidMount() {
        this.updateComponent(this.props);
    }

    componentWillReceiveProps(newProps) {
        this.updateComponent(newProps);
    }

    render() {
        return (
            <div className="calc-container p-4">
                <LoadingOverlay loading={this.state.loading} />
                <h3>{this.state.calc.name}</h3>
                <hr/>
                <CalcPlainTable data={this.state.data} />
            </div>
        );
    }
}

export default CalcOption;