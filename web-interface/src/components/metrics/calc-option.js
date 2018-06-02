import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import CalcPlainTable from './calc-plain-table';

import store from '../../store';

import './calc-style.css';
import LoadingOverlay from '../util/loading-overlay';
import CalcContingencyTable from './calc-contingency-table';


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
        let calc = null;
        if (this.state.calc.type === 'metrics') {
            calc = (
                <span>
                    <h3>{this.state.calc.name}</h3>
                    <hr />
                    {
                        this.state.data != {} ?
                        <CalcPlainTable data={this.state.data} /> :
                        ''
                    }
                </span>
            );
        } else if (this.state.calc.type === 'graphic-qualitatives') {
            if (this.state.calc.key === 'contingency-table') {
                calc = (
                    <div>
                        <div>
                            <span class="text-muted h6" >CAMPOS:</span>
                            <div class="d-inline-block ml-3">
                                {Object.keys(this.state.data).map(table => (
                                    <a className='mr-3' href='javascript:void(0)' onClick={() => {
                                        document.querySelector('#' + table).scrollIntoView(true);
                                    }} >{table}</a>
                                ))}
                            </div>
                        </div>

                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];

                            return <CalcContingencyTable id={table} className={'pt-4 mb-3'} data={data} table={table} />;  
                        })}
                    </div>
                )
            }
        }

        return (
            <div className="calc-container p-4">
                <LoadingOverlay loading={this.state.loading} />
                {calc}
            </div>
        );
    }
}

export default CalcOption;
