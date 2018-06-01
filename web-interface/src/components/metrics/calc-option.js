import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import CalcPlainTable from './calc-plain-table';

import store from '../../store';

import './calc-style.css';
import LoadingOverlay from '../util/loading-overlay';


class CalcOption extends Component {
    constructor(props) {
        super(props);

        let calcs = [];
        let { calcOptions } = store.getState();
        for (let calc in calcOptions) {
            calcs.push(...calcOptions[calc]);
        }

        let token = null;

        if (store.getState().current_token) {
            token = null;
        } else if (localStorage.getItem('token')) {
            try {
                let aux = JSON.parse(localStorage.getItem('token'));
                token = aux.token;
            } catch(e) {
                token = undefined;
            }
        }
        this.state = {
            host: store.getState().api_host,
            token: store.getState().current_token || token,
            loading: true,
            data: {},
            calcs,
            calc: calcs.filter(calc => calc.key === props.calcKey)[0]
        }

        this.updateComponent = this.updateComponent.bind(this);
    }

    updateComponent(props) {
        var myInit = {
            method: 'GET',
            // mode: 'cors',
            cache: 'default'
        };

        let self = this;
        self.setState({
            loading: true
        });

        let calcs = self.state.calcs;
        fetch(`${this.state.host}/api/calc/${props.calcKey}?token=${this.state.token}`, myInit)
        .then(response => {
            response.json().then(json => {
                self.setState({
                    data: json,
                    calc: calcs.filter(calc => calc.key === props.calcKey)[0]
                }, () => {
                    setTimeout(() => {
                        self.setState({
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
                {   
                    this.state.data != {} ?
                    <CalcPlainTable data={this.state.data} /> :
                    ''
                }
            </div>
        );
    }
}

export default CalcOption;
