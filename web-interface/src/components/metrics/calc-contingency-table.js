import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import Ionicon from 'react-ionicons';

import './calc-style.css';

class CalcContingencyTable   extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let { data } = this.props;
        let { table } = this.props;
        let subcat = [];

        Object.keys(data).forEach((key) => {
            subcat.push(...Object.keys(data[key]));
        });

        let aux = data[Object.keys(data)[0]];
        let distinct = aux[Object.keys(aux)[0]];

        let allValues = [];
        Object.keys(data).forEach(key => {
            for (let col in data[key]) {
                allValues.push(data[key][col]);
            }
        });

        return (
            <span {...this.props} className={'d-block ' + this.props.className}>
                { 
                    data.status == 'error' ?
                    <div>{
                        Object.keys(data).map(key => (
                            <span>
                                <h4>{key}</h4>
                                <p>{data[key]}</p>
                                <br />
                            </span>
                        ))
                    }</div> :
                    <span>
                        <h3>{table}</h3>
                        <hr/>
                        <Table className="table calc-plain-table">
                            <thead>
                                <tr>
                                    <th rowSpan="2">{table}</th>
                                    {Object.keys(data).map((key) => <th colSpan={Object.keys(data[key]).length} key={key}>{key}</th>)}
                                </tr>
                                <tr>
                                    {subcat.map(key => <th>{key}</th>)}
                                </tr>
                            </thead>
                            <tbody>
                                {Object.keys(distinct).map(key => (
                                    <tr>
                                        <td>{key}</td>
                                        {allValues.map(value => (
                                            <td>{value[key]}</td>
                                        ))}
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </span>
                }
            </span>
        );
    }
}

export default CalcContingencyTable;