import React, { Component } from 'react';
import { Table } from 'reactstrap';

import store from '../../store';

import LoadingOverlay from '../util/loading-overlay';
import FieldsNav from './fields-nav';
import CalcPlainTable from './calc-plain-table';
import CalcContingencyTable from './calc-contingency-table';
import CalcQuantitativeFrequencyTable from './calc-quantitative-frequency-table';
import CalcQualitativeFrequencyTable from './calc-qualitative-frequency-table';
import CalcHistogram from './calc-histogram';
import CalcGraphicBar from './calc-graphic-bar';
import CalcScatterplot from './calc-scatter-plot';
import CalcBoxplot from './calc-boxplot';

import './calc-style.css';

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
                        this.state.data !== {} ?
                        <CalcPlainTable data={this.state.data} /> :
                        ''
                    }
                </span>
            );
        } else if (this.state.calc.type === 'graphic-qualitatives') {
            if (this.state.calc.key === 'contingency-table') {
                calc = (
                    <div>
                        {
                            this.state.data.status === 'error' ?
                            <div>{
                                <Table className="calc-plain-table my-2" >
                                    <tbody>
                                        {
                                            Object.keys(this.state.data).map((field, i) => (
                                                <tr>
                                                    <th>{field.toString()}</th>
                                                    <td>{
                                                        Array.isArray(this.state.data[field]) ? 
                                                        this.state.data[field].join(', ') :
                                                        this.state.data[field]
                                                    }</td>
                                                </tr>
                                            ))
                                        }
                                        <tr></tr>
                                    </tbody>
                                </Table>
                            }</div> :
                            <span>
                                <FieldsNav data={this.state.data} />

                                {Object.keys(this.state.data).map((table) => {
                                    let data = this.state.data[table];

                                    return <CalcContingencyTable id={table} className={'pt-4 mb-3'} data={data} table={table} />;  
                                })}
                            </span>
                        }
                    </div>
                );
            } else if (this.state.calc.key === 'qualitative-frequency-table') {
                calc = (
                    <div>
                        <FieldsNav data={this.state.data} />

                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];

                            return <CalcQualitativeFrequencyTable id={table} className={'pt-4 mb-3'} data={data} table={table} />;
                        })}
                    </div>
                );  
            } else if (this.state.calc.key === 'graphic-bar') {
                calc = (
                    <div>
                        <FieldsNav data={this.state.data} />

                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];
                            return <CalcGraphicBar id={table} className={'pt-4 mb-3'} data={data} table={table} />;
                        })}
                    </div>
                );
            }
        } else if (this.state.calc.type === 'graphic-quantitatives') {
            if (this.state.calc.key === 'quantitative-frequency-table') {
                calc = (
                    <div>
                        <FieldsNav data={this.state.data} />

                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];

                            return (
                                <CalcQuantitativeFrequencyTable id={table} className={'pt-4 mb-3'} data={data} table={table} />
                            );
                        })}
                    </div>
                );
            } else if (this.state.calc.key === 'histogram') {
                calc = (
                    <div>
                        <FieldsNav data={this.state.data} />

                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];
                            return (
                                <CalcHistogram id={table} className={'pt-4 mb-3'} data={data} table={table} />
                            );
                        })}
                    </div>
                );
            } else if (this.state.calc.key === 'scatterplot') {
                calc = (
                    <div>
                        <FieldsNav data={this.state.data} />

                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];
                            return (
                                <CalcScatterplot id={table} className={'pt-4 mb-3'} data={data} table={table} />
                            );
                        })}
                    </div>
                );
            } else if (this.state.calc.key === 'boxplot') {
                calc = (
                    <div>
                        <FieldsNav data={this.state.data} />
                    
                        {Object.keys(this.state.data).map((table) => {
                            let data = this.state.data[table];
                            return (
                                <CalcBoxplot id={table} className={'pt-4 mb-3'} data={data} table={table} />
                            );
                        })}
                    </div>
                );
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
